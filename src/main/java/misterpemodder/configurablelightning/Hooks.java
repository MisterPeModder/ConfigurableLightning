package misterpemodder.configurablelightning;

import java.util.Random;

import misterpemodder.configurablelightning.gamerules.GameruleBase;
import misterpemodder.configurablelightning.gamerules.TheRules;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.world.GameRules;
import net.minecraft.world.WorldServer;

public final class Hooks {
	
	public static boolean shouldDoLightning(WorldServer world, Random rand) {
		GameRules rules = world.getGameRules();
		GameruleBase rule = TheRules.LIGHTNING_PROBABILITY.rule;
		if(rules.hasRule(rule.getId())) {
			Integer i = rule.getCastCurrentValue(Integer.class, rules);
			return i == null || i <= 0? false : rand.nextInt(i) == 0;
		}
		return rand.nextInt((int)rule.getDefaultValue()) == 0;
	}
	
	public static float getLightningDamage(Entity entity) {
		if(!entity.getEntityWorld().isRemote) {
			GameRules rules = entity.getEntityWorld().getGameRules();
			GameruleBase rule = TheRules.LIGHTNING_DAMAGE.rule;
			if(rules.hasRule(rule.getId())) {
				Float f = rule.getCastCurrentValue(Float.class, rules);
				return f == null || f < 0.0F? 0.0F : f;
			}
			return (float)rule.getDefaultValue();
		}
		return 5.0F;
	}
	
	public static double getLightningRange(EntityLightningBolt entity) {
		if(!entity.getEntityWorld().isRemote) {
			GameRules rules = entity.getEntityWorld().getGameRules();
			GameruleBase rule = TheRules.LIGHTNING_RANGE.rule;
			if(rules.hasRule(rule.getId())) {
				Double d = rule.getCastCurrentValue(Double.class, rules);
				return d == null || d < 0.0D? 0.0D : d;
			}
			return (float)rule.getDefaultValue();
		}
		return 5.0F;
	}
	
	public static boolean shouldCreateFire(EntityLightningBolt entity) {
		GameRules rules = entity.getEntityWorld().getGameRules();
		GameruleBase rule = TheRules.LIGHTNING_FIRE.rule;
		if(rules.hasRule(rule.getId())) {
			Boolean b = rule.getCastCurrentValue(Boolean.class, rules);
			if(b != null)
				return b && rules.getBoolean("doFireTick");
		}
		return rules.getBoolean("doFireTick");
	}
	
	public static boolean shouldSpawnSkeletonHorse(WorldServer world) {
		GameRules rules = world.getGameRules();
		GameruleBase rule = TheRules.LIGHTNING_HORSE_SPAWNING.rule;
		if(rules.hasRule(rule.getId())) {
			Boolean b = rule.getCastCurrentValue(Boolean.class, rules);
			if(b != null)
				return b;
		}
		return (boolean)rule.getDefaultValue();
	}

}
