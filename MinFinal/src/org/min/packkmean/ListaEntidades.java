package org.min.packkmean;

import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

public class ListaEntidades {

	private Vector<Entidad> lEntidades;
	
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
	
	public ListaEntidades buscarPorCluster(int i) {
		ListaEntidades le = new ListaEntidades();
		Entidad e = null;
		Iterator<Entidad> it = this.lEntidades.iterator();
		while(it.hasNext()) {
			e = it.next();
			if(e.esDeCluster(i)) {
				le.anadir(e);
			}
		}
		return le;
	}
	
	public ListaEntidades randomSelect(int k) {
		ListaEntidades pLista = new ListaEntidades();
		Random rnd = new Random(this.size());
		int i;
		for(int j = 0; j < k; j++) {
			i = rnd.nextInt();
			pLista.anadir(this.entidad(i));
		}
		return pLista;
	}
	
	public Entidad recalcularCentroide() {
		Entidad nuevo = new Entidad();
		Iterator<Entidad> it = this.getIterador();
		Entidad ent = null;
		double adder = 0;
		int i = this.lEntidades.elementAt(0).size();
		for(int j = 0; j < i; j++) {
			while(it.hasNext()) {
				ent = it.next();
				adder = adder + ent.atributo(j);
			}
			it = this.getIterador();
			nuevo.anadir(adder);
			adder = 0;
		}	
		return nuevo;
	}
	
	public Iterator<Entidad> getIterador() {
		return this.lEntidades.iterator();
	}
	
	public int[] clusters() {
		int[] result = new int[this.size()];
		Iterator<Entidad> it = this.getIterador();
		Entidad ent = null;
		int i = 0;
		while(it.hasNext()) {
			ent = it.next();
			result[i] = ent.cluster();
			i++;
		}
		return result;
	}
	
}
