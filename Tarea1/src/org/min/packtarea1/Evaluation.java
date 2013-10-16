package org.min.packtarea1;

public class Evaluation {
	
	private String name;
	private int counter;
	
	
	public Evaluation(String pName) {
		this.name = pName;
		this.counter = 1;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void add() {
		this.counter++;
	}
	
	public int getCounter() {
		return this.counter;
	}

}
