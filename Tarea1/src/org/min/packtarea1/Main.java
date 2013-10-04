package org.min.packtarea1;

import weka.core.Instances;

public class Main {

	public static void main(String[] args) {
		
		//Load properties file
		LoadProperties loadP = new LoadProperties(args[0]);
		loadP.load();

		//Load *.arff file, randomize and create train and test files.
		
		
	}

}
