package com.jwm.routercontroller.signal;

/**
 * Signal 
 */	
public class Signal {

	private SignalValue val;
	public Signal(SignalValue val) {
		this.val = val;
	}

	public SignalValue getSignalValue() {
		return val;
	}

}
