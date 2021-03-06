package eu.cloudscaleproject.env.overview.wizard.util;

import org.eclipse.core.resources.IProject;
import org.scaledl.overview.Overview;
import org.scaledl.overview.architecture.PlatformRuntimeService;
import org.scaledl.overview.architecture.SoftwareService;
import org.scaledl.overview.specification.CloudSpecification;

import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.system.System;;

public class WizardData{
	
	private IProject project;

	private Repository repository;
	private System system;
	private Overview overview;
	private SoftwareService softwareService;
	private CloudSpecification cloudSpecification;

	private Overview targetOverview;

	private PlatformRuntimeService platformRuntimeService;
	
	public void setProject(IProject project) {
		this.project = project;
	}
	
	public IProject getProject() {
		return project;
	}

	public Repository getRepositoryModel() {
		return repository;
	}
	public void setRepositoryModel(Repository repository) {
		this.repository = repository;
	}
	public System getSystemModel() {
		return system;
	}
	public void setSystemModel(System system) {
		this.system = system;
	}
	public Overview getOverviewModel() {
		return overview;
	}
	public void setOverviewModel(Overview overview) {
		this.overview = overview;
	}
	public CloudSpecification getCloudSpecification() {
		return cloudSpecification;
	}
	public void setCloudSpecification(CloudSpecification cloudSpecification) {
		this.cloudSpecification = cloudSpecification;
	}
	
	public void setSoftwareService(SoftwareService softwareService) {
		this.softwareService = softwareService;
	}
	public SoftwareService getSoftwareService() {
		return softwareService;
	}
	
	public void setTargetOverviewModel (Overview targetOverview)
	{
		this.targetOverview = targetOverview;		
	}
	public Overview getTargetOverview()
	{
		return targetOverview;
	}
	public void setPlatformRuntimeService(PlatformRuntimeService prs)
	{
		this.platformRuntimeService = prs;
	}
	public PlatformRuntimeService getPlatformRuntimeService()
	{
		return platformRuntimeService;
	}
	

}
