package org.min.packtarea1;

import java.util.Vector;

import weka.core.Instances;

public class Minkowsky {

	private int k,n,m;
	private Vector ldistances;
	
	private Minkowsky(int pK,int pN,int pM) {
		this.k = pK;
		this.n = pN;
		this.m = pM;
	}
}
