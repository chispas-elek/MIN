package org.min.packtarea1;

import java.util.Iterator;
import java.util.Vector;

public class Comparation {
	
	private Vector<String> predicted, real;
	
	public Comparation(Vector<String> pPredicted, Vector<String> pReal) {
		this.predicted = pPredicted;
		this.real = pReal;
	}
	
	public ConfMatrix operate() {
		int pC = this.calculatePC();
		ConfMatrix cm = new ConfMatrix(pC);
		Iterator<String> itP = this.predicted.iterator();
		Iterator<String> itR = this.real.iterator();
		String sReal, sPred;
		while(itP.hasNext() && itR.hasNext()) {
			sPred = itP.next();
			sReal = itR.next();
			cm.insert(sPred, sReal);
		}
		return cm;
	}
	
	private int calculatePC() {
		Vector<String> acumulate = new Vector<String>();
		Iterator<String> it = this.real.iterator();
		String str;
		while(it.hasNext()) {
			str = it.next();
		}
		return acumulate.size();
	}
}
