package org.min.packkmean;

import java.util.Iterator;
import java.util.Vector;


public class Minkowsky {
private double m;
private ListaEntidades centroides;
	
	/**
	 * The purpose of this class is to clasify number of different instances using
	 * Minkownsky algorithm.
	 * 
	 * @param pK the value of the neighbours.
	 * @param pN the value of the number of parameters in the data set.
	 * @param pM the value to use Minkownsky algorithm.
	 */
	public Minkowsky(double pM, ListaEntidades pLista) {
		this.m = pM;
		this.centroides = pLista;
	}
	
	/**
	 * Calculates k-NN using Minkowsky algorithm.
	 * 
	 * @param pEntidad The entity to calculate
	 * @return The entity is clasified
	 */
	
	public void calculate(Entidad pEntidad) {
		Vector<Double> distancias = new Vector<Double>();
		for(int i=0;i<centroides.size();i++) {
			double acum = 0;
			for(int l=0;l < pEntidad.size();l++) {
				//Aqui se acumula las sumas
				acum = acum + Math.pow(Math.abs(pEntidad.atributo(l) - this.centroides.entidad(i).atributo(l)),this.getM());
			}
			
			//Aqui se hace la raiz. y se guarda el resultado DE FORMA ORDENADA.
			double root = this.getM();
			double result = Math.pow(acum, 1.0/root);
			distancias.add(result);
		}
		this.asignarElCluster(distancias, pEntidad);
	}
	
	public void calculatePorArea(Entidad pEntidad) {
		int k = this.centroides.size();
		for(int i = 0; i < k; i++) {
			if(pEntidad.atributo(0) < this.centroides.entidad(i).atributo(0)) {
				pEntidad.asignarCluster(i);
			}
			if(pEntidad.atributo(0) == this.centroides.entidad(i).atributo(0)) {
				pEntidad.asignarCluster(i);
				if(this.centroides.entidad(i) != this.centroides.entidad(k-1)) {
					pEntidad.asignarCluster(i+1);
				}
			}
		}
	}
	
	private void asignarElCluster(Vector<Double> pDistancias, Entidad pEntidad) {
		pEntidad.inicializar(pEntidad.numCentroides());
		Iterator<Double> it = pDistancias.iterator();
		double minimo = 0;
		double tester = 0;
		int contador = 0;
		//Calculamos la distancia minima
		while(it.hasNext()) {
			tester = it.next();
			if(minimo == 0 || tester < minimo) {
				minimo = tester;
			}
		}
		//Comprobamos cual(es) de las distancias hay que coger
		it = pDistancias.iterator();
		while(it.hasNext()) {
			tester = it.next();
			if(tester == minimo) {
				pEntidad.asignarCluster(contador);
			}
			contador++;
		}
	}
	
	

	private double getM() {
		return m;
	}
	
	private void setCentroides(ListaEntidades pListaEntidades) {
		this.centroides = pListaEntidades;
	}
	
	public void actualizarCentroides(ListaEntidades pListaEntidades) {
		this.setCentroides(pListaEntidades);
	}
	
	public ListaEntidades centroides() {
		return this.centroides;
	}

}
