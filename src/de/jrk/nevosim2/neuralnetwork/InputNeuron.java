package de.jrk.nevosim2.neuralnetwork;

import java.io.Serializable;

public class InputNeuron extends Neuron implements Serializable {
	private static final long serialVersionUID = -2891567761325650071L;

	@Override
	public double getValue() {
		return value = input;
	}

	@Override
	public void setInput(double input) {
		super.input = input;
	}
}
