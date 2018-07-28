package modelo;

import rmi.RMI;

/**
 * Excepción creada con el objetivo de hacer uso de ella en el método {@link RMI#update(int, String, int)}
 * @author Bryan Ti
 *
 */
public class ColumnNotExistsException extends Exception {

	public ColumnNotExistsException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub

	}
}
