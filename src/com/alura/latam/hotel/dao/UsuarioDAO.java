package com.alura.latam.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
	private Connection con;

	public UsuarioDAO(Connection con) {
		this.con = con;
	}
	
	public Boolean ValidarUsuario(String usuario, String clave) {

		try {

			String sql = "SELECT USUARIO FROM USUARIOS WHERE USUARIO = ? AND CLAVE = ?";

			final PreparedStatement statement = con.prepareStatement(sql);

			try (statement) {
				
				statement.setString(1, usuario);
				statement.setString(2, clave);

				final ResultSet resultSet = statement.executeQuery();		

				try (resultSet) {
					while (resultSet.next()) {
						if (resultSet.getString("USUARIO") != null) return true;							
					}			
					
				  return false;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	
}
