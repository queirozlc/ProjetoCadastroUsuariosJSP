package servlet;

import java.io.IOException;

import dao.LoginDAORepository;
import dao.UsuarioDAORepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

@WebServlet(urlPatterns = { "/principal/LoginServlet", "/LoginServlet" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LoginDAORepository loginRepository = new LoginDAORepository();
	private UsuarioDAORepository usuarioRepository = new UsuarioDAORepository();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		
		if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("logout")) {
			request.getSession().invalidate();
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}else {
			doPost(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");

		try {
			if (login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
				ModelLogin modelLogin = new ModelLogin();
				modelLogin.setLogin(login);
				modelLogin.setSenha(senha);

				if (loginRepository.validarLogin(modelLogin)) {
					
					modelLogin = usuarioRepository.consultarUsuarioLogado(login);
					
					request.getSession().setAttribute("usuario", modelLogin.getLogin());
					request.getSession().setAttribute("perfil", modelLogin.getPerfil());
					request.getSession().setAttribute("imagemUser", modelLogin.getFotoUser());

					if (url == null || url.equals("null")) {
						url = "principal/principal.jsp";
					}

					RequestDispatcher dispatcher = request.getRequestDispatcher(url);
					dispatcher.forward(request, response);

				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
					request.setAttribute("msg", "Informe login e senha corretamente! ");
					dispatcher.forward(request, response);
				}

			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
				request.setAttribute("msg", "Login ou senha não informados !");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			dispatcher.forward(request, response);
		}

	}

}
