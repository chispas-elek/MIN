package org.min.packtarea1;

import java.util.Random;
import java.util.Vector;

import weka.core.Instances;

public class DataSelect {

	/** Train contains the instances to train Minkowsky algorithm
	 * Test is to locate test set.
	 * testUnclass is to locate test set without result class.
	 */
	private Instances train,test,testUnclass;
	/** The amount in percent to split Train set and test set*/
	private final double percent = 0.70;
	
	
	/**
	 * The main purpose of this class is to split a data instances into 3 different formats.
	 */
	public DataSelect() {
		this.train = null;
		this.test = null;
		this.testUnclass = null;
	}
	/**
	 * Select and split data instances in 3 different variables.
	 * 
	 * @param pData instances to split.
	 */
	public void select(Instances pData) {
		pData.randomize(new Random(45));
		
		//In this part, we will generate test, train and testUnclass files
		int trainSize = (int) Math.round(pData.numInstances() * this.getPercent());
		int testSize = pData.numInstances() - trainSize;
		this.setTrain(new Instances(pData,0,trainSize));
		this.setTest(new Instances(pData,trainSize,testSize));
		
		
		Instances unclass = this.getTest();
		unclass.deleteAttributeAt(this.getTest().classIndex());
		this.setTestUnclass(unclass);
		
		SaveData.guardarResultado("TrainSet", this.getTrain());
		SaveData.guardarResultado("TestSet", this.getTest());
		SaveData.guardarResultado("UnclassSet", this.testUnclass);
	}
	
	public Vector<String> testClass() {
		Vector<String> pStr = new Vector<String>();
		for(int i = 0; i < this.getTest().numInstances(); i++) {
			pStr.add(this.getTest().instance(i).attribute(this.calculateN()).toString());
		}
		return pStr;
	}
	
	public int calculateN() {
		int n = 1;
		int count = 0;
		char x;
		String str = this.getTrain().firstInstance().toString();
		while(count < str.length()) {
			x = str.charAt(count);
			if(x == ',') {
				n++;
			}
			count++;
		}
		return n;
	}

	/**
	 * Gets the train set.
	 * 
	 * @return the train set.
	 */
	public Instances getTrain() {
		return train;
	}
	/**
	 * Sets the train set.
	 * 
	 * @param train a train set.
	 */
	public void setTrain(Instances train) {
		this.train = train;
	}
	/**
	 * Gets the test set.
	 * 
	 * @return the set set.
	 */
	public Instances getTest() {
		return test;
	}
	/**
	 * Sets the test set.
	 * 
	 * @param test a test set.
	 */
	public void setTest(Instances test) {
		this.test = test;
	}
	/**
	 * Gets the unclass set.
	 * 
	 * @return the unclass set.
	 */
	public Instances getTestUnclass() {
		return testUnclass;
	}
	/**
	 * Sets the unclass set.
	 * 
	 * @param testUnclass a unclass set.
	 */
	public void setTestUnclass(Instances testUnclass) {
		this.testUnclass = testUnclass;
	}
	/**
	 * Gets the percent that is used in the class to split instances.
	 * 
	 * @return a percent value.
	 */
	public double getPercent() {
		return percent;
	}
	
	
}