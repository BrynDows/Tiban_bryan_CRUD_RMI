package modelo;

import rmi.RMI;

/**
 * Excepci�n creada con el objetivo de hacer uso de ella en el m�todo {@link RMI#update(int, String, int)}
 * @author Bryan Ti
 *
 */
public class ColumnNotExistsException extends Exception {

	public ColumnNotExistsException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub

	}
}
