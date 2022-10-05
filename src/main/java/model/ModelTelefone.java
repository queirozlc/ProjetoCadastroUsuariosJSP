package model;

import java.io.Serializable;
import java.util.Objects;

public class ModelTelefone implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	private Long id;
	private String numero;
	private ModelLogin idUsuarioPaiCadastro;
	private ModelLogin idUsuarioQueCadastrou;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public ModelLogin getIdUsuarioPaiCadastro() {
		return idUsuarioPaiCadastro;
	}
	public void setIdUsuarioPaiCadastro(ModelLogin idUsuarioPaiCadastro) {
		this.idUsuarioPaiCadastro = idUsuarioPaiCadastro;
	}
	public ModelLogin getIdUsuarioQueCadastrou() {
		return idUsuarioQueCadastrou;
	}
	public void setIdUsuarioQueCadastrou(ModelLogin idUsuarioQueCadastrou) {
		this.idUsuarioQueCadastrou = idUsuarioQueCadastrou;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelTelefone other = (ModelTelefone) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
