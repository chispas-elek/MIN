package org.min.packkmean;

import java.util.Random;
import java.util.Vector;

public class Entidad {
	
	private ListaAtributos atributos;
	int[] mPertenencia;
	
	public Entidad(String pString, int k) {
		atributos = new ListaAtributos();
		this.buscarAtributos(pString);
		mPertenencia = new int[k];
	}
	
	public Entidad() {
		atributos = new ListaAtributos();
	}
	
	public Entidad(int k) {
		atributos = new ListaAtributos();
		mPertenencia = new int[k];
	}
	
	private void buscarAtributos(String pString) {
		int j = 0;
		for(int i = 0; i < pString.length(); i++) {
			if(pString.charAt(i) == ',') {
				try {
					atributos.anadir(Double.parseDouble(pString.substring(j, i-1)));
					j = i+1;
				}catch (NumberFormatException e) {
					System.out.println("Ésto era la clase");
					j = i+1;
				}
			}
		}
	}
	
	public void anadir(double pString) {
		this.atributos.anadir(pString);
	}
	
	public double atributo(int i){
		return atributos.atributo(i);
	}
	
	public boolean esDeCluster(int i) {
		boolean is = false;
		if(this.mPertenencia[i] == 1) {
			is = true;
		}
		return is;
	}
	
	public int size() {
		return this.atributos.size();
	}
	
	public void cluster(int i) {
		this.mPertenencia[i] = 1;
	}
	
	//Distancia euclidea
	public double calcularUmbral(Entidad pEntidad) {
		double result = 0;
		double acum = 0;
		for(int l=0;l < pEntidad.size();l++) {
				//Aqui se acumula las sumas
				acum = acum + Math.pow(Math.abs(pEntidad.atributo(l) - this.atributo(l)), 2);
			}
			float root = 2;
			result = Math.pow(acum, 1.0/root);
		return result;
	}
	
	public int cluster() {
		Vector<Integer> result = new Vector<Integer>();
		for(int i = 0; i < this.mPertenencia.length; i++) {
			if(this.mPertenencia[i] == 1) {
				result.add(this.mPertenencia[i]);
			}
			i++;
		}
		Random rnd = new Random(result.size());
		int m = rnd.nextInt();
		int k = result.elementAt(m);
		return k;
	}

}
