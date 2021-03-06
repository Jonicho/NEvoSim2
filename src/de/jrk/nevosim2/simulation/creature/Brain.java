package de.jrk.nevosim2.simulation.creature;

import java.io.Serializable;

import de.jrk.nevosim2.neuralnetwork.NeuralNetwork;
import de.jrk.nevosim2.util.Util;
import de.jrk.nevosim2.util.Vec2d;

public class Brain implements Serializable {
	private static final long serialVersionUID = 3824508521991639653L;
	private final int inputAmount = 12;
	private final int outputAmount = 3;
	private final NeuralNetwork neuralNetwork = new NeuralNetwork(new int[] { inputAmount, 12, outputAmount });
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
	
	public void setEnergy(double energy) {
		inputs[9] = energy;
	}
	
	public void setEnvironmentTiles(double[] foods) {
		if (foods.length != 9) {
			throw new IllegalArgumentException("Length must be 9");
		}
		
		for (int i = 0; i < foods.length; i++) {
			inputs[i] = foods[i];
		}
	}
	
	public void setDirectionVector(Vec2d vector) {
		inputs[10] = vector.getX();
		inputs[11] = vector.getY();
	}
	
	public double getSpeed() {
		return outputs[0];
	}
	
	public double getRotation() {
		return outputs[1];
	}
	
	public boolean getWantsEat() {
		return inputs[2] > 0;
	}
}
