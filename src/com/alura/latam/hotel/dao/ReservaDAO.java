package com.alura.latam.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.alura.latam.hotel.modelo.Reserva;

public class ReservaDAO {
	private Connection con;

	public ReservaDAO(Connection con) {
		this.con = con;
	}

	public List<Reserva> buscarReserva(String numeroReserva) {

		List<Reserva> resultado = new ArrayList<>();

		try {

			String sql = "SELECT R.ID, R.FECHAENTRADA, R.FECHASALIDA, R.IMPORTE, R.FORMAPAGO, HUESPED,"
					+ " H.NOMBRE, H.APELLIDO, R.HABITACION"
					+ " FROM RESERVAS R INNER JOIN HUESPEDES H ON (R.HUESPED = H.ID)"
					+ " WHERE R.ID LIKE CONCAT( '%',?,'%')";

			final PreparedStatement statement = con.prepareStatement(sql);

			try (statement) {
				statement.setString(1, numeroReserva);
				final ResultSet resultSet = statement.executeQuery();

				try (resultSet) {
					while (resultSet.next()) {
						var reserva = new Reserva(resultSet.getInt("ID"), resultSet.getDate("FECHAENTRADA"),
								resultSet.getDate("FECHASALIDA"), resultSet.getBigDecimal("IMPORTE"),
								resultSet.getString("FORMAPAGO"), resultSet.getInt("HUESPED"), 
								resultSet.getString("NOMBRE") +' '+ resultSet.getString("APELLIDO"),
								resultSet.getInt("HABITACION"));
						resultado.add(reserva);
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return resultado;
	}

	public void guardar(Reserva reserva) {
		try {
			PreparedStatement statement = con.prepareStatement(
					"INSERT INTO RESERVAS(FECHAENTRADA, FECHASALIDA, IMPORTE, FORMAPAGO, HUESPED, HABITACION)"
							+ " VALUES(?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			java.util.Date fechaEntrada = reserva.getFechaEntrada();
			java.sql.Date fecha_Entrada = new java.sql.Date(fechaEntrada.getTime());
			java.util.Date fechaSalida = reserva.getFechaSalida();
			java.sql.Date fecha_Salida = new java.sql.Date(fechaSalida.getTime());

			statement.setDate(1, fecha_Entrada);
			statement.setDate(2, fecha_Salida);
			statement.setBigDecimal(3, reserva.getImporte());
			statement.setString(4, reserva.getFormaPago());
			statement.setInt(5, reserva.getHuesped());
			statement.setInt(6, reserva.getHabitacion());

			statement.execute();

			final ResultSet resultSet = statement.getGeneratedKeys();

			try (resultSet) {
				while (resultSet.next()) {
					System.out.println(String.format("Fue insertado el producto %s", reserva));
				}

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int eliminar(Integer id) {
		try {
			final PreparedStatement statement = con.prepareStatement("DELETE FROM RESERVAS WHERE ID = ?");
			try (statement) {
				statement.setInt(1, id);
				statement.execute();
				return statement.getUpdateCount();
			}
		} catch (SQLException e) {
			System.out.println("Mensaje errorr : " + e.getMessage());
			System.out.println("Codigo error : " + e.getErrorCode());
			System.out.println("Estado SQL : " + e.getSQLState());
			return 0;
		}
	}

	public int actualizar(Reserva reserva) {
		try {

			final PreparedStatement statement = con.prepareStatement("UPDATE RESERVAS SET FECHAENTRADA = ?, "
					+ "FECHASALIDA = ? , IMPORTE = ? , FORMAPAGO = ? , HUESPED = ? , HABITACION = ? WHERE ID = ? " );
			
			java.util.Date fechaEntrada = reserva.getFechaEntrada();
			java.sql.Date fecha_Entrada = new java.sql.Date(fechaEntrada.getTime());
			java.util.Date fechaSalida = reserva.getFechaSalida();
			java.sql.Date fecha_Salida = new java.sql.Date(fechaSalida.getTime());

			statement.setDate(1, fecha_Entrada);
			statement.setDate(2, fecha_Salida);
			statement.setBigDecimal(3, reserva.getImporte());
			statement.setString(4, reserva.getFormaPago());
			statement.setInt(5, reserva.getHuesped());
			statement.setInt(6, reserva.getHabitacion());
			statement.setInt(7, reserva.getId());

			statement.execute();

			return statement.getUpdateCount();
			
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

}
