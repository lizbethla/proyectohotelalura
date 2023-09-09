package com.alura.latam.hotel.controller;

import java.util.List;

import com.alura.latam.hotel.dao.PaisDAO;
import com.alura.latam.hotel.factory.ConnectionFactory;
import com.alura.latam.hotel.modelo.Pais;

public class PaisController {
	
	private PaisDAO paisDAO;

	public PaisController() {
		this.paisDAO = new PaisDAO(new ConnectionFactory().recuperarConexion());
	}
	
	public List<Pais> listar() {
		return this.paisDAO.listar();
	}

}
