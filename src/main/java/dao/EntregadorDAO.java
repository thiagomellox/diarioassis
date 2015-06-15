package dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entity.Assinante;
import entity.Entregador;

public class EntregadorDAO  extends DAO{
	private EntityManager em;
	
	public EntregadorDAO() {
		em = getEntityManager();
	}

//	public Entregador getEmprestimo(Entregador emprestimo) {
//		Entregador emprestimoEncontrado = new Entregador();
//		emprestimoEncontrado = (Entregador) em.load(Entregador.class,
//				emprestimo.getCodentregador());
//		return emprestimoEncontrado;
//	}

	public Entregador save(Entregador entregador) {
		em.getTransaction().begin();
		em.clear();
		em.persist(entregador);
		em.getTransaction().commit();
		return entregador;
	}
	
	public Entregador update(Entregador entregador) {
		em.getTransaction().begin();
		em.merge(entregador);
		em.getTransaction().commit();
		return entregador;
	}

	public void remove(Entregador entregador) {
		em.getTransaction().begin();
		em.remove(entregador);
		em.getTransaction().commit();
	}

	public HashSet<Entregador> saveList(HashSet<Entregador> hashSetEntregador) {
		for (Entregador emprestimo : hashSetEntregador) {
			em.persist(emprestimo);
		}
		return hashSetEntregador;
	}
	
	public Entregador findById(Integer codentregador) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT p FROM Entregador p");
		sb.append(" WHERE p.codentregador = :codentregador");

		Query query = em.createQuery(sb.toString());
		query.setParameter("codentregador", codentregador);
		return (Entregador) query.getSingleResult();
	}
	
	public List<Entregador>  findByNome(String nome) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT p FROM Entregador p");
		sb.append(" WHERE p.nome like :nome");
		
		Query query = em.createQuery(sb.toString());
		query.setParameter("nome", "%" + nome + "%");
		return query.getResultList();
	}

	public List<Entregador> listAll() {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT p FROM Entregador p");
		Query queryr = em.createQuery(query.toString());
		return queryr.getResultList();
	}

	public Entregador findByCodigo(int codigo) {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT e FROM Entregador e");
		query.append(" WHERE e.codentregador = " + codigo + ")");
		Query queryr = em.createQuery(query.toString());
		return (Entregador) queryr.getSingleResult();
	}
}
