package dao;

import java.util.HashSet;
import java.util.List;

import entity.Entregador;


public interface EntregadorDAO{

	Entregador findByCodigo(int codigo);

	List<Entregador> listAll();

	List<Entregador> findByNome(String nome);

	Entregador findById(Integer codentregador);

	HashSet<Entregador> saveList(HashSet<Entregador> hashSetEntregador);

	void remove(Entregador entregador);

	Entregador update(Entregador entregador);

	Entregador save(Entregador entregador);
	
	
}
