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
		Vect vc = new Vect();
		Iterator<String> it = this.real.iterator();
		int i = 0;
		String str;
		while(it.hasNext()) {
			str = it.next();
			if(!vc.exists(str)) {
				i++;
				vc.add(str);
			}
		}
		return i;
	}
}
