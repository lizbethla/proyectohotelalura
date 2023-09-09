package com.alura.latam.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.alura.latam.hotel.modelo.TipoHabitacion;

public class TipoHabitacionDAO {
	private Connection con;

	public TipoHabitacionDAO(Connection con) {
		this.con = con;
	}

	public List<TipoHabitacion> listar() {

		List<TipoHabitacion> resultado = new ArrayList<>();

		try {

			String sql = "SELECT ID, TIPO FROM TIPOHABITACION";

			final PreparedStatement statement = con.prepareStatement(sql);

			try (statement) {
				final ResultSet resultSet = statement.executeQuery();

				try (resultSet) {
					while (resultSet.next()) {
						var tipohabitacion = new TipoHabitacion(resultSet.getInt("ID"), resultSet.getString("TIPO"));
						resultado.add(tipohabitacion);
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return resultado;
	}

	public void guardar(TipoHabitacion tipohabitacion) {

		try {
			PreparedStatement statement = con.prepareStatement(
					"INSERT INTO TIPOHABITACION(TIPO, NUMCAMAS)" + " VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, tipohabitacion.getTipo());
			statement.setInt(2, tipohabitacion.getNumCamas());

			statement.execute();

			final ResultSet resultSet = statement.getGeneratedKeys();

			try (resultSet) {
				while (resultSet.next()) {
					System.out.println(String.format("Fue insertado el producto %s", tipohabitacion));
				}

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
