package misterpemodder.configurablelightning.gamerules;

import net.minecraft.world.GameRules.ValueType;

public abstract class GameruleBase {
	
	protected final String id;
	protected final String name;
	protected final Object defaultValue;
	protected final String defaultValueString;
	protected final ValueType type;
	
	public GameruleBase(String id, String name, Object defaultValue) {
		this(id, name, defaultValue, ValueType.ANY_VALUE);
	}
	
	public GameruleBase(String id, String name, Object defaultValue, ValueType type) {
		this.id = id;
		this.name = name;
		this.defaultValue = defaultValue;
		this.defaultValueString = defaultValue.toString();
		this.type = type;
	}
	
	public abstract String checkValue(String value);
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDefaultValueString() {
		return defaultValueString;
	}
	
	public Object getDefaultValue() {
		return defaultValue;
	}

	public ValueType getType() {
		return type;
	}
	
	
}
