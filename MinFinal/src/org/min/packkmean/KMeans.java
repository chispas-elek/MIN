package org.min.packkmean;

public class KMeans {
	//Calcula la pertenencia y recalcula el centroide de cada cluster
	private Minkowsky pMinkowsky;
	private ListaEntidades pListaEntidades;
	private int ciclos = 0;
	
	public KMeans(ListaEntidades pListaEnt, String m, ListaEntidades pRandom) {
		this.pListaEntidades = pListaEnt;
		this.pMinkowsky = new Minkowsky(Integer.parseInt(m), pRandom);
	}
	
	public KMeans(ListaEntidades pListaEnt, String m, ListaEntidades pRandom, int pCiclos) {
		this.pListaEntidades = pListaEnt;
		this.pMinkowsky = new Minkowsky(Integer.parseInt(m), pRandom);
		this.ciclos = pCiclos;
	}
	
	public void recycle() {
		
	}
}
