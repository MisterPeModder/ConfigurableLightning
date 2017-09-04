package misterpemodder.configurablelightning.gamerules;

import misterpemodder.configurablelightning.gamerules.GameruleBase;

public enum TheRules {
			
	LIGHTNING_PROBABILITY(new GameruleBase("lightningProbability", "Lightning probability", 100000) {
		@Override
		public String checkValue(String value) {
			try {
				int i = Integer.parseInt(value);
				if(i < 0) {
					return "0";
				}
			}
			catch (NumberFormatException e)  {
				return this.defaultValueString;
			}
			return value;
		}
	})
	;
		
	public final GameruleBase rule;
		
	TheRules(GameruleBase rule) {
		this.rule = rule;
	}
		
}
