package dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Assinante;

public class AssinanteDTO {
	private Integer codassinante;
	private EntregadorDTO entregador;
	private String nome;
	private String endereco;
	private String bairro;
	private String cidade;
	private String telefone;
	private Double valormensal;
	private Double valoranual;
	private Date datacadastro;
	private Date datavencimento;

	public Integer getCodassinante() {
		return this.codassinante;
	}

	public void setCodassinante(Integer codassinante) {
		this.codassinante = codassinante;
	}

	public EntregadorDTO getEntregador() {
		return this.entregador;
	}

	public void setEntregador(EntregadorDTO entregador) {
		this.entregador = entregador;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String numero) {
		this.bairro = numero;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Double getValormensal() {
		return this.valormensal;
	}

	public void setValormensal(Double valormensal) {
		this.valormensal = valormensal;
	}

	public Double getValoranual() {
		return this.valoranual;
	}

	public void setValoranual(Double valoranual) {
		this.valoranual = valoranual;
	}

	public Date getDatacadastro() {
		return this.datacadastro;
	}

	public void setDatacadastro(Date datacadastro) {
		this.datacadastro = datacadastro;
	}

	public Date getDatavencimento() {
		return this.datavencimento;
	}

	public void setDatavencimento(Date datavencimento) {
		this.datavencimento = datavencimento;
	}
	
	
	
	public AssinanteDTO() {
	}

	public AssinanteDTO(Assinante assinante) {
		this.codassinante = assinante.getCodassinante();
		this.entregador = new EntregadorDTO(assinante.getEntregador());
		this.nome = assinante.getNome();
		this.endereco = assinante.getEndereco();
		this.bairro = assinante.getBairro();
		this.cidade = assinante.getCidade();
		this.telefone = assinante.getTelefone();
		this.valormensal = assinante.getValormensal();
		this.valoranual = assinante.getValoranual();
		this.datacadastro = assinante.getDatacadastro();
		this.datavencimento = assinante.getDatavencimento();
	}

	public Assinante dtoToEntity(){
		Assinante assinante = new Assinante();
		assinante.setCodassinante(codassinante);
		assinante.setNome(nome);
		assinante.setEndereco(endereco);
		assinante.setBairro(bairro);
		assinante.setCidade(cidade);
		assinante.setTelefone(telefone);
		assinante.setDatacadastro(datacadastro);
		assinante.setDatavencimento(datavencimento);
		assinante.setValoranual(valoranual);
		assinante.setValormensal(valormensal);
		assinante.setEntregador(entregador.dtoToEntity());
		
		return assinante;
		
	}
	
	public static List<AssinanteDTO> entityToDtoList(List<Assinante> assinantes ){
		List<AssinanteDTO>  resultado = new ArrayList<AssinanteDTO>();

		for(Assinante assinante : assinantes){
			resultado.add(new AssinanteDTO(assinante));
		}
		
		return resultado;
		
	}
}
