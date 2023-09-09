package com.alura.latam.hotel.controller;

import java.util.List;

import com.alura.latam.hotel.dao.HabitacionDAO;
import com.alura.latam.hotel.factory.ConnectionFactory;
import com.alura.latam.hotel.modelo.Habitacion;
import com.alura.latam.hotel.modelo.TipoHabitacion;

public class HabitacionController {
	
	private HabitacionDAO habitacionDAO;

	public HabitacionController() {
		this.habitacionDAO = new HabitacionDAO(new ConnectionFactory().recuperarConexion());
	}
	
	public List<Habitacion> listar(TipoHabitacion tipoHabitacion) {
		return this.habitacionDAO.listar(tipoHabitacion.getId());
	}

	public TipoHabitacion buscarTipoHabitacion(Integer habitacion) {
		return this.habitacionDAO.buscarTipoHabitacion(habitacion);	
	}


}
