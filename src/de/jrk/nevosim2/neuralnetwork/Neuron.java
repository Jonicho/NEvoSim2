package de.jrk.nevosim2.neuralnetwork;

public abstract class Neuron {
	protected double value;
	protected double input;
	
	public abstract double getValue();
	public abstract void setInput(double input);
}
