package com.alura.latam.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.alura.latam.hotel.modelo.Huesped;

public class HuespedDAO {

	private Connection con;

	public HuespedDAO(Connection con) {
		this.con = con;
	}

	public List<Huesped> buscarHuesped(String apellido) {

		List<Huesped> resultado = new ArrayList<>();

		try {

			String sql = "SELECT H.ID, H.NOMBRE, H.APELLIDO, H.FECHANACIMIENTO, H.PAIS, P.NOMBRE, H.DIRECCION, H.TELEFONO, H.EMAIL"
					+ " FROM HUESPEDES H INNER JOIN PAISES P ON (H.PAIS = P.ID)"
					+ " WHERE APELLIDO LIKE CONCAT( '%',?,'%')";

			final PreparedStatement statement = con.prepareStatement(sql);

			try (statement) {
				statement.setString(1, apellido);
				final ResultSet resultSet = statement.executeQuery();

				try (resultSet) {
					while (resultSet.next()) {
						var huesped = new Huesped(resultSet.getInt("ID"), resultSet.getString("NOMBRE"),
								resultSet.getString("APELLIDO"), resultSet.getDate("FECHANACIMIENTO"),
								resultSet.getInt("PAIS"), resultSet.getString("P.NOMBRE"),resultSet.getString("DIRECCION"), 
								resultSet.getString("TELEFONO"),resultSet.getString("EMAIL"));
								
						resultado.add(huesped);
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return resultado;
	}

	public void guardar(Huesped huesped) {

		try {
			PreparedStatement statement = con.prepareStatement(
					"INSERT INTO HUESPEDES (NOMBRE,APELLIDO,FECHANACIMIENTO,DIRECCION,TELEFONO,EMAIL,PAIS)"
							+ " VALUES(?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			java.sql.Date fecha_Nacimiento = null;
			if (huesped.getFechaNacimiento() != null) {
				java.util.Date fechaNacimiento = huesped.getFechaNacimiento();
				fecha_Nacimiento = new java.sql.Date(fechaNacimiento.getTime());
			}
			statement.setString(1, huesped.getNombre());
			statement.setString(2, huesped.getApellido());
			statement.setDate(3, fecha_Nacimiento);
			statement.setString(4, huesped.getDireccion());
			statement.setString(5, huesped.getTelefono());
			statement.setString(6, huesped.getEmail());
			statement.setInt(7, huesped.getPais());

			statement.execute();

			final ResultSet resultSet = statement.getGeneratedKeys();

			try (resultSet) {
				while (resultSet.next()) {
					System.out.println(String.format("Fue insertado el producto %s", huesped));
				}

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int eliminar(Integer id) {
		try {
			final PreparedStatement statement = con.prepareStatement("DELETE FROM HUESPEDES WHERE ID = ?");
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

	public int actualizar(Huesped huesped) {
		try {

			final PreparedStatement statement = con.prepareStatement("UPDATE HUESPEDES SET NOMBRE = ?, "
					+ "APELLIDO = ? , FECHANACIMIENTO = ? , DIRECCION = ? , TELEFONO = ? , EMAIL = ?, PAIS = ? WHERE ID = ? " );
			
			java.sql.Date fecha_Nacimiento = null;
			if (huesped.getFechaNacimiento() != null) {
				java.util.Date fechaNacimiento = huesped.getFechaNacimiento();
				fecha_Nacimiento = new java.sql.Date(fechaNacimiento.getTime());
			}

			statement.setString(1, huesped.getNombre());
			statement.setString(2, huesped.getApellido());
			statement.setDate(3, fecha_Nacimiento);
			statement.setString(4, huesped.getDireccion());
			statement.setString(5, huesped.getTelefono());
			statement.setString(6, huesped.getEmail());
			statement.setInt(7, huesped.getPais());
			statement.setInt(8, huesped.getId());

			statement.execute();

			return statement.getUpdateCount();
			
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

}
