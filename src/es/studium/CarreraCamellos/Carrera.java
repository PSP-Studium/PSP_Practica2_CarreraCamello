/**
 * 
 */
package es.studium.CarreraCamellos;

import java.util.Arrays;
import java.util.Collections;
/**
 * @author Alvca
 *
 */
public class Carrera {
	int numCamello=1;
	static int posPrimero=-1;

	int numCamellos;
	static Camellos listacamellos[];
	int i=0;

	//Constructor de la clase al que le pasamos el nº de camellos y la distancia de la carrera.
	public Carrera(int numCamellosCarrera,int distanciaMeta) throws InterruptedException
	{	
		listacamellos = new Camellos[numCamellosCarrera];
		for (int i = 0 ; i < numCamellosCarrera; i++) {
			//Creamos los camellos y los añadimos a un ThreadGroup
			listacamellos[i] = new Camellos("camello "+numCamello, distanciaMeta);
			listacamellos[i].start();
			numCamello++;//Aumentamos para tener un numero nuevo
		}
		for (Camellos camellos : listacamellos) {
			camellos.join();
		}
		//llamamos a .sort de la clase Arrays. Esta función llama al método compareTo.
		//Ordenamos el array de forma descendente.
		Arrays.sort(listacamellos,Collections.reverseOrder());
		System.out.println("El "+listacamellos[0].getName()+" ha llegado a la meta.");
		System.out.println("La clasificación es la siguiente:");
		int puestos=1;
		for (Camellos c : listacamellos)//Con este for extendido recorremos todos los objetos camellos ya ORDENADOS.
		{
			System.out.println(puestos+"º posición para el "+c.getName().toString());
			puestos++;
		}
	}	
	public static int getPosPrimero() {
		return posPrimero;
	}
	public static void setPosPrimero(int posPrimero) {
		Carrera.posPrimero = posPrimero;
	}
}
