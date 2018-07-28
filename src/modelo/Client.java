package modelo;

import java.awt.TextArea;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JButton;
import javax.swing.JTextArea;

import rmi.InterfaceCrud;
import vista.JFClient;


/**
 * Esta clase es utilizada por {@link JFClient}, permite la conexión con el servidor.
 * Da acceso al objeto remoto.
 * Con el método {@link getCRUD()}
 * podemos obtener el objeto remoto y por lo tanto a tabién a sus métodos.
 * @author Bryan Ti
 *
 */
public class Client implements Serializable{
	/*
	 * 1025 = puerto del servidor
	 */
	private static final int PORT_SERVER = 1025;
	/**
	 * objeto remoto
	 */
	private InterfaceCrud interfaceCrud;
	
	/**
	 * Constructor por defecto, instancia la clase Client de manera que realiza una conexión con el servidor RMI y da valor
	 * a la variable {@link interfaceCrud} de esta clase.
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public Client() throws RemoteException, NotBoundException {
		//canal
		Registry reg = LocateRegistry.getRegistry("127.0.0.1", PORT_SERVER);
		//objeto remoto
		interfaceCrud = (InterfaceCrud) reg.lookup("InterfaceCrud");
		interfaceCrud.buildDB();
		
	}
	
	/**
	 * Con este método tenemos acceso a la clase remota y a todos su métodos.
	 * @return Ojeto remoto.
	 */
	public InterfaceCrud getCRUD() {
		return interfaceCrud;
	}
	

}
