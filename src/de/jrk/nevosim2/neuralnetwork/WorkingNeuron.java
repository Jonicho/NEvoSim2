package de.jrk.nevosim2.neuralnetwork;

import java.util.ArrayList;

import de.jrk.nevosim2.neuralnetwork.activationfunction.ActivationFunction;

public class WorkingNeuron extends Neuron {
	
	protected ArrayList<Connection> connections = new ArrayList<>();
	private boolean valid = false;
	private ActivationFunction activationFunction = ActivationFunction.softsign;
	
	public WorkingNeuron() {
		InputNeuron bias = new InputNeuron();
		bias.setInput(1);
		connections.add(new Connection(bias));
	}
	
	public void addConnection(Neuron neuron) {
		connections.add(new Connection(neuron));
	}

	@Override
	public double getValue() {
		if (!valid) {
			calculateValue();
		}
		return value;
	}
	
	private void calculateValue() {
		input = 0;
		for (Connection connection : connections) {
			input += connection.getValue();
		}
		value = activationFunction.function(input);
		valid = true;
	}
	
	public void invalidate() {
		valid = false;
		input = 0;
		value = 0;
	}

	@Override
	public void setInput(double input) {
		super.input = input;
	}
	
	public void mutate() {
		for (Connection connection : connections) {
			connection.mutate();
		}
	}
}
