package de.jrk.nevosim2.gui;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

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
		{
			JButton buttonSave = new JButton("Save");
			buttonSave.addActionListener(e -> {
				Main.simulation.save();
			});
			menuBar.add(buttonSave);
		}
		frame.setJMenuBar(menuBar);
		
		frame.addWindowListener(new WindowListenerImpl());
		frame.addKeyListener(screen.new KeyHandler());
		
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
	
	private class WindowListenerImpl implements WindowListener {

		@Override
		public void windowOpened(WindowEvent e) {

		}

		@Override
		public void windowClosing(WindowEvent e) {
			int option = JOptionPane.showConfirmDialog(frame, "Do you really want to exit?", "Exit?",
					JOptionPane.YES_NO_CANCEL_OPTION);
			if (option == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}

		@Override
		public void windowClosed(WindowEvent e) {

		}

		@Override
		public void windowIconified(WindowEvent e) {

		}

		@Override
		public void windowDeiconified(WindowEvent e) {

		}

		@Override
		public void windowActivated(WindowEvent e) {

		}

		@Override
		public void windowDeactivated(WindowEvent e) {

		}
	}
}
