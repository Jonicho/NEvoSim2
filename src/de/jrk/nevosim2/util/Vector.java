package de.jrk.nevosim2.util;

import java.io.Serializable;

public class Vector implements Serializable {
	private static final long serialVersionUID = 9161810857195109198L;
	double x;
	double y;

	public Vector() {
		this(0, 0);
	}

	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void add(Vector vector) {
		x += vector.x;
		y += vector.y;
	}

	public double getDistance(Vector vector) {
		return Math.sqrt(Math.pow(x - vector.x, 2) + Math.pow(y - vector.y, 2));
	}

	@Override
	public String toString() {
		return x + "; " + y;
	}
}
