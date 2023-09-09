package com.alura.latam.hotel.modelo;

import java.math.BigDecimal;

public class Habitacion {
	private Integer numHabitacion;
	private Integer tipo;
	private BigDecimal precio = new BigDecimal(0);
	private String estadoOcupada;

	public Habitacion() {
	}

	public Habitacion(Integer numHabitacion, BigDecimal precio) {
		this.numHabitacion = numHabitacion;
		this.precio = precio;
	}


	public Integer getNumHabitacion() {
		return numHabitacion;
	}

	public void setNumHabitacion(Integer numHabitacion) {
		this.numHabitacion = numHabitacion;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public String getEstadoOcupada() {
		return estadoOcupada;
	}

	public void setEstadoOcupada(String estadoOcupada) {
		this.estadoOcupada = estadoOcupada;
	}

	@Override
	public String toString() {
		return "" + numHabitacion;
	}

}
