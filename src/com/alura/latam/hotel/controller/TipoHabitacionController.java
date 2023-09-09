package com.alura.latam.hotel.controller;

import java.util.List;

import com.alura.latam.hotel.dao.TipoHabitacionDAO;
import com.alura.latam.hotel.factory.ConnectionFactory;
import com.alura.latam.hotel.modelo.TipoHabitacion;

public class TipoHabitacionController {
	private TipoHabitacionDAO tipoHabitacionDAO;

	public TipoHabitacionController() {
		this.tipoHabitacionDAO = new TipoHabitacionDAO(new ConnectionFactory().recuperarConexion());
	}

	public List<TipoHabitacion> listar() {
		return this.tipoHabitacionDAO.listar();
	}
}
