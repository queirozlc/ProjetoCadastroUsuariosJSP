package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import bean.BeanGraficoSalarioUsuario;
import connection.SingleConnection;

public class RelatorioDAORepository implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection connection;

	public RelatorioDAORepository() {
		connection = SingleConnection.getConnection();
	}

	public BeanGraficoSalarioUsuario montarGraficoMediaSalario(Long idUsuarioLogado) throws SQLException {
		DecimalFormat df = new DecimalFormat("####.00");
		List<String> listaPerfil = new ArrayList<String>();
		List<Double> salarios = new ArrayList<Double>();
		List<Double> listaMediaSalarial = new ArrayList<Double>();
		BeanGraficoSalarioUsuario beanGraficoSalarioUsuario = new BeanGraficoSalarioUsuario();

		String sql = "SELECT avg(salario) AS mediasalario, perfil from model_login WHERE usuario_id = ? GROUP BY perfil;";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, idUsuarioLogado);
		ResultSet resultado = preparedStatement.executeQuery();

		while (resultado.next()) {
			Double mediaSalario = resultado.getDouble("mediasalario");
			String perfil = resultado.getString("perfil");

			listaPerfil.add(perfil);
			salarios.add(mediaSalario);
		}

		beanGraficoSalarioUsuario.setListaPerfil(listaPerfil);

		for (Double salario : salarios) {
			Double salarioFormatado = Double.valueOf(df.format(salario));
			listaMediaSalarial.add(salarioFormatado);
		}

		beanGraficoSalarioUsuario.setListaMediaSalarial(listaMediaSalarial);

		return beanGraficoSalarioUsuario;
	}

	public BeanGraficoSalarioUsuario montarGraficoMediaSalarioPorData(Long idUsuarioLogado, String dataInicial,
			String dataFinal) throws SQLException, ParseException {
		
		DecimalFormat df = new DecimalFormat("####.00");
		List<String> listaPerfil = new ArrayList<String>();
		List<Double> salarios = new ArrayList<Double>();
		List<Double> listaMediaSalarial = new ArrayList<Double>();
		BeanGraficoSalarioUsuario beanGraficoSalarioUsuario = new BeanGraficoSalarioUsuario();

		String sql = "SELECT avg(salario) AS mediasalario, perfil from model_login WHERE usuario_id = ? AND datanascimento >= ? AND datanascimento <= ? GROUP BY perfil;";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, idUsuarioLogado);
		preparedStatement.setDate(2, Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataInicial))));
		preparedStatement.setDate(3, Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataFinal))));
		ResultSet resultado = preparedStatement.executeQuery();

		while (resultado.next()) {
			Double mediaSalario = resultado.getDouble("mediasalario");
			String perfil = resultado.getString("perfil");

			listaPerfil.add(perfil);
			salarios.add(mediaSalario);
		}

		beanGraficoSalarioUsuario.setListaPerfil(listaPerfil);

		for (Double salario : salarios) {
			Double salarioFormatado = Double.valueOf(df.format(salario));
			listaMediaSalarial.add(salarioFormatado);
		}

		beanGraficoSalarioUsuario.setListaMediaSalarial(listaMediaSalarial);

		return beanGraficoSalarioUsuario;
	}

}
