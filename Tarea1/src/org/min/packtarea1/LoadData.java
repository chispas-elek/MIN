package org.min.packtarea1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import weka.core.Instances;


public class LoadData {

	private String path;
	
	
	public LoadData(String pPath) {
		this.path = pPath;
	}
	
	/**
	 * In this section we will obtain instances on a *.arff
	 * @return instances of a .arff file
	*/
	public Instances loadInstances() {
		// In this code we will open the file.
	    FileReader fi=null;
		try {
			fi= new FileReader(this.getPath());
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: Please check if the pack that you use is correct:"+this.getPath());
		}
		// Load the instances
		Instances data=null;
		try {
			data = new Instances(fi);
		} catch (IOException e) {
			System.out.println("ERROR: Check file format. This program requires a *.arff file: "+this.getPath());
		}
		// Close data file.
		try {
			fi.close();
		} catch (IOException e) {
			System.out.println("There is an error with de data file when the program trys to close it");
		}
		
		// Mix the instances using Randomize class and randomize method.
		Random rdm = new Random(45);
		data.randomize(rdm);

		
		// Specify which attribute will be used as the class: the last one, in this case 
		data.setClassIndex(data.numAttributes()-1);
		
		return data;
	}
	
	private String getPath() {
		return this.path;
	}
}