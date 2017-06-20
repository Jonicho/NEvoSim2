package de.jrk.nevosim2.util;

import java.io.Serializable;

public class Vec2i implements Serializable {
	private static final long serialVersionUID = 9161810857195109198L;
	int x;
	int y;

	public Vec2i() {
		this(0, 0);
	}

	public Vec2i(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void add(Vec2i vector) {
		x += vector.x;
		y += vector.y;
	}

	public double getDistance(Vec2i vector) {
		return Math.sqrt(Math.pow(x - vector.x, 2) + Math.pow(y - vector.y, 2));
	}

	@Override
	public String toString() {
		return x + "; " + y;
	}
}
