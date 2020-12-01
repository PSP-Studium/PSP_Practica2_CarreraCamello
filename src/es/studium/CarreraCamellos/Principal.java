/**
 * 
 */
package es.studium.CarreraCamellos;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Alvca
 *
 */
public class Principal {
	//Variable para comprobar si los rangos introducidos son correctos.
	static boolean rangoCorrectoC=false;
	static boolean rangoCorrectoD=false;
	//Distancia, numero de camellos.
	static int numeroCamellos=0;
	static int distanciaMeta=0;
	static boolean ganador=false;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Preparamos el BufferedReader para leer por teclado
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		preguntarNumeroCamellos(br);
		preguntarDistancia(br);
		//Comprobamos si los rangos introducidos son válidos. Antes de ejecutar el programa.
		if(rangoCorrectoC && rangoCorrectoD ==true) {
			try {
				@SuppressWarnings("unused")
				Carrera carrera = new Carrera(numeroCamellos,distanciaMeta);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//Creamos las funciones para preguntar por teclado
	private static void preguntarDistancia(BufferedReader br) {
		while(rangoCorrectoD==false) {
			System.out.println("¿A cuanto esta la meta?");
			try {
				distanciaMeta=Integer.parseInt(br.readLine());			
			}
			catch(Exception error)
			{
				System.out.println("Error: "+error);
				System.out.println("Por favor, introduce un número correcto.");
			}
			if(distanciaMeta!=0) {
				rangoCorrectoD=true;
			}
		}
	}
	private static void preguntarNumeroCamellos(BufferedReader br) {
		while((numeroCamellos==0)||(rangoCorrectoC==false)) {
			System.out.println("¿Cuántos camellos participarán? Indica un número del 2 al 10");
			try {
				numeroCamellos=Integer.parseInt(br.readLine());			
			}
			catch(Exception error)
			{
				System.out.println("Error: "+error);
				System.out.println("Por favor, introduzca un número correcto.");
			}
			switch(numeroCamellos) {
			case 2: 
			case 3:
			case 4:
			case 5:
			case 6:
			case 7: 
			case 8: 
			case 9: 
			case 10:
				System.out.println("En la carrera habrá "+numeroCamellos+" camellos");
				rangoCorrectoC=true;
				break;
			default:
				System.out.println("El número introducido no está en el rango válido");
				break;
			}
		}
	}
}
