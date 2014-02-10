package eu.cloudscaleproject.env.toolchain.handlers;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.e4.core.di.annotations.Execute;

import eu.cloudscaleproject.env.toolchain.definition.IAnalyser;

public class AnalyseTestHandler {

	private static final String IANALYSER_ID = "eu.cloudscaleproject.env.toolchain.analyser";

	@Execute
	public void execute(IExtensionRegistry registry) {
		evaluate(registry);
	}

	private void evaluate(IExtensionRegistry registry) {

		System.out.println("Evaluating... ");
		IConfigurationElement[] config = registry
				.getConfigurationElementsFor(IANALYSER_ID);
		try {
			for (IConfigurationElement e : config) {
				System.out.println("Evaluating extension");
				System.out.println("Extension id = " + e.getAttribute("id"));
				System.out
						.println("Extension name = " + e.getAttribute("name"));
				final Object o = e.createExecutableExtension("class");

				if (o instanceof IAnalyser) {
					executeExtension(o);
				}
			}
		} catch (CoreException ex) {
			System.out.println(ex.getMessage());
		}
	}

	private void executeExtension(final Object o) {
		ISafeRunnable runnable = new ISafeRunnable() {
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in client");
			}

			@Override
			public void run() throws Exception {
				((IAnalyser) o).analyse();
			}
		};
		SafeRunner.run(runnable);
	}
}