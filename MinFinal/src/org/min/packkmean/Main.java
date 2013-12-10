package org.min.packkmean;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		//Cargamos el fichero Properties
		LoadProperties loadP = new LoadProperties(args[0]);
		loadP.load();
		
		//Cargamos los datos del fichero .arff
		LoadData loadD = new LoadData(loadP.getData());
		ListaEntidades lista = loadD.CargarDatos(Integer.parseInt(loadP.getK()));
		
		//Llamamos a k-means
		boolean menu1 = false;
		while(!menu1) {
			System.out.println("Determine la inicializacion deseada");
			System.out.println("1.-Aleatoria");
			System.out.println("2.-Por division de espacio");
			System.out.println("===================================");
			Scanner sc = new Scanner(System.in);
			int psc = sc.nextInt();
			switch (psc){
				case 1:
					menu1 = true;
					boolean menu2a = false;
					while(!menu2a) {
						System.out.println("Determine la iterabilidad");
						System.out.println("1.-Definido");
						System.out.println("2.-Automatico");
						System.out.println("===================================");
						Scanner read = new Scanner(System.in);
						int pRead = read.nextInt();
						switch (pRead){
							case 1: 
								menu2a = true;
								System.out.println("Inserte el numero de ciclos");
								Scanner read2 = new Scanner(System.in);
								int pRead2 = read2.nextInt();
								KMeans pKMeans = new KMeans(lista, loadP.getM(), lista.randomSelect(Integer.parseInt(loadP.getK())), pRead2);
								pKMeans.recycle();
								break;
							case 2:
								menu2a = true;
								KMeans pKMeans2 = new KMeans(lista, loadP.getM(), lista.randomSelect(Integer.parseInt(loadP.getK())));
								pKMeans2.recycle();
								break;
							default: 
								System.out.println("Opcion no valida.");
								break;
								
						}
						read.close();
					}
					break;
	
				case 2:
					menu1 = true;
					boolean menu2b = false;
					while(!menu2b) {
						System.out.println("Determine la iterabilidad");
						System.out.println("1.-Definido");
						System.out.println("2.-Automatico");
						System.out.println("===================================");
						Scanner read3 = new Scanner(System.in);
						int pRead3 = sc.nextInt();
						switch (pRead3){
							case 1: 
								menu2b = true;
								System.out.println("Inserte el numero de ciclos");
								Scanner read4 = new Scanner(System.in);
								int pRead4 = read4.nextInt();
								KMeans pKMeans3 = new KMeans(lista, loadP.getM(), Integer.parseInt(loadP.getK()), pRead4);
								pKMeans3.recycle();
								break;
							case 2:
								menu2b = true;
								KMeans pKMeans4 = new KMeans(lista, loadP.getM(), Integer.parseInt(loadP.getK()));
								pKMeans4.recycle();
								break;
							default: 
								System.out.println("Opcion no valida.");
								break;
						}
						read3.close();
					}
					break;
					
				default: 
					System.out.println("Opcion no valida.");
					break;
					
			}
			sc.close();
		}
		
		//Escribimos los resultados obtenidos en un fichero en el disco duro.
		SaveData.escribirResultadosEvaluador("Resultados.txt", lista);
	}
	
}
