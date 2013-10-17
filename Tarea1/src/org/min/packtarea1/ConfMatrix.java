package org.min.packtarea1;

import java.util.Iterator;
import java.util.Vector;

public class ConfMatrix {
	
	private int[][] matrix;
	private Vector<String> vReal, vPred;
	
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
}
