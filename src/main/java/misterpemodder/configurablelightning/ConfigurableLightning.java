package misterpemodder.configurablelightning;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;

@Mod(
		modid = ConfigurableLightning.MOD_ID,
		name = ConfigurableLightning.MOD_NAME,
		version = ConfigurableLightning.MOD_VERSION,
		acceptedMinecraftVersions = ConfigurableLightning.ACCEPTED_MC_VERSIONS,
		acceptableRemoteVersions = "*",
		dependencies = "required-after:hc@[1.0.0,)"
	)
public class ConfigurableLightning {
	
	public static final String MOD_ID = "cl";
	public static final String MOD_NAME = "Configurable Lightning";
	public static final String MOD_VERSION = "1.1.0";
	public static final String ACCEPTED_MC_VERSIONS = "[1.10.2]";
	
	@Instance(ConfigurableLightning.MOD_ID)
	public static ConfigurableLightning instance;

}
