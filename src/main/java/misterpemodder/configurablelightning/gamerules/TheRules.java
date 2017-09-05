package misterpemodder.configurablelightning.gamerules;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.world.GameRules.ValueType;

public enum TheRules {
			
	LIGHTNING_PROBABILITY(new GameruleBase("lightningProbability", "Lightning probability", 100000, Integer::valueOf) {
		@Override
		public Pair<String, Object> checkValue(String value) {
			try {
				int i = Integer.parseInt(value);
				if(i < 0) {
					value = "0";
					i = 0;
				}
				return Pair.of(value, i);
			}
			catch (NumberFormatException e)  {}
			return Pair.of(this.getDefaultValueString(), this.getDefaultValue());
		}
	}),
	LIGHTNING_DAMAGE(new GameruleBase("lightningDamage", "Lightning damage", 5.0F, Float::valueOf) {
		@Override
		public Pair<String, Object> checkValue(String value) {
			try {
				float f = Float.parseFloat(value);
				if(f < 0) {
					value = "0.0";
					f = 0.0F;
				}
				return Pair.of(value, f);
			}
			catch (NumberFormatException e)  {}
			return Pair.of(this.getDefaultValueString(), this.getDefaultValue());
		}
	}),
	LIGHTNING_RANGE(new GameruleBase("lightningRange", "Lightning range", 3.0D, Double::valueOf) {
		@Override
		public Pair<String, Object> checkValue(String value) {
			try {
				double d = Double.parseDouble(value);
				if(d < 0) {
					value = "0.0";
					d = 0.0D;
				}
				return Pair.of(value, d);
			}
			catch (NumberFormatException e)  {}
			return Pair.of(this.getDefaultValueString(), this.getDefaultValue());
		}
	}),
	LIGHTNING_FIRE(new GameruleBase("lightningFire", "Lightning fire", true, ValueType.BOOLEAN_VALUE, Boolean::valueOf) {
		@Override
		public Pair<String, Object> checkValue(String value) {
			value = value.equalsIgnoreCase("true")? "true" : "false";
			return Pair.of(value, Boolean.valueOf(value));
		}
	}),
	LIGHTNING_HORSE_SPAWNING(new GameruleBase("lightningHorseSpawning", "Lightning horse spawning", true, ValueType.BOOLEAN_VALUE, Boolean::valueOf) {
		@Override
		public Pair<String, Object> checkValue(String value) {
			value = value.equalsIgnoreCase("true")? "true" : "false";
			return Pair.of(value, Boolean.valueOf(value));
		}
	})
	;
		
	public final GameruleBase rule;
		
	TheRules(GameruleBase rule) {
		this.rule = rule;
	}
		
}
