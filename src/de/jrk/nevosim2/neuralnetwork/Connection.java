package de.jrk.nevosim2.neuralnetwork;

import de.jrk.nevosim2.util.Util;

public class Connection {
	private double weight;
	private Neuron neuron;
	
	public Connection(Neuron neuron) {
		this.neuron = neuron;
		this.weight = getRandomWeight();
	}
	
	public Connection(Neuron neuron, double weight) {
		this.neuron = neuron;
		this.weight = weight;
	}
	
	public double getValue() {
		return neuron.getValue() * weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public void mutate() {
		weight += (Util.random.nextBoolean() ? 1 : -1) * weight * Math.random() * 0.1;
	}
	
	private static double getRandomWeight() {
		return Math.random() * 2 - 1;
	}
}
