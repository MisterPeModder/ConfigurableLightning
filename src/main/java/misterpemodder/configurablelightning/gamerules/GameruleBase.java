package misterpemodder.configurablelightning.gamerules;

import java.util.function.Function;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.world.GameRules;
import net.minecraft.world.GameRules.ValueType;

public abstract class GameruleBase {
	
	protected final String id;
	protected final String name;
	protected final Object defaultValue;
	protected final String defaultValueString;
	protected final ValueType type;
	protected final Function<String, Object> valueParser;
	
	public String currentValueString;
	public Object currentValue;
	
	public GameruleBase(String id, String name, Object defaultValue, Function<String, Object> valueParser) {
		this(id, name, defaultValue, ValueType.ANY_VALUE, valueParser);
	}
	
	public GameruleBase(String id, String name, Object defaultValue, ValueType type, Function<String, Object> valueParser) {
		this.id = id;
		this.name = name;
		this.defaultValue = defaultValue;
		this.defaultValueString = defaultValue.toString();
		this.type = type;
		this.valueParser = valueParser;
	}
	
	public abstract Pair<String, Object> checkValue(String value);
	
	public Object getCurrentValue(GameRules rules) {
		return this.getCurrentValue(rules.getString(this.id));
	}
	
	public Object getCurrentValue(String strValue) {
		if(strValue.equals(this.currentValueString)) {
			return this.currentValue;
		}
		try {
			this.currentValueString = strValue;
			return this.currentValue = this.valueParser.apply(strValue);
		} catch(Exception e) {}
		this.currentValueString = this.defaultValueString;
		return this.currentValue = this.defaultValue;
	}
	
	public <T> T getCastCurrentValue(Class<T> clazz, GameRules rules) {
		Object val = this.getCurrentValue(rules);
		return getCastValue(clazz, val);
	}
	
	public <T> T getCastCurrentValue(Class<T> clazz, String strValue) {
		Object val = this.getCurrentValue(strValue);
		return getCastValue(clazz, val);
	}
	
	private <T> T getCastValue(Class<T> clazz, Object val) {
		try {
			return clazz.cast(val);
		} catch(ClassCastException e) {}
		return null;
	}
	
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
