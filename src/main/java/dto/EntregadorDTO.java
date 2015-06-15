package dto;

import entity.Entregador;


public class EntregadorDTO {
	private Integer codentregador;
	private String nome;
	private String cpf;
	private String telefone;
	private String endereco;
	private String bairro;
	private String cidade;

	public Integer getCodentregador() {
		return this.codentregador;
	}

	public void setCodentregador(Integer codentregador) {
		this.codentregador = codentregador;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
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

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String toString() {
		return getNome();
	}

	public int compareTo(EntregadorDTO o) {
		int i = this.codentregador.compareTo(o.getCodentregador());
		System.out.println(i);
		return i;
	}
	
	
	
	public EntregadorDTO() {
	}
	
	

	public EntregadorDTO(Entregador entregador) {
		this.codentregador = entregador.getCodentregador();
		this.nome = entregador.getNome();
		this.cpf = entregador.getCpf();
		this.telefone = entregador.getTelefone();
		this.endereco = entregador.getEndereco();
		this.bairro = entregador.getBairro();
		this.cidade = entregador.getCidade();
	}

	public Entregador dtoToEntity(){
		Entregador entregador = new Entregador();
		entregador.setCodentregador(codentregador);
		entregador.setNome(nome);
		entregador.setCpf(cpf);
		entregador.setTelefone(telefone);
		entregador.setEndereco(endereco);
		entregador.setBairro(bairro);
		entregador.setCidade(cidade);
		return entregador;
	}
}
