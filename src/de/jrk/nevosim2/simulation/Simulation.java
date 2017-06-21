package de.jrk.nevosim2.simulation;

import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import de.jrk.nevosim2.simulation.world.World;

public class Simulation implements Runnable {
	
	public World world;
	public static boolean run = false;
	public static boolean fast = false;
	
	public Simulation(boolean load) {
		if (!load) world = new World();
		else load();
	}

	private void load() {
		try {
			File file = new File("C:/Users/jonas/Desktop/test.file");
			FileInputStream fis = new FileInputStream(file);
			GZIPInputStream zis = new GZIPInputStream(fis);
			ObjectInputStream ois = new ObjectInputStream(zis);
			world = (World) ois.readObject();
			ois.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void save() {
		try {
			File file = new File("C:/Users/jonas/Desktop/test.file");
			FileOutputStream fos = new FileOutputStream(file);
			GZIPOutputStream zos = new GZIPOutputStream(fos);
			ObjectOutputStream oos = new ObjectOutputStream(zos);
			oos.writeObject(world);
			oos.flush();
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			if (run) {
				world.update();
				if (!fast) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} 
				}
			} else {
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
