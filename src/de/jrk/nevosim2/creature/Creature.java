package de.jrk.nevosim2.creature;

import de.jrk.nevosim2.util.Vector;

public class Creature {
	private Vector pos = new Vector();
	private Brain brain = new Brain();
	private double direction = 0;
	
	public void update() {
		updateBrain();
		
		pos.add(new Vector(Math.sin(Math.toRadians(direction)), Math.cos(Math.toRadians(direction))));
	}
	
	private void updateBrain() {
		brain.setFood(0);//TODO change to real value
		
		brain.calculate();
		direction += brain.getRotation();
	}
}
