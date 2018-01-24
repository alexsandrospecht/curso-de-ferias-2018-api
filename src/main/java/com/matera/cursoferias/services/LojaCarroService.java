package com.matera.cursoferias.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.matera.cursoferias.domain.Carro;
import com.matera.cursoferias.requests.NovoCarro;

@Service
public class LojaCarroService {

	private final Map<UUID, Carro> db = new HashMap<>();
	
	public UUID criarCarro(NovoCarro novoCarro) {
	
		UUID id = UUID.randomUUID();
		
		String cor = novoCarro.getCor();
		String modelo = novoCarro.getModelo();
		String ano = novoCarro.getAno();
		Double valor = novoCarro.getValor();
		
		db.put(id, new Carro(id, cor, modelo, ano, valor));
		
		return id;
	}
	
	public Collection<Carro> getCarros() {
		return db.values();
	}
	
	public boolean delete(UUID carroId) {
		if (db.containsKey(carroId)) {
			db.remove(carroId);
			return true;
		}
		return false;
	}
	
}
