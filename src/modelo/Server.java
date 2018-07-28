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
	 * Constructor que inicializa un servidor RMI y lo pone a la esccucha a través del puerto 1025.
	 * Permite la obtención de un objeto remoto de la clase {@link InterfaceCrud}. las operaciones se realizarán
	 * en sel servidor y serán llamadas desde el cliente.
	 */
	public Server() {
		try {
			//Instanciar Servidor RMI que implementa interfaz
			RMI crud = new RMI();		
			//Crear objeto stub del la clase remota
			InterfaceCrud stub = (InterfaceCrud) UnicastRemoteObject.exportObject(crud,0);
			//creamos canal de comunicación indicando puerto de transmisión
			Registry registry = LocateRegistry.createRegistry(PORT);
			//enlazamos...
			registry.bind("InterfaceCrud", stub);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
