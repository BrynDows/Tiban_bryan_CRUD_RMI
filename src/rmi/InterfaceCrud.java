package rmi;

import java.awt.Cursor;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.ResultSet;

import modelo.ColumnNotExistsException;

/**
 * Esta interfaz contiene los métodos que serán solicitados para su ejecución por parte del cliente y ejecutados desde el
 * servidor.
 * @author Bryan Ti
 *
 */
public interface InterfaceCrud extends Remote{

	/**
	 * Método a modo de constructor de la clase que lo implementa, el cual debe iniciar 
 	 * comunicación con la base de datos SQLITE y crear una tabla.
	 * @throws RemoteException
	 */
	void buildDB() throws RemoteException;
	
	/**
	 * Método que se encarga de insertar datos en la base de datos SQLite
	 * @param nombre 
	 * @param apellido
	 * @return Entero que representa el número de filas que han sido afectadas.
	 * @throws RemoteException
	 */
	int insert(String nombre, String apellido) throws RemoteException;
	
	/**
	 * Método que permite actualizar datos de la base de datoss
	 * @param _id ID del elemento a modificar.
	 * @param s Nuevo nombre o nuevo apellido.
	 * @param column Columna a modificar, puede tomar los siguientes valores:
	 * 0 = nombre y 1 = apellido
	 * @return Entero que representa el número de filas que han sido afectas
	 * @throws RemoteException
	 */
	int update(int _id, String s, int column) throws RemoteException, ColumnNotExistsException;
	
	/**
	 * Método que permite actualz datos de la base de datos.
	 * @param _id ID del elementos a modificar
	 * @param newNnombre Nuevo nombre
	 * @param newApellido Nuevo Apellido
	 * @return Integer
	 * @throws RemoteException
	 */
	int update(int _id, String newNombre, String newApellido) throws RemoteException;
	
	/**
	 * Método que permite eliminar datos de la base de datoss
	 * @return Entero que representa el número de filas que han sido afectas.
	 * @throws RemoteException
	 */
	int delete(int _id) throws RemoteException;
	
	/**
	 * Método que permite obtener todos los registros de una tabla.
	 * @return {@link String} con todos lso registros de la tabla pasada por parámetro.
	 * @throws RemoteException
	 */
	String getTable() throws RemoteException;
}
