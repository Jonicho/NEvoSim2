package de.jrk.nevosim2;

import de.jrk.nevosim2.gui.Gui;

public class Main {
	
	private static boolean gui = false;

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("You have to give arguments!");
			System.exit(0);
		}
		
		gui = Boolean.parseBoolean(args[0]);
		
		Thread simThread = new Thread(); //TODO
		simThread.setName("NEvoSim2 simulation thread");
		simThread.start();
		
		if (gui) {
			Thread guiThread = new Thread(new Gui());
			guiThread.setName("NEvoSim2 gui thread");
			guiThread.start();
		}
	}
	
	public static boolean isGui() {
		return gui;
	}
}
