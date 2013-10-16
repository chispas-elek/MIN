package org.min.packtarea1;

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
		
		
	}

}
