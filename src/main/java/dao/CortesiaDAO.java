package dao;

import java.util.List;

import entity.Cortesia;


public interface CortesiaDAO {

	List<Cortesia> findByEntregadorDAta(String codEntregador,
			String dataInicio, String dataFim);

	List<Cortesia> findByDate(String data);

	List<Cortesia> findByName(String nome, String codEntregador);

	Cortesia findById(Integer codCortesia);

	Integer findQtdeRegistros();

	List<Cortesia> listAll();

	Cortesia update(Cortesia cortesia);

	void delete(Cortesia cortesia);

	Cortesia save(Cortesia cortesia);
	
	
}
