package de.jrk.nevosim2.neuralnetwork;

import java.io.Serializable;
import java.util.ArrayList;

public class NeuralNetwork implements Serializable {
	private static final long serialVersionUID = 240125117250288172L;
	private ArrayList<InputNeuron> inputNeurons = new ArrayList<>();
	private ArrayList<ArrayList<WorkingNeuron>> workingNeurons = new ArrayList<>();
	private ArrayList<WorkingNeuron> outputNeurons = new ArrayList<>();
	
	public NeuralNetwork(int[] neurons) {
		
		for (int i = 0; i < neurons[0]; i++) {
			inputNeurons.add(new InputNeuron());
		}
		
		for (int i = 1; i < neurons.length - 1; i++) {
			workingNeurons.add(new ArrayList<>());
			for (int j = 0; j < neurons[i]; j++) {
				workingNeurons.get(i-1).add(new WorkingNeuron());
			}
		}
		
		for (int i = 0; i < neurons[neurons.length - 1]; i++) {
			outputNeurons.add(new WorkingNeuron());
		}
		
		connectAll();
	}
	
	private void connectAll() {
		for (WorkingNeuron outputNeuron : outputNeurons) {
			for (WorkingNeuron workingNeuron : workingNeurons.get(workingNeurons.size() - 1)) {
				outputNeuron.addConnection(workingNeuron);
			}
		}
		
		for (int i = workingNeurons.size() - 1; i > 0; i--) {
			for (WorkingNeuron workingNeuron : workingNeurons.get(i)) {
				for (WorkingNeuron workingNeuron2 : workingNeurons.get(i - 1)) {
					workingNeuron.addConnection(workingNeuron2);
				}
			}
		}
		
		for (WorkingNeuron workingNeuron : workingNeurons.get(0)) {
			for (InputNeuron inputNeuron : inputNeurons) {
				workingNeuron.addConnection(inputNeuron);
			}
		}
	}
	
	private void invalidateAll() {
		for (ArrayList<WorkingNeuron> arrayList : workingNeurons) {
			for (WorkingNeuron workingNeuron : arrayList) {
				workingNeuron.invalidate();
			}
		}
		
		for (WorkingNeuron workingNeuron : outputNeurons) {
			workingNeuron.invalidate();
		}
	}
	
	public double[] calculate(double[] inputs) {
		double[] outputs = new double[outputNeurons.size()];
		
		if (inputs.length != inputNeurons.size()) {
			throw new IllegalArgumentException("Inputs amount has to be equal to inputneurons amount!");
		}
		
		for (int i = 0; i < inputs.length; i++) {
			inputNeurons.get(i).setInput(inputs[i]);
		}
		
		invalidateAll();
		
		for (int i = 0; i < outputs.length; i++) {
			outputs[i] = outputNeurons.get(i).getValue();
		}
		return outputs;
	}
	
	public void mutate() {
		for (ArrayList<WorkingNeuron> arrayList : workingNeurons) {
			for (WorkingNeuron workingNeuron : arrayList) {
				workingNeuron.mutate();
			}
		}
		
		for (WorkingNeuron workingNeuron : outputNeurons) {
			workingNeuron.mutate();
		}
	}
	
	public NeuralNetwork getClone() {
		NeuralNetwork networkClone = null;
		try {
			networkClone = (NeuralNetwork) this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
		return networkClone;
	}
	
	public NeuralNetwork getMutatedClone() {
		NeuralNetwork networkClone = getClone();
		networkClone.mutate();
		return networkClone;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		int [] neurons = new int[workingNeurons.size() + 2];
		neurons[0] = inputNeurons.size();
		for (int i = 0; i < neurons.length - 2; i++) {
			neurons[i + 1] = workingNeurons.get(i).size();
		}
		neurons[neurons.length - 1] = outputNeurons.size();
		
		NeuralNetwork clone = new NeuralNetwork(neurons);
		
		for (int i = 0; i < outputNeurons.size(); i++) {
			for (int j = 0; j < outputNeurons.get(i).connections.size(); j++) {
				clone.outputNeurons.get(i).connections.get(j).setWeight(
						outputNeurons.get(i).connections.get(j).getWeight()
						);
			}
		}
		
		for (int w = 0; w < workingNeurons.size(); w++) {
			for (int i = 0; i < workingNeurons.get(w).size(); i++) {
				for (int j = 0; j < workingNeurons.get(w).get(i).connections.size(); j++) {
					clone.workingNeurons.get(w).get(i).connections.get(j).setWeight(
							workingNeurons.get(w).get(i).connections.get(j).getWeight()
							);
				}
			}
		}
		return clone;
	}
}
