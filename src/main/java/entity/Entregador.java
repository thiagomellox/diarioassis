package entity;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="entregador")
public class Entregador
  implements Serializable, Comparable<Entregador>
{
  private Integer codentregador;
  private String nome;
  private String cpf;
  private String telefone;
  private String endereco;
  private String bairro;
  private String cidade;
  private Set<Assinante> assinantes = new HashSet(0);
  private Set<Cortesia> cortesias = new HashSet(0);
  
  @Id
  @GeneratedValue
  @Column(name="CODENTREGADOR", unique=true, nullable=false)
  public Integer getCodentregador()
  {
    return this.codentregador;
  }
  
  public void setCodentregador(Integer codentregador)
  {
    this.codentregador = codentregador;
  }
  
  @Column(name="NOME", nullable=false, length=45)
  public String getNome()
  {
    return this.nome;
  }
  
  public void setNome(String nome)
  {
    this.nome = nome;
  }
  
  @Column(name="CPF", length=15)
  public String getCpf()
  {
    return this.cpf;
  }
  
  public void setCpf(String cpf)
  {
    this.cpf = cpf;
  }
  
  @Column(name="TELEFONE", length=15)
  public String getTelefone()
  {
    return this.telefone;
  }
  
  public void setTelefone(String telefone)
  {
    this.telefone = telefone;
  }
  
  @Column(name="ENDERECO", length=45)
  public String getEndereco()
  {
    return this.endereco;
  }
  
  public void setEndereco(String endereco)
  {
    this.endereco = endereco;
  }
  
  @Column(name="BAIRRO", length=45)
  public String getBairro()
  {
    return this.bairro;
  }
  
  public void setBairro(String bairro)
  {
    this.bairro = bairro;
  }
  
  @Column(name="CIDADE", length=45)
  public String getCidade()
  {
    return this.cidade;
  }
  
  public void setCidade(String cidade)
  {
    this.cidade = cidade;
  }
  
  @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="entregador")
  public Set<Assinante> getAssinantes()
  {
    return this.assinantes;
  }
  
  public void setAssinantes(Set<Assinante> assinantes)
  {
    this.assinantes = assinantes;
  }
  
  @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="entregador")
  public Set<Cortesia> getCortesias()
  {
    return this.cortesias;
  }
  
  public void setCortesias(Set<Cortesia> cortesias)
  {
    this.cortesias = cortesias;
  }
  
  public String toString()
  {
    return getNome();
  }
  
  public int compareTo(Entregador o)
  {
    int i = this.codentregador.compareTo(o.getCodentregador());
    System.out.println(i);
    return i;
  }
}
