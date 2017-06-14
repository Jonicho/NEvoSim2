package de.jrk.nevosim2.neuralnetwork;

public class InputNeuron extends Neuron {

	@Override
	public double getValue() {
		return value = input;
	}

	@Override
	public void setInput(double input) {
		super.input = input;
	}
}
