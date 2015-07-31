package dao;

import java.util.List;

import entity.Usuario;

public interface UsuarioDAO {

	Usuario save(Usuario Usuario);

	void delete(Usuario Usuario);

	Usuario update(Usuario Usuario);

	List<Usuario> listAll();

	Usuario findById(Integer codUsuario);

	List<Usuario> findByName(String nome, String codEntregador);

	Usuario isUsuarioValido(String nome, String senha);

}
