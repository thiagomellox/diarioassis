package dao;

import java.util.List;

import entity.Assinante;


public interface AssinanteDAO {

	Assinante save(Assinante assinante);

	void delete(Assinante assinante);

	Assinante update(Assinante assinante);

	List<Assinante> listAll();

	Integer findQtdeRegistros();

	Assinante findById(Integer codassinante);

	List<Assinante> findByName(String nome, String codEntregador);

	List<Assinante> findByDate(String data);

	List<Assinante> findByEntregadorDAta(String codEntregador,
			String dataInicio, String dataFim);
	
	
}
