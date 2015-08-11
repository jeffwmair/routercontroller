package com.jwm.routercontroller.signal;

/**
 * Temporary Signal - one that switches back to another state
 */	
public class SignalTemporary extends Signal {

	private SignalValue previousSignalState;
	private long secondsUtilRevert;

	public SignalTemporary(SignalValue newValue, long secondsUtilRevert) {
		super(newValue);
		this.secondsUtilRevert = secondsUtilRevert;

		// calculate the previous value
		SignalValue prevValue = newValue.equals(SignalValue.enable) ? SignalValue.disable : SignalValue.enable;
		this.previousSignalState = prevValue;
	}

	/**
	 * How many seconds till the system should switch back
	 */
	public long getSecondsUntilRevert() {
		return secondsUtilRevert;
	}

	/**
	 * Get the old signal state
	 */
	public SignalValue getPreviousSignalState() {
		return previousSignalState;
	}
}
