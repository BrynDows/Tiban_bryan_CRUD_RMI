package modelo;

import java.io.Serializable;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import rmi.InterfaceCrud;
import rmi.RMI;

/**
 * Clase la cual permite instanciar un servidor RMI.
 * @author Bryan Ti
 *
 */
public class Server implements Serializable{
	
	private static final int PORT = 1025;
	
	/**
	 * Constructor que inicializa un servidor RMI y lo pone a la esccucha a trav�s del puerto 1025.
	 * Permite la obtenci�n de un objeto remoto de la clase {@link InterfaceCrud}. las operaciones se realizar�n
	 * en sel servidor y ser�n llamadas desde el cliente.
	 */
	public Server() {
		try {
			//Instanciar Servidor RMI que implementa interfaz
			RMI crud = new RMI();		
			//Crear objeto stub del la clase remota
			InterfaceCrud stub = (InterfaceCrud) UnicastRemoteObject.exportObject(crud,0);
			//creamos canal de comunicaci�n indicando puerto de transmisi�n
			Registry registry = LocateRegistry.createRegistry(PORT);
			//enlazamos...
			registry.bind("InterfaceCrud", stub);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
