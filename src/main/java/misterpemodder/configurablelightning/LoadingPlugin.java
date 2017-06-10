package misterpemodder.configurablelightning;

import java.util.Map;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.Name(LoadingPlugin.NAME)
@IFMLLoadingPlugin.MCVersion("1.10.2")
@IFMLLoadingPlugin.SortingIndex(1042)
public class LoadingPlugin implements IFMLLoadingPlugin {
	
	public static boolean runtimeDeobfuscation = false;
	public static final String NAME = "CL Loading Plugin";

	@Override
	public String[] getASMTransformerClass() {
		return new String[]{ClassTransformer.class.getName()};
	}

	@Override
	public String getModContainerClass() {
		return null;
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
    public void injectData(Map<String, Object> data) {
		runtimeDeobfuscation = !(Boolean)data.get("runtimeDeobfuscationEnabled");
    }

	@Override
	public String getAccessTransformerClass() {
		return null;
	}

}
