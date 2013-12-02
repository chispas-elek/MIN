package org.min.packkmean;

import java.util.Vector;

public class ListaEntidades {

	Vector<Entidad> lEntidades;
	
	public ListaEntidades() {
		lEntidades = new Vector<Entidad>();
	}
	
	public void anadir(Entidad pEntidad) {
		this.lEntidades.add(pEntidad);
	}
	
	public Entidad entidad(int i) {
		return lEntidades.elementAt(i);
	}
	
	public int size() {
		return lEntidades.size();
	}
}
