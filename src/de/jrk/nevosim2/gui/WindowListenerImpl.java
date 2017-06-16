package de.jrk.nevosim2.gui;

import java.awt.Component;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

public class WindowListenerImpl implements java.awt.event.WindowListener {

	private Component parentComponent;

	public WindowListenerImpl(Component parentComponent) {
		this.parentComponent = parentComponent;
	}

	@Override
	public void windowOpened(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) {
		int option = JOptionPane.showConfirmDialog(parentComponent, "Do you really want to exit?", "Exit?",
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
