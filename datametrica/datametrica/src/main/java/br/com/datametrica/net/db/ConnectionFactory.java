package br.com.datametrica.net.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	static Connection con = null;

	public static Connection getConnection() throws Exception {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection("jdbc:sqlserver://10.48.200.97;database=master", "genesys", "genesys@2015");

		} catch (Exception e) {
			throw new Exception("Falha ao obter conexão com o Banco de Dados");
		}
		return con;
	}

	public static void closeConnection() throws Exception {
		try {
			con.close();
		} catch (Exception e) {
			throw new Exception("Connection já está fechada");
		}
	}

}
