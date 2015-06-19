package dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Assinante;
import entity.Cortesia;

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
	
	public CortesiaDTO() {
	}

	public CortesiaDTO(Cortesia cortesia) {
		this.codCortesia = cortesia.getCodCortesia();
		this.entregador = new EntregadorDTO(cortesia.getEntregador());
		this.nome = cortesia.getNome();
		this.endereco = cortesia.getEndereco();
		this.bairro = cortesia.getBairro();
		this.cidade = cortesia.getCidade();
		this.telefone = cortesia.getTelefone();
		this.datacadastro = cortesia.getDatacadastro();
		this.datavencimento = cortesia.getDatavencimento();
	}

	public Cortesia dtoToEntity(){
		Cortesia cortesia = new Cortesia();
		cortesia.setCodCortesia(codCortesia);
		cortesia.setNome(nome);
		cortesia.setEndereco(endereco);
		cortesia.setBairro(bairro);
		cortesia.setCidade(cidade);
		cortesia.setTelefone(telefone);
		cortesia.setDatacadastro(datacadastro);
		cortesia.setDatavencimento(datavencimento);
		cortesia.setEntregador(entregador.dtoToEntity());
		
		return cortesia;
		
	}
	
	public static List<CortesiaDTO> entityToDtoList(List<Cortesia> cortesias ){
		List<CortesiaDTO>  resultado = new ArrayList<CortesiaDTO>();

		for(Cortesia cortesia : cortesias){
			resultado.add(new CortesiaDTO(cortesia));
		}
		
		return resultado;
		
	}
}
