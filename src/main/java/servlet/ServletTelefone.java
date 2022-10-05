package servlet;

import java.io.IOException;
import java.util.List;

import dao.TelefoneDAORepository;
import dao.UsuarioDAORepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;
import model.ModelTelefone;

@WebServlet("/ServletTelefone")
public class ServletTelefone extends ServletGenericUtil {

	private static final long serialVersionUID = 1L;

	private TelefoneDAORepository telefoneRepository = new TelefoneDAORepository();
	private UsuarioDAORepository usuarioRepository = new UsuarioDAORepository();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String idUser = request.getParameter("idUser");
			String acao = request.getParameter("acao");
			String telefoneUserPai = request.getParameter("userpai");
			
			if (acao != null && !acao.isEmpty() && acao.equals("excluir")) {
				String idTelefone = request.getParameter("id");
				telefoneRepository.deleteTelefone(Long.parseLong(idTelefone));
				
				ModelLogin model = usuarioRepository.consultaPorIdSemUsuarioLogado(Long.parseLong(telefoneUserPai));
				
				List<ModelTelefone> listaTelefones = telefoneRepository.listarTelefone(model.getId());
				request.setAttribute("listaTelefones", listaTelefones);
				
				request.setAttribute("msg", "Telefone deletado com sucesso!");
				request.setAttribute("modelLogin", model);
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
				return;
			}
			
			if (idUser != null && !idUser.isEmpty()) {
				
				ModelLogin model = usuarioRepository.consultaPorIdSemUsuarioLogado(Long.parseLong(idUser));
				List<ModelTelefone> listaTelefones = telefoneRepository.listarTelefone(model.getId());
				request.setAttribute("listaTelefones", listaTelefones);
				request.setAttribute("modelLogin", model);
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);

			} else {
				List<ModelLogin> listaUsuarios = usuarioRepository.consultarTodosUsuarios(super.getUserLogado(request));
				request.setAttribute("listaUsers", listaUsuarios);
				request.setAttribute("totalPaginas", usuarioRepository.totalPaginas(super.getUserLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			ModelTelefone modelTelefone = new ModelTelefone();
			
			String idUsuarioPai = request.getParameter("id");
			String numero = request.getParameter("numero");
			
			if (!telefoneRepository.existeNumero(numero)) {

				modelTelefone.setNumero(numero);
				modelTelefone.setIdUsuarioPaiCadastro(
						usuarioRepository.consultaPorIdSemUsuarioLogado(Long.parseLong(idUsuarioPai)));
				modelTelefone.setIdUsuarioQueCadastrou(super.getUserLogadoObjeto(request));

				telefoneRepository.gravarTelefone(modelTelefone);

				request.setAttribute("msg", "Telefone cadastrado com sucesso!");

			} else {
				request.setAttribute("msg", "Esse número de telefone já existe!");
			}

			List<ModelTelefone> listaTelefones = telefoneRepository.listarTelefone(Long.parseLong(idUsuarioPai));
			ModelLogin model = usuarioRepository.consultaPorIdSemUsuarioLogado(Long.parseLong(idUsuarioPai));
			request.setAttribute("modelLogin", model);
			request.setAttribute("listaTelefones", listaTelefones);
			request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
