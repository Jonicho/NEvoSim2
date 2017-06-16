package de.jrk.nevosim2.gui;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

import de.jrk.nevosim2.Main;
import de.jrk.nevosim2.simulation.Simulation;

public class Gui implements Runnable {
	private JFrame frame = new JFrame("NEvoSim2");
	public Dimension size = new Dimension(800, 450);
	private Screen screen = new Screen();

	@Override
	public void run() {
		frame.pack();
		frame.setSize(1280, 720);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
		frame.add(screen);
		
		JMenuBar menuBar = new JMenuBar();
		{
			JButton buttonNewWindow = new JButton("New Window");
			buttonNewWindow.addActionListener(e -> {
				Main.addWindow();
			});
			menuBar.add(buttonNewWindow);
		}
		{
			JButton buttonPlay = new JButton("Play");
			buttonPlay.addActionListener(e -> {
				Simulation.run = !Simulation.run;
			});
			menuBar.add(buttonPlay);
		}
		{
			JButton buttonFast = new JButton("Fast");
			buttonFast.addActionListener(e -> {
				Simulation.fast = !Simulation.fast;
			});
			menuBar.add(buttonFast);
		}
		frame.setJMenuBar(menuBar);
		
		frame.addWindowListener(new WindowListenerImpl(frame));
		
		frame.setVisible(true);

		guiLoop();
	}
	
	private void guiLoop() {
		while (true) {
			screen.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
