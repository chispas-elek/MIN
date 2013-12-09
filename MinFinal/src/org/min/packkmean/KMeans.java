package org.min.packkmean;

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
		//Escogemos el modelo dependiento si se han especificado o no ciclos.
		
	}
}
