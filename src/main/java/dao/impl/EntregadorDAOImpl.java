package dao.impl;

import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.EntregadorDAO;
import entity.Entregador;

public class EntregadorDAOImpl extends DAO implements EntregadorDAO {
	private EntityManager em;

	public EntregadorDAOImpl() {
		em = getEntityManager();
	}

	// public Entregador getEmprestimo(Entregador emprestimo) {
	// Entregador emprestimoEncontrado = new Entregador();
	// emprestimoEncontrado = (Entregador) em.load(Entregador.class,
	// emprestimo.getCodentregador());
	// return emprestimoEncontrado;
	// }

	@Override
	public Entregador save(Entregador entregador) {
		em.getTransaction().begin();
		em.clear();
		em.persist(entregador);
		em.getTransaction().commit();
		return entregador;
	}

	@Override
	public Entregador update(Entregador entregador) {
		em.getTransaction().begin();
		em.merge(entregador);
		em.getTransaction().commit();
		return entregador;
	}

	@Override
	public void remove(Entregador entregador) {
		em.getTransaction().begin();
		em.remove(entregador);
		em.getTransaction().commit();
	}

	@Override
	public HashSet<Entregador> saveList(HashSet<Entregador> hashSetEntregador) {
		for (Entregador emprestimo : hashSetEntregador) {
			em.persist(emprestimo);
		}
		return hashSetEntregador;
	}

	@Override
	public Entregador findById(Integer codentregador) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT p FROM Entregador p");
		sb.append(" WHERE p.codentregador = :codentregador");

		Query query = em.createQuery(sb.toString());
		query.setParameter("codentregador", codentregador);
		return (Entregador) query.getSingleResult();
	}

	@Override
	public List<Entregador> findByNome(String nome) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT p FROM Entregador p");
		sb.append(" WHERE p.nome like :nome");

		Query query = em.createQuery(sb.toString());
		query.setParameter("nome", "%" + nome + "%");
		return query.getResultList();
	}

	@Override
	public List<Entregador> listAll() {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT p FROM Entregador p");
		Query queryr = em.createQuery(query.toString());
		return queryr.getResultList();
	}

	@Override
	public Entregador findByCodigo(int codigo) {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT e FROM Entregador e");
		query.append(" WHERE e.codentregador = " + codigo + ")");
		Query queryr = em.createQuery(query.toString());
		return (Entregador) queryr.getSingleResult();
	}
}
