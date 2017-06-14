package de.jrk.nevosim2.gui;

import javax.swing.JFrame;

public class Gui implements Runnable {
	private JFrame frame = new JFrame("NEvoSim2");

	@Override
	public void run() {
		frame.setSize(200, 200);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		frame.setVisible(true);
		
		frame.addWindowListener(new WindowListener());
	}
}
