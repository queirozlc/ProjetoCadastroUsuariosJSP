package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnection;
import model.ModelLogin;
import model.ModelTelefone;

public class UsuarioDAORepository {

	private Connection connection;

	public UsuarioDAORepository() {
		connection = SingleConnection.getConnection();

	}

	public ModelLogin gravarUsuario(ModelLogin model, Long idUsuarioLogado) throws SQLException {

		// Verifica se o id do usuario nao existe e grava um novo
		if (model.verificaId()) {

			String sql = "INSERT INTO model_login (login, senha, nome, email, usuario_id, perfil, sexo, cep, logradouro, localidade, uf, numero, bairro, datanascimento, salario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
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
			statement.setDate(14, model.getDataNascimento());
			statement.setDouble(15, model.getSalario());

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
			String sql = "UPDATE model_login SET login = ?, senha = ?, nome = ?, email = ?, perfil = ?, sexo = ?, cep = ?, logradouro = ?, localidade = ?, uf = ?, numero = ?, bairro = ?, datanascimento = ?, salario = ? WHERE id = " + model.getId() + ";";
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
			statement.setDate(13, model.getDataNascimento());
			statement.setDouble(14, model.getSalario());

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
	
	public int buscarUsuarioNomeEPaginacao(String nome, Long userLogado) throws SQLException {

		String sql = "SELECT COUNT(1) AS total FROM model_login WHERE upper(nome) LIKE upper(?) AND useradmin is false AND usuario_id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, "%" + nome + "%");
		preparedStatement.setLong(2, userLogado);
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
			model.setDataNascimento(result.getDate("datanascimento"));
			model.setSalario(result.getDouble("salario"));
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
			model.setDataNascimento(result.getDate("datanascimento"));
			model.setSalario(result.getDouble("salario"));
			
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
			model.setDataNascimento(result.getDate("datanascimento"));
			model.setSalario(result.getDouble("salario"));
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
			model.setDataNascimento(result.getDate("datanascimento"));
			model.setSalario(result.getDouble("salario"));
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
	
	public List<ModelLogin> buscaUsuariosPorNomeOffset(String nome, Long idUsuarioLogado, Integer offset) throws SQLException {
		List<ModelLogin> listaUsuarios = new ArrayList<ModelLogin>();

		String sql = "SELECT * FROM model_login WHERE upper(nome) LIKE upper(?) AND useradmin is false AND usuario_id = ? OFFSET "+offset+" LIMIT 5;";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, "%" + nome + "%");
		preparedStatement.setLong(2, idUsuarioLogado);
		
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
	
	public ModelLogin consultaPorIdSemUsuarioLogado(Long id) throws SQLException {
		ModelLogin model = new ModelLogin();

		String sql = "SELECT * FROM model_login WHERE id = ? AND useradmin is false;";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, id);
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
			model.setDataNascimento(result.getDate("datanascimento"));
			model.setSalario(result.getDouble("salario"));
		}

		return model;
	}

	public List<ModelLogin> consultarTodosUsuariosSemLimitar(Long userLogado) throws SQLException {
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();

		String sql = "select * from model_login where useradmin is false and usuario_id = " + userLogado;
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) { /* percorrer as linhas de resultado do SQL */

			ModelLogin modelLogin = new ModelLogin();

			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			modelLogin.setDataNascimento(resultado.getDate("datanascimento"));
			modelLogin.setListaTelefones(this.listarTelefone(modelLogin.getId()));

			retorno.add(modelLogin);
		}

		return retorno;
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
			model.setIdUsuarioPaiCadastro(this.consultaPorIdSemUsuarioLogado(resultado.getLong("usuario_id")));
			model.setIdUsuarioQueCadastrou(this.consultaPorIdSemUsuarioLogado(resultado.getLong("usuario_cad_id")));
			
			listaTelefones.add(model);
			
		}
		
		return listaTelefones;
	}

	public List<ModelLogin> consultarTodosUsuariosRelatorio(Long userLogado, String dataInicial, String dataFinal) throws SQLException, ParseException {
List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		String sql = "select * from model_login where useradmin is false and usuario_id = " + userLogado + " and datanascimento >= ? and datanascimento <= ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setDate(1, Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataInicial))));
		statement.setDate(2, Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataFinal))));
		
		ResultSet resultado = statement.executeQuery();
		
		while (resultado.next()) { /*percorrer as linhas de resultado do SQL*/
			
			ModelLogin modelLogin = new ModelLogin();

			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			// modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setDataNascimento(resultado.getDate("datanascimento"));
			modelLogin.setSexo(resultado.getString("sexo"));
			modelLogin.setListaTelefones(this.listarTelefone(modelLogin.getId()));

			retorno.add(modelLogin);
		}

		return retorno;
	}
}
