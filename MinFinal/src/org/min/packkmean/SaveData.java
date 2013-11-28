package org.min.packkmean;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class SaveData {

	/**
	 * El objetivo primordial de ésta clase es guardar los datos que deseemos en el disco duro para observar resultados.
	 */
	public SaveData() {
		
	}
	
	/**
	 * Se encarga de guardar los resultados en un fichero con extensión *.arff
	 * @param pNombreFichero El nombre que va a tener el fichero.
	 * @param pData Los datos que van a ser guardados.
	 */
	public static void guardarResultado(String pNombreFichero, Vector pData) {
		File fichero = new File(pNombreFichero);
		if(!fichero.exists()) {
			//No existe duplicado
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(pNombreFichero));
				//Escribimos el relation
				bw.write("@relation "+pData.relationName());
				bw.newLine();
				//Aquí imprimimos cada atributo
				for(int k=0;k<pData.numAttributes();k++) {
					bw.newLine();
					bw.write(""+pData.attribute(k));
				}
				
				//Aquí escribiremos las instancias de la clase
				bw.newLine();
				bw.newLine();
				bw.write("@data");
				for(int i=0;i<pData.numInstances();i++) {
					bw.newLine();
					bw.write(""+pData.instance(i));
				}
				bw.close();
				System.out.println("El fichero "+pNombreFichero+" ha sido creado satisfactoriamente");
			}catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("El fichero "+pNombreFichero+" existe en el directorio de datos, por favor eliminalo");
		}
	}
	
	/**
	 * Se encarga de guardar los resultados de un evaluador en el disco duro.
	 * @param pNombreFichero
	 * @param pTest
	 * @param pPrediction
	 */
	
	public static void escribirResultadosEvaluador(String pNombreFichero,Vector pTest,double[] pPrediction){
		File fichero = new File(pNombreFichero);
		if(!fichero.exists()) {
			//No existen duplicados
			try{
				BufferedWriter bw = new BufferedWriter(new FileWriter(pNombreFichero));
				for(int i=0;i<pPrediction.length;i++){
					// Escribir la clase real que aparece en el conjunto de test y la clase estimada
					double prediction = pPrediction[i];
					bw.write(" REAL CLASS: " + pTest.classAttribute().value((int) pTest.instance(i).classValue()));
					bw.write("Â  SYSTEM PREDICTED CLASS: " + pTest.classAttribute().value((int) prediction));
					bw.newLine();
				}
				bw.close();
				System.out.println("Los resultados han sido guardados con el nombre de "+pNombreFichero);
			}catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("El fichero de Resultados ya existe. Por favor elimina primero dicho fichero");
		}
	}
	
}