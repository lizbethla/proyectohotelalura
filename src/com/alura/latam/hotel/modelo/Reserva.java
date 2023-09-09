package com.alura.latam.hotel.modelo;

import java.math.BigDecimal;
import java.util.Date;


public class Reserva {
	private Integer id;
	private Date fechaEntrada;
	private Date fechaSalida;
	private BigDecimal importe = new BigDecimal(0);
	private String formaPago;
	private Integer huesped;
	private Integer habitacion;
	private String nombreHuesped;
	
	public Reserva() {
	}

	public Reserva(Date fechaEntrada, Date fechaSalida, BigDecimal importe, String formaPago, Integer huesped,
			Integer habitacion) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.importe = importe;
		this.formaPago = formaPago;
		this.huesped = huesped;
		this.habitacion = habitacion;
	}
	
	public Reserva(Integer id, Date fechaEntrada, Date fechaSalida, BigDecimal importe, String formaPago, Integer huesped,
			Integer habitacion) {
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.importe = importe;
		this.formaPago = formaPago;
		this.huesped = huesped;
		this.habitacion = habitacion;
	}

	public Reserva(Integer id, Date fechaEntrada, Date fechaSalida, BigDecimal importe, String formaPago, Integer huesped,
			String nombrehuesped, Integer habitacion) {
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.importe = importe;
		this.formaPago = formaPago;
		this.huesped = huesped;
		this.nombreHuesped = nombrehuesped;
		this.habitacion = habitacion;
	}
	
	public Integer getId() {
		return id;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public Integer getHuesped() {
		return huesped;
	}

	public void setHuesped(Integer huesped) {
		this.huesped = huesped;
	}

	public Integer getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(Integer habitacion) {
		this.habitacion = habitacion;
	}

	public String getNombreHuesped() {
		return nombreHuesped;
	}

}
