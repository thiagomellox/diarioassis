package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entity.Assinante;

public class AssinanteDAO extends DAO {
	private EntityManager em;

	public AssinanteDAO() {
		em = getEntityManager();
	}


	
	public Assinante save(Assinante assinante) {
		 
		em.getTransaction().begin();
		em.persist(assinante);
		em.getTransaction().commit();
		return assinante;
	}

	public void delete(Assinante assinante) {
		em.getTransaction().begin();
		em.remove(assinante);
		em.getTransaction().commit();
	}

	public Assinante update(Assinante assinante) {
		em.getTransaction().begin();
		em.persist(assinante);
		em.getTransaction().commit();
		return assinante;
	}

	public List<Assinante> listAll() {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT p FROM Assinante p");
		Query queryr = em.createQuery(query.toString());
		return queryr.getResultList();
	}

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

	public List<Assinante> findByDate(String data) {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT p FROM Assinante p");
		query.append(" WHERE Month(p.datavencimento) = Month('" + data
				+ "') AND Year(p.datavencimento)  = Year('" + data + "')");
		Query queryr = em.createQuery(query.toString());
		return queryr.getResultList();
	}

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
