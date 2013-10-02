package org.min.packtarea1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {

	private String k;
	private String n;
	private String m;
	private String data;
	private String path;
	
	public LoadProperties(String pArgs) {
		this.path = pArgs;
	}
	
	//In this site we will load properties file.
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

	public String getK() {
		return k;
	}

	private void setK(String k) {
		this.k = k;
	}

	public String getN() {
		return n;
	}

	private void setN(String n) {
		this.n = n;
	}

	public String getM() {
		return m;
	}

	private void setM(String m) {
		this.m = m;
	}

	public String getData() {
		return data;
	}

	private void setData(String data) {
		this.data = data;
	}

	public String getPath() {
		return path;
	}
}
