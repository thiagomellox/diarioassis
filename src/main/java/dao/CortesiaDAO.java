package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import entity.Cortesia;

public class CortesiaDAO {
	private Session session;
	private static CortesiaDAO instance;

	public CortesiaDAO() {
//		this.session = HibernateFactory.getSession();
	}

	public static CortesiaDAO getInstance() {
		if (instance == null) {
			instance = new CortesiaDAO();
		}
		return instance;
	}

	public Cortesia save(Cortesia cortesia) {
		this.session.beginTransaction();
		this.session.save(cortesia);
		this.session.getTransaction().commit();
		return cortesia;
	}

	public void delete(Cortesia cortesia) {
		this.session.beginTransaction();
		this.session.delete(cortesia);
		this.session.getTransaction().commit();
	}

	public Cortesia update(Cortesia cortesia) {
		this.session.beginTransaction();
		this.session.update(cortesia);
		this.session.getTransaction().commit();
		return cortesia;
	}

	public List<Cortesia> listAll() {
		return (ArrayList) this.session.createCriteria(Cortesia.class).list();
	}

	public List<Cortesia> findByName(String nome, String codEntregador) {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT p FROM Cortesia p");
		query.append(" WHERE upper(p.nome) like upper('%" + nome + "%')");
		if (!codEntregador.equals("")) {
			query.append(" AND p.entregador.codentregador = " + codEntregador
					+ ")");
		}
		Query queryr = this.session.createQuery(query.toString());
		return queryr.list();
	}

	public List<Cortesia> findByDate(String data) {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT p FROM Cortesia p");
		query.append(" WHERE Month(p.datavencimento) = Month('" + data
				+ "') AND Year(p.datavencimento)  = Year('" + data + "')");
		Query queryr = this.session.createQuery(query.toString());
		return queryr.list();
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
		Query queryr = this.session.createQuery(query.toString());
		return queryr.list();
	}
}
