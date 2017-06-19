package de.jrk.nevosim2;

import java.util.Random;

import de.jrk.nevosim2.gui.Gui;
import de.jrk.nevosim2.simulation.Simulation;

public class Main {
	public static final Random rand = new Random();
	private static boolean gui = false;
	public static Simulation simulation = new Simulation(false);

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("You have to give arguments!");
			System.exit(0);
		}

		gui = Boolean.parseBoolean(args[0]);

		Thread simThread = new Thread(simulation);
		simThread.setName("NEvoSim2 simulation thread");
		simThread.start();

		if (gui) {
			Thread guiThread = new Thread(new Gui(), "NEvoSim2 gui thread");
			guiThread.start();
		}
	}

	public static boolean isGui() {
		return gui;
	}
}
