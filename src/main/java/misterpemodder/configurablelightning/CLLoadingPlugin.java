package misterpemodder.configurablelightning;

import java.util.Map;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;


@IFMLLoadingPlugin.Name(CLLoadingPlugin.NAME)
@IFMLLoadingPlugin.MCVersion("1.10.2")
@IFMLLoadingPlugin.SortingIndex(1042)
public class CLLoadingPlugin implements IFMLLoadingPlugin {

	public static final String NAME = "CL Loading Plugin";

	@Override
	public String[] getASMTransformerClass() {
		return new String[]{CLClassTransformer.class.getName()};
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
    public void injectData(Map<String, Object> data) {}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}

}
