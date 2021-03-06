package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import util.MD5Utils;
import dao.UsuarioDAO;
import entity.Usuario;

public class UsuarioDAOImpl extends DAO implements UsuarioDAO {
	
	private EntityManager em;

	public UsuarioDAOImpl() {
		em = getEntityManager();
	}

	@Override
	public Usuario save(Usuario Usuario) {
		 
		em.getTransaction().begin();
		em.persist(Usuario);
		em.getTransaction().commit();
		return Usuario;
	}

	@Override
	public void delete(Usuario Usuario) {
		em.getTransaction().begin();
		em.remove(Usuario);
		em.getTransaction().commit();
	}

	@Override
	public Usuario update(Usuario Usuario) {
		em.getTransaction().begin();
		em.merge(Usuario);
		em.getTransaction().commit();
		return Usuario;
	}

	@Override
	public List<Usuario> listAll() {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT p FROM Usuario p");
		Query queryr = em.createQuery(query.toString());
		return queryr.getResultList();
	}

	@Override
	public Usuario findById(Integer codUsuario) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT p FROM Usuario p");
		sb.append(" WHERE p.codUsuario = :codUsuario");

		Query query = em.createQuery(sb.toString());
		query.setParameter("codUsuario", codUsuario);
		return (Usuario) query.getSingleResult();
	}
	
	@Override
	public List<Usuario> findByName(String nome, String codEntregador) {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT p FROM Usuario p");
		query.append(" WHERE upper(p.nome) like upper('%" + nome + "%')");
		if (!codEntregador.equals("")) {
			query.append(" AND p.entregador.codentregador = " + codEntregador
					+ ")");
		}
		Query queryr = em.createQuery(query.toString());
		return queryr.getResultList();
	}

	@Override
	public Usuario isUsuarioValido(String nome, String senha){
		Usuario usuarioValido = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT p FROM Usuario p");
		sb.append(" WHERE p.nome = :nome");

		try{
			Query query = em.createQuery(sb.toString());
			query.setParameter("nome", nome);
			Usuario usuario = (Usuario) query.getSingleResult();
			
			if(usuario != null){
				if(usuario.getSenha().equals(MD5Utils.convertStringToMd5(senha))){
					usuarioValido = usuario;
				}
			}
		}catch(Exception e){ 
			usuarioValido = null;
		}
		
		return usuarioValido;
	}

}
