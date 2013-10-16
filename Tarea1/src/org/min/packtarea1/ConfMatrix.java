package org.min.packtarea1;

import java.util.Vector;

public class ConfMatrix {
	
	private Vector<Integer> real;
	private Vector<Integer> predicted;
	
	public ConfMatrix(int pC) {
		real = new Vector<Integer>(pC);
		predicted = new Vector<Integer>(pC);
	}
}
