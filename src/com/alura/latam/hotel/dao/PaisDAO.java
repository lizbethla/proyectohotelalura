package com.alura.latam.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.alura.latam.hotel.modelo.Pais;

public class PaisDAO {
	
	private Connection con;

	public PaisDAO(Connection con) {
		this.con = con;
	}
	
	public List<Pais> listar() {
		
		List<Pais> resultado = new ArrayList<>();

		try {
	
			String sql = "SELECT ID, NOMBRE FROM PAISES";
						
			final PreparedStatement statement = con.prepareStatement(sql);
			
			try (statement) {
				final ResultSet resultSet = statement.executeQuery();

				try (resultSet) {
					while (resultSet.next()) {
						var pais = new Pais(resultSet.getInt("ID"), resultSet.getString("NOMBRE"));
						resultado.add(pais);
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return resultado;
	}

	public void guardar(Pais pais) {
		
		try {
			PreparedStatement statement = con.prepareStatement(
					"INSERT INTO PAISES (NOMBRE)" + " VALUES(?)",
					Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, pais.getNombre());
			statement.execute();
			
			final ResultSet resultSet = statement.getGeneratedKeys();

			try (resultSet) {
				while (resultSet.next()) {
					System.out.println(String.format("Fue insertado el producto %s", pais));
				}

			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
