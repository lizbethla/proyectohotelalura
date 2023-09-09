package com.alura.latam.hotel.modelo;

public class Pais {
	private Integer id;
	private String nombre;

	public Pais() {
	}

	public Pais(Integer id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return this.nombre;
	}

}
