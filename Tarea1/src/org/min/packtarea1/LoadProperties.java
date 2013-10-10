package org.min.packtarea1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {
	
	/** The number of neighbours */
	private String k;
	/** Number of the attributes to use in Minkowsky */
	private String n;
	/** Number to use in the Minkowsky algoritmh */
	private String m;
	/** The system path that contains the .arff file */
	private String data;
	/** Path where is located .properties file in the system */
	private String path;
	
	/**
	 * LoadProperties is a simple class to load a properties file and use later for set Minkowsky variables.
	 * @param pArgs the path that contains a .properties file in the system
	 */
	public LoadProperties(String pArgs) {
		this.path = pArgs;
	}
	
	/**
	 * In this section we will load properties file
	 * @throws Exception if the file can't load or path is incorrect.
	 */
	public void load() {
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream(this.getPath()));
			
			this.setK(prop.getProperty("k"));
			this.setN(prop.getProperty("n"));
			this.setM(prop.getProperty("m"));
			this.setData(prop.getProperty("data"));
		}catch (FileNotFoundException e) {
			System.out.println("Can not find properties file, please check system path");
		}catch (IOException e) {
			System.out.println("Error, can not load data, please check properties file");
		}
		
	}
	/** 
	 * Gets the number of neighbours.
	 * 
	 * @return the number of neighbours.
	 */
	public String getK() {
		return k;
	}
	/**
	 * Sets the number of neighbours.
	 * 
	 * @param k the number of neighbours.
	 */
	private void setK(String k) {
		this.k = k;
	}
	/**
	 * Gets the number of attributes.
	 * 
	 * @return the number of attributes.
	 */
	public String getN() {
		return n;
	}
	/**
	 * Sets the number of attributes
	 * 
	 * @param n number of attributes.
	 */
	private void setN(String n) {
		this.n = n;
	}
	/**
	 * Gets the number that is used in Minkowsky algorithm.
	 * 
	 * @return a simple number.
	 */
	public String getM() {
		return m;
	}
	/**
	 * Sets the number that is used in Minkowsky algorithm.
	 * 
	 * @param m a random number.
	 */
	private void setM(String m) {
		this.m = m;
	}
	/**
	 * Gets the path that contains a .arff file.
	 * 
	 * @return path of the .arff file.
	 */
	public String getData() {
		return data;
	}
	/**
	 * Sets the path that contains a .arff file.
	 * 
	 * @param path of the .arff file.
	 */
	private void setData(String data) {
		this.data = data;
	}

	/**
	 * Gets the path of a properties file.
	 * 
	 * @return path of a properties file.
	 */
	public String getPath() {
		return path;
	}
}
