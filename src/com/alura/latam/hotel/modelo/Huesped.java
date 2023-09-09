package com.alura.latam.hotel.modelo;

import java.util.Date;

public class Huesped {

	private Integer id;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private String direccion;
	private String telefono;
	private String email;
	private Integer pais;
	private String nombrePais;

	public Huesped() {
	}
	
	public Huesped(Integer id, String nombre, String apellido, Date fechaNacimiento, String direccion, String telefono,
			String email, Integer pais) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.pais = pais;
	}

	public Huesped(String nombre, String apellido, Date fechaNacimiento, String direccion, String telefono,
			String email) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
	}
		
	public Huesped(Integer id, String nombre, String apellido, Date fechaNacimiento, Integer pais, String nombrePais, 
			String direccion, String telefono, String email) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.pais = pais;
		this.nombrePais = nombrePais;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date date) {
		this.fechaNacimiento = date;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getPais() {
		return pais;
	}

	public void setPais(Integer pais) {
		this.pais = pais;
	}

	public String getNombrePais() {
		return nombrePais;
	}

}
