package servlet;

import java.io.Serializable;
import java.sql.SQLException;

import dao.UsuarioDAORepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ServletGenericUtil extends HttpServlet implements Serializable {

	private static final long serialVersionUID = 1L;

	private UsuarioDAORepository usuarioRepository = new UsuarioDAORepository();

	public Long getUserLogado(HttpServletRequest request) throws SQLException {

		HttpSession session = request.getSession();
		String usuarioLogado = (String) session.getAttribute("usuario");

		return usuarioRepository.consultarUsuarioLogado(usuarioLogado).getId();
	}
}
