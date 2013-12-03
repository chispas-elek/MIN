package org.min.packkmean;

import java.util.Vector;

public class ListaAtributos {
	
	private Vector<String> lAtributos;
	
	
	public ListaAtributos() {
		lAtributos = new Vector<String>();
	}
	
	public void anadir(String pString) {
		lAtributos.add(pString);
	}
	
	public String atributo(int i) {
		return lAtributos.elementAt(i);
	}
	
	public int size() {
		return this.lAtributos.size();
	}

}
