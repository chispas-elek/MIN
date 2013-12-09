package org.min.packkmean;

import java.util.Iterator;

public class KMeans {
	
	private Minkowsky pMinkowsky;
	private ListaEntidades pListaEntidades;
	private int ciclos = 0;
	
	/**
	 * Calcula la pertenencia y recalcula el centroide de cada cluster
	 * @param pListaEnt
	 * @param m
	 * @param pRandom
	 */
	public KMeans(ListaEntidades pListaEnt, String m, ListaEntidades pRandom) {
		this.pListaEntidades = pListaEnt;
		this.pMinkowsky = new Minkowsky(Integer.parseInt(m), pRandom);
	}
	
	public KMeans(ListaEntidades pListaEnt, String m, ListaEntidades pRandom, int pCiclos) {
		this.pListaEntidades = pListaEnt;
		this.pMinkowsky = new Minkowsky(Integer.parseInt(m), pRandom);
		this.ciclos = pCiclos;
	}
	
	/**
	 * Se encarga de calcular iterativamente la pertenencia y recalcula el centroide de cada cluster.
	 */
	
	public void recycle() {
		Entidad ent1;
		if (this.getCiclos() == 0) {
			//Repetición por umbral. Especificar valor de umbral.
			double umbral = 0.35;
			
		}else {
			//Repetición por n veces
			for(int i = 0;i<this.getCiclos();i++) {
				Iterator<Entidad> it = this.pListaEntidades.getIterador();
				while(it.hasNext()) {
					ent1 = it.next();
				}
			}
		}
	}
	
	private int getCiclos() {
		return this.ciclos;
	}
	
	private Minkowsky getMinkowsky() {
		return this.pMinkowsky;
	}
	
	private ListaEntidades getListaEntidades() {
		return this.pListaEntidades;
	}
}
