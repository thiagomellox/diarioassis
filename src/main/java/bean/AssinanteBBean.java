package bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import dao.AssinanteDAO;
import dao.EntregadorDAO;
import entity.Assinante;
import entity.Entregador;

@ManagedBean
public class AssinanteBBean {

	private String nome;
	private String endereco;
	private String cidade;
	private String telefone;
	private String valorMensal;
	private String valorAnual;
	private String dataCadastro;
	private String dataValidade;

	private List<Assinante> listaGrid;
	private List<SelectItem> entregadorSelectItem;
	
	private Integer codEntregador;
	
	private AssinanteDAO dao = new AssinanteDAO();
	private EntregadorDAO entregadorDAO = new EntregadorDAO();
	
	public AssinanteBBean() {
		entregadorSelectItem = new ArrayList<SelectItem>();
		for(Entregador entregador : entregadorDAO.listAll()){
			entregadorSelectItem.add(new SelectItem(entregador.getCodentregador(), entregador.getNome()));
		}
	}

	public String salvar() {

		System.out.println(nome);

		List<Assinante> asas = dao.listAll();

		return "";
	}
	
	public final String pesquisar() {
		System.out.println(nome);
		
		listaGrid = dao.findByName(nome,"");
		
		return "";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getValorMensal() {
		return valorMensal;
	}

	public void setValorMensal(String valorMensal) {
		this.valorMensal = valorMensal;
	}

	public String getValorAnual() {
		return valorAnual;
	}

	public void setValorAnual(String valorAnual) {
		this.valorAnual = valorAnual;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
	}

	public List<Assinante> getListaGrid() {
		return listaGrid;
	}

	public void setListaGrid(List<Assinante> listaGrid) {
		this.listaGrid = listaGrid;
	}

	public List<SelectItem> getEntregadorSelectItem() {
		return entregadorSelectItem;
	}

	public void setEntregadorSelectItem(List<SelectItem> entregadorSelectItem) {
		this.entregadorSelectItem = entregadorSelectItem;
	}

	public Integer getCodEntregador() {
		return codEntregador;
	}

	public void setCodEntregador(Integer codEntregador) {
		this.codEntregador = codEntregador;
	}

}
