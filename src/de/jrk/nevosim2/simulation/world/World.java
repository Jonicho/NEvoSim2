package de.jrk.nevosim2.simulation.world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

import de.jrk.nevosim2.simulation.creature.Creature;
import de.jrk.nevosim2.simulation.world.Tile.Type;
import de.jrk.nevosim2.util.Vector;

public class World implements Serializable {
	private static final long serialVersionUID = -1441875949063228959L;
	private final Tile[][] world = new Tile[100][100];
	private ArrayList<Creature> creatures = new ArrayList<>();
	private final double GROW_VALUE = 0.001;

	public World() {
		generateRandomWorld();

		for (int i = 0; i < 100; i++) {
			creatures.add(new Creature(new Vector(Math.random(), Math.random())));
		}
	}

	public void update() {
		for (int y = 0; y < world.length; y++) {
			for (int x = 0; x < world[0].length; x++) {
				if (world[x][y].getType() == Type.WATER || world[x][y].getFood() == 1.0) {
					continue;
				}

				int growCounter = 0;

				if (isFruitful(x + 1, y)) {
					growCounter++;
				}

				if (isFruitful(x - 1, y)) {
					growCounter++;
				}

				if (isFruitful(x, y + 1)) {
					growCounter++;
				}

				if (isFruitful(x, y - 1)) {
					growCounter++;
				}
				
//				if (growCounter > 0) growCounter = 1;
				
				world[x][y].grow(GROW_VALUE * growCounter);
			}
		}

		ArrayList<Creature> toBeRemoved = new ArrayList<>();

		for (Creature creature : creatures) {
			creature.update();
			if (!creature.isAlive()) {
				toBeRemoved.add(creature);
			}
		}

		for (Creature creature : toBeRemoved) {
			creatures.remove(creature);
		}
	}

	private boolean isFruitful(int x, int y) {
		return x < 0 || y < 0 || x >= world.length || y >= world[0].length || world[x][y].getType() == Type.WATER
				|| (world[x][y].getFood() > 0.9);
	}

	public void draw(Graphics g, int screenSize) {
		BufferedImage img = new BufferedImage(world.length, world[0].length, BufferedImage.TYPE_INT_RGB);
		Graphics imgG = img.getGraphics();

		for (int y = 0; y < world.length; y++) {
			for (int x = 0; x < world[0].length; x++) {
				switch (world[x][y].getType()) {
				case LAND:
					imgG.setColor(new Color((float) (1 - (world[x][y].getFood())), 1f, 0f));
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

	// private void generateEmptyWorld() {
	// for (int y = 0; y < world.length; y++) {
	// for (int x = 0; x < world[0].length; x++) {
	// world[x][y] = new Tile(Type.WATER);
	// }
	// }
	// }

	private void generateRandomWorld() {
		ValueNoise valueNoise = new ValueNoise();
		for (int y = 0; y < world.length; y++) {
			for (int x = 0; x < world[0].length; x++) {
				world[x][y] = new Tile(valueNoise.get(x, y) > 0 ? Type.WATER : Type.LAND);
			}
		}
	}
}
