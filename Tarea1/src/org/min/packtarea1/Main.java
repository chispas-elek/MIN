package org.min.packtarea1;

import java.util.Vector;

import weka.core.Instances;

public class Main {

	public static void main(String[] args) {
		
		//Load properties file
		LoadProperties loadP = new LoadProperties(args[0]);
		loadP.load();

		//Load *.arff file, randomize and create train and test files.
		LoadData loadD = new LoadData(loadP.getData());
		Instances data = loadD.loadInstances();
		
		//Split instances into Train and Test sets.
		DataSelect dataS = new DataSelect();
		dataS.select(data);
		
		//Apply Minkowsky
		Minkowsky mk = new Minkowsky(Integer.parseInt(loadP.getK()), Integer.parseInt(loadP.getM()));
		Vector<String> result = mk.calculate(dataS.getTrain(), dataS.getTestUnclass());
		
		Comparation cmp = new Comparation(result, dataS.getTestClass());
		
	}

}
