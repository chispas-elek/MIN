package org.min.packtarea1;

import java.util.Iterator;
import java.util.Vector;

public class ListDistIndex {
	
	private Vector<DistIndex> lDistances;
	private double maxDistance;
	
	/**
	 * This class is in charge of controlling the nearest K neighbours with their distances
	 * and their respective index in the Train set.
	 * 
	 * In order to determine the class in the future, the constructor decides wether to set 
	 * k or k+1 so that it is always odd and thus, be ready to resolve equalities. 
	 * 
	 * @param pK Value of the wanted neighbours to check.
	 */
	
	public ListDistIndex(int pK) {
		if((pK % 2) == 0) {
			this.lDistances = new Vector<DistIndex>(pK+1);
		} else {
			this.lDistances = new Vector<DistIndex>(pK);
		}
		this.maxDistance = -1;
	}
	
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
	
	private Iterator<DistIndex> getIterator() {
		return this.lDistances.iterator();
	}

	private double getMaxDistance() {
		return maxDistance;
	}

	private void setMaxDistance(double maxDistance) {
		this.maxDistance = maxDistance;
	}
	
	

}
