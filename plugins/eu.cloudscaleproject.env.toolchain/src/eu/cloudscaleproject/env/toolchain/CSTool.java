package eu.cloudscaleproject.env.toolchain;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public enum CSTool {
	
	EXTRACTOR("Extractor", 
			"eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractor"),
	EXTRACTOR_INPUT("Extractor Input", 
					"eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorInput"),
	EXTRACTOR_CONF("Extractor configuration alternative", 
				   "eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorConf"),
	EXTRACTOR_RES("Extractor Results", 
				  "eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorRes"),
				  
	ANALYSER("Analyser", 
				"eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyser"),
	ANALYSER_INPUT("Analyser Input", 
				  "eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserInput"),
	ANALYSER_CONF("Analyser Configuration",
				  "eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserConf"),
	ANALYSER_RES("Analyser Results", 
				 "eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserRes"),

	SPOTTER_DYN("Dynamic Spotter", 
					  "eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterDyn"),
	SPOTTER_DYN_INPUT("Dynamic Spotter Input", 
					  "eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterDynInput"),
	SPOTTER_DYN_CONF("Dynamic Spotter Configuration",
					 "eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterDynConf"),
	SPOTTER_DYN_RES("Dynamic Spotter Results", 
					"eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterDynRes"),
	
	SPOTTER_STA("Static Spotter", 
					"eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterSta"),
	SPOTTER_STA_INPUT("Static Spotter Input", 
					  "eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterStaInput"),
	SPOTTER_STA_CONF("Static Spotter Configuration",
					 "eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterStaConf"),
	SPOTTER_STA_RES("Static Spotter Results", 
					"eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterStaRes"),
	
	SCALE_DL("ScaleDL", 
			  "eu.cloudscaleproject.env.toolchain.ToolchainUtils.scaleDl"),
					
	USAGEEVOLUTION("Usage Evolution", 
					  "eu.cloudscaleproject.env.toolchain.ToolchainUtils.usageevolution"),
	OVERVIEW("Overview", 
			 "eu.cloudscaleproject.env.toolchain.ToolchainUtils.overview"),
	ARCHITECTURAL_TEMPLATES("Architectural Templates", 
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
