package misterpemodder.configurablelightning;

import misterpemodder.configurablelightning.gamerules.GameruleBase;
import misterpemodder.configurablelightning.gamerules.TheRules;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandGameRule;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.GameRules;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class EventHandler {
	
	@SubscribeEvent
	public static void worldLoadingEvent(WorldEvent.Load event) {
		GameRules rules = event.getWorld().getGameRules();
		for(TheRules r : TheRules.values()) {
			GameruleBase rule = r.rule;
			if(!rules.hasRule(rule.getId())) {
				event.getWorld().getGameRules().addGameRule(rule.getId(), rule.getDefaultValueString(), rule.getType());
			}
		}
	}
	
	@SubscribeEvent
	public static void commandEvent(CommandEvent event) {
		if(event.getCommand() instanceof CommandGameRule && event.getCommand().getName().equals("gamerule")) {
			String[] args = event.getParameters();
			for(TheRules r : TheRules.values()) {
				GameruleBase rule = r.rule;
				if(args.length >= 2 && args[0].equals(rule.getId())) {
					args[1] = rule.checkValue(args[1]);

					String str = "";
					if(args[1].equals("0")) {
						str = " is now disabled";
					} else if(args[1].equals(rule.getDefaultValueString())) {
						str = " set to default value ("+rule.getDefaultValueString()+")";
					} else {
						str = " set to "+args[1];
					}
					CommandBase.notifyCommandListener(event.getSender(), event.getCommand(), TextFormatting.AQUA + rule.getName() + str);
				}
			}
		}
	}
	
}
