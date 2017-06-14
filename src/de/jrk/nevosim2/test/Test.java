package de.jrk.nevosim2.test;

import de.jrk.nevosim2.neuralnetwork.NeuralNetwork;

public class Test {

	public static void main(String[] args) {
		NeuralNetwork n = new NeuralNetwork(new int[] { 2, 2, 2 });
		NeuralNetwork nClone = n.getMutatedClone();
		
		double[] testInputs = new double[] { 1, 1 };
		printArray(testInputs);
		printArray(n.calculate(testInputs));
		printArray(nClone.calculate(testInputs));
		
		double[] testInputs2 = new double[] { -1, -1 };
		printArray(testInputs2);
		printArray(n.calculate(testInputs2));
		printArray(nClone.calculate(testInputs2));
		
	}
	
	public static void printArray(double[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "; ");
		}
		System.out.println();
	}

}
