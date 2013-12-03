package org.min.packkmean;

public class Entidad {
	
	private ListaAtributos atributos;
	int[] mPertenencia;
	
	public Entidad(String pString, int k) {
		atributos = new ListaAtributos();
		this.buscarAtributos(pString);
		mPertenencia = new int[k];
	}
	
	private void buscarAtributos(String pString) {
		int j = 0;
		for(int i = 0; i < pString.length(); i++) {
			if(pString.charAt(i) == ',') {
				atributos.anadir(pString.substring(j, i-1));
				j = i+1;
			}
		}
	}
	
	public String atributo(int i){
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

}
