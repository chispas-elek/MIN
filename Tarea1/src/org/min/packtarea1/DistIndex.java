package org.min.packtarea1;

public class DistIndex {
	
	private int position, index;
	private double value;
	
	public DistIndex(int pPos, double pVal) {
		this.position = pPos;
		this.value = pVal;
		this.index = 0;
	}
	
	public double getValue() {
		return this.value;
	}
	
	public int getPosition() {
		return this.position;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public void setIndex(int pIndex) {
		this.index = pIndex;
	}

}
