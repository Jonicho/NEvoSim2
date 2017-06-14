package de.jrk.nevosim2.neuralnetwork.activationfunction;

public abstract interface ActivationFunction {
	public static ActivationFunctionSoftsign softsign = new ActivationFunctionSoftsign();
	
	public abstract double function(double input);
}
