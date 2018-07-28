package vista;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

import modelo.Client;
import modelo.ColumnNotExistsException;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class JFClient extends JFrame{
	
	
	private final JPanel panelAlta = new JPanel();
	private JTextField tfNombreAlta;
	private JTextField tfApellidoAlta;
	private final JPanel panelBaja = new JPanel();
	private final JLabel lblId = new JLabel("ID: ");
	private final JTextField tfDelete = new JTextField();
	private final JPanel panelModificacionTodo = new JPanel();
	private final JLabel lblNuevoNombre = new JLabel("Nuevo nombre: ");
	private final JTextField tfNewName = new JTextField();
	private final JLabel lblNuevoApellido = new JLabel("Nuevo apellido: ");
	private final JTextField tfnewsurname = new JTextField();
	private final JTextArea textArea = new JTextArea();
	private JTextField tfIDModifyNameORsurname;
	private final JLabel lblId_1 = new JLabel("ID del elemento a modifcar: ");
	private final JTextField tfIDModififyName_surname = new JTextField();
	private final JTextField tfNameORsurname = new JTextField();
	FlowLayout flowLayout = (FlowLayout) panelAlta.getLayout();
	JLabel lblNombre = new JLabel("Nombre: ");
	JLabel lblApellido = new JLabel("Apellido: ");
	JPanel panelModifcarNombreOApellido = new JPanel();
	JLabel lblNuevo = new JLabel("ID del elemento a modificar: ");
	JComboBox comboBox = new JComboBox();
	JButton btnUpdate_Name_OR_Surname = new JButton("Modifcar");
	JButton btnUpdateNAME_AND_surname = new JButton("Modificar");
	JButton btndelete = new JButton("eliminar");
	JButton btnInsert = new JButton("Alta");
	private Client client;
	
	public JFClient(Client client) {
		this.client = client;
		getContentPane().setLayout(null);
		this.setSize(910, 553);
		
		panelAlta.setBorder(new TitledBorder(null, "Alta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelAlta.setBounds(12, 14, 813, 63);
		
		getContentPane().add(panelAlta);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		panelAlta.add(lblNombre);
		
		tfNombreAlta = new JTextField();
		tfNombreAlta.setHorizontalAlignment(SwingConstants.CENTER);
		panelAlta.add(tfNombreAlta);
		tfNombreAlta.setColumns(10);
		
		panelAlta.add(lblApellido);
		
		tfApellidoAlta = new JTextField();
		panelAlta.add(tfApellidoAlta);
		tfApellidoAlta.setColumns(10);
		
		panelAlta.add(btnInsert);
		panelBaja.setBorder(new TitledBorder(null, "Baja", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelBaja.setBounds(12, 251, 813, 63);
		
		getContentPane().add(panelBaja);
		
		panelBaja.add(lblId);
		tfDelete.setColumns(10);
		
		panelBaja.add(tfDelete);
		
		panelBaja.add(btndelete);
		panelModificacionTodo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Modificar nombre y apellido", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelModificacionTodo.setBounds(12, 90, 813, 70);
		
		getContentPane().add(panelModificacionTodo);
		panelModificacionTodo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panelModificacionTodo.add(lblId_1);
		tfIDModififyName_surname.setHorizontalAlignment(SwingConstants.CENTER);
		tfIDModififyName_surname.setColumns(5);
		
		panelModificacionTodo.add(tfIDModififyName_surname);
		
		panelModificacionTodo.add(lblNuevoNombre);
		tfNewName.setHorizontalAlignment(SwingConstants.CENTER);
		tfNewName.setColumns(10);
		
		panelModificacionTodo.add(tfNewName);
		
		panelModificacionTodo.add(lblNuevoApellido);
		tfnewsurname.setColumns(10);
		
		panelModificacionTodo.add(tfnewsurname);
		
		panelModificacionTodo.add(btnUpdateNAME_AND_surname);
		textArea.setBounds(12, 319, 864, 174);
		
		getContentPane().add(textArea);
		
		panelModifcarNombreOApellido.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Modificar nombre O apellido", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelModifcarNombreOApellido.setBounds(12, 169, 813, 70);
		getContentPane().add(panelModifcarNombreOApellido);
		panelModifcarNombreOApellido.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panelModifcarNombreOApellido.add(lblNuevo);
		
		tfIDModifyNameORsurname = new JTextField();
		tfIDModifyNameORsurname.setColumns(5);
		panelModifcarNombreOApellido.add(tfIDModifyNameORsurname);
	
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Nuevo nombre", "Nuevo apellido"}));
		panelModifcarNombreOApellido.add(comboBox);
		tfNameORsurname.setColumns(10);
		
		panelModifcarNombreOApellido.add(tfNameORsurname);
		
		
		panelModifcarNombreOApellido.add(btnUpdate_Name_OR_Surname);
		
		updateTextArea();
		
		btnInsert.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					client.getCRUD().insert(tfNombreAlta.getText(), tfApellidoAlta.getText());
					updateTextArea();
					
				} catch (RemoteException e1) {
					JOptionPane.showMessageDialog(null, "Ha habido un error durante la comunicación con el servidor");
				}catch ( Exception ex) {
					JOptionPane.showMessageDialog(null, "El campo ID debe contener números");
				}
			}
		});
		
		
		btndelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					client.getCRUD().delete(Integer.parseInt(tfDelete.getText()));
					updateTextArea();
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "El campo ID debe contener números");
					
				} catch (RemoteException e1) {
					JOptionPane.showMessageDialog(null, "Ha habido un error durante la comunicación con el servidor");
				}
				
			}
		});
		
		
		btnUpdate_Name_OR_Surname.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					try {
						client.getCRUD().update(Integer.parseInt(tfIDModifyNameORsurname.getText()), tfNameORsurname.getText(), comboBox.getSelectedIndex());
						updateTextArea();
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "El campo ID debe contener números");
						
					} catch (RemoteException e1) {
						JOptionPane.showMessageDialog(null, "Ha habido un error durante la comunicación con el servidor");
					} catch (ColumnNotExistsException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch ( Exception ex) {
						}
			}
		});
		
		
		btnUpdateNAME_AND_surname.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					client.getCRUD().update(Integer.parseInt(tfIDModififyName_surname.getText()), tfNewName.getText(), tfnewsurname.getText());
					updateTextArea();
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "El campo ID debe contener números");
				} catch (RemoteException e1) {
					JOptionPane.showMessageDialog(null, "Ha habido un error durante la comunicación con el servidor");
				}catch ( Exception ex) {
					
				}
			}
		});
		
	}
	
	private void updateTextArea() {
		try {
			textArea.setText(client.getCRUD().getTable());
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, "Ha habido un error durante la comunicación con el servidor");
			
		}
	}
	
}
