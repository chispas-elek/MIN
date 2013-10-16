package org.min.packtarea1;

import java.util.Random;
import java.util.Vector;

import weka.core.Instances;

public class Minkowsky {

	private int k,n,m;
	
	/**
	 * The purpose of this class is to clasify number of different instances using
	 * Minkownsky algorithm.
	 * 
	 * @param pK the value of the neighbours.
	 * @param pN the value of the number of parameters in the data set.
	 * @param pM the value to use Minkownsky algorithm.
	 */
	public Minkowsky(int pK,int pM) {
		this.k = pK;
		this.n = 0;
		this.m = pM;
	}
	
	/**
	 * Calculates k-NN using Minkowsky algorithm.
	 * 
	 * @param pTrain a train set of instances.
	 * @param pTestUnclass a test set of instances without the classIndex.
	 * @return Vector with the results of the classifier.
	 */
	
	public Vector<String> calculate(Instances pTrain,Instances pTestUnclass) {
		this.calculateN(pTrain);
		Vector<String> result= new Vector<String>();
		ListDistIndex indexedDist = new ListDistIndex();
		DistIndex couple = null;
		double acum = 0;
		double distance = 0;
		for(int i=0;i<pTestUnclass.numInstances();i++) {
			for(int j=0;j<pTrain.numInstances();j++) {
				for(int l=0;l<this.getN();l++) {
					//Aqui se acumula las sumas
					acum = acum + Math.pow(Math.abs(pTestUnclass.instance(i).value(l) - pTrain.instance(j).value(l)),this.getM());
				}
				//Aqui se hace la raiz. y se guarda el resultado DE FORMA ORDENADA.
				distance = Math.pow(acum, 1/this.getM());
				//Guardar el resultado de forma ordenada.
				couple = new DistIndex(j, distance);
				indexedDist.insert(couple);
			}
			//Aqui clasificamos la instancia y se guarda ene l vector de l distances.
			//LLamar a otra clase que gestione los posibles conflictos.
			indexedDist.hash();
			ListDistIndex neighbours = this.kReload(this.getK(), indexedDist);
			//Clasificar
			result.add(this.clasify(neighbours, pTrain));
		}
		return result;
	}
	
	private String clasify(ListDistIndex pNeighbours, Instances pTrainSet) {
		ListEvaluation lClas = pNeighbours.sign(pTrainSet, this.getN());
		return lClas.topVoted();
	}
	
	private ListDistIndex kReload(int pK, ListDistIndex pList) {
		ListDistIndex kLoad = new ListDistIndex();
		int counter = 1;
		while(kLoad.size() < pK) {
			ListDistIndex elements = pList.returnIndex(counter);
			if(elements.size() == pK - kLoad.size()) {
				kLoad.insertList(elements);
			}else if (elements.size() < pK - kLoad.size()){
				kLoad.insertList(elements);
				counter++;
			}else if (elements.size() > pK - kLoad.size()){
				Random rnd = new Random(pK);
				while(kLoad.size() < pK) {
					kLoad.insert(elements.element(rnd.nextInt()));
				}	
			}
		}
		return kLoad;
	}

	private int getK() {
		return k;
	}

	private int getN() {
		return n;
	}

	private int getM() {
		return m;
	}
	
	private void setN(int pN) {
		this.n = pN;
	}
	
	private void calculateN(Instances pInst) {
		int n = 1;
		int count = 0;
		char x;
		String str = pInst.firstInstance().toString();
		while(count < str.length()) {
			x = str.charAt(count);
			if(x == ',') {
				n++;
			}
			count++;
		}
		this.setN(n);
	}
}
