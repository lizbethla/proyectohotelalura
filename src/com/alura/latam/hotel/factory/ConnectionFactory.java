package com.alura.latam.hotel.factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	
	private DataSource datasource;
	
	public ConnectionFactory() {
		var pooledDataSource = new ComboPooledDataSource();
		pooledDataSource.setJdbcUrl("jdbc:mysql://localhost/hotel_alura?useTimeZone=true&serverTimeZone=UTC");
		pooledDataSource.setUser("root");
		pooledDataSource.setPassword("mysqlserver");
		pooledDataSource.setMaxPoolSize(10);
		
		this.datasource= pooledDataSource;
	}

	public Connection recuperarConexion() {
		
		try {
			return this.datasource.getConnection();		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
