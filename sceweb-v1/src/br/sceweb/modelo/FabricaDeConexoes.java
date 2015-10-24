package br.sceweb.modelo;

import java.sql.Connection;
import java.sql.DriverManager;

public class FabricaDeConexoes {

	public Connection getConnection(){
		String url = "jdbc:mysql://localhost/sceweb";
		
		try {
			return DriverManager.getConnection(url, "root", "aluno");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
			
		
	}
	
}
