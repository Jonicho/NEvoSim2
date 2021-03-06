package de.jrk.nevosim2.simulation.creature;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import de.jrk.nevosim2.simulation.Simulation;
import de.jrk.nevosim2.util.Vec2d;
import de.jrk.nevosim2.util.Vec2i;

public class Creature implements Serializable {
	private static final long serialVersionUID = 8246894358517353395L;
	private Vec2d pos = new Vec2d();
	private Vec2i tilePos = new Vec2i();
	private double size = 0.01;
	private Color color;
	private Brain brain = new Brain();
	private double direction = 0;
	private Vec2d directionVector;
	private double speed = 0;
	private double energy = 0;
	private boolean alive = true;
	private double eatValue = 0.1;
	private double waterDamage = 0.1;

	public Creature(Vec2d pos) {
		energy = 1.5;
		color = Color.RED;
		this.pos = pos;
	}

	public void update() {
		directionVector = new Vec2d(Math.sin(direction) * speed, Math.cos(direction) * speed);
		pos.add(directionVector);
		tilePos = new Vec2i((int) (pos.getX() * Simulation.world.world.length), (int) (pos.getY() * Simulation.world.world[0].length));
		
		updateBrain();
		
		if (brain.getWantsEat()) {
			if (Simulation.world.letEat(tilePos.getX(), tilePos.getY(), eatValue)) {
				energy += eatValue;
			}
		}
		
		if (Simulation.world.getFood(tilePos.getX(), tilePos.getY()) <= -1) {
			energy -= waterDamage;
		}

		if (energy < 1) {
			alive = false;
		}
	}

	private void updateBrain() {
		brain.setEnergy(energy);
		brain.setDirectionVector(directionVector);
		{
			double[] foods = new double[9];
			int i = 0;
			for (int y = -1; y <= 1; y++) {
				for (int x = -1; x <= 1; x++) {
					int posX = tilePos.getX() + x;
					int posY = tilePos.getY() + y;
					foods[i] = Simulation.world.getFood(posX, posY);
					i++;
				}
			}
			brain.setEnvironmentTiles(foods);
		}

		brain.calculate();
		direction += brain.getRotation() / 100;
		speed = brain.getSpeed() / 1000;
	}

	public void draw(Graphics g, int screenSize) {
		g.setColor(color);
		int x = (int) ((pos.getX()) * screenSize);
		int y = (int) ((pos.getY()) * screenSize);
		int drawSize = (int) (size * screenSize);
		g.fillOval(x - drawSize/2, y - drawSize/2, drawSize, drawSize);
	}

	public boolean isAlive() {
		return alive;
	}
}
