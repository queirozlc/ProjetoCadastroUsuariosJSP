package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnection;

public class VersionadorDAORepository implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private Connection connection;
	
	public VersionadorDAORepository() {
		connection = SingleConnection.getConnection();
	}
	
	public boolean arquivoSqlRodado(String nomeArquivo) throws Exception{
		
		String sql = "SELECT COUNT(1) > 0 AS rodado FROM versionadorbanco WHERE arquivo_sql = ?";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, nomeArquivo);

		ResultSet resultado = preparedStatement.executeQuery();

		resultado.next();

		return resultado.getBoolean("rodado");
	}
	
	public void gravarArquivoSQLRodado(String nomeArquivo) throws Exception{

		String sql = "INSERT INTO versionadorbanco(arquivo_sql) VALUES (?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, nomeArquivo);
		preparedStatement.execute();
	}
}
