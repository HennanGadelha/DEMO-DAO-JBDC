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

	private static Connection conn = null;

	private static Properties loadProperties() {

		try (FileInputStream fs = new FileInputStream("db.properties")) {

			Properties props = new Properties();
			props.load(fs);

			return props;

		} catch (IOException e) {

			throw new DbException(e.getMessage());

		}

	}

	public static Connection startConnection() {

		if (conn == null) {

			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");

				conn = DriverManager.getConnection(url, props);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return conn;
	}

	public static void closeConnection() {


		if (conn != null) {

			try {
				conn.close();
			} catch (SQLException e) {

				throw new DbException(e.getMessage());
			}
		}

	}
		
	public static void closeStatement(Statement st) {

		if (st != null) {

			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DbException(e.getMessage());
			}

		}

	}

	public static void closeResultset(ResultSet result) {
		
		try {
			result.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DbException(e.getMessage());
		}
		
	}
}
