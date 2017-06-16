package de.jrk.nevosim2.simulation;

import java.awt.Graphics;

import de.jrk.nevosim2.simulation.world.World;

public class Simulation implements Runnable {
	
	public World world = new World();
	public static boolean run = false;
	public static boolean fast = false;
	
	public Simulation() {
		
	}

	@Override
	public void run() {
		while (true) {
			if (run) {
				world.update();
			}
			if (!fast) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
			}
		}
	}
	
	public void draw(Graphics g, int screenSize) {
		world.draw(g, screenSize);
	}
}
