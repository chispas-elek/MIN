package org.min.packkmean;

import java.util.Random;
import java.util.Vector;

public class Minkowsky {
private int m;
private ListaEntidades centroides;
	
	/**
	 * The purpose of this class is to clasify number of different instances using
	 * Minkownsky algorithm.
	 * 
	 * @param pK the value of the neighbours.
	 * @param pN the value of the number of parameters in the data set.
	 * @param pM the value to use Minkownsky algorithm.
	 */
	public Minkowsky(int pM, ListaEntidades pLista) {
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
		double distance = 0;
		double result = 0;
		for(int i=0;i<centroides.size();i++) {
			double acum = 0;
			for(int l=0;l < pEntidad.size();l++) {
				//Aqui se acumula las sumas
					acum = acum + Math.pow(Math.abs(pEntidad.atributo(l) - this.centroides.entidad(i).atributo(l)),this.getM());
				}
				//Aqui se hace la raiz. y se guarda el resultado DE FORMA ORDENADA.
				float root = this.getM();
				result = Math.pow(acum, 1.0/root);
				if(distance == 0) {
					distance = result;
				}
				if(distance >= result) {
					distance = result;
					pEntidad.cluster(i);
					
				}
		}
	}

	private int getM() {
		return m;
	}
	
	private void setCentroides(ListaEntidades pListaEntidades) {
		this.centroides = pListaEntidades;
	}
	
	public void actualizarCentroides(ListaEntidades pListaEntidades) {
		this.setCentroides(pListaEntidades);
	}

}
