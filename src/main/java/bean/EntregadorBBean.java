package bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import util.FacesUtils;
import dao.AssinanteDAO;
import dao.EntregadorDAO;
import dto.EntregadorDTO;
import entity.Entregador;

@ManagedBean
@SessionScoped
public class EntregadorBBean {

	private Integer codentregador;
	private String nome;
	private String cpf;
	private String telefone;
	private String endereco;
	private String bairro;
	private String cidade;

	private List<EntregadorDTO> listaGrid;
	
	private String nomePesquisa;
	private boolean alterando;
	
	private AssinanteDAO assinanteDAO = new AssinanteDAO();
	private EntregadorDAO entregadorDAO = new EntregadorDAO();
	

	public String salvar() {
		try{
			Entregador entregador = new Entregador();
			if(isAlterando()){
				entregador.setCodentregador(codentregador);
			}
			entregador.setNome(nome);
			entregador.setCidade(cidade);
			entregador.setBairro(bairro);
			entregador.setEndereco(endereco);
			entregador.setTelefone(telefone);
			entregador.setCpf(cpf);
			
			if(isAlterando()){
				entregadorDAO.update(entregador);
			}else{
				entregadorDAO.save(entregador);
			}
			
			FacesUtils.addInfoMessage("Salvo com sucesso!");
			RequestContext.getCurrentInstance().addCallbackParam("sucess", true);
			return "";
		} catch (Exception e) {
			FacesUtils.addErrorMessage("Houve um problema ao tentar salvar. " + e.getMessage());
			RequestContext.getCurrentInstance().addCallbackParam("sucess", false);
			return "";
		}

		//return listar();
	}
	
	public String listar() {
		resetInclusao();
		listaGrid = new ArrayList<EntregadorDTO>();
		return "listarEntregador";
	}
	
	public String cancelar() {
		return "listarEntregador";
	}
	
	private void resetInclusao(){
		nome = null;
		endereco = null;
		bairro = null;
		cidade = null;
		telefone = null;
		cpf = null;
		alterando = false;
	}
	
	public String abrirIncluir() {
		
		resetInclusao();
		
		return "incluirEntregador";
	}
	
	public String abrirAlterar() {
		try{
			Entregador ass = entregadorDAO.findById(codentregador);
			
			nome = ass.getNome();
			endereco = ass.getEndereco();
			cidade = ass.getCidade();
			bairro = ass.getBairro();
			telefone = ass.getTelefone();
			cpf = ass.getCpf();
			
			alterando = true;
		}catch(Exception e){
			FacesUtils.addErrorMessage("Falha ao executar a ação");
			return "";
		}
		
		return "incluirEntregador";
	}
	
	public void abrirExcluir() {
		try{
			Entregador ass = entregadorDAO.findById(codentregador);
			
			nome = ass.getNome();
			endereco = ass.getEndereco();
			cidade = ass.getCidade();
			bairro = ass.getBairro();
			telefone = ass.getTelefone();
			cpf = ass.getCpf();
			
			RequestContext.getCurrentInstance().addCallbackParam("sucess", true);
		}catch(Exception e){
			RequestContext.getCurrentInstance().addCallbackParam("sucess", false);
			FacesUtils.addErrorMessage("Falha ao executar a ação");
		}
		
	}
	
	public final void pesquisar() {
		try{
			listaGrid = new ArrayList<EntregadorDTO>();
			
			listaGrid = EntregadorDTO.entityToDtoList(entregadorDAO.findByNome(nomePesquisa));
		}catch(Exception e){
			FacesUtils.addErrorMessage("Falha ao executar a ação");
		}
	
		
	}

	public Integer getCodentregador() {
		return codentregador;
	}

	public void setCodentregador(Integer codentregador) {
		this.codentregador = codentregador;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public List<EntregadorDTO> getListaGrid() {
		return listaGrid;
	}

	public void setListaGrid(List<EntregadorDTO> listaGrid) {
		this.listaGrid = listaGrid;
	}

	public String getNomePesquisa() {
		return nomePesquisa;
	}

	public void setNomePesquisa(String nomePesquisa) {
		this.nomePesquisa = nomePesquisa;
	}

	public boolean isAlterando() {
		return alterando;
	}

	public void setAlterando(boolean alterando) {
		this.alterando = alterando;
	}


}
