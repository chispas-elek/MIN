package org.min.packtarea1;

import java.util.Iterator;
import java.util.Vector;

public class Vect {

	private Vector<String> vStr;
	
	public Vect() {
		this.vStr = new Vector<String>();
	}
	
	public boolean exists(String pStr) {
		boolean ex = false;
		Iterator<String> it = this.vStr.iterator();
		String str;
		while(it.hasNext() && !ex) {
			str = it.next();
			if(str == pStr) {
				ex = true;
			}
		}
		return ex;
	}
	
	public void add(String pStr) {
		this.vStr.add(pStr);
	}
}
