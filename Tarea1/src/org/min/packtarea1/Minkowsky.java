package org.min.packtarea1;

import java.util.Vector;
import java.util.Iterator;
import weka.core.Instances;

public class Minkowsky {

	private int k,n,m;
	private Vector<Double> ldistances;
	
	/**
	 * The purpose of this class is to clasify number of different instances using
	 * Minkownsky algorithm.
	 * 
	 * @param pK the value of the neighbours.
	 * @param pN the value of the number of parameters in the data set.
	 * @param pM the value to use Minkownsky algorithm.
	 */
	public Minkowsky(int pK,int pN,int pM) {
		this.k = pK;
		this.n = pN;
		this.m = pM;
		//Generación de una lista dinámica de elementos impar
	}
	
	/**
	 * Calculates k-NN using Minkowsky algorithm.
	 * 
	 * @param pTrain a train set of instances.
	 * @param pTestUnclass a test set of instances without the classIndex.
	 * @return Vector with the results of the classifier.
	 */
	
	public Vector<String> calculate(Instances pTrain,Instances pTestUnclass) {
		Vector<String> result= new Vector<String>();
		double acum = 0;
		double distance = 0;
		for(int i=0;i<pTestUnclass.numInstances();i++) {
			for(int j=0;j<pTrain.numInstances();j++) {
				for(int l=0;l<this.getN();l++) {
					//Aqui se acumula las sumas
					acum = acum + Math.abs(Math.pow(pTestUnclass.get(i).value(l) - pTrain.get(j).value(l),this.getM()));
				}
				//Aquí se hace la raiz. y se guarda el resultado DE FORMA ORDENADA.
				distance = Math.pow(acum, 1/this.getM());
				//Guardar el resultado de forma ordenada.
				
			}
			//Aquí clasificamos la instancia y se guarda ene l vector de l distances.
			//LLamar a otra clase que gestione los posibles conflictos.
		}
		return result;
	}

	private int getK() {
		return k;
	}

	private void setK(int k) {
		this.k = k;
	}

	private int getN() {
		return n;
	}

	private void setN(int n) {
		this.n = n;
	}

	private int getM() {
		return m;
	}

	private void setM(int m) {
		this.m = m;
	}

	private Vector getLdistances() {
		return ldistances;
	}

	private void setLdistances(Vector ldistances) {
		this.ldistances = ldistances;
	}
	
	
}
