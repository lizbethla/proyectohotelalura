package com.alura.latam.hotel.modelo;

public class TipoHabitacion {
	private Integer id;
	private String tipo;
	private Integer numCamas;

	public TipoHabitacion() {
	}

	public TipoHabitacion(Integer id, String tipo) {
		this.id = id;
		this.tipo = tipo;
	}

	public Integer getId() {
		return id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getNumCamas() {
		return numCamas;
	}

	public void setNumCamas(Integer numCamas) {
		this.numCamas = numCamas;
	}

	@Override
	public String toString() {
		return this.tipo;
	}

}
