package misterpemodder.configurablelightning.asm;

import java.util.Set;

import com.google.common.collect.Sets;

import misterpemodder.hc.asm.ClassPatcher;
import misterpemodder.hc.asm.HCClassTransformer;

public class CLClassTransformer extends HCClassTransformer {

	@Override
	protected Set<ClassPatcher> getClassPatchers() {
		return Sets.newHashSet(new ClassPatcherWorldServer(), new ClassPatcherEntity(), new ClassPatcherEntityLightningBolt());
	}

	@Override
	protected String getName() {
		return CLLoadingPlugin.NAME;
	}

}
