package eu.cloudscaleproject.env.toolchain;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public enum CSTool {
	
	EXTRACTOR_INPUT("Extractor input alternative", 
					"eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorInput"),
	EXTRACTOR_CONF("Extractor configuration alternative", 
				   "eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorConf"),
	EXTRACTOR_RES("Extractor results alternative", 
				  "eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorRes"),

	ANALYSER_INPUT("Analyser input alternative", 
				   "eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserInput"),
	ANALYSER_CONF("Analyser configuration alternative",
				  "eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserConf"),
	ANALYSER_RES("Analyser results alternative", 
				 "eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserRes"),

	SPOTTER_DYN_INPUT("Dynamic spotter input alternative", 
					  "eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterDynInput"),
	SPOTTER_DYN_CONF("Dynamic spotter configuration alternative",
					 "eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterDynConf"),
	SPOTTER_DYN_RES("Dynamic spotter results alternative", 
					"eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterDynRes"),
	
	SPOTTER_STA_INPUT("Static spotter input alternative", 
					  "eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterStaInput"),
	SPOTTER_STA_CONF("Static spotter configuration alternative",
					 "eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterStaConf"),
	SPOTTER_STA_RES("Static spotter results alternative", 
					"eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterStaRes"),
	
	USAGEEVOLUTION("Usage evolution alternative", 
					  "eu.cloudscaleproject.env.toolchain.ToolchainUtils.usageevolution"),
	OVERVIEW("Overview alternative", 
			 "eu.cloudscaleproject.env.toolchain.ToolchainUtils.overview"),
	ARCHITECTURAL_TEMPLATES("Architectural templates alternative", 
							"eu.cloudscaleproject.env.toolchain.ToolchainUtils.architecturaltemplates");

	private final String name;
	private final String id;
	
	public static CSTool getTool(String id){
		for(CSTool tc : CSTool.values()){
			if(tc.getID().equals(id)){
				return tc;
			}
		}
		return null;
	}
	
	public static String getToolName(String id){
		for(CSTool tc : CSTool.values()){
			if(tc.getID().equals(id)){
				return tc.name;
			}
		}
		return null;
	}
	
	CSTool(String name, String id){
		this.name = name;
		this.id = id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getID(){
		return this.id;
	}
}
