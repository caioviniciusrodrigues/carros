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

import br.com.caio.apicarros.domain.dto.CarroDTO;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/carros")
public class CarroController {

	@Autowired
	private CarroService service;
	
	@GetMapping
	public ResponseEntity<?> getCarros() {
		return ResponseEntity.ok(service.getCarros());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CarroDTO> get(@PathVariable("id") Long id) {
		Optional<CarroDTO> carroDTO = service.getCarroById(id);
		if(carroDTO.isPresent()) {
			return ResponseEntity.ok(carroDTO.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@GetMapping("tipo/{tipo}")
	public ResponseEntity<?> getCarrosByTipo(@PathVariable("tipo") String tipo) {		
		List<CarroDTO> carros = service.getCarrosByTipo(tipo);		
		return carros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(carros);
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
