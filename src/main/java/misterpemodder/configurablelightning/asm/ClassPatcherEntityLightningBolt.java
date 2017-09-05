package misterpemodder.configurablelightning.asm;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.DADD;
import static org.objectweb.asm.Opcodes.DLOAD;
import static org.objectweb.asm.Opcodes.DSTORE;
import static org.objectweb.asm.Opcodes.DSUB;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;

import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import com.google.common.collect.Lists;

import misterpemodder.hc.asm.ClassPatcher;

public class ClassPatcherEntityLightningBolt extends ClassPatcher {
	
	@Override
	public boolean matches(String transformedClassName) {
		return transformedClassName.equals("net.minecraft.entity.effect.EntityLightningBolt");
	}

	@Override
	protected List<IMethodPatcher> getMethodPatchers() {
		return Lists.newArrayList(new IMethodPatcher() {

			@Override
			public BiPredicate<Boolean, MethodNode> getMethodNodePredicate() {
				return (obf, mn) -> mn.access == ACC_PUBLIC && mn.name.equals("<init>") && mn.desc.equals("(Lnet/minecraft/world/World;DDDZ)V");
			}

			@Override
			public List<IPatch> getPatches() {
				return Collections.singletonList(new IPatch() {
					
					@Override
					public BiPredicate<Boolean, AbstractInsnNode> getNodePredicate() {
						return (obf, node) -> node.getOpcode() == INVOKEVIRTUAL && node instanceof MethodInsnNode && ((MethodInsnNode)node).owner.equals("net/minecraft/world/GameRules") && ((MethodInsnNode)node).name.equals(obf? "getBoolean" : "func_82766_b") && ((MethodInsnNode)node).desc.equals("(Ljava/lang/String;)Z");
					}
					
					@Override
					public void makePatch(MethodNode mn, AbstractInsnNode node, int nodeIndex) {
						mn.instructions.remove(node.getPrevious());
						mn.instructions.remove(node.getPrevious());
						mn.instructions.remove(node.getPrevious());
						mn.instructions.insert(node, new MethodInsnNode(INVOKESTATIC, "misterpemodder/configurablelightning/Hooks", "shouldCreateFire", "(Lnet/minecraft/entity/effect/EntityLightningBolt;)Z", false));
						mn.instructions.insert(node, new VarInsnNode(ALOAD, 0));
						mn.instructions.remove(node);
					}
					
				});
			}
			
		},
		new IMethodPatcher() {
			
			@Override
			public BiPredicate<Boolean, MethodNode> getMethodNodePredicate() {
				return (obf, mn) -> mn.access == ACC_PUBLIC && mn.name.equals(obf ? "onUpdate" : "func_70071_h_") && mn.desc.equals("()V") && mn.signature == null;
			}
			
			@Override
			public List<IPatch> getPatches() {
				
				return Lists.newArrayList(new IPatch() {
					
					@Override
					public BiPredicate<Boolean, AbstractInsnNode> getNodePredicate() {
						return (obf, node) -> node.getOpcode() == INVOKEVIRTUAL && node instanceof MethodInsnNode && ((MethodInsnNode)node).owner.equals("net/minecraft/world/GameRules") && ((MethodInsnNode)node).name.equals(obf? "getBoolean" : "func_82766_b") && ((MethodInsnNode)node).desc.equals("(Ljava/lang/String;)Z");
					}
					
					@Override
					public void makePatch(MethodNode mn, AbstractInsnNode node, int nodeIndex) {
						mn.instructions.remove(node.getPrevious());
						mn.instructions.remove(node.getPrevious());
						mn.instructions.remove(node.getPrevious());
						mn.instructions.insert(node, new MethodInsnNode(INVOKESTATIC, "misterpemodder/configurablelightning/Hooks", "shouldCreateFire", "(Lnet/minecraft/entity/effect/EntityLightningBolt;)Z", false));
						mn.instructions.remove(node);
					}
					
				},
				new IPatch() {
					
					@Override
					public BiPredicate<Boolean, AbstractInsnNode> getNodePredicate() {
						return (obf, node) -> node.getOpcode() == DSTORE && node instanceof VarInsnNode && node.getPrevious() instanceof LdcInsnNode && ((LdcInsnNode)node.getPrevious()).cst.equals(3.0D);
					}
					
					@Override
					public void makePatch(MethodNode mn, AbstractInsnNode node, int nodeIndex) {
						mn.instructions.remove(node.getPrevious());
						mn.instructions.insertBefore(node, new VarInsnNode(ALOAD, 0));
						mn.instructions.insertBefore(node, new MethodInsnNode(INVOKESTATIC, "misterpemodder/configurablelightning/Hooks", "getLightningRange", "(Lnet/minecraft/entity/effect/EntityLightningBolt;)D", false));
					}
					
				},
				new IPatch() {
					
					private int varIndex = -1;
					
					@Override
					public BiPredicate<Boolean, AbstractInsnNode> getNodePredicate() {
						return (obf, node) -> node instanceof LdcInsnNode && ((LdcInsnNode)node).cst.equals(3.0D) && (node.getNext().getOpcode() == DSUB || node.getNext().getOpcode() == DADD);
					}
					
					@Override
					public void makePatch(MethodNode mn, AbstractInsnNode node, int nodeIndex) {
						if(varIndex == -1) {
							for(LocalVariableNode n : mn.localVariables) {
								if(n.desc.equals("D")) {
									varIndex = n.index;
									break;
								}
							}
						}
						if(varIndex != -1) {
							mn.instructions.insert(node, new VarInsnNode(DLOAD, varIndex));
							mn.instructions.remove(node);
						}
					}
					
					@Override
					public boolean alwaysPatch() {
						return true;
					}
					
				});
			}
		});
	}

}
