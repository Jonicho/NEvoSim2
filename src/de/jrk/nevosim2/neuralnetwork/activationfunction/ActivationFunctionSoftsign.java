package de.jrk.nevosim2.neuralnetwork.activationfunction;

public class ActivationFunctionSoftsign implements ActivationFunction {
	
	protected ActivationFunctionSoftsign() {
	}

	@Override
	public double function(double x) {
		return x / (1 + Math.abs(x));
	}

}
