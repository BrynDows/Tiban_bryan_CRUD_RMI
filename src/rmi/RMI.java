package rmi;

import java.awt.Cursor;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;

import connection.Connection;
import modelo.Client;
import modelo.ColumnNotExistsException;

/**
 * Esta clase implementa {@link InterfaceCrud} y da funcioamiento a los métodos implementados.
 * @author Bryan Ti
 *
 */
public class RMI implements InterfaceCrud, Serializable {

	private Connection connection;
	private Statement terminal;
	private final static String TABLE = "nombres";
	/**
	 * Sentencia SQL almacenada en un {@link String}
	 */
	private static final String CREATE_TABLE_NOMBRES = "CREATE TABLE IF NOT EXISTS " + TABLE + "( "
			+ "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
			+ "nombre VARCHAR(30) NOT NULL,"
			+ "apellido VARCHAR(30) NOT NULL"
			+ ");";
	
	
	@Override
	public void buildDB() throws RemoteException {
		try {
			connection = Connection.getInstance();
			terminal = connection.getStatement();
			terminal.execute(CREATE_TABLE_NOMBRES);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public int insert(String nombre, String apellido) throws RemoteException {
		int result = 0;
		try {
			result = terminal.executeUpdate("INSERT INTO " + TABLE + " (nombre, apellido) VALUES( '" + nombre + "', '" + apellido + "');");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public int update(int _id, String s, int column) throws RemoteException, ColumnNotExistsException {
		int result = 0;
		try {
			if(column == 0) {
				result = terminal.executeUpdate("UPDATE " + TABLE + " SET nombre = '" + s + "' WHERE _id = " + _id + ";");
			}else if( column == 1) {
				result = terminal.executeUpdate("UPDATE " + TABLE + " SET apellido = '" + s + "' WHERE _id = " + _id + ";");
			}else {
				throw new ColumnNotExistsException("El valor de columna no es válido, solo puede ser 1 o 2");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	@Override
	public int update(int _id, String newNombre, String newApellido) throws RemoteException {
		int result = 0;
		try {
			//update nombres set nombre = 'kiko' where _id = 2;
			result = terminal.executeUpdate("UPDATE " + TABLE + " SET nombre = '" + newNombre + "', apellido = '" + newApellido + "' WHERE _id = " + _id + ";");
		}catch(SQLException s) {
			s.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int delete(int _id) throws RemoteException {
		int result = 0;
		try {
			result = terminal.executeUpdate("DELETE FROM " + TABLE + " WHERE _id = " + _id);
		}catch(SQLException s) {
			s.printStackTrace();
		}
		
		return result;
	}
	@Override
	public String getTable() throws RemoteException {
		String rows ="";
		try {
			ResultSet cursor = terminal.executeQuery("SELECT * FROM " + TABLE + ";");
			while(cursor.next()) {
				rows = rows + "ID_ : " + cursor.getInt(1) + ",\t\t NOMBRE: " + cursor.getString(2) + ",\t\t APELLIDO: " + cursor.getString(3) + "\n";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rows;
	}
	
}
