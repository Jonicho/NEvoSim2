package de.jrk.nevosim2.gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

import de.jrk.nevosim2.Main;

public class Screen extends JLabel {
	private static final long serialVersionUID = -3223288134920254727L;
	private MouseHandler mouseHandler = new MouseHandler();
	private double zoom = 1;
	private int posX;
	private int posY;
	public boolean draw = true;

	public Screen() {
		super();
		addMouseListener(mouseHandler);
		addMouseMotionListener(mouseHandler);
		addMouseWheelListener(mouseHandler);
	}

	@Override
	protected void paintComponent(Graphics g) {
		int screenSize = (int) (getHeight() > getWidth() ? getWidth() : getHeight());
		screenSize *= zoom;

		BufferedImage screenImage = new BufferedImage(screenSize, screenSize, BufferedImage.TYPE_INT_ARGB);
		Graphics screenImageG = screenImage.getGraphics();

		if (draw && Main.simulation != null)
			Main.simulation.draw(screenImageG, screenSize);

		g.drawImage(screenImage, posX, posY, screenSize, screenSize, this);
	}

	private void moveWorld(int x, int y) {
		posX += x;
		posY += y;
	}

	private void resetView() {
		posX = getWidth() / 2;
		posY = getHeight() / 2;
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
			moveWorld(e.getX() - mousePosX, e.getY() - mousePosY);
			updateMousePos(e);
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			updateMousePos(e);
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			if (e.getWheelRotation() > 0 && zoom > 0.5) {
				zoom /= 1.5;
			} else if (e.getWheelRotation() < 0 && zoom < 3.0) {
				zoom *= 1.5;
			}
			System.out.println(zoom);
		}

		private void updateMousePos(MouseEvent e) {
			mousePosX = e.getX();
			mousePosY = e.getY();
		}
	}

	public class KeyHandler implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
			System.out.println("Key released: " + e.getKeyChar());
			switch (e.getKeyCode()) {
			case KeyEvent.VK_R:
				resetView();
				break;

			default:
				break;
			}
		}

	}
}
