package dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.Query;

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

	public Entregador save(Entregador emprestimo) {
		em.getTransaction().begin();
		em.clear();
		em.persist(emprestimo);
		em.getTransaction().commit();
		return emprestimo;
	}

	public void remove(Entregador emprestimo) {
		em.getTransaction().begin();
		em.remove(emprestimo);
		em.getTransaction().commit();
	}

	public HashSet<Entregador> saveList(HashSet<Entregador> hashSetEntregador) {
		for (Entregador emprestimo : hashSetEntregador) {
			em.persist(emprestimo);
		}
		return hashSetEntregador;
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
