package org.min.packtarea1;

import java.util.Iterator;
import java.util.Vector;

public class ListDistIndex {
	
	private Vector<DistIndex> lDistances;
	
	/**
	 * This class is in charge of controlling the nearest K neighbours with their distances
	 * and their respective index in the Train set.
	 * 
	 * In order to determine the class in the future, the constructor decides wether to set 
	 * k or k+1 so that it is always odd and thus, be ready to resolve equalities. 
	 * 
	 * @param pK Value of the wanted neighbours to check.
	 */
	
	public ListDistIndex() {
		this.lDistances = new Vector<DistIndex>();	
	}
	/*
	public void insert(DistIndex pDistIndex) {
		if(lDistances.size() < lDistances.capacity()) {
			lDistances.add(pDistIndex);
			if(this.getMaxDistance()< pDistIndex.getValue()) {
				this.setMaxDistance(pDistIndex.getValue());
			}
		}
		else if(pDistIndex.getValue() < maxDistance && lDistances.size() == lDistances.capacity()) {
			boolean flag = false;
			Iterator<DistIndex> it = this.getIterator();
			DistIndex di;
			while(it.hasNext() && !flag) {
				di = it.next();
				if(di.getValue() == this.getMaxDistance()) {
					this.lDistances.removeElement(di);
					flag = true;
				}
			}
			this.lDistances.add(pDistIndex);
			//===============================================
			it = this.getIterator();
			while(it.hasNext() && !flag) {
				di = it.next();
				if(di.getValue() > this.getMaxDistance()) {
					this.setMaxDistance(di.getValue());
				}
			}
		}
	}
	*/
	private Iterator<DistIndex> getIterator() {
		return this.lDistances.iterator();
	}

	public void insert(DistIndex pDI) {
		Iterator<DistIndex> it = this.getIterator();
		DistIndex actual = null;
		boolean inserted = false;
		int i = 0;
		while(it.hasNext() && !inserted) {
			actual = it.next();
			if(pDI.getValue() < actual.getValue()) {
				this.lDistances.add(i, pDI);
				inserted = true;
			}
			i++;
		}
		if(!inserted) {
			this.lDistances.add(pDI);
		}
		
	}
	
	public void hash() {
		Iterator<DistIndex> it = this.getIterator();
		DistIndex actual = null;
		int index = 0;
		double dist = -1;
		while(it.hasNext()) {
			actual = it.next();
			if(actual.getValue() > dist) {
				dist = actual.getValue();
				index++;
			}
			actual.setIndex(index);
			
		}
	}
	
	public ListDistIndex kReload(int pK) {
		ListDistIndex kLoad = new ListDistIndex();
		int counter = 0;
		if(this.countByIndex(pK) > pK) {
			
		}
		return kLoad;
	}
	
	private int countByIndex(int pIndex) {
		int available = 0;
		boolean over = false;
		Iterator<DistIndex> it = this.getIterator();
		DistIndex actual = null;
		while(it.hasNext() && !over) {
			actual = it.next();
			if(actual.getIndex() > pIndex) {
				over = true;
			}
			else {
				available++;
			}
		}
		return available;
	}

}
