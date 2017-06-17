package de.jrk.nevosim2.neuralnetwork;

import java.io.Serializable;

public abstract class Neuron implements Serializable {
	private static final long serialVersionUID = -5127959732489150473L;
	protected double value;
	protected double input;
	
	public abstract double getValue();
	public abstract void setInput(double input);
}
