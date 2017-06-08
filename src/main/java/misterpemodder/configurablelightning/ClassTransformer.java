package misterpemodder.configurablelightning;

import static org.objectweb.asm.Opcodes.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import net.minecraft.launchwrapper.IClassTransformer;

public class ClassTransformer implements IClassTransformer {

	public static final Logger LOGGER = LogManager.getLogger(LoadingPlugin.NAME);

	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		if (name.equals("net.minecraft.world.WorldServer")) {
			return transformWorldServer(basicClass);
		} else {
			return basicClass;
		}
	}

	private byte[] transformWorldServer(byte[] basicClass) {
		LOGGER.info("Patching WorldServer...");
		ClassNode classNode = new ClassNode();
		ClassReader classReader = new ClassReader(basicClass);
		classReader.accept(classNode, ClassReader.SKIP_FRAMES);

		String methodName = LoadingPlugin.runtimeDeobfuscation ? "updateBlocks" : "func_147456_g";
		for (MethodNode mn : classNode.methods) {

			if (mn.access == ACC_PROTECTED && mn.name.equals(methodName) && mn.desc.equals("()V") && mn.signature == null) {
				for (int i = 0; i < mn.instructions.size(); i++) {
					AbstractInsnNode node = mn.instructions.get(i);

					if (node instanceof FieldInsnNode && node.getOpcode() == GETFIELD && ((FieldInsnNode)node).owner.equals("net/minecraft/world/WorldServer") && ((FieldInsnNode)node).desc.equals("Ljava/util/Random;")) {

							((JumpInsnNode)mn.instructions.get(i+3)).setOpcode(IFEQ);
							
							mn.instructions.remove(mn.instructions.get(i+2));
							mn.instructions.remove(mn.instructions.get(i+1));

							mn.instructions.insertBefore(node, new VarInsnNode(ALOAD, 0));
							mn.instructions.insert(node, new MethodInsnNode(INVOKESTATIC, "misterpemodder/configurablelightning/CLHandler", "shouldDoLightning", "(Lnet/minecraft/world/WorldServer;Ljava/util/Random;)Z", false));
							
						LOGGER.info("WorldServer patching complete!");
						break;
					}
				}

			}
		}
		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        classNode.accept(writer);
        return writer.toByteArray();
	}
}