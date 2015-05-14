package eu.cloudscaleproject.env.analyser;

import eu.cloudscaleproject.env.toolchain.ToolchainUtils;

public enum PCMModelType{
	
	 REPOSITORY(ToolchainUtils.KEY_FILE_REPOSITORY, "repository"), 
	 SYSTEM(ToolchainUtils.KEY_FILE_SYSTEM, "system"),
	 RESOURCE(ToolchainUtils.KEY_FILE_RESOURCEENV, "resourceenvironment"),
	 ALLOCATION(ToolchainUtils.KEY_FILE_ALLOCATION, "allocation"),
	 AT(ToolchainUtils.KEY_FILE_AT, "pa.xmi"),
	 USAGE(ToolchainUtils.KEY_FILE_USAGE, "usagemodel"),
	 
	 USAGE_EVOLUTION(ToolchainUtils.KEY_FILE_USAGEEVOLUTION, "usageevolution"),
	 PMS(ToolchainUtils.KEY_FILE_MONITOR, "monitor"),
	 VARIATIONS(ToolchainUtils.KEY_FILE_VARIATIONS, "variations"),
	 MEASURING_POINT(ToolchainUtils.KEY_FILE_MESURPOINTS, "pcmmeasuringpoint"),
	 SLO(ToolchainUtils.KEY_FILE_SLO, "slo"),
	 EXPERIMENTS(ToolchainUtils.KEY_FILE_EXPERIMENTS, "experiments");
	 
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