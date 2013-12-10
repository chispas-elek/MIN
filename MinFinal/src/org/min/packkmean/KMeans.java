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
	public KMeans(ListaEntidades pListaEnt, String m, ListaEntidades pRandom) {
		this.pListaEntidades = pListaEnt;
		this.pMinkowsky = new Minkowsky(Integer.parseInt(m), pRandom);
		this.k=pRandom.size();
	}
	
	public KMeans(ListaEntidades pListaEnt, String m, ListaEntidades pRandom, int pCiclos) {
		this.pListaEntidades = pListaEnt;
		this.pMinkowsky = new Minkowsky(Integer.parseInt(m), pRandom);
		this.ciclos = pCiclos;
		this.k=pRandom.size();
	}
	
	public KMeans(ListaEntidades pListaEnt, String m, int k, int pCiclos) {
		this.pListaEntidades = pListaEnt;
		this.pMinkowsky = new Minkowsky(Integer.parseInt(m), this.calcularCentroidesIniciales(k));
		this.ciclos = pCiclos;
		this.k=k;
	}
	
	public KMeans(ListaEntidades pListaEnt, String m, int k) {
		this.pListaEntidades = pListaEnt;
		this.pMinkowsky = new Minkowsky(Integer.parseInt(m), this.calcularCentroidesIniciales(k));
		this.k=k;
	}
	
	
	
	/**
	 * Se encarga de calcular iterativamente la pertenencia y recalcula el centroide de cada cluster.
	 */
	
	public void recycle() {
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
		Entidad ent1;
		ListaEntidades nuevaListaCentroide = new ListaEntidades();		
		Iterator<Entidad> it = this.pListaEntidades.getIterador();
		while(it.hasNext()) {
			ent1 = it.next();
			this.getMinkowsky().calculate(ent1);
		}
		//Hemos clasificado las instancias ahora recalculamos el centroide.
		for(int z=0;z<this.getK();z++) {
			ListaEntidades le = this.pListaEntidades.buscarPorCluster(z);
			nuevaListaCentroide.anadir(le.recalcularCentroide());
		}
		//Actualizamos los centroides en minkowsky.
		this.getMinkowsky().actualizarCentroides(nuevaListaCentroide);
	}
	
	private int getCiclos() {
		return this.ciclos;
	}
	
	private Minkowsky getMinkowsky() {
		return this.pMinkowsky;
	}
	
	private ListaEntidades getListaEntidades() {
		return this.pListaEntidades;
	}
	
	private int getK() {
		return this.k;
	}
	
	private double getUmbral(){
		return this.getUmbral();
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
			p = p +dist;
			centroides.anadir(ent);
		}
		return centroides;
	}
}
