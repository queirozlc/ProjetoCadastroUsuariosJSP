package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnection;
import model.ModelLogin;

public class UsuarioDAORepository {

	private Connection connection;

	public UsuarioDAORepository() {
		connection = SingleConnection.getConnection();

	}

	public ModelLogin gravarUsuario(ModelLogin model, Long idUsuarioLogado) throws SQLException {

		// Verifica se o id do usuario nao existe e grava um novo
		if (model.verificaId()) {

			String sql = "INSERT INTO model_login (login, senha, nome, email, usuario_id, perfil, sexo, cep, logradouro, localidade, uf, numero, bairro) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, model.getLogin());
			statement.setString(2, model.getSenha());
			statement.setString(3, model.getNome());
			statement.setString(4, model.getEmail());
			statement.setLong(5, idUsuarioLogado);
			statement.setString(6, model.getPerfil());
			statement.setString(7, model.getSexo());
			statement.setString(8, model.getCep());
			statement.setString(9, model.getLogradouro());
			statement.setString(10, model.getLocalidade());
			statement.setString(11, model.getUf());
			statement.setString(12, model.getNumero());
			statement.setString(13, model.getBairro());

			statement.execute();
			connection.commit();

			if (model.getFotoUser() != null && !model.getFotoUser().isEmpty()) {
				sql = "UPDATE model_login SET fotouser = ?, extensaofotouser = ? WHERE login = ?;";
				statement = connection.prepareStatement(sql);
				statement.setString(1, model.getFotoUser());
				statement.setString(2, model.getExtensaoFotoUser());
				statement.setString(3, model.getLogin());

				statement.execute();
				connection.commit();
			}

		} else {
			String sql = "UPDATE model_login SET login = ?, senha = ?, nome = ?, email = ?, perfil = ?, sexo = ?, cep = ?, logradouro = ?, localidade = ?, uf = ?, numero = ?, bairro = ? WHERE id = " + model.getId() + ";";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, model.getLogin());
			statement.setString(2, model.getSenha());
			statement.setString(3, model.getNome());
			statement.setString(4, model.getEmail());
			statement.setString(5, model.getPerfil());
			statement.setString(6, model.getSexo());
			statement.setString(7, model.getCep());
			statement.setString(8, model.getLogradouro());
			statement.setString(9, model.getLocalidade());
			statement.setString(10, model.getUf());
			statement.setString(11, model.getNumero());
			statement.setString(12, model.getBairro());

			statement.executeUpdate();
			connection.commit();

			if (model.getFotoUser() != null && !model.getFotoUser().isEmpty()) {
				sql = "UPDATE model_login SET fotouser = ?, extensaofotouser = ? WHERE id = ?;";
				statement = connection.prepareStatement(sql);
				statement.setString(1, model.getFotoUser());
				statement.setString(2, model.getExtensaoFotoUser());
				statement.setLong(3, model.getId());

				statement.execute();
				connection.commit();
			}

		}
		return this.consultarUsuarioLogin(model.getLogin(), idUsuarioLogado);
	}

	public List<ModelLogin> buscarUsuarioNome(String nome, Long userLogado) throws SQLException {
		List<ModelLogin> listaUsuarios = new ArrayList<ModelLogin>();

		String sql = "SELECT * FROM model_login WHERE upper(nome) LIKE upper(?) AND useradmin is false AND usuario_id = ? limit 5";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, "%" + nome + "%");
		preparedStatement.setLong(2, userLogado);
		ResultSet result = preparedStatement.executeQuery();

		while (result.next()) {
			ModelLogin model = new ModelLogin();
			model.setEmail(result.getString("email"));
			model.setId(result.getLong("id"));
			model.setLogin(result.getString("login"));
			model.setNome(result.getString("nome"));
			model.setUserAdmin(result.getBoolean("useradmin"));
			model.setPerfil(result.getString("perfil"));
			model.setSexo(result.getString("sexo"));

			listaUsuarios.add(model);
		}

		return listaUsuarios;
	}

	public ModelLogin consultarUsuarioLogin(String login, Long idUsuarioLogado) throws SQLException {

		ModelLogin model = new ModelLogin();
		String sql = "SELECT * FROM model_login WHERE upper(login) = upper(?) AND useradmin is false AND usuario_id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, login);
		statement.setLong(2, idUsuarioLogado);
		ResultSet result = statement.executeQuery();

		while (result.next()) {
			model.setId(result.getLong("id"));
			model.setEmail(result.getString("email"));
			model.setLogin(result.getString("login"));
			model.setNome(result.getString("nome"));
			model.setSenha(result.getString("senha"));
			model.setUserAdmin(result.getBoolean("useradmin"));
			model.setPerfil(result.getString("perfil"));
			model.setSexo(result.getString("sexo"));
			model.setFotoUser(result.getString("fotouser"));
			model.setCep(result.getString("cep"));
			model.setLogradouro(result.getString("logradouro"));
			model.setLocalidade(result.getString("localidade"));
			model.setUf(result.getString("uf"));
			model.setNumero(result.getString("numero"));
			model.setBairro(result.getString("bairro"));
		}

		return model;
	}

	public ModelLogin consultarUsuarioLoginSemId(String login) throws SQLException {

		ModelLogin model = new ModelLogin();
		String sql = "SELECT * FROM model_login WHERE upper(login) = upper(?) AND useradmin is false;";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, login);
		ResultSet result = statement.executeQuery();

		while (result.next()) {
			model.setId(result.getLong("id"));
			model.setEmail(result.getString("email"));
			model.setLogin(result.getString("login"));
			model.setNome(result.getString("nome"));
			model.setSenha(result.getString("senha"));
			model.setUserAdmin(result.getBoolean("useradmin"));
			model.setPerfil(result.getString("perfil"));
			model.setSexo(result.getString("sexo"));
			model.setFotoUser(result.getString("fotouser"));
			model.setCep(result.getString("cep"));
			model.setLogradouro(result.getString("logradouro"));
			model.setLocalidade(result.getString("localidade"));
			model.setUf(result.getString("uf"));
			model.setNumero(result.getString("numero"));
			model.setBairro(result.getString("bairro"));
			
		}

		return model;
	}

	public ModelLogin consultarUsuarioLogado(String login) throws SQLException {

		ModelLogin model = new ModelLogin();
		String sql = "SELECT * FROM model_login WHERE upper(login) = upper(?);";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, login);
		ResultSet result = statement.executeQuery();

		while (result.next()) {
			model.setId(result.getLong("id"));
			model.setEmail(result.getString("email"));
			model.setLogin(result.getString("login"));
			model.setNome(result.getString("nome"));
			model.setSenha(result.getString("senha"));
			model.setUserAdmin(result.getBoolean("useradmin"));
			model.setPerfil(result.getString("perfil"));
			model.setSexo(result.getString("sexo"));
			model.setFotoUser(result.getString("fotouser"));
			model.setCep(result.getString("cep"));
			model.setLogradouro(result.getString("logradouro"));
			model.setLocalidade(result.getString("localidade"));
			model.setUf(result.getString("uf"));
			model.setNumero(result.getString("numero"));
			model.setBairro(result.getString("bairro"));
		}

		return model;
	}

	public ModelLogin consultaPorId(String id, Long idUsuarioLogado) throws SQLException {
		ModelLogin model = new ModelLogin();

		String sql = "SELECT * FROM model_login WHERE id = ? AND useradmin is false AND usuario_id = ?;";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, Long.parseLong(id));
		preparedStatement.setLong(2, idUsuarioLogado);
		ResultSet result = preparedStatement.executeQuery();

		while (result.next()) {
			model.setId(result.getLong("id"));
			model.setEmail(result.getString("email"));
			model.setLogin(result.getString("login"));
			model.setNome(result.getString("nome"));
			model.setSenha(result.getString("senha"));
			model.setUserAdmin(result.getBoolean("useradmin"));
			model.setPerfil(result.getString("perfil"));
			model.setSexo(result.getString("sexo"));
			model.setFotoUser(result.getString("fotouser"));
			model.setExtensaoFotoUser(result.getString("extensaofotouser"));
			model.setCep(result.getString("cep"));
			model.setLogradouro(result.getString("logradouro"));
			model.setLocalidade(result.getString("localidade"));
			model.setUf(result.getString("uf"));
			model.setNumero(result.getString("numero"));
			model.setBairro(result.getString("bairro"));
		}

		return model;
	}

	public boolean validaLogin(String login) throws Exception {

		String sql = "SELECT COUNT(1) > 0 AS EXISTE FROM model_login WHERE upper(login) = upper(?);";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, login);
		ResultSet result = statement.executeQuery();
		result.next();
		return result.getBoolean("existe");

	}

	public void deletarUsuario(String idUser) throws SQLException {
		String sql = "DELETE FROM model_login WHERE id = ? AND useradmin is false;";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, Long.parseLong(idUser));
		preparedStatement.executeUpdate();
		connection.commit();
	}

	public List<ModelLogin> consultarTodosUsuarios(Long idUsuarioLogado) throws SQLException {
		List<ModelLogin> listaUsuarios = new ArrayList<ModelLogin>();

		String sql = "SELECT * FROM model_login WHERE useradmin IS false AND usuario_id = " + idUsuarioLogado + " limit 5";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultado = preparedStatement.executeQuery();

		while (resultado.next()) {
			ModelLogin model = new ModelLogin();
			model.setEmail(resultado.getString("email"));
			model.setId(resultado.getLong("id"));
			model.setLogin(resultado.getString("login"));
			model.setNome(resultado.getString("nome"));
			model.setPerfil(resultado.getString("perfil"));
			model.setSexo(resultado.getString("sexo"));

			listaUsuarios.add(model);
		}

		return listaUsuarios;
	}
	
	public int totalPaginas(Long usuarioLogado) throws SQLException {
		String sql = "SELECT COUNT(1) AS total FROM model_login WHERE usuario_id = " + usuarioLogado;
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultado = preparedStatement.executeQuery();
		resultado.next();
		Double cadastros = resultado.getDouble("total");
		Double numeroPaginas = 5.0;
		Double pagina = cadastros / numeroPaginas;
		Double resto = pagina % 2;
		
		if (resto > 0 ) {
			pagina++;
		}
		return pagina.intValue();
	}
	
	public List<ModelLogin> consultarListaUsuariosPaginada(Long idUsuarioLogado, Integer offset) throws SQLException {
		List<ModelLogin> listaUsuarios = new ArrayList<ModelLogin>();

		String sql = "SELECT * FROM model_login WHERE useradmin IS false AND usuario_id = "+ idUsuarioLogado +" ORDER BY nome OFFSET "+ offset +" limit 5";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultado = preparedStatement.executeQuery();

		while (resultado.next()) {
			ModelLogin model = new ModelLogin();
			model.setEmail(resultado.getString("email"));
			model.setId(resultado.getLong("id"));
			model.setLogin(resultado.getString("login"));
			model.setNome(resultado.getString("nome"));
			model.setPerfil(resultado.getString("perfil"));
			model.setSexo(resultado.getString("sexo"));

			listaUsuarios.add(model);
		}

		return listaUsuarios;
	}

}
