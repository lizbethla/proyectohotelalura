package com.alura.latam.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.alura.latam.hotel.modelo.Habitacion;
import com.alura.latam.hotel.modelo.TipoHabitacion;

public class HabitacionDAO {

	private Connection con;

	public HabitacionDAO(Connection con) {
		this.con = con;
	}

	public List<Habitacion> listar(Integer tipohabitacionId) {
		
		List<Habitacion> resultado = new ArrayList<>();

		try {
	
			String sql = "SELECT NUMHABITACION, PRECIO FROM HABITACIONES WHERE TIPO=? AND ESTADOOCUPADA=?";
						 
			final PreparedStatement statement = con.prepareStatement(sql);
			
			try (statement) {
				statement.setInt(1, tipohabitacionId);
				statement.setString(2, "NO");
				
				final ResultSet resultSet = statement.executeQuery();

				try (resultSet) {
					while (resultSet.next()) {
						var habitacion = new Habitacion(resultSet.getInt("NUMHABITACION"), resultSet.getBigDecimal("PRECIO"));
						resultado.add(habitacion);
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return resultado;
	}
	
	public void guardar(Habitacion habitacion) {
		try {
			PreparedStatement statement = con.prepareStatement(
					"INSERT INTO HABITACIONES(NUMHABITACION, TIPO, ESTADOOCUPADA)" + " VALUES(?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			statement.setInt(1, habitacion.getNumHabitacion());
			statement.setInt(2, habitacion.getTipo());
			statement.setString(3, habitacion.getEstadoOcupada());

			statement.execute();

			final ResultSet resultSet = statement.getGeneratedKeys();

			try (resultSet) {
				while (resultSet.next()) {
					System.out.println(String.format("Fue insertado el producto %s", habitacion));
				}

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public TipoHabitacion buscarTipoHabitacion(Integer habitacionId) {

		TipoHabitacion tipohabitacion = null;
		
		try {
	
			String sql = "SELECT TH.ID, TH.TIPO FROM HABITACIONES HA INNER JOIN TIPOHABITACION TH "
					+ " ON (HA.TIPO = TH.ID) WHERE NUMHABITACION = ?";
						 
			final PreparedStatement statement = con.prepareStatement(sql);
			
			try (statement) {
				statement.setInt(1, habitacionId);
				
				final ResultSet resultSet = statement.executeQuery();

				try (resultSet) {
					while (resultSet.next()) {
						tipohabitacion = new TipoHabitacion(resultSet.getInt("TH.ID"), resultSet.getString("TH.TIPO"));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return tipohabitacion;
	}
}
