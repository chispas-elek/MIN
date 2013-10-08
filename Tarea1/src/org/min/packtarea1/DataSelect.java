package org.min.packtarea1;

import java.util.Random;

import weka.core.Instances;

public class DataSelect {

	private Instances train,test,testUnclass;
	private final double percent = 0.70;
	
	public DataSelect() {
		this.train = null;
		this.test = null;
		this.testUnclass = null;
	}
	
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
	}

	public Instances getTrain() {
		return train;
	}

	public void setTrain(Instances train) {
		this.train = train;
	}

	public Instances getTest() {
		return test;
	}

	public void setTest(Instances test) {
		this.test = test;
	}

	public Instances getTestUnclass() {
		return testUnclass;
	}

	public void setTestUnclass(Instances testUnclass) {
		this.testUnclass = testUnclass;
	}

	public double getPercent() {
		return percent;
	}
	
	
}
