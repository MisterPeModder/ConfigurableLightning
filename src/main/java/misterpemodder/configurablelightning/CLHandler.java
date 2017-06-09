package misterpemodder.configurablelightning;

import java.util.Random;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandGameRule;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.GameRules;
import net.minecraft.world.GameRules.ValueType;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = ConfigurableLightning.MOD_ID)
public class CLHandler {

	public static final int DEFAULT_VALUE = 100000;
	public static final String GAMERULE_NAME = "lightningProbability";
	
	@SubscribeEvent
	public static void worldLoadingEvent(WorldEvent.Load event) {
		if(!event.getWorld().getGameRules().hasRule(GAMERULE_NAME)) {
			event.getWorld().getGameRules().addGameRule(GAMERULE_NAME, String.valueOf(DEFAULT_VALUE), ValueType.ANY_VALUE);
		}
	}
	
	@SubscribeEvent
	public static void commandEvent(CommandEvent event) {
		if(event.getCommand() instanceof CommandGameRule && event.getCommand().getName().equals("gamerule")) {
			String[] args = event.getParameters();
			if(args.length >= 2 && args[0].equals(GAMERULE_NAME)) {
				try {
					int i = Integer.parseInt(args[1]);
					if(i < 0) {
						args[1] = "0";
					}
				}
				catch (NumberFormatException e)  {
					args[1] = String.valueOf(DEFAULT_VALUE);
				}
				
				String str = "";
				if(args[1].equals("0")) {
					str = "Lightning is now disabled";
				} else if(args[1].equals(String.valueOf(DEFAULT_VALUE))) {
					str = "Lightning probability set to default value ("+DEFAULT_VALUE+")";
				} else {
					str = "Lightning probability set to "+args[1];
				}
				CommandBase.notifyCommandListener(event.getSender(), event.getCommand(), TextFormatting.AQUA+str);
			}
		}
	}
	
	public static boolean shouldDoLightning(WorldServer world, Random rand) {
		GameRules rules = world.getGameRules();
		if(rules.hasRule(GAMERULE_NAME)) {
			int i = rules.getInt(GAMERULE_NAME);
			if(i <= 0) return false;
			return rand.nextInt(i) == 0;
		} else {
			return rand.nextInt(DEFAULT_VALUE) == 0;
		}
	}
	
}
