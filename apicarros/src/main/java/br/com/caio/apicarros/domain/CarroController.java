package br.com.caio.apicarros.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/carros")
public class CarroController {

	@Autowired
	private CarroService service;
	
	@GetMapping
	public ResponseEntity<List<Carro>> get() {
		return ResponseEntity.ok(service.getCarros());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Carro>> get(@PathVariable("id") Long id) {
		Optional<Carro> carro = service.getCarroById(id);
		if(carro.isPresent()) {
			return ResponseEntity.ok(carro);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@GetMapping("tipo/{tipo}")
	public List<Carro> getCarrosByTipo(@PathVariable("tipo") String tipo) {
		return service.getCarrosByTipo(tipo);
	}
	
	@PostMapping
	public String post(@RequestBody Carro carro) {
		Carro c = service.save(carro);
		return "Carro salvo com sucesso " + c.getId();
	}
	
	@PutMapping("/{id}")
	public String put(@PathVariable("id") Long id, @RequestBody Carro carro) {
		Carro c = service.update(carro, id);
		return "Carro atualizado com sucesso " + c.getId();
	} 
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") Long id) {
		service.delete(id);		
		return "Carro deletado com sucesso";		
	}
}
