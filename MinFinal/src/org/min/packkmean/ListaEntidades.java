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
	
	public Entidad buscarSiExiste(Entidad pEntidad) {
		Iterator<Entidad> it = this.getIterador();
		Entidad ent1 = null;
		boolean found = false;
		while(it.hasNext() && !found) {
			ent1 = it.next();
			if(ent1 == pEntidad) {
				found = true;
			}
		}
		if(!found) {
			ent1 = null;
		}
		return ent1;
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
		Random rnd = new Random();
		int i;
		int j = 0;
		while(j < k) {
			i = rnd.nextInt(this.size());
			if(pLista.buscarSiExiste(this.entidad(i)) == null) {
				pLista.anadir(this.entidad(i));
				j++;
			}
		}
		return pLista;
	}
	
	public Entidad recalcularCentroide() {
		Entidad nuevo = null;
		Entidad ent = null;
		double adder = 0;
		double media = 0;
		if(this.lEntidades.size() > 0) {
			nuevo = new Entidad();
			int i = this.lEntidades.elementAt(0).size();
			for(int j = 0; j < i; j++) {
				Iterator<Entidad> it = this.getIterador();
				while(it.hasNext()) {
					ent = it.next();
					adder = adder + ent.atributo(j);	
				}
				System.out.println("Vale "+this.lEntidades.size());
				media = adder / this.lEntidades.size();
				nuevo.anadir(media);
				adder = 0;
				media = 0;
			}	
		}
		return nuevo;
	}
	
	public Iterator<Entidad> getIterador() {
		return this.lEntidades.iterator();
	}
	
	public Vector<Integer> clusters() {
		Vector<Integer> result = new Vector<Integer>();
		Iterator<Entidad> it = this.getIterador();
		Entidad ent = null;
		while(it.hasNext()) {
			ent = it.next();
			result.add(ent.cluster());
		}
		return result;
	}
	
}
