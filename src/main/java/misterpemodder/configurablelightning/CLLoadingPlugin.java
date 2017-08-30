package misterpemodder.configurablelightning;

import java.util.Collections;
import java.util.Set;

import misterpemodder.hc.asm.ClassPatcher;
import misterpemodder.hc.asm.HCLoadingPlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;


@IFMLLoadingPlugin.Name(CLLoadingPlugin.NAME)
@IFMLLoadingPlugin.MCVersion("1.10.2")
@IFMLLoadingPlugin.SortingIndex(1042)
public class CLLoadingPlugin extends HCLoadingPlugin {

	public static final String NAME = "CL Loading Plugin";

	@Override
	protected Set<ClassPatcher> getClassPatchers() {
		return Collections.singleton(new CLClassPatcher());
	}

}
