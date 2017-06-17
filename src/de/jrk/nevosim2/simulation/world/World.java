package de.jrk.nevosim2.simulation.world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import de.jrk.nevosim2.simulation.creature.Creature;
import de.jrk.nevosim2.simulation.world.Tile.Type;
import de.jrk.nevosim2.util.Vector;

public class World {
	private final Tile[][] world = new Tile[101][101];
	private ArrayList<Creature> creatures = new ArrayList<>();

	public World() {
		generateEmptyWorld();

		for (int i = 0; i < 100; i++) {
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
		BufferedImage img = new BufferedImage(world.length, world[0].length, BufferedImage.TYPE_INT_RGB);
		Graphics imgG = img.getGraphics();
		
		for (int y = 0; y < world.length; y++) {
			for (int x = 0; x < world[0].length; x++) {
				switch (world[x][y].getType()) {
				case LAND:
					imgG.setColor(new Color((float) (1 - (world[x][y].getFood()) / 101), 1f, 0f));
					break;
				case WATER:
					imgG.setColor(Color.BLUE);
					break;
				default:
					break;
				}
				imgG.fillRect(x, y, 1, 1);
			}
		}
		
		g.drawImage(img, 0, 0, screenSize, screenSize, null);

		for (int i = 0; i < creatures.size(); i++) {
			creatures.get(i).draw(g, screenSize);
		}
	}
	
	private void generateEmptyWorld() {
		for (int y = 0; y < world.length; y++) {
			for (int x = 0; x < world[0].length; x++) {
				world[x][y] = new Tile(Type.WATER);
			}
		}
	}
}
