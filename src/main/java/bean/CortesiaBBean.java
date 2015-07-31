package bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import util.FacesUtils;
import dao.CortesiaDAO;
import dao.EntregadorDAO;
import dao.impl.CortesiaDAOImpl;
import dao.impl.EntregadorDAOImpl;
import dto.CortesiaDTO;
import entity.Assinante;
import entity.Cortesia;
import entity.Entregador;

@ManagedBean
@SessionScoped
public class CortesiaBBean {

	Integer codCortesia;
	private String nome;
	private String endereco;
	private String bairro;
	private String cidade;
	private String telefone;
	private Date dataCadastro;
	private Date dataValidade;
	private Integer codEntregador;

	private List<CortesiaDTO> listaGrid;
	private List<SelectItem> entregadorSelectItem;
	
	private String nomePesquisa;
	private Integer codEntregadorPesquisa;
	private boolean alterando;
	
	private Integer qtdeRegistros;
	
	private CortesiaDAO cortesiaDAO = new CortesiaDAOImpl();
	private EntregadorDAO entregadorDAO = new EntregadorDAOImpl();
	
	public void init() {
		entregadorSelectItem = new ArrayList<SelectItem>();
		entregadorSelectItem.add(new SelectItem(null, "Selecione uma opção"));
		for(Entregador entregador : entregadorDAO.listAll()){
			entregadorSelectItem.add(new SelectItem(entregador.getCodentregador(), entregador.getNome()));
		}
		
		qtdeRegistros = cortesiaDAO.findQtdeRegistros();
	}

	public String salvar() {
		try{
			Cortesia ass = new Cortesia();
			if(isAlterando()){
				ass.setCodCortesia(codCortesia);
			}
			ass.setNome(nome);
			ass.setCidade(cidade);
			ass.setBairro(bairro);
			ass.setEndereco(endereco);
			ass.setTelefone(telefone);
			ass.setDatacadastro(dataCadastro);
			ass.setDatavencimento(dataValidade);
			Entregador e = new Entregador();
			e.setCodentregador(codEntregador);
			ass.setEntregador(e);
			if(isAlterando()){
				cortesiaDAO.update(ass);
			}else{
				cortesiaDAO.save(ass);
			}
			
			FacesUtils.addInfoMessage("Salvo com sucesso!");
			FacesUtils.sucesso();
		} catch (Exception e) {
			FacesUtils.addErrorMessage("Houve um problema ao tentar salvar. " + e.getMessage());
			FacesUtils.erro();
		}
		
		return "";
	}
	
	public String listar() {
		resetInclusao();
		listaGrid = new ArrayList<CortesiaDTO>();
		return "listarCortesia";
	}
	
	private void resetInclusao(){
		nome = null;
		endereco = null;
		bairro = null;
		cidade = null;
		telefone = null;
		dataCadastro = null;
		dataValidade = null;
		alterando = false;
		codEntregador = null;
	}
	
	public String abrirIncluir() {
		
		resetInclusao();
		
		return "incluirCortesia";
	}
	
	public String abrirAlterar() {
		if(FacesUtils.getRequestParameter("codCortesia") != null)
			codCortesia = Integer.valueOf(FacesUtils.getRequestParameter("codCortesia"));
		try{
			Cortesia ass = cortesiaDAO.findById(codCortesia);
			
			nome = ass.getNome();
			endereco = ass.getEndereco();
			cidade = ass.getCidade();
			bairro = ass.getBairro();
			telefone = ass.getTelefone();
			dataCadastro = ass.getDatacadastro();
			dataValidade = ass.getDatavencimento();
			codEntregador = ass.getEntregador().getCodentregador();
			
			alterando = true;
		}catch(Exception e){
			FacesUtils.addErrorMessage("Falha ao executar a ação");
			return "";
		}
		
		return "incluirCortesia";
	}
	
	public void abrirExcluir() {
		if(FacesUtils.getRequestParameter("codCortesia") != null)
			codCortesia = Integer.valueOf(FacesUtils.getRequestParameter("codCortesia"));
		try{
			Cortesia ass = cortesiaDAO.findById(codCortesia);
			
			nome = ass.getNome();
			endereco = ass.getEndereco();
			cidade = ass.getCidade();
			bairro = ass.getBairro();
			telefone = ass.getTelefone();
			dataCadastro = ass.getDatacadastro();
			dataValidade = ass.getDatavencimento();
			codEntregador = ass.getEntregador().getCodentregador();
			
			FacesUtils.sucesso();
		}catch(Exception e){
			FacesUtils.erro();
			FacesUtils.addErrorMessage("Falha ao executar a ação");
		}
		
	}
	
	public void excluir() {
		if(codCortesia != null){
			try{
				Cortesia ass = cortesiaDAO.findById(codCortesia);
				cortesiaDAO.delete(ass);

				FacesUtils.sucesso();
				FacesUtils.addInfoMessage("Registro excluído com sucesso!");
				pesquisar();
				qtdeRegistros = cortesiaDAO.findQtdeRegistros();
			}catch(Exception e){
				FacesUtils.erro();
				FacesUtils.addErrorMessage("Falha ao executar a ação" + e.getMessage());
			}
		}else{
			FacesUtils.addErrorMessage("Falha ao executar a ação");
			
		}
		
	}
	
	public final void pesquisar() {
		listaGrid = new ArrayList<CortesiaDTO>();
		
		String entregadorB = "";
		if(codEntregadorPesquisa != null && codEntregadorPesquisa.intValue() != 0){
			entregadorB = codEntregadorPesquisa.toString();
		}
		listaGrid = CortesiaDTO.entityToDtoList(cortesiaDAO.findByName(nomePesquisa,entregadorB));
		
	}



	public Integer getCodCortesia() {
		return codCortesia;
	}

	public void setCodCortesia(Integer codCortesia) {
		this.codCortesia = codCortesia;
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

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public List<CortesiaDTO> getListaGrid() {
		return listaGrid;
	}

	public void setListaGrid(List<CortesiaDTO> listaGrid) {
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

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public boolean isAlterando() {
		return alterando;
	}

	public void setAlterando(boolean alterando) {
		this.alterando = alterando;
	}

	public String getNomePesquisa() {
		return nomePesquisa;
	}

	public void setNomePesquisa(String nomePesquisa) {
		this.nomePesquisa = nomePesquisa;
	}

	public Integer getCodEntregadorPesquisa() {
		return codEntregadorPesquisa;
	}

	public void setCodEntregadorPesquisa(Integer codEntregadorPesquisa) {
		this.codEntregadorPesquisa = codEntregadorPesquisa;
	}

	public Integer getQtdeRegistros() {
		return qtdeRegistros;
	}

	public void setQtdeRegistros(Integer qtdeRegistros) {
		this.qtdeRegistros = qtdeRegistros;
	}
	

}
