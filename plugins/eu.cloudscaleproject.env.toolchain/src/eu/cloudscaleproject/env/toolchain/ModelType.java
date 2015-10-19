package eu.cloudscaleproject.env.toolchain;

import org.eclipse.emf.ecore.resource.Resource;



public enum ModelType{
	
	 OVERVIEW(ToolchainUtils.KEY_FILE_OVERVIEW, "Overview model", "sdlo"),
	
	 REPOSITORY(ToolchainUtils.KEY_FILE_REPOSITORY, "Repository model", "repository"), 
	 SYSTEM(ToolchainUtils.KEY_FILE_SYSTEM, "System model" ,"system"),
	 RESOURCE(ToolchainUtils.KEY_FILE_RESOURCEENV, "Resource environment model", "resourceenvironment"),
	 ALLOCATION(ToolchainUtils.KEY_FILE_ALLOCATION, "Allocation model", "allocation"),
	 //AT(ToolchainUtils.KEY_FILE_AT, "xmi"),
	 USAGE(ToolchainUtils.KEY_FILE_USAGE, "Usage model", "usagemodel"),
	 
	 USAGE_EVOLUTION(ToolchainUtils.KEY_FILE_USAGEEVOLUTION, "Usage evolution model", "usageevolution"),
	 MONITOR(ToolchainUtils.KEY_FILE_MONITOR, "Monitors model", "monitorrepository"),
	 MEASURING_POINT(ToolchainUtils.KEY_FILE_MESURPOINTS, "Measuring points model", "measuringpoint"),
	 SLO(ToolchainUtils.KEY_FILE_SLO, "SLO model", "slo"),
	 EXPERIMENTS(ToolchainUtils.KEY_FILE_EXPERIMENTS, "Experiments model", "experiments"),
	 VARIATION(ToolchainUtils.KEY_FILE_VARIATION, "Variations model", "variation"),
	 
	 LIMBO(ToolchainUtils.KEY_FILE_LIMBO, "Limbo model", "dlim"),

	 SOURCECODEDECORATOR(ToolchainUtils.KEY_FILE_SOURCEDECORATOR, "Source decorator model", "sourcecodedecorator"),
	 ECORE(null, "ECore model", "ecore");
	 
	 public static ModelType[] GROUP_ALL  		   	 = ModelType.values();
	 public static ModelType[] GROUP_NONE  		   	 = {};
	 public static ModelType[] GROUP_PCM  		   	 = {REPOSITORY, SYSTEM, RESOURCE, ALLOCATION, USAGE, USAGE_EVOLUTION} ;
	 public static ModelType[] GROUP_PCM_EXTENDED	 = {REPOSITORY, SYSTEM, RESOURCE, ALLOCATION, USAGE, USAGE_EVOLUTION} ;
	 public static ModelType[] GROUP_EXPERIMENTS 	 = {EXPERIMENTS, MONITOR, SLO, VARIATION, USAGE_EVOLUTION, MEASURING_POINT} ;
	 public static ModelType[] GROUP_SOURCEDECORATOR = {SOURCECODEDECORATOR, REPOSITORY, SYSTEM, ECORE} ;
	 
	 private final String extension;
	 private final String name;
	 private final String displayName;
	 private final String toolcahinId;
	 
	 public static ModelType getModelType(String extension){
		 for(ModelType mt : ModelType.values()){
			 if(mt.extension.equals(extension)){
				 return mt;
			 }
		 }
		 return null;
	 }
	 
	 public static ModelType getModelType(Resource resource){
		 for(ModelType mt : ModelType.values()){
			 if(mt.extension.equals(resource.getURI().fileExtension())){
				 return mt;
			 }
		 }
		 return null;
	 }
	 
	 ModelType(String toolchainId, String displayName, String modelExtension){
		 this.extension = modelExtension;
		 this.name = "pcm";
		 this.displayName = displayName;
		 this.toolcahinId = toolchainId;
	 }
	 
	 public String getFullName(){
		 return name + "." + this.extension;
	 }
	 
	 public String getDisplayName(){
		 return this.displayName;
	 }
	 
	 public String getFileExtension(){
		 return extension;
	 }
	 
	 public String getDiagramFileExtension(){
		 return extension + "_diagram";
	 }
	 
	 public String getToolchainFileID(){
		 return toolcahinId;
	 }
	 
	 public void check (Object o)
	 {
		 
	 }
}