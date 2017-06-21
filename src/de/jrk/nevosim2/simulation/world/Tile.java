package de.jrk.nevosim2.simulation.world;

import java.io.Serializable;

public class Tile implements Serializable {
	private static final long serialVersionUID = -4246435246612818750L;

	enum Type {
		WATER, LAND
	}
	
	private Type type;
	private double grass = 0;
	
	public Tile(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
	
	public double getGrass() {
		if (type == Type.WATER) {
			return -1;
		}
		return grass;
	}
	
	public void grow(double d) {
		grass += d;
		if (grass > 1.0) grass = 1.0;
	}
	
	public boolean letEat(double d) {
		if (d > grass || d < 0) {
			return false;
		}
		grass -= d;
		return true;
	}
}
