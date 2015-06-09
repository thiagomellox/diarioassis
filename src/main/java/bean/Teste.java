package bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Teste {
	
	private List<Lista> listas;
	
	public Teste(){
		listas = new ArrayList<Lista>();
		Lista lista = new Lista();
		lista.setCodigo("1");
		lista.setNome("teste");
		listas.add(lista);
	}

	public List<Lista> getListas() {
		return listas;
	}

	public void setListas(List<Lista> lista) {
		this.listas = lista;
	}
	
	
	
}
