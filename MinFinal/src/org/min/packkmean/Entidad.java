package org.min.packkmean;

import java.util.Random;
import java.util.Vector;

public class Entidad {
	
	private ListaAtributos atributos;
	private Vector<Integer> mPertenencia;
	
	public Entidad(String pString, int k) {
		atributos = new ListaAtributos();
		this.buscarAtributos(pString);
		mPertenencia = new Vector<Integer>(k);
		this.inicializar(k);
	}
	
	public Entidad() {
		atributos = new ListaAtributos();
	}
	
	public Entidad(int k) {
		atributos = new ListaAtributos();
		mPertenencia = new Vector<Integer>(k);
		this.inicializar(k);
	}
	
	private void buscarAtributos(String pString) {
		int j = 0;
		for(int i = 0; i < pString.length(); i++) {
			if(pString.charAt(i) == ',' || i+1 == pString.length()) {
				try {
					//Corremos un valor.
					if(i+1 == pString.length()) {
						i++;
					}
					String comprobar = pString.substring(j, i);
					atributos.anadir(Double.parseDouble(comprobar));
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
		if(this.mPertenencia.elementAt(i) == 1) {
			is = true;
		}
		return is;
	}
	
	public int size() {
		return this.atributos.size();
	}
	
	public void asignarCluster(int i) {
		this.mPertenencia.setElementAt(1, i);
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
		Random rnd = new Random();
		int r = this.mPertenencia.size();
		int m = 0;
		int k = 0;
		while(k == 0) {
			m = rnd.nextInt(r);
			k = this.mPertenencia.elementAt(m);
		}
		return m;
	}
	
	public void inicializar(int pK) {
		this.mPertenencia = new Vector<Integer>();
		for(int i = 0; i < pK; i++) {
			this.mPertenencia.add(0);
		}
	}
	
	public int numCentroides() {
		return this.mPertenencia.size();
	}
}
