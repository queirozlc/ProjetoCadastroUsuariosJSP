package servlet;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import bean.BeanGraficoSalarioUsuario;
import dao.RelatorioDAORepository;
import dao.UsuarioDAORepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;
import util.ReportUtil;

@WebServlet("/ServletRelatorioController")
public class ServletRelatorioController extends ServletGenericUtil {
	private static final long serialVersionUID = 1L;
	
	private UsuarioDAORepository usuarioRepository = new UsuarioDAORepository();
	private RelatorioDAORepository relatorioRepository = new RelatorioDAORepository();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			
			String acao = request.getParameter("acao");
			
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("imprimirRelatorioUser")) {
				
				String dataInicial = request.getParameter("dataInicial");
				String dataFinal = request.getParameter("dataFinal");
				
				if (dataInicial == null || dataInicial.isEmpty() && dataFinal == null || dataFinal.isEmpty()) {
					
					request.setAttribute("listaUser", usuarioRepository.consultarTodosUsuariosSemLimitar(super.getUserLogado(request)));
					
				}else {
					request.setAttribute("listaUser", usuarioRepository.consultarTodosUsuariosRelatorio(super.getUserLogado(request), dataInicial, dataFinal));
					
				}
				
				request.setAttribute("dataInicial", dataInicial);
				request.setAttribute("dataFinal", dataFinal);
				request.getRequestDispatcher("principal/relatoriouser.jsp").forward(request, response);
			
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("imprimirRelatorioPDF")) {
				
				List<ModelLogin> listaUser;
				String dataInicial = request.getParameter("dataInicial");
				String dataFinal = request.getParameter("dataFinal");
				
				if (dataInicial == null || dataInicial.isEmpty() && dataFinal == null || dataFinal.isEmpty()) {
					listaUser = usuarioRepository.consultarTodosUsuariosSemLimitar(super.getUserLogado(request));
				
				}else {
					listaUser = usuarioRepository.consultarTodosUsuariosRelatorio(super.getUserLogado(request), dataInicial, dataFinal);
				}
				
				byte[] relatorio = new ReportUtil().gerarRelatorioPDF(listaUser, "relatoriouser_jsp", request.getServletContext());
				
				response.setHeader("Content-disposition", "attachment;filename=arquivo.pdf");
				response.getOutputStream().write(relatorio);
			
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("graficoSalario")) {
				
				String dataInicial = request.getParameter("dataInicial");
				String dataFinal = request.getParameter("dataFinal");
				
				if (dataInicial == null || dataInicial.isEmpty() && dataFinal == null || dataFinal.isEmpty()) {
					
					BeanGraficoSalarioUsuario beanGraficoSalario = relatorioRepository.montarGraficoMediaSalario(super.getUserLogado(request));
					ObjectMapper mapper = new ObjectMapper();
					String json = mapper.writeValueAsString(beanGraficoSalario);
					response.getWriter().write(json);
				
				} else {
					
					BeanGraficoSalarioUsuario beanGraficoSalario = relatorioRepository.
							montarGraficoMediaSalarioPorData(super.getUserLogado(request), dataInicial, dataFinal);
					ObjectMapper mapper = new ObjectMapper();
					String json = mapper.writeValueAsString(beanGraficoSalario);
					response.getWriter().write(json);
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
