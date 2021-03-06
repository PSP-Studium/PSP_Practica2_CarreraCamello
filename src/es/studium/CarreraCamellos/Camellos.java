package es.studium.CarreraCamellos;
import java.util.Random;
/**
 * @author Alvca
 *
 */

public class Camellos extends Thread implements Comparable<Camellos> {
	public int posicionCamello=0;
	int distanciaMeta;
	int resultado;
	int probabilidadBola;
	String mostrarResultado = "";
	//int i=0;
	Random rd = new Random();
	//Creamos el constructor de la clase con el grupo de los hilos
	public Camellos(String nombreCamello, int distanciaMeta) {
		//Llamamos a la clase derivada
		super(nombreCamello);
		this.distanciaMeta = distanciaMeta;
	}
	//Ejecutamos los Hilos.
	public synchronized void run() {
		//while(distanciaMeta>=Carrera.getPosPrimero())
		while(!Thread.interrupted())
		{
			synchronized (this) {
				if (!Principal.ganador) {
					lanzarBola();
				}else {
					Thread.interrupted();
					break;
				}
			}
		}
	}

	/**
	 * 
	 */
	private void lanzarBola() {
		//Hacemos la tirada.
		probabilidadBola = rd.nextInt(100)+1;
		int segundos = rd.nextInt(10)+1;
		//Creamos los porcentajes para ganar.
		try {
			Thread.sleep(segundos*1000);
			if (Principal.ganador) {
				Thread.interrupted();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
		if (probabilidadBola<=30)//30% ning�n agujero, no avanza.
		{
			resultado=0;
		}
		else if (probabilidadBola>30 && probabilidadBola<=70)//40% agujero amarillo, avanza 1.
		{
			resultado=1;
		}
		else if (probabilidadBola>70 && probabilidadBola<=90)//20% agujero azul, avanza 2.
		{
			resultado=2;
		}
		else if (probabilidadBola>90 && probabilidadBola<=100)//10% agujero rojo, avanza 3.
		{
			resultado=3;
		}
		//Sumamos a la posici�n del camello el resultado de la tirada.
		posicionCamello+=resultado;
		
		if ((posicionCamello>=distanciaMeta)&&(!Principal.ganador))
		{
			cruzarMeta(posicionCamello);
		}
		//Si la posici�n del camello es mayor que la posici�n del camello primero
		if((posicionCamello>Carrera.getPosPrimero())&&(Carrera.getPosPrimero()<distanciaMeta))
		{
			//Establecemos la nueva posici�n como primero
			Carrera.setPosPrimero(posicionCamello);
			//getName() Obtiene el nombre del hilo.
			mostrarResultado = "El "+ getName() + " avanza "+ resultado + ", avanza hasta la posici�n "+ posicionCamello +" y se encuentra en primera posici�n";
		}
		//Si la posici�n del camello es igual que la posici�n del camello primero
		else if ((posicionCamello==Carrera.getPosPrimero())&&(Carrera.getPosPrimero()<distanciaMeta)&&(!Principal.ganador))
		{
			//getName() Obtiene el nombre del hilo.
			mostrarResultado = "El "+ getName() + " avanza "+ resultado + ", avanza hasta la posici�n "+ posicionCamello +" y va primero empatado con otro camello";
		}
		//Si la posici�n del camello es menor que la posici�n del camello primero
		else if ((Carrera.getPosPrimero()<distanciaMeta)&&(!Principal.ganador))
		{
			//getName() Obtiene el nombre del hilo.
			mostrarResultado = "El "+ getName() + " avanza "+ resultado + ", avanza hasta la posici�n "+ posicionCamello +" a "+obtenerDiferencia(Carrera.getPosPrimero())+ " posiciones del primero.";
		}			
		if (posicionCamello>=distanciaMeta)
		{
			System.out.println(mostrarResultado);
		}
		else if (!Principal.ganador)
		{
			System.out.println(mostrarResultado);
		}
	}

	public synchronized void cruzarMeta(int posicionCamello) {
		if(!Principal.ganador) {
			Principal.ganador = true;
		}
	}
	//Metodo compareTo para realizar el TOP.
	public int compareTo(Camellos c) {
		//Creamos dos variables.
		//Esta variable es igual que el valor de PosActual de camello Actual.
		Integer camello1 = this.posicionCamello; 
		//Esta variable es igual que la posici�n del camello pasado por parametro.
		Integer camello2= c.posicionCamello;
		return camello1.compareTo(camello2);
		//Devolvemos la comparaci�n.Para ordenar de menor a mayor.
	}
	//Creamos un m�todo sincronizado encargado de obtener las casillas de diferencia con respecto al primero.
	public synchronized int obtenerDiferencia(int posicionPrimero) { 
		//Esta variable es igual que el valor de PosActual de camello Actual
		Integer camello1 = this.posicionCamello;
		//Esta variable es igual a la posici�n del primer camello.
		Integer camello2= posicionPrimero;
		//Devuelve la diferencia entre los camellos
		return camello2 - camello1;
	}
}