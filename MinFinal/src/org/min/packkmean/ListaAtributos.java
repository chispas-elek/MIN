package org.min.packkmean;

import java.util.Vector;

public class ListaAtributos {
	
	private Vector<Double> lAtributos;
	
	
	public ListaAtributos() {
		lAtributos = new Vector<Double>();
	}
	
	public void anadir(double pString) {
		lAtributos.add(pString);
	}
	
	public double atributo(int i) {
		return lAtributos.elementAt(i);
	}
	
	public int size() {
		return this.lAtributos.size();
	}

}
