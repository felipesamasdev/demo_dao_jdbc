package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	// CRIAR METODOS STATICOS PARA CONECTAR E DESCONECTAR O BANCO DE DADOS

	public static Connection conn = null;

	// CONECTAR
	public static Connection getConnection() {

		if (conn == null) {

			try {

				Properties props = loadPropeties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);

			} catch (SQLException e) {

				throw new DbException(e.getMessage());

			}
		}

		return conn;
	}

	// FECHAR CONEÇÃO
	public static void closeConnection() {

		if (conn != null) {
			try {

				conn.close();

			} catch (SQLException e) {

				throw new DbException(e.getMessage());

			}
		}
	}

	// PEGAR AS VARIAVEIS DO BANCO DE DADOS ESCRITO NO ARQUIVO DB.PROPERTIES
	public static Properties loadPropeties() {

		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);

			return props;
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}

	}

	// FECHAR O METODO ST
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {

				st.close();

			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}

	}

	// FECHAR O METODO RS
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {

				rs.close();

			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}

	}

}
