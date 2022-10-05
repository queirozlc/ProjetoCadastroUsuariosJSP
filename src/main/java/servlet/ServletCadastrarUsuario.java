package servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.tomcat.jakartaee.commons.compress.utils.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.UsuarioDAORepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.ModelLogin;

@MultipartConfig
@WebServlet(urlPatterns = { "/ServletCadastrarUsuario" })
public class ServletCadastrarUsuario extends ServletGenericUtil {
	private static final long serialVersionUID = 1L;
	private UsuarioDAORepository usuarioRepository = new UsuarioDAORepository();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String acao = request.getParameter("acao");

			// Metodo delete Comum
			/*
			 * if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("delete")) {
			 * String idUser = request.getParameter("id");
			 * usuarioRepository.deletarUsuario(idUser); request.setAttribute("msg",
			 * "Registro excluido com sucesso!");
			 * request.getRequestDispatcher("principal/usuario.jsp").forward(request,
			 * response);
			 * 
			 * }
			 */

			// Delete usando ajax
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deleteajax")) {
				String idUser = request.getParameter("id");
				usuarioRepository.deletarUsuario(idUser);
				response.getWriter().write("Registro excluido com sucesso!");

				// Select usando ajax

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUser")) {
				String nomeBusca = request.getParameter("nomeBusca");
				List<ModelLogin> usuariosJson = usuarioRepository.buscarUsuarioNome(nomeBusca, super.getUserLogado(request));

				ObjectMapper objectMapper = new ObjectMapper();
				String json = objectMapper.writeValueAsString(usuariosJson);
				
				response.addHeader("totalPagina", ""+ usuarioRepository.buscarUsuarioNomeEPaginacao(nomeBusca, this.getUserLogado(request)));
				response.getWriter().write(json);

			}else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjaxPage")) {
			
				String nomeBusca = request.getParameter("nomeBusca");
				String pagina = request.getParameter("pagina");
				List<ModelLogin> usuariosJson = usuarioRepository.buscaUsuariosPorNomeOffset(nomeBusca, this.getUserLogado(request), Integer.parseInt(pagina));

				ObjectMapper objectMapper = new ObjectMapper();
				String json = objectMapper.writeValueAsString(usuariosJson);
				
				response.addHeader("totalPagina", ""+ usuarioRepository.buscarUsuarioNomeEPaginacao(nomeBusca, this.getUserLogado(request)));
				response.getWriter().write(json);
				
				
				
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {// Editar usuario pelo botão
				
				String id = request.getParameter("id");
				ModelLogin model = usuarioRepository.consultaPorId(id, super.getUserLogado(request));

				List<ModelLogin> listaUsuarios = usuarioRepository.consultarTodosUsuarios(super.getUserLogado(request));
				request.setAttribute("listaUsers", listaUsuarios);

				request.setAttribute("msg", "Usuário em edição");
				request.setAttribute("modelLogin", model);
				request.setAttribute("totalPaginas", usuarioRepository.totalPaginas(super.getUserLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

				// listar todos usuarios na tela principal
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")) {

				List<ModelLogin> listaUsuarios = usuarioRepository.consultarTodosUsuarios(super.getUserLogado(request));
				request.setAttribute("listaUsers", listaUsuarios);
				request.setAttribute("msg", "Usuários Cadastrados:");
				request.setAttribute("totalPaginas", usuarioRepository.totalPaginas(super.getUserLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("downloadFoto")) {
				
				String idUser = request.getParameter("id");
				
				ModelLogin model = usuarioRepository.consultaPorId(idUser, super.getUserLogado(request));
				if(model.getFotoUser() != null && !model.getFotoUser().isEmpty()) {
					response.setHeader("Content-disposition", "attachment;filename=arquivo." + model.getExtensaoFotoUser());
					response.getOutputStream().write(new Base64().decode(model.getFotoUser().split("\\,")[1]));
				}
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("paginar")) {
				
				Integer offset = Integer.parseInt(request.getParameter("pagina"));
				
				List<ModelLogin> listaUsuarios = usuarioRepository.consultarListaUsuariosPaginada(this.getUserLogado(request), offset);
				request.setAttribute("listaUsers", listaUsuarios);
				request.setAttribute("totalPaginas", usuarioRepository.totalPaginas(this.getUserLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				
			} else {
				List<ModelLogin> listaUsuarios = usuarioRepository.consultarTodosUsuarios(super.getUserLogado(request));
				request.setAttribute("listaUsers", listaUsuarios);
				request.setAttribute("totalPaginas", usuarioRepository.totalPaginas(super.getUserLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
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

		try {
			String msg = "Operação realizada com sucesso !";
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String login = request.getParameter("login");
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			String perfil = request.getParameter("perfil");
			String sexo = request.getParameter("sexo");
			String cep = request.getParameter("cep");
			String logradouro = request.getParameter("logradouro");
			String localidade = request.getParameter("localidade");
			String uf = request.getParameter("uf");
			String numero = request.getParameter("numero");
			String bairro = request.getParameter("bairro");
			String dataNascimento = request.getParameter("dataNascimento");
			String salario = request.getParameter("salario");
			
			salario = salario.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
			
			ModelLogin model = new ModelLogin();
			model.setEmail(email);
			model.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			model.setLogin(login);
			model.setNome(nome);
			model.setSenha(senha);
			model.setPerfil(perfil);
			model.setSexo(sexo);
			model.setCep(cep);
			model.setLogradouro(logradouro);
			model.setLocalidade(localidade);
			model.setUf(uf);
			model.setNumero(numero);
			model.setBairro(bairro); 
			model.setDataNascimento(Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataNascimento))));
			model.setSalario(Double.valueOf(salario));
			
			if(ServletFileUpload.isMultipartContent(request)) {
				// Pegar foto da tela
				Part part = request.getPart("fileFoto");
				
				
				if (part.getSize() > 0) {
					byte[] foto = IOUtils.toByteArray(part.getInputStream());
					String imagemBase64 = "data:image/" + part.getContentType().split("\\/")[1] + ";base64,"
							+ new Base64().encodeBase64String(foto);

					model.setFotoUser(imagemBase64);
					model.setExtensaoFotoUser(part.getContentType().split("\\/")[1]);
				}
			}

			if (usuarioRepository.validaLogin(model.getLogin()) && model.getId() == null) {
				msg = "Usuário ja existente. Informe outro login!";
			} else {
				if (model.verificaId()) {
					msg = "Usuário cadastrado com sucesso !";
				} else {
					msg = "Usuário atualizado com sucesso !";
				}
				model = usuarioRepository.gravarUsuario(model, super.getUserLogado(request));
			}

			List<ModelLogin> listaUsuarios = usuarioRepository.consultarTodosUsuarios(super.getUserLogado(request));
			request.setAttribute("listaUsers", listaUsuarios);
			request.setAttribute("msg", msg);
			request.setAttribute("modelLogin", model);
			request.setAttribute("totalPaginas", usuarioRepository.totalPaginas(super.getUserLogado(request)));
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			dispatcher.forward(request, response);
		}
	}

}
