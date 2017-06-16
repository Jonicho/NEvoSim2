package de.jrk.nevosim2.simulation.world;

public class Tile {
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
		return food;
	}
}
