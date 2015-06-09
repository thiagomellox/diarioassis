package entity;

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

@Entity
@Table(name = "assinante")
public class Assinante implements Serializable {
	private Integer codassinante;
	private Entregador entregador;
	private String nome;
	private String endereco;
	private String bairro;
	private String cidade;
	private String telefone;
	private Double valormensal;
	private Double valoranual;
	private Date datacadastro;
	private Date datavencimento;

	@Id
	@GeneratedValue
	@Column(name = "CODASSINANTE", unique = true, nullable = false)
	public Integer getCodassinante() {
		return this.codassinante;
	}

	public void setCodassinante(Integer codassinante) {
		this.codassinante = codassinante;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CODENTREGADOR", nullable = false)
	public Entregador getEntregador() {
		return this.entregador;
	}

	public void setEntregador(Entregador entregador) {
		this.entregador = entregador;
	}

	@Column(name = "NOME", nullable = false, length = 45)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "ENDERECO", nullable = false, length = 45)
	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Column(name = "BAIRRO", nullable = false, length = 45)
	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String numero) {
		this.bairro = numero;
	}

	@Column(name = "CIDADE", nullable = false, length = 45)
	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Column(name = "TELEFONE", nullable = false, length = 15)
	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Column(name = "VALORMENSAL", precision = 6)
	public Double getValormensal() {
		return this.valormensal;
	}

	public void setValormensal(Double valormensal) {
		this.valormensal = valormensal;
	}

	@Column(name = "VALORANUAL", precision = 6)
	public Double getValoranual() {
		return this.valoranual;
	}

	public void setValoranual(Double valoranual) {
		this.valoranual = valoranual;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATACADASTRO", nullable = false, length = 0)
	public Date getDatacadastro() {
		return this.datacadastro;
	}

	public void setDatacadastro(Date datacadastro) {
		this.datacadastro = datacadastro;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATAVENCIMENTO", nullable = false, length = 0)
	public Date getDatavencimento() {
		return this.datavencimento;
	}

	public void setDatavencimento(Date datavencimento) {
		this.datavencimento = datavencimento;
	}
}
