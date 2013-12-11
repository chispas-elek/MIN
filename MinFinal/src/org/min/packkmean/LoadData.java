package org.min.packkmean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LoadData {

	private String path;
	
	/**
	 * El objetivo de ésta clase es cargar los ficheros .arff
	 */
	public LoadData (String pPath) {
		this.path = pPath;
	}
	
	/**
	 * Carga los datos del fichero arff
	 * @return Devuelve un Vector de Strings con las instancias de la clase.
	 */
	
	public ListaEntidades CargarDatos(int pK) {
		
		ListaEntidades lista = new ListaEntidades();
		File fichero = new File(this.getPath());
		if(fichero.exists()) {
			//buscaremos por lineas en "arff" hasta encontrar @data
			Entidad ent1 = null;
			BufferedReader entrada;
			boolean enc = false;
			try {
				entrada = new BufferedReader(new FileReader(this.getPath()));
				String linea;
				while(entrada.ready() && !enc){
					linea = entrada.readLine();
					if(linea.equals("@data")){
						enc=true;
					}      
				}
				//Escribimos las lineas en un array
				if(enc != true) {
					//El fichero no parece ser de tipo .arff
					new Exception();
				}else {
					while(entrada.ready()){
						linea=entrada.readLine();
						ent1 = new Entidad(linea, pK);
						lista.anadir(ent1);
					}
					entrada.close();
				}
			}catch (IOException e) {
				e.printStackTrace();
			}catch (Exception e) {
				System.out.println("Parece que algo no va bien con tu fichero .arff");
			}
		}else {
			System.out.println("El fichero que has establecido no existe");
			lista = null;
		}
		return lista;
	}

	public String getPath() {
		return path;
	}
}