package de.jrk.nevosim2.simulation.creature;

import de.jrk.nevosim2.neuralnetwork.NeuralNetwork;
import de.jrk.nevosim2.util.Util;

public class Brain {
	private final int inputAmount = 1;
	private final int outputAmount = 2;
	private final NeuralNetwork neuralNetwork = new NeuralNetwork(new int[] { inputAmount, 2, outputAmount });
	private final double[] inputs = new double[inputAmount];
	private final double[] outputs = new double[outputAmount];
	
	public Brain() {
		for (int i = 0; i < inputs.length; i++) {
			inputs[i] = 0;
		}
		
		for (int i = 0; i < outputs.length; i++) {
			outputs[i] = 0;
		}
	}
	
	public void calculate() {
		Util.storeIn(neuralNetwork.calculate(inputs), outputs);
	}
	
	public void setEnergy(double food) {
		inputs[0] = food;
	}
	
	public double getSpeed() {
		return outputs[0];
	}
	
	public double getRotation() {
		return outputs[1];
	}
}
