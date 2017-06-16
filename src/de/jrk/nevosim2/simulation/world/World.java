package de.jrk.nevosim2.simulation.world;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import de.jrk.nevosim2.simulation.creature.Creature;
import de.jrk.nevosim2.simulation.world.Tile.Type;
import de.jrk.nevosim2.util.Vector;

public class World {
	private final Tile[][] world = new Tile[11][11];
	private ArrayList<Creature> creatures = new ArrayList<>();

	public World() {
		boolean a = false;
		for (int y = 0; y < world.length; y++) {
			for (int x = 0; x < world[0].length; x++) {
				world[x][y] = new Tile(a ? Type.LAND : Type.WATER);
				a = !a;
			}
		}

		for (int i = 0; i < 10; i++) {
			creatures.add(new Creature(new Vector(Math.random(), Math.random())));
		}
	}

	public void update() {
		for (int y = 0; y < world.length; y++) {
			for (int x = 0; x < world[0].length; x++) {
				//TODO update world-tiles
			}
		}
		
		for (Creature creature : creatures) {
			creature.update();
		}
	}

	public void draw(Graphics g, int screenSize) {
		double tileSize = (double) screenSize / world.length;

		for (int y = 0; y < world.length; y++) {
			for (int x = 0; x < world[0].length; x++) {
				switch (world[x][y].getType()) {
				case LAND:
					g.setColor(Color.GREEN);
					break;
				case WATER:
					g.setColor(Color.BLUE);
					break;
				default:
					break;
				}
				g.fillRect((int) (x * tileSize), (int) (y * tileSize), (int) tileSize, (int) tileSize);
			}
		}

		for (int i = 0; i < creatures.size(); i++) {
			creatures.get(i).draw(g, screenSize);
		}
	}
}
