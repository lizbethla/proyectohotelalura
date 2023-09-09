package com.alura.latam.hotel.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;

import com.alura.latam.hotel.controller.HabitacionController;
import com.alura.latam.hotel.controller.ReservaController;
import com.alura.latam.hotel.controller.TipoHabitacionController;
import com.alura.latam.hotel.modelo.Habitacion;
import com.alura.latam.hotel.modelo.Reserva;
import com.alura.latam.hotel.modelo.TipoHabitacion;
import com.toedter.calendar.JDateChooser;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Toolkit;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.beans.PropertyChangeEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class ReservasView extends JFrame {

	private JPanel contentPane;
	public static JTextField txtValor;
	public static JTextField txtHuesped;
	public static JTextField txtIdHuesped;
	public static JTextField txtIdReserva;
	public static JTextField txtHabitacion;
	public static JDateChooser txtFechaEntrada;
	public static JDateChooser txtFechaSalida;
	public static JComboBox<String> txtFormaPago;
	private JComboBox<Habitacion> comboHabitaciones;
	private JComboBox<TipoHabitacion> comboTipoHabitacion;
	int xMouse, yMouse;
	private JLabel labelExit;
	private JLabel labelAtras;
	// Controller
	private ReservaController reservaController;
	private HabitacionController habitacionController;
	private TipoHabitacionController tipoHabitacionController;
	// Variable
	private Habitacion habitacionSeleccionada;
	private long dias = 0;


	/**
	 * Create the frame.
	 */
	public ReservasView(char opcion, Integer numHabitacion, TipoHabitacion tipohabitacionseleccionar) {
		// Controller
		this.reservaController = new ReservaController();
		this.habitacionController = new HabitacionController();
		this.tipoHabitacionController = new TipoHabitacionController();

		setIconImage(Toolkit.getDefaultToolkit().getImage(ReservasView.class.getResource("/imagenes/aH-40px.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 560);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 910, 560);
		contentPane.add(panel);
		panel.setLayout(null);

		// Código que crea los elementos de la interfáz gráfica

		JLabel lblTitulo = new JLabel("SISTEMA DE RESERVAS");
		lblTitulo.setBounds(68, 60, 289, 42);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto", Font.BOLD, 20));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblTitulo);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(428, 0, 482, 560);
		panel_1.setBackground(new Color(12, 138, 199));
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel logo = new JLabel("");
		logo.setBounds(197, 68, 104, 107);
		panel_1.add(logo);
		logo.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/Ha-100px.png")));

		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(0, 140, 500, 409);
		panel_1.add(imagenFondo);
		imagenFondo.setBackground(Color.WHITE);
		imagenFondo.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/reservas-img-3.png")));

		// Componentes para dejar la interfaz con estilo Material Design
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int respuesta = JOptionPane.showConfirmDialog(null,"¿Desea salir del Sistema?","Mensaje",dialogButton);
			    if (respuesta == 0) {
					MenuPrincipal principal = new MenuPrincipal();
					principal.setVisible(true);
					dispose();
			    }
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnexit.setBackground(new Color(12, 138, 199));
				labelExit.setForeground(Color.white);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(new Color(12, 138, 199));
		btnexit.setBounds(429, 0, 53, 36);
		panel_1.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setForeground(Color.WHITE);
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel header = new JPanel();
		header.setBounds(0, 0, 910, 36);
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
		panel.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (opcion=='1') {
					MenuUsuario usuario = new MenuUsuario();
					usuario.setVisible(true);
				}
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
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));

		// Campos que guardaremos en la base de datos
		txtIdReserva = new JTextField();
		txtIdReserva.setBounds(25, 207, 20, 33);
		txtIdReserva.setVisible(false);
		panel.add(txtIdReserva);
			
		JLabel lblHabitacion = new JLabel("HABITACION");
		lblHabitacion.setBounds(68, 110, 289, 35);
		lblHabitacion.setForeground(SystemColor.textInactiveText);
		lblHabitacion.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblHabitacion);

		comboTipoHabitacion = new JComboBox<>();
		comboTipoHabitacion.setBounds(68, 145, 145, 36);
		comboTipoHabitacion.setBackground(SystemColor.text);
		comboTipoHabitacion.setFont(new Font("Roboto", Font.PLAIN, 16));
		comboTipoHabitacion.addItem(new TipoHabitacion(0, "Seleccionar Tipo"));

		var tipohabitaciones = this.tipoHabitacionController.listar();
		tipohabitaciones.forEach(tipohabitacion -> comboTipoHabitacion.addItem(tipohabitacion));
		panel.add(comboTipoHabitacion);
		
		comboHabitaciones = new JComboBox<>();
		comboHabitaciones.setBounds(215, 145, 140, 36);
		comboHabitaciones.setBackground(SystemColor.text);
		comboHabitaciones.setFont(new Font("Roboto", Font.PLAIN, 16));

		if (opcion=='2') {
		   comboTipoHabitacion.setSelectedIndex(tipohabitacionseleccionar.getId());	
		   var habitacionesseleccionar = habitacionController.listar(tipohabitacionseleccionar);
		   comboHabitaciones.removeAllItems();
		   habitacionesseleccionar.forEach(habitacion -> comboHabitaciones.addItem(habitacion));
		   panel.add(comboHabitaciones);
		   comboHabitaciones.setSelectedItem(numHabitacion);
		   habitacionSeleccionada = (Habitacion) comboHabitaciones.getSelectedItem();
		}
		
		comboTipoHabitacion.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {

				var tipoHabitacion = (TipoHabitacion) comboTipoHabitacion.getSelectedItem();
				var habitaciones = habitacionController.listar(tipoHabitacion);
				comboHabitaciones.removeAllItems();
				habitaciones.forEach(habitacion -> comboHabitaciones.addItem(habitacion));
				panel.add(comboHabitaciones);
			}
		});

		comboHabitaciones.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				habitacionSeleccionada = (Habitacion) comboHabitaciones.getSelectedItem();
				calcularValor();
			}
		});
		
		JSeparator separator_1_5 = new JSeparator();
		separator_1_5.setForeground(SystemColor.textHighlight);
		separator_1_5.setBounds(68, 183, 289, 2);
		separator_1_5.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_5);

		JLabel lblHuesped = new JLabel("HUESPED");
		lblHuesped.setForeground(SystemColor.textInactiveText);
		lblHuesped.setBounds(68, 190, 289, 14);
		lblHuesped.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblHuesped);

		txtHuesped = new JTextField();
		txtHuesped.setBackground(SystemColor.text);
		txtHuesped.setForeground(Color.BLACK);
		txtHuesped.setBounds(68, 207, 249, 33);
		txtHuesped.setFont(new Font("Roboto Black", Font.PLAIN, 16));
		txtHuesped.setBorder(BorderFactory.createLineBorder(Color.blue));
		txtHuesped.setEditable(false);
		panel.add(txtHuesped);

		txtIdHuesped = new JTextField();
		txtIdHuesped.setBounds(50, 207, 20, 33);
		txtIdHuesped.setVisible(false);
		panel.add(txtIdHuesped);

		JPanel btnBuscarHuesped = new JPanel();
		btnBuscarHuesped.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Busqueda busqueda = new Busqueda('1');
				busqueda.setVisible(true);
			}

		});
		btnBuscarHuesped.setLayout(null);
		btnBuscarHuesped.setBackground(SystemColor.textHighlight);
		btnBuscarHuesped.setBounds(317, 207, 40, 33);
		btnBuscarHuesped.setBorder(new LineBorder(SystemColor.window));
		panel.add(btnBuscarHuesped);

		JLabel lblBuscar = new JLabel("");
		btnBuscarHuesped.add(lblBuscar);
		lblBuscar.setBounds(0, 0, 40, 33);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/pessoas.png")));

		JSeparator separator_1_4 = new JSeparator();
		separator_1_4.setForeground(SystemColor.textHighlight);
		separator_1_4.setBounds(68, 240, 289, 2);
		separator_1_4.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_4);

		JLabel lblCheckIn = new JLabel("FECHA DE CHECK IN");
		lblCheckIn.setForeground(SystemColor.textInactiveText);
		lblCheckIn.setBounds(68, 248, 289, 14);
		lblCheckIn.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblCheckIn);

		txtFechaEntrada = new JDateChooser();
		txtFechaEntrada.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaEntrada.getCalendarButton()
				.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaEntrada.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 12));
		txtFechaEntrada.setBounds(68, 265, 289, 35);
		txtFechaEntrada.getCalendarButton().setBounds(268, 0, 21, 33);
		txtFechaEntrada.setBackground(Color.WHITE);
		txtFechaEntrada.setBorder(new LineBorder(SystemColor.window));
		txtFechaEntrada.setDateFormatString("yyyy-MM-dd");
		txtFechaEntrada.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtFechaEntrada.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				calcularValor();
			}
		});
		panel.add(txtFechaEntrada);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(SystemColor.textHighlight);
		separator_1_2.setBounds(68, 300, 289, 2);
		separator_1_2.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_2);

		JLabel lblCheckOut = new JLabel("FECHA DE CHECK OUT");
		lblCheckOut.setForeground(SystemColor.textInactiveText);
		lblCheckOut.setBounds(68, 310, 289, 14);
		lblCheckOut.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblCheckOut);

		txtFechaSalida = new JDateChooser();
		txtFechaSalida.getCalendarButton()
				.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaSalida.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 11));
		txtFechaSalida.setBounds(68, 328, 289, 35);
		txtFechaSalida.getCalendarButton().setBounds(267, 1, 21, 31);
		txtFechaSalida.setBackground(Color.WHITE);
		txtFechaSalida.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtFechaSalida.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				calcularValor();
			}
		});
		txtFechaSalida.setDateFormatString("yyyy-MM-dd");
		txtFechaSalida.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaSalida.setBorder(new LineBorder(new Color(255, 255, 255), 0));
		panel.add(txtFechaSalida);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(SystemColor.textHighlight);
		separator_1_1.setBounds(68, 363, 289, 2);
		separator_1_1.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_1);

		JLabel lblValor = new JLabel("VALOR DE LA RESERVA");
		lblValor.setForeground(SystemColor.textInactiveText);
		lblValor.setBounds(68, 371, 289, 14);
		lblValor.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblValor);

		txtValor = new JTextField();
		txtValor.setBackground(SystemColor.text);
		txtValor.setForeground(Color.BLACK);
		txtValor.setBounds(68, 387, 289, 33);
		txtValor.setEditable(false);
		txtValor.setFont(new Font("Roboto Black", Font.PLAIN, 16));
		txtValor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		panel.add(txtValor);
		txtValor.setColumns(10);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(SystemColor.textHighlight);
		separator_1.setBounds(68, 420, 289, 2);
		separator_1.setBackground(SystemColor.textHighlight);
		panel.add(separator_1);

		JLabel lblFormaPago = new JLabel("FORMA DE PAGO");
		lblFormaPago.setForeground(SystemColor.textInactiveText);
		lblFormaPago.setBounds(68, 426, 187, 24);
		lblFormaPago.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblFormaPago);

		txtFormaPago = new JComboBox();
		txtFormaPago.setBounds(68, 450, 289, 38);
		txtFormaPago.setBackground(SystemColor.text);
		txtFormaPago.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		txtFormaPago.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtFormaPago.setModel(new DefaultComboBoxModel(
				new String[] { "Tarjeta de Crédito", "Tarjeta de Débito", "Dinero en efectivo" }));
		panel.add(txtFormaPago);

		JSeparator separator_1_3 = new JSeparator();
		separator_1_3.setForeground(SystemColor.textHighlight);
		separator_1_3.setBackground(SystemColor.textHighlight);
		separator_1_3.setBounds(68, 488, 289, 2);
		panel.add(separator_1_3);

		JLabel lblGuardar = new JLabel("GUARDAR");
		lblGuardar.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuardar.setForeground(Color.WHITE);
		lblGuardar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblGuardar.setBounds(238, 503, 122, 35);
		panel.add(lblGuardar);

		JPanel btnguardar = new JPanel();
		btnguardar.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				if (txtHuesped.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "El huesped es requerido.");
					return;
				}

				if (habitacionSeleccionada == null) {
					JOptionPane.showMessageDialog(null, "El número de habitación es requerido.");
					return;
				}
				
				if (txtFechaEntrada.getDate() == null || txtFechaSalida.getDate() == null) {
					JOptionPane.showMessageDialog(null, "La fecha de Entrada y/o fecha de Salida son requeridas.");
					return;
				}
				
				if (txtFechaEntrada.getDate().after(txtFechaSalida.getDate())) {
					JOptionPane.showMessageDialog(null, "La fecha de Entrada no puede ser mayor que la fecha de Salida.");
					return;
				}
		
				if(opcion=='1') {
				   guardar();
				   MenuUsuario usuario = new MenuUsuario();
				   usuario.setVisible(true);
				}else {
				   actualizar();
				   Busqueda.button.doClick();
				}
				   dispose();
				
			}
		});
		btnguardar.setLayout(null);
		btnguardar.setBackground(new Color(3, 187, 133));
		btnguardar.setBounds(238, 503, 122, 35);
		panel.add(btnguardar);
		btnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
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

	private static long diasEntreFechas(Date primeraFecha, Date segundaFecha) throws IOException {
		return ChronoUnit.DAYS.between(primeraFecha.toInstant(), segundaFecha.toInstant());
	}

	private void calcularValor() {

		if ((txtFechaEntrada.getDate() != null) && (txtFechaSalida.getDate() != null) &&
				txtFechaSalida.getDate().after(txtFechaEntrada.getDate())) {
			try {
				dias = diasEntreFechas(txtFechaEntrada.getDate(), txtFechaSalida.getDate());
				if (habitacionSeleccionada != null) {
					txtValor.setText(
							String.valueOf((new BigDecimal(dias)).multiply(habitacionSeleccionada.getPrecio())));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void guardar() {

		reservaController.guardar(new Reserva(txtFechaEntrada.getDate(), txtFechaSalida.getDate(),
				new BigDecimal(txtValor.getText()), (String) txtFormaPago.getSelectedItem(),
				Integer.valueOf(txtIdHuesped.getText()), habitacionSeleccionada.getNumHabitacion()));
		JOptionPane.showMessageDialog(this, "Registrado con éxito!");
	}
	
	private void actualizar() {

		reservaController.actualizar(new Reserva(Integer.valueOf(txtIdReserva.getText()), txtFechaEntrada.getDate(), 
				txtFechaSalida.getDate(),new BigDecimal(txtValor.getText()), (String) txtFormaPago.getSelectedItem(),
				Integer.valueOf(txtIdHuesped.getText()), habitacionSeleccionada.getNumHabitacion()));
		JOptionPane.showMessageDialog(this, "Actualizado con éxito!");
	}

}
