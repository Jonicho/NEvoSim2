package de.jrk.nevosim2.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JLabel;

import de.jrk.nevosim2.Main;

public class Screen extends JLabel {
	private static final long serialVersionUID = -3223288134920254727L;
	private MouseHandler mouseHandler = new MouseHandler();
	private double zoom = 1;
	
	public Screen() {
		addMouseListener(mouseHandler);
		addMouseMotionListener(mouseHandler);
		addMouseWheelListener(mouseHandler);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Dimension size = getSize();
		int screenSize = (int) (size.getHeight() > size.getWidth() ? size.getWidth() : size.getHeight());
		screenSize *= zoom;
		Main.simulation.draw(g, screenSize);
	}
	
	private class MouseHandler implements MouseListener, MouseMotionListener, MouseWheelListener {
		
		public int mousePosX;
		public int mousePosY;

		@Override
		public void mouseClicked(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

		@Override
		public void mouseDragged(MouseEvent e) {
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			mousePosX = e.getX();
			mousePosY = e.getY();
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			if (e.getWheelRotation() > 0 && zoom > 0.5) {
				zoom /= 2;
			} else if (e.getWheelRotation() < 0 && zoom < 8.0) {
				zoom *= 2;
			}
		}

	}
}
