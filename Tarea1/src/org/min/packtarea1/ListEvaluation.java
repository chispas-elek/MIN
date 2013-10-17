package org.min.packtarea1;

import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

public class ListEvaluation {
	
	private Vector<Evaluation> evaluations;
	
	public ListEvaluation() {
		this.evaluations = new Vector<Evaluation>();
	}
	
	private Iterator<Evaluation> getIterator() {
		return this.evaluations.iterator();
	}
	
	public String topVoted() {
		int i = 0;
		String voted = null;
		Iterator<Evaluation> it = this.getIterator();
		Evaluation eval = null;
		while(it.hasNext()) {
			eval = it.next();
			if(eval.getCounter() > i) {
				i = eval.getCounter();
				voted = eval.getName();
			}else if(eval.getCounter() == i) {
				Random rnd = new Random(45);
				boolean taker = rnd.nextBoolean();
				if(taker) {
					i = eval.getCounter();
					voted = eval.getName();
				}
			}
		}
		return voted;
	}
	
	public void vote(Evaluation pEval) {
		int check = this.exists(pEval.getName());
		if(check > 0) {
			this.evaluations.elementAt(check).add();
		}
		if(check < 0) {
			this.evaluations.add(pEval);
		}
	}
	
	private int exists(String pClass) {
		Iterator<Evaluation> it = this.getIterator();
		Evaluation eval = null;
		boolean is = false;
		int i = 0;
		while(it.hasNext() && !is) {
			eval = it.next();
			if(eval.getName() == pClass) {
				is = true;
			}
			else {
				i++;
			}
		}
		if(!is) {
			i = -1;
		}
		return i;
	}

}
