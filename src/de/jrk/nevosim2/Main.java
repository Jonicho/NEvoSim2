package de.jrk.nevosim2;

import java.util.ArrayList;

import de.jrk.nevosim2.gui.Gui;
import de.jrk.nevosim2.simulation.Simulation;

public class Main {

	private static boolean gui = false;
	public static Simulation simulation = new Simulation();
	private static ArrayList<Thread> windowThreads = new ArrayList<>();

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
			addWindow();
		}
	}
	
	public static void addWindow() {
		Thread guiThread = new Thread(new Gui(), "NEvoSim2 gui thread " + windowThreads.size());
		guiThread.start();
	}

	public static boolean isGui() {
		return gui;
	}
}
