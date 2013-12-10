package org.min.packkmean;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

public class SaveData {

	/**
	 * El objetivo primordial de ésta clase es guardar los datos que deseemos en el disco duro para observar resultados.
	 */
	public SaveData() {
		
	}
	
	/**
	 * Se encarga de guardar los resultados de un evaluador en el disco duro.
	 * @param pNombreFichero El nombre del fichero que deseemos darle
	 * @param pTest 
	 * @param pPrediction
	 */
	
	public static void escribirResultadosEvaluador(String pNombreFichero, ListaEntidades result){
		File fichero = new File(pNombreFichero);
		if(!fichero.exists()) {
			//No existen duplicados
			try{
				BufferedWriter bw = new BufferedWriter(new FileWriter(pNombreFichero));
				//obtenemos los resultados de la lista de entidades
				Vector<Integer> predicctions = result.clusters();
				Iterator<Integer> it = predicctions.iterator();
				while(it.hasNext()){
					// Escribir los resultados en el fichero.
					int clasificado = it.next();
					bw.write("Clase estimada " + clasificado);
					bw.newLine();
				}
				//Cerramos y damos las gracias.
				bw.close();
				System.out.println("Los resultados han sido guardados con el nombre de "+pNombreFichero);
				System.out.println("Gracias por usar éste programa, tenga un buen día :D~");
			}catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			//Damos la posibilidad de que el usuario borre el fichero y relance el programa.
			System.out.println("El fichero de Resultados ya existe. Por favor elimina primero dicho fichero");
			System.out.println("Por favor, borra el fichero y pulse enter");
			Scanner sc = new Scanner(System.in);
			sc.next();
			sc.close();
			SaveData.escribirResultadosEvaluador(pNombreFichero, result);
		}
	}
	
}