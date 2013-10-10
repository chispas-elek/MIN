package org.min.packtarea1;

public class DistIndex {
	
	private int position;
	private double value;
	
	public DistIndex(int pPos, double pVal) {
		this.position = pPos;
		this.value = pVal;
	}
	
	public double getValue() {
		return this.value;
	}
	
	public int getPosition() {
		return this.position;
	}

}
