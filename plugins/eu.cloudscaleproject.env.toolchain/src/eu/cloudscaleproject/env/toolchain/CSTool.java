package eu.cloudscaleproject.env.toolchain;

/**
 *
 * @author Vito Čuček <vito.cucek@xlab.si>
 *
 */
public enum CSTool {
	
	
	EXTRACTOR("Extractor", 
			"eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractor",
			CSToolResource.EXTRACTOR_INPUT, CSToolResource.EXTRACTOR_CONF, CSToolResource.EXTRACTOR_RES),
	ANALYSER("Analyser", 
			"eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyser",
			CSToolResource.ANALYSER_INPUT, CSToolResource.ANALYSER_CONF, CSToolResource.ANALYSER_RES),
	SPOTTER_DYN("Dynamic Spotter", 
			"eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterDyn",
			CSToolResource.SPOTTER_DYN_INPUT, CSToolResource.SPOTTER_DYN_CONF, CSToolResource.SPOTTER_DYN_RES),
	SPOTTER_STA("Static Spotter", 
			"eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterSta",
			CSToolResource.SPOTTER_STA_INPUT, CSToolResource.SPOTTER_STA_CONF, CSToolResource.SPOTTER_STA_RES);
	
	private final String name;
	private final String id;
	
	private final CSToolResource input;
	private final CSToolResource config;
	private final CSToolResource result;
	
	private 
	
	CSTool(String name, String id, CSToolResource input, CSToolResource config, CSToolResource result){
		this.name = name;
		this.id = id;
		
		this.input = input;
		this.config = config;
		this.result = result;
	}
	
	public String getId()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public CSToolResource getInput()
	{
		return input;
	}
	
	public CSToolResource getConfig()
	{
		return config;
	}
	
	public CSToolResource getResult()
	{
		return result;
	}
}
