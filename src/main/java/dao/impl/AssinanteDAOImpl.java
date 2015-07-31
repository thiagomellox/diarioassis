package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.AssinanteDAO;
import entity.Assinante;

public class AssinanteDAOImpl extends DAO implements AssinanteDAO {
	private EntityManager em;

	public AssinanteDAOImpl() {
		em = getEntityManager();
	}

	@Override
	public Assinante save(Assinante assinante) {

		em.getTransaction().begin();
		em.persist(assinante);
		em.getTransaction().commit();
		return assinante;
	}

	@Override
	public void delete(Assinante assinante) {
		em.getTransaction().begin();
		em.remove(assinante);
		em.getTransaction().commit();
	}

	@Override
	public Assinante update(Assinante assinante) {
		em.getTransaction().begin();
		em.merge(assinante);
		em.getTransaction().commit();
		return assinante;
	}

	@Override
	public List<Assinante> listAll() {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT p FROM Assinante p");
		Query queryr = em.createQuery(query.toString());
		return queryr.getResultList();
	}

	@Override
	public Integer findQtdeRegistros() {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT p FROM Assinante p");
		Query queryr = em.createQuery(query.toString());
		return queryr.getResultList().size();
	}

	@Override
	public Assinante findById(Integer codassinante) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT p FROM Assinante p");
		sb.append(" WHERE p.codassinante = :codassinante");

		Query query = em.createQuery(sb.toString());
		query.setParameter("codassinante", codassinante);
		return (Assinante) query.getSingleResult();
	}

	@Override
	public List<Assinante> findByName(String nome, String codEntregador) {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT p FROM Assinante p");
		query.append(" WHERE upper(p.nome) like upper('%" + nome + "%')");
		if (!codEntregador.equals("")) {
			query.append(" AND p.entregador.codentregador = " + codEntregador
					+ ")");
		}
		Query queryr = em.createQuery(query.toString());
		return queryr.getResultList();
	}

	@Override
	public List<Assinante> findByDate(String data) {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT p FROM Assinante p");
		query.append(" WHERE Month(p.datavencimento) = Month('" + data
				+ "') AND Year(p.datavencimento)  = Year('" + data + "')");
		Query queryr = em.createQuery(query.toString());
		return queryr.getResultList();
	}

	@Override
	public List<Assinante> findByEntregadorDAta(String codEntregador,
			String dataInicio, String dataFim) {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT p FROM Assinante p WHERE");
		if (!codEntregador.equals("")) {
			query.append(" p.entregador.codentregador = " + codEntregador);
		}
		if ((!dataInicio.equals("")) && (!dataFim.equals(""))
				&& (!codEntregador.equals(""))) {
			query.append(" AND ");
		}
		if ((!dataInicio.equals("")) && (!dataFim.equals(""))) {
			query.append("  p.datavencimento BETWEEN '" + dataInicio
					+ "' AND '" + dataFim + "'");
		}
		Query queryr = em.createQuery(query.toString());
		return queryr.getResultList();
	}
}
