package eu.cloudscaleproject.env.toolchain;



public enum ModelType{
	
	 OVERVIEW(ToolchainUtils.KEY_FILE_OVERVIEW, "sdlo"),
	
	 REPOSITORY(ToolchainUtils.KEY_FILE_REPOSITORY, "repository"), 
	 SYSTEM(ToolchainUtils.KEY_FILE_SYSTEM, "system"),
	 RESOURCE(ToolchainUtils.KEY_FILE_RESOURCEENV, "resourceenvironment"),
	 ALLOCATION(ToolchainUtils.KEY_FILE_ALLOCATION, "allocation"),
	 AT(ToolchainUtils.KEY_FILE_AT, "xmi"),
	 USAGE(ToolchainUtils.KEY_FILE_USAGE, "usagemodel"),
	 
	 USAGE_EVOLUTION(ToolchainUtils.KEY_FILE_USAGEEVOLUTION, "usageevolution"),
	 PMS(ToolchainUtils.KEY_FILE_MONITOR, "monitor"),
	 VARIATIONS(ToolchainUtils.KEY_FILE_VARIATIONS, "variations"),
	 MEASURING_POINT(ToolchainUtils.KEY_FILE_MESURPOINTS, "pcmmeasuringpoint"),
	 SLO(ToolchainUtils.KEY_FILE_SLO, "slo"),
	 EXPERIMENTS(ToolchainUtils.KEY_FILE_EXPERIMENTS, "experiments"),
	 
	 LIMBO(ToolchainUtils.KEY_FILE_LIMBO, "dlim"),

	 SOURCECODEDECORATOR(ToolchainUtils.KEY_FILE_SOURCEDECORATOR, "sourcecodedecorator"),
	 ECORE(null, "ecore");
	 
	 public static ModelType[] GROUP_ALL  		   	 = ModelType.values();
	 public static ModelType[] GROUP_PCM  		   	 = {REPOSITORY, SYSTEM, RESOURCE, ALLOCATION, USAGE} ;
	 public static ModelType[] GROUP_PCM_EXTENDED	 = {REPOSITORY, SYSTEM, RESOURCE, ALLOCATION, USAGE, AT} ;
	 public static ModelType[] GROUP_EXPERIMENTS 	 = {EXPERIMENTS, PMS, SLO, VARIATIONS, USAGE_EVOLUTION, MEASURING_POINT} ;
	 public static ModelType[] GROUP_SOURCEDECORATOR = {SOURCECODEDECORATOR, REPOSITORY, SYSTEM, ECORE} ;
	 
	 private final String extension;
	 private final String name;
	 private final String toolcahinId;
	 
	 public static ModelType getModelType(String extension){
		 for(ModelType mt : ModelType.values()){
			 if(mt.extension.equals(extension)){
				 return mt;
			 }
		 }
		 return null;
	 }
	 
	 ModelType(String toolchainId, String modelExtension){
		 this.extension = modelExtension;
		 this.name = "pcm";
		 this.toolcahinId = toolchainId;
	 }
	 
	 public String getFullName(){
		 return name + "." + this.extension;
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