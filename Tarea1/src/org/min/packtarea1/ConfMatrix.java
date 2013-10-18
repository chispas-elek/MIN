package org.min.packtarea1;

import java.util.Iterator;
import java.util.Vector;

public class ConfMatrix {
	
	private int[][] matrix;
	private Vector<String> vReal, vPred;
	private double tp, tn, fp, fn;
	
	public ConfMatrix(int pC) {
		this.matrix = new int[pC][pC];
		vReal = new Vector<String>();
		vPred = new Vector<String>();
	}
	
	public void insert(String pPred, String pReal) {
		int x = this.searchP(pPred);
		int y = this.searchR(pReal);
		matrix[x][y]++;
	}
	
	private int searchR(String pStr) {
		int i = 0;
		boolean found = false;
		Iterator<String> it = this.vReal.iterator();
		String tStr = null;
		while(it.hasNext() && !found) {
			tStr = it.next();
			if(tStr == pStr) {
				found = true;
			}
			else {
				i++;
			}
		}
		if(!found) {
			this.vReal.add(pStr);
			i++;
		}
		return i;
	}
	
	private int searchP(String pStr) {
		int i = 0;
		boolean found = false;
		Iterator<String> it = this.vPred.iterator();
		String tStr = null;
		while(it.hasNext() && !found) {
			tStr = it.next();
			if(tStr == pStr) {
				found = true;
			}
			else {
				i++;
			}
		}
		if(!found) {
			this.vPred.add(pStr);
			i++;
		}
		return i;
	}
	
	private void solve(){
		this.setTp(this.stp());
		this.setFp(this.sfp());
		this.setTn(this.stn());
		this.setFn(this.sfn());
	}
	
	private double stp() {
		int i = this.vPred.size();
		double result = 0;
		int iterable = 0;
		while(iterable < i) {
			for(int y = 0; y < i; y++) {
				for(int x = 0; x < i; x++) {
					if( x == iterable && y == iterable) {	
						result = result + this.getMatrix()[x][y];
					}
				}
			}
			iterable++;
		}
		return result/i;
	}
	
	private double stn() {
		int i = this.vPred.size();
		double result = 0;
		int iterable = 0;
		while(iterable < i) {
			for(int y = 0; y < i; y++) {
				for(int x = 0; x < i; x++) {
					if( x != iterable && y != iterable) {	
						result = result + this.getMatrix()[x][y];
					}
				}
			}
			iterable++;
		}
		return result/i;
	}

	private double sfp() {
		int i = this.vPred.size();
		double result = 0;
		int iterable = 0;
		while(iterable < i) {
			for(int y = 0; y < i; y++) {
				for(int x = 0; x < i; x++) {
					if( x != iterable && y == iterable) {	
						result = result + this.getMatrix()[x][y];
					}
				}
			}
			iterable++;
		}
		return result/i;
	}

	private double sfn() {
		int i = this.vPred.size();
		double result = 0;
		int iterable = 0;
		while(iterable < i) {
			for(int y = 0; y < i; y++) {
				for(int x = 0; x < i; x++) {
					if( x == iterable && y != iterable) {	
						result = result + this.getMatrix()[x][y];
					}
				}
			}
			iterable++;
		}
		return result/i;
	}
	
	public void print() {
		int i = this.vPred.size();
		char abc = 'a';
		this.solve();
		System.out.println("The specificity for the model is " + (this.getTn()/(this.getTn() + this.getFp())));
		System.out.println("The recall for the model is " + (this.getTp()/this.getTp() + this.getFn()));
		System.out.println("=====================================");
		System.out.println();
		System.out.println("Confusion matrix");
		Iterator<String> it = this.vPred.iterator();
		while(it.hasNext()) {
			System.out.print( " " + abc);
			abc++;
		}
		abc = 'a';
		System.out.println(" |");
		for(int y = 0; y < i; y++) {
			for(int x = 0; x < i; x++) {
				System.out.print("| " + this.getMatrix()[x][y] + " ");
			}
			System.out.println("| " + abc + "->" + this.vPred.elementAt(y));
		}
		
	}

	public double getTp() {
		return tp;
	}

	public void setTp(double pTp) {
		this.tp = pTp;
	}

	public double getTn() {
		return tn;
	}

	public void setTn(double pTn) {
		this.tn = pTn;
	}

	public double getFp() {
		return fp;
	}

	public void setFp(double pFp) {
		this.fp = pFp;
	}

	public double getFn() {
		return fn;
	}

	public void setFn(double pFn) {
		this.fn = pFn;
	}

	public int[][] getMatrix() {
		return matrix;
	}
	
	
}
