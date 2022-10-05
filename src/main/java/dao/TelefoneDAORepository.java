package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnection;
import model.ModelTelefone;

public class TelefoneDAORepository {
	
	private Connection connection;
	private UsuarioDAORepository usuarioRepository = new UsuarioDAORepository();
	
	public TelefoneDAORepository() {
		connection = SingleConnection.getConnection();
	}
	
	public void gravarTelefone(ModelTelefone model) throws Exception {
		
		String sql = "INSERT INTO telefone (numero, usuario_id, usuario_cad_id) VALUES (?, ?, ?);";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, model.getNumero());
		preparedStatement.setLong(2, model.getIdUsuarioPaiCadastro().getId());
		preparedStatement.setLong(3, model.getIdUsuarioQueCadastrou().getId());
		
		preparedStatement.execute();
		connection.commit();

	}
	
	public void deleteTelefone(Long idTelefone) throws SQLException {
		
		String sql = "DELETE FROM telefone WHERE id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, idTelefone);
		preparedStatement.executeUpdate();
		connection.commit();
		
	}
	
	public List<ModelTelefone> listarTelefone(Long idUserPai) throws SQLException {
		
		List<ModelTelefone> listaTelefones = new ArrayList<ModelTelefone>();
		
		String sql = "SELECT * FROM TELEFONE WHERE usuario_id = ?;";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, idUserPai);
		ResultSet resultado = preparedStatement.executeQuery();
		
		while(resultado.next()) {
			ModelTelefone model = new ModelTelefone();
			
			model.setId(resultado.getLong("id"));
			model.setNumero(resultado.getString("numero"));
			model.setIdUsuarioPaiCadastro(usuarioRepository.consultaPorIdSemUsuarioLogado(resultado.getLong("usuario_id")));
			model.setIdUsuarioQueCadastrou(usuarioRepository.consultaPorIdSemUsuarioLogado(resultado.getLong("usuario_cad_id")));
			
			listaTelefones.add(model);
			
		}
		
		return listaTelefones;
	}
	
	public boolean existeNumero(String numero) throws SQLException {
		
		String sql = "SELECT COUNT(1) > 0 AS existe FROM telefone WHERE numero = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, numero);
		
		ResultSet resultado = preparedStatement.executeQuery();
		
		resultado.next();
		
		
		return resultado.getBoolean("existe");
	}
	
}
