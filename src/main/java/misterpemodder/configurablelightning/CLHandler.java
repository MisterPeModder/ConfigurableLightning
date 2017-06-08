package misterpemodder.configurablelightning;

import java.util.Random;

import net.minecraft.world.GameRules;
import net.minecraft.world.GameRules.ValueType;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = ConfigurableLightning.MOD_ID)
public class CLHandler {

	@SubscribeEvent
	public static void worldLoadingEvent(WorldEvent.Load event) {
		if(!event.getWorld().getGameRules().hasRule("lightningProbability")) {
			event.getWorld().getGameRules().addGameRule("lightningProbability", "100000", ValueType.NUMERICAL_VALUE);
		}
	}
	
	public static boolean shouldDoLightning(WorldServer world, Random rand) {
		GameRules rules = world.getGameRules();
		if(rules.hasRule("lightningProbability")) {
			return rand.nextInt(rules.getInt("lightningProbability")) == 0;
		} else {
			return rand.nextInt(100000) == 0;
		}
	}
	
}
