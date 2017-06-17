package de.jrk.nevosim2.neuralnetwork.activationfunction;

import java.io.Serializable;

public class ActivationFunctionSoftsign implements ActivationFunction, Serializable {
	private static final long serialVersionUID = 3194657596804707898L;

	protected ActivationFunctionSoftsign() {
	}

	@Override
	public double function(double x) {
		return x / (1 + Math.abs(x));
	}

}
