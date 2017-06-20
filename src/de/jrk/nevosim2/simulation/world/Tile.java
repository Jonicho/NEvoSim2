package de.jrk.nevosim2.simulation.world;

import java.io.Serializable;

public class Tile implements Serializable {
	private static final long serialVersionUID = -4246435246612818750L;

	enum Type {
		WATER, LAND
	}
	
	private Type type;
	private double food = 0;
	
	public Tile(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
	
	public double getFood() {
		if (type == Type.WATER) {
			return -1;
		}
		return food;
	}
	
	public void grow(double d) {
		food += d;
		if (food > 1.0) food = 1.0;
	}
}
