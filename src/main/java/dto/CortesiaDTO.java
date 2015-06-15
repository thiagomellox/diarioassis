package dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class CortesiaDTO {
	private Integer codCortesia;
	private EntregadorDTO entregador;
	private String nome;
	private String endereco;
	private String bairro;
	private String cidade;
	private String telefone;
	private Date datacadastro;
	private Date datavencimento;

	public Integer getCodCortesia() {
		return this.codCortesia;
	}

	public void setCodCortesia(Integer codCortesia) {
		this.codCortesia = codCortesia;
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
}
