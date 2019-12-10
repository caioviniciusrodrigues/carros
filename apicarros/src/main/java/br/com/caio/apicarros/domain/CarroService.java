package br.com.caio.apicarros.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarroService {

	@Autowired
	private CarroRepository rep;

	public List<Carro> getCarros() {
		return rep.findAll();
	}

	public Optional<Carro> getCarroById(Long id) {
		return rep.findById(id);
	}

	public List<Carro> getCarrosByTipo(String tipo) {
		return rep.findByTipo(tipo);
	}

	public Carro save(Carro carro) {
		return rep.save(carro);
	}

	public Carro update(Carro carro, Long id) {

		Optional<Carro> optional = rep.findById(id);
		if (optional.isPresent()) {
			Carro carroDB = optional.get();
			carroDB.setNome(carro.getNome());
			carroDB.setTipo(carro.getTipo());

			return rep.save(carroDB);
		} else {
			throw new RuntimeException("NAO FOI POSSIVEL ATUALIZAR O CARRO");
		}
		
	}

	public void delete(Long id) {
		Optional<Carro> carro = rep.findById(id);
		if(carro.isPresent()) {
			rep.deleteById(id);
		}
	}
}
