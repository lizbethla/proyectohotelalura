package com.alura.latam.hotel.controller;

import java.util.List;

import com.alura.latam.hotel.dao.ReservaDAO;
import com.alura.latam.hotel.factory.ConnectionFactory;
import com.alura.latam.hotel.modelo.Reserva;

public class ReservaController {

	private ReservaDAO reservaDAO;

	public ReservaController() {
		this.reservaDAO = new ReservaDAO(new ConnectionFactory().recuperarConexion());
	}
	
	public List<Reserva> buscarReserva(String numeroReserva) {
		return reservaDAO.buscarReserva(numeroReserva);
	}

	public void guardar(Reserva reserva) {
		reservaDAO.guardar(reserva);
	}

	public int eliminar(Integer id) {
		return reservaDAO.eliminar(id);
	}
	
	public void actualizar(Reserva reserva) {
		reservaDAO.actualizar(reserva);
	}
}
