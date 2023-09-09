package com.alura.latam.hotel.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.alura.latam.hotel.controller.HabitacionController;
import com.alura.latam.hotel.controller.HuespedController;
import com.alura.latam.hotel.controller.ReservaController;
import com.alura.latam.hotel.modelo.Huesped;
import com.alura.latam.hotel.modelo.Reserva;
import com.alura.latam.hotel.modelo.TipoHabitacion;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {
	
	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modeloReserva;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	public static JButton button;
	int xMouse, yMouse;
	// Controller
	private HuespedController huespedController;
	private ReservaController reservaController;
	private HabitacionController habitacionController;
	// Variables
	public String id = "";
	public String datosHuesped = "";
	public Boolean isSeleccionHuesped = false, isSeleccionReserva = true;
	public int idSeleccionado = 0;

	/**
	 * Create the frame.
	 */
	public Busqueda(char opcion) {
		this.huespedController = new HuespedController();
		this.reservaController = new ReservaController();
		this.habitacionController = new HabitacionController();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(200, 62, 500, 42);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblTextoBuscar = new JLabel("");
		lblTextoBuscar.setBounds(282, 127, 160, 31);
		lblTextoBuscar.setForeground(SystemColor.textInactiveText);
		lblTextoBuscar.setFont(new Font("Roboto Black", Font.PLAIN, 16));
		contentPane.add(lblTextoBuscar);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {

				idSeleccionado = 0;

				if (panel.getSelectedIndex() == 0) {
					isSeleccionHuesped = false;
					isSeleccionReserva = true;
					lblTextoBuscar.setText("Ingresar Nro. Reserva:");
				}

				if (panel.getSelectedIndex() == 1) {
					isSeleccionHuesped = true;
					isSeleccionReserva = false;
					lblTextoBuscar.setText("Ingresar Apellido:");
				}

			}
		});

		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		if (!(opcion == '1')) {
			tbReservas = new JTable();
			tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tbReservas.setFont(new Font("Roboto", Font.PLAIN, 14));

			tbReservas.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					int fila = tbReservas.rowAtPoint(e.getPoint());
					if (fila > -1) {
						idSeleccionado = Integer.valueOf(tbReservas.getValueAt(fila, 0).toString());
					}
				}
			});

			modeloReserva = (DefaultTableModel) tbReservas.getModel();
			modeloReserva.addColumn("Número");
			modeloReserva.addColumn("Fecha Check In");
			modeloReserva.addColumn("Fecha Check Out");
			modeloReserva.addColumn("Valor");
			modeloReserva.addColumn("Forma de Pago");
			modeloReserva.addColumn("Huesped");
			modeloReserva.addColumn("Habitación");
			modeloReserva.addColumn("Id Huesped");
			JScrollPane scroll_table = new JScrollPane(tbReservas);
			panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table,
					null);
			scroll_table.setVisible(true);
			tbReservas.getColumnModel().getColumn(7).setMaxWidth(0);
			tbReservas.getColumnModel().getColumn(7).setMinWidth(0);
			tbReservas.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
			tbReservas.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
			lblTextoBuscar.setText("Ingresar Nro. Reserva:");
		}

		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 14));
		tbHuespedes.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (opcion == '1') {
					id = String.valueOf(tbHuespedes.getModel().getValueAt(tbHuespedes.getSelectedRow(), 0));
					datosHuesped = String.valueOf(tbHuespedes.getModel().getValueAt(tbHuespedes.getSelectedRow(), 1)
							+ " " + tbHuespedes.getModel().getValueAt(tbHuespedes.getSelectedRow(), 2));
					ReservasView.txtIdHuesped.setText(id);
					ReservasView.txtHuesped.setText(datosHuesped);
					dispose();
				}

				int fila = tbHuespedes.rowAtPoint(e.getPoint());
				if (fila > -1) {
					idSeleccionado = Integer.valueOf(tbHuespedes.getValueAt(fila, 0).toString());
				}

			}

		});

		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Pais");
		modeloHuesped.addColumn("Direccion");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Email");
		modeloHuesped.addColumn("IdPais");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")),
				scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		tbHuespedes.getColumnModel().getColumn(8).setMaxWidth(0);
		tbHuespedes.getColumnModel().getColumn(8).setMinWidth(0);
		tbHuespedes.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
		tbHuespedes.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		if (!(opcion == '1')) {
			JPanel btnAtras = new JPanel();
			btnAtras.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					MenuUsuario usuario = new MenuUsuario();
					usuario.setVisible(true);
					dispose();
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					btnAtras.setBackground(new Color(12, 138, 199));
					labelAtras.setForeground(Color.white);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					btnAtras.setBackground(Color.white);
					labelAtras.setForeground(Color.black);
				}
			});
			btnAtras.setLayout(null);
			btnAtras.setBackground(Color.WHITE);
			btnAtras.setBounds(0, 0, 53, 36);
			header.add(btnAtras);

			labelAtras = new JLabel("<");
			labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
			labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
			labelAtras.setBounds(0, 0, 53, 36);
			btnAtras.add(labelAtras);
		}

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!(opcion == '1')) {
					MenuUsuario usuario = new MenuUsuario();
					usuario.setVisible(true);
				}
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) { 
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { 
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(446, 127, 160, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtBuscar.setFont(new Font("Roboto", Font.PLAIN, 16));
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(446, 159, 160, 2);
		contentPane.add(separator_1_2);

		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscar(opcion);
				txtBuscar.setText("");
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(3, 187, 133));
		btnbuscar.setBounds(618, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		button = new JButton();
	    button.addActionListener(e -> {
	        buscar(opcion);
	    });
	    

		if (opcion == '1') {
			JPanel btnregistrar = new JPanel();
			btnregistrar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					RegistroHuesped registro = new RegistroHuesped('1');
					registro.setVisible(true);
				}
			});
			btnregistrar.setLayout(null);
			btnregistrar.setBackground(new Color(3, 187, 133));
			btnregistrar.setBounds(748, 125, 122, 35);
			btnregistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
			contentPane.add(btnregistrar);

			JLabel lblRegistrar = new JLabel("REGISTRAR");
			lblRegistrar.setBounds(0, 0, 122, 35);
			btnregistrar.add(lblRegistrar);
			lblRegistrar.setHorizontalAlignment(SwingConstants.CENTER);
			lblRegistrar.setForeground(Color.WHITE);
			lblRegistrar.setFont(new Font("Roboto", Font.PLAIN, 18));
			lblTextoBuscar.setText("Ingresar Apellido:");
		} else {
			JPanel btnEditar = new JPanel();
			btnEditar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					if (idSeleccionado <= 0) {
						JOptionPane.showMessageDialog(null, "Por favor, seleccione un item.");
						return;
					}
					
					modificar(opcion);
					
				}
			});
			btnEditar.setLayout(null);
			btnEditar.setBackground(new Color(3, 187, 133));
			btnEditar.setBounds(305, 508, 122, 35);
			btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
			contentPane.add(btnEditar);

			JLabel lblEditar = new JLabel("EDITAR");
			lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
			lblEditar.setForeground(Color.WHITE);
			lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
			lblEditar.setBounds(0, 0, 122, 35);
			btnEditar.add(lblEditar);

			JPanel btnEliminar = new JPanel();
			btnEliminar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					if (idSeleccionado <= 0) {
						JOptionPane.showMessageDialog(null, "Por favor, seleccione un item.");
						return;
					}

					if (eliminar(opcion) == 1) {
						buscar(opcion);
						JOptionPane.showMessageDialog(null, "Eliminado con éxito!");
					} else {
						JOptionPane.showMessageDialog(null, "No se pudo eliminar el item seleccionado!");
					}
				}
			});
			btnEliminar.setLayout(null);
			btnEliminar.setBackground(new Color(3, 187, 133));
			btnEliminar.setBounds(437, 508, 122, 35);
			btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
			contentPane.add(btnEliminar);

			JLabel lblEliminar = new JLabel("ELIMINAR");
			lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
			lblEliminar.setForeground(Color.WHITE);
			lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
			lblEliminar.setBounds(0, 0, 122, 35);
			btnEliminar.add(lblEliminar);
		}
		setResizable(false);
	}

	// Código que permite mover la ventana por la pantalla según la posición de "x"
	// y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}

	private void LimpiarJTable(DefaultTableModel modelo) {
		int filas = modelo.getRowCount() - 1;
		for (int i = filas; i >= 0; i--) {
			modelo.removeRow(i);
		}
	}

	private void buscar(char opcion) {
		idSeleccionado = 0;
		if (opcion == '1' || isSeleccionHuesped) {
			LimpiarJTable(modeloHuesped);
			buscarHuesped();
		} else {
			LimpiarJTable(modeloReserva);
			buscarReserva();
		}
	}

	private void buscarHuesped() {
		List<Huesped> listaHuespedes = new ArrayList<>();
		listaHuespedes = this.huespedController.buscarHuesped(txtBuscar.getText());

		for (Huesped huesped : listaHuespedes) {
			Object[] datos = { huesped.getId(), huesped.getNombre(), huesped.getApellido(),
					huesped.getFechaNacimiento(), huesped.getNombrePais(), huesped.getDireccion(), huesped.getTelefono(), 
					huesped.getEmail(), huesped.getPais()};
			modeloHuesped.addRow(datos);
		}
	}

	private void buscarReserva() {
		List<Reserva> listaReservas = new ArrayList<>();
		
		listaReservas = this.reservaController.buscarReserva(txtBuscar.getText());

		for (Reserva reserva : listaReservas) {
			Object[] datos = { reserva.getId(), reserva.getFechaEntrada(), reserva.getFechaSalida(),
					reserva.getImporte(), reserva.getFormaPago(), reserva.getNombreHuesped(),
					reserva.getHabitacion(), reserva.getHuesped()};
			modeloReserva.addRow(datos);
		}
	}

	private int eliminar(char opcion) {
		if (opcion == '1' || isSeleccionHuesped) {
			if (eliminarHuesped(idSeleccionado) <= 0)
				return 0;
		} else {
			if (eliminarReserva(idSeleccionado) <= 0)
				return 0;
		}
		return 1;
	}

	private int eliminarReserva(int id) {
		return this.reservaController.eliminar(id);
	}

	private int eliminarHuesped(int id) {
		return this.huespedController.eliminar(id);
	}

	private void modificar(char opcion) {
		if (opcion == '1' || isSeleccionHuesped) {
			modificarHuesped();
		} else {
			modificarReserva();
		}
	}

	private void modificarReserva() {

		SimpleDateFormat formatodeltexto = new SimpleDateFormat("yyyy-MM-dd");
		String fechaE = tbReservas.getValueAt(tbReservas.getSelectedRow(), 1).toString().trim();
		String fechaS = tbReservas.getValueAt(tbReservas.getSelectedRow(), 2).toString().trim();
		Date fechaEntrada = null, fechaSalida = null;
		try {
			fechaEntrada = (Date) formatodeltexto.parse(fechaE);
			fechaSalida = (Date) formatodeltexto.parse(fechaS);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Integer numHabitacion = Integer.valueOf(modeloReserva.getValueAt(tbReservas.getSelectedRow(), 6).toString());
		TipoHabitacion tipoHabitacion = habitacionController.buscarTipoHabitacion(numHabitacion);

		ReservasView reservas = new ReservasView('2', numHabitacion, tipoHabitacion);
		reservas.setVisible(true);
		ReservasView.txtIdReserva.setText(modeloReserva.getValueAt(tbReservas.getSelectedRow(), 0).toString());
		ReservasView.txtFechaEntrada.setDate(fechaEntrada);
		ReservasView.txtFechaSalida.setDate(fechaSalida);
		ReservasView.txtValor.setText(modeloReserva.getValueAt(tbReservas.getSelectedRow(), 3).toString());
		ReservasView.txtFormaPago.setSelectedItem(modeloReserva.getValueAt(tbReservas.getSelectedRow(), 4).toString());
		ReservasView.txtHuesped.setText(modeloReserva.getValueAt(tbReservas.getSelectedRow(), 5).toString());
		ReservasView.txtIdHuesped.setText(modeloReserva.getValueAt(tbReservas.getSelectedRow(), 7).toString());
	}
	
	private void modificarHuesped() {
		RegistroHuesped registro = new RegistroHuesped('2');
		registro.setVisible(true);
		RegistroHuesped.comboPaises.setSelectedIndex(Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(),8).toString()));
		RegistroHuesped.txtIdHuesped.setText(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
		RegistroHuesped.txtNombre.setText(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1).toString());
		RegistroHuesped.txtApellido.setText(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2).toString());
		Object fechaNacimiento =  modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3);
		Object direccion = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5);
		Object telefono = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6);
		Object email = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 7);
		if (fechaNacimiento!=null) RegistroHuesped.txtFechaN.setDate((Date) fechaNacimiento);
		if (direccion!=null) RegistroHuesped.txtDireccion.setText(direccion.toString());
		if (telefono!=null) RegistroHuesped.txtTelefono.setText(telefono.toString());
		if (email!=null) RegistroHuesped.txtEmail.setText(email.toString());		
	}

}
