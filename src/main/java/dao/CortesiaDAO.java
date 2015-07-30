package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entity.Cortesia;

public class CortesiaDAO extends DAO {
	private EntityManager em;


	public CortesiaDAO() {
		em = getEntityManager();
	}

	public Cortesia save(Cortesia cortesia) {
		em.getTransaction().begin();
		em.persist(cortesia);
		em.getTransaction().commit();
		return cortesia;
	}

	public void delete(Cortesia cortesia) {
		em.getTransaction().begin();
		em.remove(cortesia);
		em.getTransaction().commit();
	}

	public Cortesia update(Cortesia cortesia) {
		em.getTransaction().begin();
		em.merge(cortesia);
		em.getTransaction().commit();
		return cortesia;
	}

	public List<Cortesia> listAll() {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT p FROM Cortesia p");
		Query queryr = em.createQuery(query.toString());
		return queryr.getResultList();
	}
	
	public Integer findQtdeRegistros() {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT p FROM Cortesia p");
		Query queryr = em.createQuery(query.toString());
		return queryr.getResultList().size();
	}
	
	public Cortesia findById(Integer codCortesia) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT p FROM Cortesia p");
		sb.append(" WHERE p.codCortesia = :codCortesia");

		Query query = em.createQuery(sb.toString());
		query.setParameter("codCortesia", codCortesia);
		return (Cortesia) query.getSingleResult();
	}

	public List<Cortesia> findByName(String nome, String codEntregador) {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT p FROM Cortesia p");
		query.append(" WHERE upper(p.nome) like upper('%" + nome + "%')");
		if (!codEntregador.equals("")) {
			query.append(" AND p.entregador.codentregador = " + codEntregador
					+ ")");
		}
		Query queryr = em.createQuery(query.toString());
		return queryr.getResultList();
	}

	public List<Cortesia> findByDate(String data) {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT p FROM Cortesia p");
		query.append(" WHERE Month(p.datavencimento) = Month('" + data
				+ "') AND Year(p.datavencimento)  = Year('" + data + "')");
		Query queryr = em.createQuery(query.toString());
		return queryr.getResultList();
	}

	public List<Cortesia> findByEntregadorDAta(String codEntregador,
			String dataInicio, String dataFim) {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT p FROM Cortesia p WHERE");
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
