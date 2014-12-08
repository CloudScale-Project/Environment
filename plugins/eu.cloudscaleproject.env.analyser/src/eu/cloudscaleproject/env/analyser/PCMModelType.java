package eu.cloudscaleproject.env.analyser;

public enum PCMModelType{
	
	 REPOSITORY("repository"), 
	 SYSTEM("system"),
	 RESOURCE("resourceenvironment"),
	 ALLOCATION("allocation"),
	 USAGE("usagemodel"),
	 
	 USAGE_EVOLUTION("usageevolution"),
	 PMS("pms"),
	 VARIATIONS("variations"),
	 MEASURING_POINT("pcmmeasuringpoint"),
	 SLO("slo"),
	 EXPERIMENTS("experiments");
	 
	 private final String extension;
	 private final String name;
	 
	 PCMModelType(String modelExtension){
		 this.extension = modelExtension;
		 this.name = "pcm";
	 }
	 
	 public String getFullName(){
		 return name + "." + this.extension;
	 }
	 
	 public String getFileExtension(){
		 return extension;
	 }
}