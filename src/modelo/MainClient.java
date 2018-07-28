package modelo;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.UIManager;

import vista.JFClient;

public class MainClient {

	public static void main(String[] args) {
		
		//Se estace un tema para cambiar visualmente la ventana que se mostrará posteriormente
		try 
	    { 
	        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); 
	    } 
	    catch(Exception e){ 
	    }
		
		Client client;
		try {
			client = new Client();
			new JFClient(client).setVisible(true);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
