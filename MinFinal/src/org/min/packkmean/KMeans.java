package org.min.packkmean;

import java.util.Iterator;

public class KMeans {
	
	/**
	 * Especificar el valor del umbral para que deje de hacer iteraciones.
	 */
	private final double umbral = 0.35;
	
	private Minkowsky pMinkowsky;
	private ListaEntidades pListaEntidades;
	private int ciclos = 0;
	private int k;
	/**
	 * Calcula la pertenencia y recalcula el centroide de cada cluster
	 * @param pListaEnt
	 * @param m
	 * @param pRandom
	 */
	public KMeans(ListaEntidades pListaEnt, String m, ListaEntidades pRandom, boolean pCond) {
		this.pListaEntidades = pListaEnt;
		this.pMinkowsky = new Minkowsky(Integer.parseInt(m), pRandom);
		this.actualizarEntidades(pCond);
		this.k=pRandom.size();
	}
	
	public KMeans(ListaEntidades pListaEnt, String m, ListaEntidades pRandom, int pCiclos, boolean pCond) {
		this.pListaEntidades = pListaEnt;
		this.pMinkowsky = new Minkowsky(Integer.parseInt(m), pRandom);
		this.actualizarEntidades(pCond);
		this.ciclos = pCiclos;
		this.k=pRandom.size();
	}
	
	public KMeans(ListaEntidades pListaEnt, String m, int k, int pCiclos, boolean pCond) {
		this.pListaEntidades = pListaEnt;
		this.pMinkowsky = new Minkowsky(Integer.parseInt(m), this.calcularCentroidesIniciales(k));
		this.actualizarEntidades(pCond);
		this.ciclos = pCiclos;
		this.k=k;
	}
	
	public KMeans(ListaEntidades pListaEnt, String m, int k, boolean pCond) {
		this.pListaEntidades = pListaEnt;
		this.pMinkowsky = new Minkowsky(Integer.parseInt(m), this.calcularCentroidesIniciales(k));
		this.actualizarEntidades(pCond);
		this.k=k;
	}
	
	
	
	/**
	 * Se encarga de calcular iterativamente la pertenencia y recalcula el centroide de cada cluster.
	 */
	
	public void recycle(boolean pCond) {
		if (this.getCiclos() == 0) {
			//Repetición por umbral.
			ListaEntidades viejoCentroide = new ListaEntidades();
			ListaEntidades nuevoCentroide = new ListaEntidades();
			boolean umbralSi = false;
			//Mientras el umbral no se cumpla seguiremos repitiendo.
			while(!umbralSi) {
				viejoCentroide = this.getMinkowsky().centroides();
				this.recalcularCentroide();
				nuevoCentroide = this.getMinkowsky().centroides();
				//Calculamos cuánto ha variado los nuevos centroides respecto a los viejos.
				boolean superaUmbral = false;
				Iterator<Entidad> itV = viejoCentroide.getIterador();
				Iterator<Entidad> itN = nuevoCentroide.getIterador();
				Entidad entV,entN = null;
				while(itV.hasNext() && itN.hasNext() && !superaUmbral) {
					entV = itV.next();
					entN = itN.next();
					//Obtenemos la distancia
					double distancia = entV.calcularUmbral(entN);
					if(distancia > this.getUmbral()) {
						superaUmbral = true;
					}
				}
				
				
				if(!superaUmbral) {
					//No se ha superado el umbral por lo que hemos llegado al umbral
					umbralSi = true;
				}
			}
			
		}else {
			//Repetición por n veces
			for(int i = 0;i<this.getCiclos();i++) {
				this.recalcularCentroide();
			}
		}
	}
	
	/**
	 * Se encarga de recalcular el centroide e insertarlo en la clase Minkowsky.
	 */
	private void recalcularCentroide() {
		ListaEntidades nuevaListaCentroide = new ListaEntidades();
		//Hemos clasificado las instancias ahora recalculamos el centroide.
		for(int z=0;z<this.getK();z++) {
			ListaEntidades le = this.pListaEntidades.buscarPorCluster(z);
			nuevaListaCentroide.anadir(le.recalcularCentroide());
		}
		//Actualizamos los centroides en minkowsky.
		this.getMinkowsky().actualizarCentroides(nuevaListaCentroide);
		
		//Actualizar el book
		this.actualizarEntidades(false);
	}
	
	private int getCiclos() {
		return this.ciclos;
	}
	
	private Minkowsky getMinkowsky() {
		return this.pMinkowsky;
	}
	
	//private ListaEntidades getListaEntidades() {
	//	return this.pListaEntidades;
	//}
	
	private int getK() {
		return this.k;
	}
	
	private double getUmbral(){
		return this.umbral;
	}
	
	private void actualizarEntidades(boolean pCond) {
		if(!pCond) {
			Entidad ent1;		
			Iterator<Entidad> it = this.pListaEntidades.getIterador();
			while(it.hasNext()) {
				ent1 = it.next();
				this.getMinkowsky().calculate(ent1);
			}
		}
		else {
			Entidad ent1;		
			Iterator<Entidad> it = this.pListaEntidades.getIterador();
			while(it.hasNext()) {
				ent1 = it.next();
				this.getMinkowsky().calculatePorArea(ent1);
			}	
		}
	}
	
	private ListaEntidades calcularCentroidesIniciales(int k) {
		Iterator<Entidad> it = this.pListaEntidades.getIterador();
		Entidad ent = null;
		double dist = 0;
		while(it.hasNext()) {
			ent = it.next();
			if(ent.atributo(0) > dist) {
				dist = ent.atributo(0);
			}
		}
		dist = dist / k;
		double p = 0;
		ListaEntidades centroides = new ListaEntidades();
		for(int i = 0; i < k; i++) {
			ent = new Entidad(k);
			ent.anadir(p + dist);
			int l = this.pListaEntidades.entidad(0).size();
			for(int x = 1; x < l; x++) {
				ent.anadir(0);
			}
			p = p +dist;
			centroides.anadir(ent);
		}
		return centroides;
	}
}
