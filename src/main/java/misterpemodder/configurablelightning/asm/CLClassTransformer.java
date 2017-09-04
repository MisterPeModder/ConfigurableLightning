package misterpemodder.configurablelightning.asm;

import java.util.Collections;
import java.util.Set;

import misterpemodder.hc.asm.ClassPatcher;
import misterpemodder.hc.asm.HCClassTransformer;

public class CLClassTransformer extends HCClassTransformer {

	@Override
	protected Set<ClassPatcher> getClassPatchers() {
		return Collections.singleton(new CLClassPatcher());
	}

	@Override
	protected String getName() {
		return CLLoadingPlugin.NAME;
	}

}
