package misterpemodder.configurablelightning;

import java.util.Random;

import misterpemodder.configurablelightning.gamerules.GameruleBase;
import misterpemodder.configurablelightning.gamerules.TheRules;
import net.minecraft.world.GameRules;
import net.minecraft.world.WorldServer;

public final class Hooks {
	
	public static boolean shouldDoLightning(WorldServer world, Random rand) {
		GameRules rules = world.getGameRules();
		GameruleBase rule = TheRules.LIGHTNING_PROBABILITY.rule;
		if(rules.hasRule(rule.getId())) {
			int i = rules.getInt(rule.getId());
			if(i <= 0) return false;
			return rand.nextInt(i) == 0;
		} else {
			return rand.nextInt((int)rule.getDefaultValue()) == 0;
		}
	}

}
