package misterpemodder.configurablelightning.asm;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;

import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import misterpemodder.hc.asm.ClassPatcher;

public class ClassPatcherEntity extends ClassPatcher {

	@Override
	public boolean matches(String transformedClassName) {
		return transformedClassName.equals("net.minecraft.entity.Entity");
	}

	@Override
	protected List<IMethodPatcher> getMethodPatchers() {
		return Collections.singletonList(new IMethodPatcher() {
			
			@Override
			public BiPredicate<Boolean, MethodNode> getMethodNodePredicate() {
				return (obf, mn) -> mn.access == ACC_PUBLIC && mn.name.equals(obf ? "onStruckByLightning" : "func_70077_a") && mn.desc.equals("(Lnet/minecraft/entity/effect/EntityLightningBolt;)V") && mn.signature == null;
			}
			
			@Override
			public List<IPatch> getPatches() {
				return Collections.singletonList(new IPatch() {
					
					@Override
					public BiPredicate<Boolean, AbstractInsnNode> getNodePredicate() {
						return (obf, node) -> node.getOpcode() == GETSTATIC && node instanceof FieldInsnNode && ((FieldInsnNode)node).owner.equals("net/minecraft/util/DamageSource");
					}
					
					@Override
					public void makePatch(MethodNode mn, AbstractInsnNode node, int nodeIndex) {
						mn.instructions.remove(node.getNext());
						mn.instructions.insert(node, new MethodInsnNode(INVOKESTATIC, "misterpemodder/configurablelightning/Hooks", "getLightningDamage", "(Lnet/minecraft/entity/Entity;)F", false));
						mn.instructions.insert(node, new VarInsnNode(ALOAD, 0));
					}
					
				});
			}
		});
	}

}
