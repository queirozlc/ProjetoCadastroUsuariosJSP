package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import connection.SingleConnection;
import dao.VersionadorDAORepository;

@WebFilter(urlPatterns = { "/principal/*" })
public class LoginFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;

	private static Connection connection;

	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();

			String usuarioLogado = (String) session.getAttribute("usuario");
			String urlParaAutenticar = req.getServletPath();

			// Validar se esta logado, se nao estiver redireciona pra tela de login

			if (usuarioLogado == null && !urlParaAutenticar.equalsIgnoreCase("/principal/LoginServlet")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
				request.setAttribute("msg", "Por favor realize o Login !");
				dispatcher.forward(req, response);
				return;

			} else {
				chain.doFilter(request, response);
			}
			
			
			
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			dispatcher.forward(request, response);
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleConnection.getConnection();
		VersionadorDAORepository versionadorRepository = new VersionadorDAORepository();
		
		String caminhoPastaSQL = fConfig.getServletContext().getRealPath("versionadorbancosql") + File.separator;
		File[] sqlFiles = new File(caminhoPastaSQL).listFiles();
		
		for (File file : sqlFiles) {
			try {
				
				boolean arquivoJaRodado = versionadorRepository.arquivoSqlRodado(caminhoPastaSQL);
				
				if (!arquivoJaRodado) {
					FileInputStream entradaArquivo = new FileInputStream(file);
					Scanner lerArquivo = new Scanner(entradaArquivo, "UTF-8");
					StringBuilder sql = new StringBuilder();
					
					while(lerArquivo.hasNext()) {
						sql.append(lerArquivo.nextLine());
						sql.append("\n");
					}
					
					connection.prepareStatement(sql.toString()).execute();
					versionadorRepository.gravarArquivoSQLRodado(file.getName());
					connection.commit();
					lerArquivo.close();
				}
				
			} catch (Exception e) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			
		}
		
	}

}
