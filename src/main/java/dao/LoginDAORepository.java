package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnection;
import model.ModelLogin;

public class LoginDAORepository {

	private Connection connection;
	
	public LoginDAORepository() {
		connection = SingleConnection.getConnection();
	}
	
	public boolean validarLogin(ModelLogin model) throws SQLException {
		
		String sql = "SELECT * FROM model_login WHERE login = ? AND senha = ?";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, model.getLogin());
		statement.setString(2, model.getSenha());
		
		ResultSet result = statement.executeQuery();
		
		if (result.next()) {
			return true;
		}
		
		return false;
	}
	
}
