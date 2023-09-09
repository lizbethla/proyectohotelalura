package com.alura.latam.hotel.controller;

import com.alura.latam.hotel.factory.ConnectionFactory;
import com.alura.latam.hotel.modelo.Huesped;
import com.alura.latam.hotel.dao.HuespedDAO;
import java.util.List;

public class HuespedController {

	private HuespedDAO huespedDAO;

	public HuespedController() {
		this.huespedDAO = new HuespedDAO(new ConnectionFactory().recuperarConexion());
	}

	public int eliminar(Integer id) {
		return huespedDAO.eliminar(id);
	}

	public void guardar(Huesped huesped, Integer paisId) {
		huesped.setPais(paisId);
		huespedDAO.guardar(huesped);
	}

	public List<Huesped> buscarHuesped(String apellido) {
		return huespedDAO.buscarHuesped(apellido);
	}

	public int actualizar(Huesped huesped) {
		return huespedDAO.actualizar(huesped);		
	}

}
