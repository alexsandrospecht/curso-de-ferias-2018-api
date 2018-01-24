package com.matera.cursoferias.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matera.cursoferias.domain.Carro;
import com.matera.cursoferias.requests.NovoCarro;
import com.matera.cursoferias.services.LojaCarroService;

@RestController
public class LojaDeCarros {

	@Autowired
	private LojaCarroService service;
	
	@PostMapping(
			path = "/lojacarros/carros", 
			consumes = { "application/json" }
	)
	public ResponseEntity<Void> criarCarro(@RequestBody NovoCarro novoCarro) throws URISyntaxException {
		
		UUID id = service.criarCarro(novoCarro);
		URI location = new URI("/lojacarros/carros/" + id.toString());
		
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping(
		path = "/lojacarros/carros", 
		produces = { 
				"application/json",
				"application/xml"
				}
	)
	public ResponseEntity<Collection<Carro>> listaCarros(
			@RequestParam(required = false) String cor, 
			@RequestParam(required = false) String ano
	) {
		
		Stream<Carro> carros = service.getCarros().stream();
		if (cor != null && !cor.isEmpty()) {
			carros = carros.filter(carro -> cor.equals(carro.getCor()));
		}
		
		if (ano != null && !ano.isEmpty()) {
			carros = carros.filter(carro -> ano.equals(carro.getAno()));
		}
		return ResponseEntity.ok(carros.collect(Collectors.toList()));
	}
	
	@GetMapping(
		path = "/lojacarros/carros/{id}", 
		produces = { "application/json" }
	)
	public ResponseEntity<Carro> getCarroById(@PathVariable String id) {
		
		Optional<Carro> response = 
			service.getCarros()
				.stream()
				.filter(carro -> UUID.fromString(id).equals(carro.getId()))
				.findFirst();
		
		if (response.isPresent()) {
			return ResponseEntity.ok(response.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping(path = "/lojacarros/carros/{id}")
	public ResponseEntity<Void> deletaCarro(@PathVariable String id) {
		
		UUID carroId = UUID.fromString(id);
		boolean deleted = service.delete(carroId);
		if (deleted) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
}
