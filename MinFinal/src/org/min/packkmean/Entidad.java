package org.min.packkmean;

public class Entidad {
	
	private ListaAtributos atributos;
	
	public Entidad(String pString) {
		atributos = new ListaAtributos();
		this.buscarAtributos(pString);
	}
	
	private void buscarAtributos(String pString) {
		int j = 0;
		for(int i = 0; i < pString.length(); i++) {
			if(pString.charAt(i) == ',') {
				atributos.anadir(pString.substring(j, i-1));
				j = i;
			}
		}
	}
	
	public String atributo(int i){
		return atributos.atributo(i);
	}

}
