package misterpemodder.configurablelightning;

import static org.objectweb.asm.Opcodes.ACC_PROTECTED;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.GETFIELD;
import static org.objectweb.asm.Opcodes.IFEQ;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;

import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import misterpemodder.hc.asm.ClassPatcher;

public class CLClassPatcher extends ClassPatcher {

	@Override
	public boolean matches(String transformedClassName) {
		return transformedClassName.equals("net.minecraft.world.WorldServer");
	}

	@Override
	protected List<IMethodPatcher> getMethodPatchers() {
		return Collections.singletonList(new IMethodPatcher() {
			
			@Override
			public BiPredicate<Boolean, MethodNode> getMethodNodePredicate() {
				return (obf, mn) -> mn.access == ACC_PROTECTED && mn.name.equals(obf ? "updateBlocks" : "func_147456_g") && mn.desc.equals("()V") && mn.signature == null;
			}
			
			@Override
			public List<IPatch> getPatches() {
				return Collections.singletonList(new IPatch() {
					
					@Override
					public BiPredicate<Boolean, AbstractInsnNode> getNodePredicate() {
						return (obf, node) -> node instanceof FieldInsnNode && node.getOpcode() == GETFIELD && ((FieldInsnNode)node).owner.equals("net/minecraft/world/WorldServer") && ((FieldInsnNode)node).desc.equals("Ljava/util/Random;");
					}
					
					@Override
					public void makePatch(MethodNode mn, AbstractInsnNode targetNode, int i) {
						((JumpInsnNode)mn.instructions.get(i+3)).setOpcode(IFEQ);
						
						mn.instructions.remove(mn.instructions.get(i+2));
						mn.instructions.remove(mn.instructions.get(i+1));

						mn.instructions.insertBefore(targetNode, new VarInsnNode(ALOAD, 0));
						mn.instructions.insert(targetNode, new MethodInsnNode(INVOKESTATIC, "misterpemodder/configurablelightning/CLHandler", "shouldDoLightning", "(Lnet/minecraft/world/WorldServer;Ljava/util/Random;)Z", false));
					}

				});
			}

		});
	}

}
