package com.alura.latam.hotel.controller;

import com.alura.latam.hotel.dao.UsuarioDAO;
import com.alura.latam.hotel.factory.ConnectionFactory;

public class UsuarioController {
	private UsuarioDAO usuarioDAO;

	public UsuarioController() {
		this.usuarioDAO = new UsuarioDAO(new ConnectionFactory().recuperarConexion());
	}
	
	public Boolean validarUsuario(String usuario, String clave) {
		return this.usuarioDAO.ValidarUsuario(usuario,clave);
	}

}
