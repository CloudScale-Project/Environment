package eu.cloudscaleproject.env.analyser;

import eu.cloudscaleproject.env.toolchain.ToolchainUtils;

public enum PCMModelType{
	
	 REPOSITORY("repository", ToolchainUtils.KEY_FILE_REPOSITORY), 
	 SYSTEM("system", ToolchainUtils.KEY_FILE_SYSTEM),
	 RESOURCE("resourceenvironment", ToolchainUtils.KEY_FILE_RESOURCEENV),
	 ALLOCATION("allocation", ToolchainUtils.KEY_FILE_ALLOCATION),
	 USAGE("usagemodel", ToolchainUtils.KEY_FILE_USAGE),
	 
	 USAGE_EVOLUTION("usageevolution", ToolchainUtils.KEY_FILE_USAGEEVOLUTION),
	 PMS("pms", ToolchainUtils.KEY_FILE_MONITOR),
	 VARIATIONS("variations", ToolchainUtils.KEY_FILE_VARIATIONS),
	 MEASURING_POINT("pcmmeasuringpoint", ToolchainUtils.KEY_FILE_MESURPOINTS),
	 SLO("slo", ToolchainUtils.KEY_FILE_SLO),
	 EXPERIMENTS("experiments", ToolchainUtils.KEY_FILE_EXPERIMENTS);
	 
	 private final String extension;
	 private final String name;
	 private final String toolcahinId;
	 
	 PCMModelType(String toolchainId, String modelExtension){
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
	 
	 public String getToolchainFileID(){
		 return toolcahinId;
	 }
}