package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	
	
	private static String url = "jdbc:postgresql://localhost:5432/projetojsp?autoReconnect=true";
	private static String user = "postgres";
	private static String senha = "admin"; 
	private static Connection connection = null;
	
	static {
		connect();
	}
	
	public SingleConnection() {
		connect();
	}
	
	private static void connect() {
		
		try {
			
			if (connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, senha);
				connection.setAutoCommit(false);
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection() {
		return connection;
	}	
	
	
}
