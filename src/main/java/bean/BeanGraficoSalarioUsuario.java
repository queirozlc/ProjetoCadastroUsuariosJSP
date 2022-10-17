package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BeanGraficoSalarioUsuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<String> listaPerfil = new ArrayList<String>();
	private List<Double> listaMediaSalarial = new ArrayList<Double>();

	public List<String> getListaPerfil() {
		return listaPerfil;
	}

	public void setListaPerfil(List<String> listaPerfil) {
		this.listaPerfil = listaPerfil;
	}

	public List<Double> getListaMediaSalarial() {
		return listaMediaSalarial;
	}

	public void setListaMediaSalarial(List<Double> listaMediaSalarial) {
		this.listaMediaSalarial = listaMediaSalarial;
	}

}
