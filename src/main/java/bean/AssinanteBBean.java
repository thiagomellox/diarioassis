package bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;

import util.DecimalConverter;
import util.FacesUtils;
import util.Util;
import dao.AssinanteDAO;
import dao.EntregadorDAO;
import dao.UsuarioDAO;
import dao.impl.AssinanteDAOImpl;
import dao.impl.EntregadorDAOImpl;
import dao.impl.UsuarioDAOImpl;
import dto.AssinanteDTO;
import entity.Assinante;
import entity.Entregador;

@ManagedBean
@SessionScoped
public class AssinanteBBean {

	Integer codAssinante;
	private String nome;
	private String endereco;
	private String bairro;
	private String cidade;
	private String telefone;
	private String valorMensal;
	private String valorAnual;
	private Date dataCadastro;
	private Date dataValidade;
	private Integer codEntregador;
	
	private Integer qtdeAssinantes;
	private Integer mesDataValidade;

	private List<AssinanteDTO> listaGrid;
	private List<SelectItem> entregadorSelectItem;
	
	private String nomePesquisa;
	private Integer codEntregadorPesquisa;
	private boolean alterando;
	
	private AssinanteDAO assinanteDAO = new AssinanteDAOImpl();
	private EntregadorDAO entregadorDAO = new EntregadorDAOImpl();
	private UsuarioDAO usu = new UsuarioDAOImpl();
	

	public void init() {
		entregadorSelectItem = new ArrayList<SelectItem>();
		entregadorSelectItem.add(new SelectItem(null, "Selecione uma op��o"));
		for(Entregador entregador : entregadorDAO.listAll()){
			entregadorSelectItem.add(new SelectItem(entregador.getCodentregador(), entregador.getNome()));
		}
		
		qtdeAssinantes = assinanteDAO.findQtdeRegistros();
	}

	public String salvar() {
		try{
			Assinante ass = new Assinante();
			if(isAlterando()){
				ass.setCodassinante(codAssinante);
			}
			ass.setNome(nome);
			ass.setCidade(cidade);
			ass.setBairro(bairro);
			ass.setEndereco(endereco);
			ass.setTelefone(telefone);
			ass.setValormensal(DecimalConverter.parse(valorMensal) );
			ass.setValoranual(DecimalConverter.parse(valorAnual) );
			ass.setDatacadastro(dataCadastro);
			ass.setDatavencimento(dataValidade);
			Entregador e = new Entregador();
			e.setCodentregador(codEntregador);
			ass.setEntregador(e);
			if(isAlterando()){
				assinanteDAO.update(ass);
			}else{
				assinanteDAO.save(ass);
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
		listaGrid = new ArrayList<AssinanteDTO>();
		return "listarAssinante";
	}
	

	public String cancelar() {
		return "listarAssinante";
	}
	
	private void resetInclusao(){
		nome = null;
		endereco = null;
		bairro = null;
		cidade = null;
		telefone = null;
		valorMensal = null;
		valorAnual = null;
		dataCadastro = null;
		dataValidade = null;
		alterando = false;
		codEntregador = null;
		mesDataValidade = null;
	}
	
	public String abrirIncluir() {
		
		resetInclusao();
		
		return "incluirAssinante";
	}
	
	public String abrirAlterar() {
		if(codAssinante != null){
			try{
				Assinante ass = assinanteDAO.findById(codAssinante);
				
				nome = ass.getNome();
				endereco = ass.getEndereco();
				cidade = ass.getCidade();
				bairro = ass.getBairro();
				telefone = ass.getTelefone();
				valorMensal = DecimalConverter.format(ass.getValormensal())  ;
				valorAnual = DecimalConverter.format(ass.getValoranual()) ;
				dataCadastro = ass.getDatacadastro();
				dataValidade = ass.getDatavencimento();
				mesDataValidade = Util.pegarMeses(dataCadastro, dataValidade);
				codEntregador = ass.getEntregador().getCodentregador();
				
				alterando = true;
			}catch(Exception e){
				FacesUtils.addErrorMessage("Falha ao executar a a��o");
				return "";
			}
			
			return "incluirAssinante";
		}else{
			FacesUtils.addErrorMessage("Falha ao executar a a��o");
			return "";
		}
		
	}
	
	
	public String abrirExcluir() {
		if(codAssinante != null){
			try{
				Assinante ass = assinanteDAO.findById(codAssinante);
				
				nome = ass.getNome();
				endereco = ass.getEndereco();
				cidade = ass.getCidade();
				bairro = ass.getBairro();
				telefone = ass.getTelefone();
				valorMensal = DecimalConverter.format(ass.getValormensal())  ;
				valorAnual = DecimalConverter.format(ass.getValoranual()) ;
				dataCadastro = ass.getDatacadastro();
				dataValidade = ass.getDatavencimento();
				codEntregador = ass.getEntregador().getCodentregador();
				
				FacesUtils.sucesso();

			}catch(Exception e){
				FacesUtils.erro();
				FacesUtils.addErrorMessage("Falha ao executar a a��o" + e.getMessage());
			}
		}else{
			FacesUtils.addErrorMessage("Falha ao executar a a��o");
			
		}
		
		return "";
		
	}
	
	public void excluir() {
		if(codAssinante != null){
			try{
				Assinante ass = assinanteDAO.findById(codAssinante);
				assinanteDAO.delete(ass);

				FacesUtils.sucesso();
				FacesUtils.addInfoMessage("Registro exclu�do com sucesso!");
				pesquisar();
				qtdeAssinantes = assinanteDAO.findQtdeRegistros();
			}catch(Exception e){
				FacesUtils.erro();
				FacesUtils.addErrorMessage("Falha ao executar a a��o" + e.getMessage());
			}
		}else{
			FacesUtils.addErrorMessage("Falha ao executar a a��o");
			
		}
		
	}
	
	public final void pesquisar() {
		listaGrid = new ArrayList<AssinanteDTO>();
		
//		Usuario usuario = new Usuario();
//		usuario.setDataCadastro(new Date());
//		usuario.setNome("thiago");
//		usuario.setEmail("thiagomelox@hotmail.com");
//		usuario.setSenha(MD5Utils.convertStringToMd5("teste"));;
//		usu.save(usuario);
		
		String entregadorB = "";
		if(codEntregadorPesquisa != null && codEntregadorPesquisa.intValue() != 0){
			entregadorB = codEntregadorPesquisa.toString();
		}
		listaGrid = AssinanteDTO.entityToDtoList(assinanteDAO.findByName(nomePesquisa,entregadorB));
		
	}
	
	public List<SelectItem> getMeses(){
		List<SelectItem> lista = new ArrayList<SelectItem>();
		lista.add(new SelectItem(null,"Selecione uma op��o"));
		for(int i = 1; i<=30; i++){
			lista.add(new SelectItem(i,String.valueOf(i)));
		}
		
		return lista;
	}
	
	public void changeDataCadastro(ValueChangeEvent e){
		Date data = (Date) e.getNewValue();
		if(mesDataValidade != null && mesDataValidade.intValue() != 0){
			dataValidade = Util.somaDate(data, mesDataValidade);
		}
	}
	
	public void changeDataValidade(ValueChangeEvent e){
		mesDataValidade = Integer.valueOf(e.getNewValue().toString());
		if(mesDataValidade != null){
			if(dataCadastro != null){
				dataValidade = Util.somaDate(dataCadastro, mesDataValidade);
			}
		}else{
			dataValidade = null;
		}
	}

	public Integer getCodAssinante() {
		return codAssinante;
	}

	public void setCodAssinante(Integer codAssinante) {
		this.codAssinante = codAssinante;
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

	public List<AssinanteDTO> getListaGrid() {
		return listaGrid;
	}

	public void setListaGrid(List<AssinanteDTO> listaGrid) {
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

	public Integer getQtdeAssinantes() {
		return qtdeAssinantes;
	}

	public void setQtdeAssinantes(Integer qtdeAssinantes) {
		this.qtdeAssinantes = qtdeAssinantes;
	}

	public Integer getMesDataValidade() {
		return mesDataValidade;
	}

	public void setMesDataValidade(Integer mesDataValidade) {
		this.mesDataValidade = mesDataValidade;
	}
	

}
