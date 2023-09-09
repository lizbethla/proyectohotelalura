package com.alura.latam.hotel.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Component;

import com.alura.latam.hotel.controller.HuespedController;
import com.alura.latam.hotel.controller.PaisController;
import com.alura.latam.hotel.modelo.Huesped;
import com.alura.latam.hotel.modelo.Pais;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class RegistroHuesped extends JFrame {

	private JPanel contentPane;
	public static JTextField txtIdHuesped;
	public static JTextField txtNombre;
	public static JTextField txtApellido;
	public static JTextField txtDireccion;
	public static JTextField txtTelefono;
	public static JTextField txtEmail;
	public static JDateChooser txtFechaN;
	public static JComboBox<Pais> comboPaises;
	private JLabel labelExit;
	private JLabel labelAtras;
	int xMouse, yMouse;
	// Controller
	private HuespedController huespedController;
	private PaisController paisController;
	// Variable
	private Pais paisSeleccionado;

	/**
	 * Create the frame.
	 */
	public RegistroHuesped(char opcion) {
		// Controller
		this.huespedController = new HuespedController();
		this.paisController = new PaisController();

		setIconImage(
				Toolkit.getDefaultToolkit().getImage(RegistroHuesped.class.getResource("/imagenes/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 634);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane.setLayout(null);

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
		header.setBackground(SystemColor.text);
		header.setOpaque(false);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(new Color(12, 138, 199));
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setForeground(Color.WHITE);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		JLabel lblTitulo = new JLabel("REGISTRO HUÉSPED");
		lblTitulo.setBounds(560, 45, 285, 42);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.PLAIN, 23));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTitulo);

		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setBounds(562, 109, 253, 14);
		lblNombre.setForeground(SystemColor.textInactiveText);
		lblNombre.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblNombre);
		
		txtIdHuesped = new JTextField();
		txtIdHuesped.setBounds(540, 194, 20, 33);
		txtIdHuesped.setVisible(false);
		contentPane.add(txtIdHuesped);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtNombre.setBounds(560, 125, 285, 33);
		txtNombre.setBackground(Color.WHITE);
		txtNombre.setColumns(10);
		txtNombre.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtNombre);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setBounds(560, 160, 289, 2);
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2);

		JLabel lblApellido = new JLabel("APELLIDO");
		lblApellido.setBounds(560, 179, 255, 14);
		lblApellido.setForeground(SystemColor.textInactiveText);
		lblApellido.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblApellido);

		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtApellido.setBounds(560, 194, 285, 33);
		txtApellido.setColumns(10);
		txtApellido.setBackground(Color.WHITE);
		txtApellido.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtApellido);

		JSeparator separator_1_2_1 = new JSeparator();
		separator_1_2_1.setBounds(560, 230, 289, 2);
		separator_1_2_1.setForeground(new Color(12, 138, 199));
		separator_1_2_1.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_1);

		JLabel lblFechaN = new JLabel("FECHA DE NACIMIENTO");
		lblFechaN.setBounds(560, 246, 255, 14);
		lblFechaN.setForeground(SystemColor.textInactiveText);
		lblFechaN.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblFechaN);

		txtFechaN = new JDateChooser();
		txtFechaN.setBounds(560, 268, 285, 36);
		txtFechaN.getCalendarButton()
				.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaN.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaN.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtFechaN.setDateFormatString("yyyy-MM-dd");
		contentPane.add(txtFechaN);

		JSeparator separator_1_2_2 = new JSeparator();
		separator_1_2_2.setBounds(560, 304, 289, 2);
		separator_1_2_2.setForeground(new Color(12, 138, 199));
		separator_1_2_2.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_2);

		JLabel lblPais = new JLabel("PAIS");
		lblPais.setBounds(560, 316, 255, 14);
		lblPais.setForeground(SystemColor.textInactiveText);
		lblPais.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblPais);

		comboPaises = new JComboBox<>();
		comboPaises.setBounds(560, 340, 289, 36);
		comboPaises.setBackground(SystemColor.text);
		comboPaises.setFont(new Font("Roboto", Font.PLAIN, 16));
		comboPaises.addItem(new Pais(0, "Elige un País"));

		var paises = this.paisController.listar();
		paises.forEach(pais -> comboPaises.addItem(pais));
		contentPane.add(comboPaises);

		JSeparator separator_1_2_3 = new JSeparator();
		separator_1_2_3.setBounds(560, 376, 289, 2);
		separator_1_2_3.setForeground(new Color(12, 138, 199));
		separator_1_2_3.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_3);

		JLabel lblDireccion = new JLabel("DIRECCIÓN");
		lblDireccion.setBounds(562, 396, 253, 14);
		lblDireccion.setForeground(SystemColor.textInactiveText);
		lblDireccion.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblDireccion);

		txtDireccion = new JTextField();
		txtDireccion.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtDireccion.setBounds(560, 411, 285, 33);
		txtDireccion.setColumns(10);
		txtDireccion.setBackground(Color.WHITE);
		txtDireccion.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtDireccion);

		JSeparator separator_1_2_4 = new JSeparator();
		separator_1_2_4.setBounds(560, 444, 289, 2);
		separator_1_2_4.setForeground(new Color(12, 138, 199));
		separator_1_2_4.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_4);

		JLabel lblTelefono = new JLabel("TELÉFONO");
		lblTelefono.setBounds(560, 460, 253, 14);
		lblTelefono.setForeground(SystemColor.textInactiveText);
		lblTelefono.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblTelefono);

		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtTelefono.setBounds(560, 477, 285, 33);
		txtTelefono.setColumns(10);
		txtTelefono.setBackground(Color.WHITE);
		txtTelefono.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtTelefono);

		JSeparator separator_1_2_5 = new JSeparator();
		separator_1_2_5.setBounds(560, 511, 289, 2);
		separator_1_2_5.setForeground(new Color(12, 138, 199));
		separator_1_2_5.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_5);

		JLabel lblEmail = new JLabel("EMAIL");
		lblEmail.setBounds(560, 528, 253, 14);
		lblEmail.setForeground(SystemColor.textInactiveText);
		lblEmail.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtEmail.setBounds(560, 547, 285, 33);
		txtEmail.setColumns(10);
		txtEmail.setBackground(Color.WHITE);
		txtEmail.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtEmail);

		JSeparator separator_1_2_6 = new JSeparator();
		separator_1_2_6.setBounds(560, 580, 289, 2);
		separator_1_2_6.setForeground(new Color(12, 138, 199));
		separator_1_2_6.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_6);

		JPanel btnguardar = new JPanel();
		btnguardar.setBounds(723, 592, 122, 35);
		btnguardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtNombre.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "El Nombre es requerido.");
					return;
				}

				if (txtApellido.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "El Apellido es requerido.");
					return;
				}

				paisSeleccionado = (Pais) comboPaises.getSelectedItem();
				if (paisSeleccionado == null || paisSeleccionado.getId() == 0) {
					JOptionPane.showMessageDialog(null, "El Pais es requerido.");
					return;
				}

				if (opcion == '1') {
					guardar();
				} else {
					actualizar();
				}
				Busqueda.button.doClick();
				dispose();
			}
		});
		btnguardar.setLayout(null);
		btnguardar.setBackground(new Color(3, 187, 133));
		contentPane.add(btnguardar);
		btnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		JLabel labelGuardar = new JLabel("GUARDAR");
		labelGuardar.setHorizontalAlignment(SwingConstants.CENTER);
		labelGuardar.setForeground(Color.WHITE);
		labelGuardar.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelGuardar.setBounds(0, 0, 122, 35);
		btnguardar.add(labelGuardar);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 489, 634);
		panel.setBackground(new Color(12, 138, 199));
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(0, 121, 479, 502);
		panel.add(imagenFondo);
		imagenFondo.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/registro.png")));

		JLabel logo = new JLabel("");
		logo.setBounds(194, 39, 104, 107);
		panel.add(logo);
		logo.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/Ha-100px.png")));

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
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
		btnexit.setBackground(Color.white);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(SystemColor.black);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
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

	private void guardar() {

		var huesped = new Huesped(txtNombre.getText(), txtApellido.getText(), txtFechaN.getDate(),
				txtDireccion.getText(), txtTelefono.getText(), txtEmail.getText());

		this.huespedController.guardar(huesped, paisSeleccionado.getId());

		JOptionPane.showMessageDialog(this, "Registrado con éxito!");

	}
	
	private void actualizar() {
		this.huespedController.actualizar(new Huesped(Integer.valueOf(txtIdHuesped.getText()), txtNombre.getText(), txtApellido.getText(), txtFechaN.getDate(),
				 txtDireccion.getText(), txtTelefono.getText(), txtEmail.getText(), comboPaises.getSelectedIndex()));
		JOptionPane.showMessageDialog(this, "Actualizado con éxito!");
	}

}
