/**
 * Copyright 2014 SAP AG
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.cloudscaleproject.env.spotter;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.progress.IProgressConstants;
import org.spotter.eclipse.ui.Activator;
import org.spotter.eclipse.ui.ServiceClientWrapper;
import org.spotter.eclipse.ui.handlers.RunHandler;
import org.spotter.eclipse.ui.jobs.JobsContainer;
import org.spotter.eclipse.ui.navigator.SpotterProjectResults;
import org.spotter.eclipse.ui.navigator.SpotterProjectRunResult;
import org.spotter.eclipse.ui.util.DialogUtils;
import org.spotter.shared.status.DiagnosisProgress;
import org.spotter.shared.status.SpotterProgress;

/**
 * A job to run a DynamicSpotter diagnosis which can be scheduled by the job
 * manager. This job updates the progress monitor according to the progress of
 * the DynamicSpotter run that is performed.
 * 
 * @author Denis Knoepfle
 * 
 */
public class DynamicSpotterRunJob extends Job {

	private static final String ICON_PATH = "icons/diagnosis.png"; //$NON-NLS-1$

	private static final int SLEEP_TIME_MILLIS = 1000;
	private static final int HUNDRED_PERCENT = 100;
	private static final int KEY_HASH_LENGTH = 7;

	private static final String MSG_RUN_FINISH = "Finished the DynamicSpotter diagnosis successfully!";
	private static final String MSG_RUN_ERROR = "Aborted the DynamicSpotter diagnosis due to an error!";
	private static final String MSG_LOST_CONNECTION = "Lost connection to DS Service!";
	private static final String MSG_CANCELLED = "The progress report has been cancelled and you will "
			+ "not be informed when the run is finished. DynamicSpotter will continue to run on the "
			+ "server though (cancellation of a running diagnosis is not implemented yet).";

	private final IProject project;
	private final ServiceClientWrapper client;
	private final String name;
	
	private final long jobId;
	private final Set<String> processedProblems;
	private Map.Entry<String, DiagnosisProgress> currentProblem;

	/**
	 * Create a new job for the given project and job id.
	 * 
	 * @param project
	 *            The project the job is for
	 * @param jobId
	 *            The job id of the new job
	 * @param timestamp
	 *            The timestamp when the job was initiated
	 */
	public DynamicSpotterRunJob(final IProject project, final ServiceClientWrapper client, 
								final String name, final long jobId, long timestamp) {
		
		super("DynamicSpotter Diagnosis '" + project.getName() + "'");

		this.project = project;
		this.client = client;
		this.name = name;
		
		this.jobId = jobId;
		this.processedProblems = new HashSet<>();

		ImageDescriptor imageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, ICON_PATH);
		setProperty(IProgressConstants.ICON_PROPERTY, imageDescriptor);

		IAction gotoAction = new Action("Results") {
			public void run() {
				revealResults();
			}
		};

		setProperty(IProgressConstants.ACTION_PROPERTY, gotoAction);
		setPriority(LONG);
		setUser(true);

		if (!JobsContainer.registerJobId(project, jobId, timestamp)) {
			DialogUtils.openError(RunHandler.DIALOG_TITLE,
					"There was an error when saving the job id. You may not access the results of the diagnosis run.");
		}
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		processedProblems.clear();
		currentProblem = null;
		monitor.beginTask("DynamicSpotter Diagnosis '" + name + "'", IProgressMonitor.UNKNOWN);

		while (client.isRunning(true)) {
			if (monitor.isCanceled()) {
				return onUserCancelledJob(null);
			}
			Long currentJobId = client.getCurrentJobId();
			// check if the job is still being processed
			if (currentJobId != null && currentJobId == jobId) {
				updateCurrentRun(client, monitor);
				try {
					Thread.sleep(SLEEP_TIME_MILLIS);
				} catch (InterruptedException e) {
					return onUserCancelledJob(e);
				}
			} else {
				// assume our job is due
				break;
			}
		}

		Exception runException = null;

		if (client.getLastException() != null) {
			if (client.isConnectionIssue()) {
				DialogUtils.openWarning(RunHandler.DIALOG_TITLE, MSG_LOST_CONNECTION);
				return new Status(Status.OK, Activator.PLUGIN_ID, Status.OK, MSG_LOST_CONNECTION, null);
			} else {
				// job was cancelled on server-side due to an error
				runException = client.getLastException();
			}
		}

		monitor.done();
		onFinishedJob(runException);

		// keep the finished job in the progress view only if
		// it is not running in the progress dialog
		Boolean inDialog = (Boolean) getProperty(IProgressConstants.PROPERTY_IN_DIALOG);
		if (inDialog != null && !inDialog.booleanValue()) {
			setProperty(IProgressConstants.KEEP_PROPERTY, Boolean.TRUE);
		}
		return Status.OK_STATUS;
	}

	private void updateCurrentRun(ServiceClientWrapper client, IProgressMonitor monitor) {
		SpotterProgress spotterProgress = client.getCurrentProgressReport();
		if (spotterProgress == null || spotterProgress.getProblemProgressMapping() == null) {
			return;
		}

		Map<String, DiagnosisProgress> progressAll = spotterProgress.getProblemProgressMapping();
		if (progressAll.isEmpty()) {
			return;
		}

		for (Map.Entry<String, DiagnosisProgress> progressEntry : progressAll.entrySet()) {
			String key = progressEntry.getKey();
			if (processedProblems.contains(key)) {
				if (currentProblem.getKey().equals(key)) {
					currentProblem = progressEntry;
				}
			} else {
				// the current problem under investigation changed
				currentProblem = progressEntry;
				processedProblems.add(currentProblem.getKey());
			}
		}
		if (currentProblem != null) {
			DiagnosisProgress progress = currentProblem.getValue();
			String estimation = String.format("%.1f", HUNDRED_PERCENT * progress.getEstimatedProgress());
			String keyHashTag = createKeyHashTag(currentProblem.getKey());
			String problemName = progress.getName() + "-" + keyHashTag;
			monitor.setTaskName(problemName + " (" + estimation + " %): " + progress.getStatus());
		}
	}

	private String createKeyHashTag(String key) {
		if (key.length() < KEY_HASH_LENGTH) {
			return key;
		} else {
			return key.substring(0, KEY_HASH_LENGTH);
		}
	}

	private IStatus onUserCancelledJob(Exception exception) {
		DialogUtils.openInformation(RunHandler.DIALOG_TITLE, MSG_CANCELLED);
		return new Status(Status.CANCEL, Activator.PLUGIN_ID, MSG_CANCELLED, exception);
	}

	private void onFinishedJob(final Exception runException) {
		final Activator activator = Activator.getDefault();
		final Display display = PlatformUI.getWorkbench().getDisplay();

		display.asyncExec(new Runnable() {
			@Override
			public void run() {
				if (runException == null) {
					Map<String, SpotterProjectResults> results = activator.getProjectHistoryElements();
					SpotterProjectResults projectResultsNode = results.get(name);
					projectResultsNode.refreshChildren();
					CommonViewer viewer = activator.getNavigatorViewer();
					if (!viewer.isBusy()) {
						viewer.refresh(projectResultsNode);
						viewer.expandToLevel(projectResultsNode, 1);
					}
					DialogUtils.openAsyncInformation(RunHandler.DIALOG_TITLE, MSG_RUN_FINISH);
					revealResults();
				} else {
					// remove the job id because the job failed
					JobsContainer.removeJobId(project, jobId);

					String exceptionMsg = runException.getLocalizedMessage();
					if (exceptionMsg == null || exceptionMsg.isEmpty()) {
						exceptionMsg = runException.getClass().getSimpleName();
					}
					String msg = DialogUtils.appendCause(MSG_RUN_ERROR, exceptionMsg, true);
					DialogUtils.openWarning(RunHandler.DIALOG_TITLE, msg);
				}
			}
		});
	}

	private void revealResults() {
		Activator activator = Activator.getDefault();
		Map<String, SpotterProjectResults> results = activator.getProjectHistoryElements();
		SpotterProjectResults projectResultsNode = results.get(name);
		if (projectResultsNode != null) {
			SpotterProjectRunResult runNode = projectResultsNode.getRunResultForJobId(jobId);
			if (runNode != null) {
				CommonViewer viewer = activator.getNavigatorViewer();
				viewer.getControl().setFocus();
				viewer.setSelection(new StructuredSelection(runNode), true);
			}
		}
	}

}
