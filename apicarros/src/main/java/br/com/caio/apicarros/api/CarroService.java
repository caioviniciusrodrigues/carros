package br.com.caio.apicarros.api;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.caio.apicarros.domain.Carro;
import br.com.caio.apicarros.domain.dto.CarroDTO;

@Service
public class CarroService {

	@Autowired
	private CarroRepository rep;

	public List<CarroDTO> getCarros() {

		return rep.findAll().stream().map(CarroDTO::create).collect(Collectors.toList());

//		List<Carro> carros = rep.findAll();
//		
//		List<CarroDTO> list = new ArrayList<CarroDTO>();
//		for(Carro c : carros) {
//			list.add(new CarroDTO(c));
//		}		
//		return list;
	}

	public Optional<CarroDTO> getCarroById(Long id) {

		return rep.findById(id).map(CarroDTO::create);

		/*
		 * Optional<Carro> carro = rep.findById(id); if(carro.isPresent()) { return
		 * Optional.of(new CarroDTO(carro.get())); } else { return Optional.empty(); }
		 */
	}

	public List<CarroDTO> getCarrosByTipo(String tipo) {
		return rep.findByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());
	}

	public CarroDTO save(Carro carro) {
		return CarroDTO.create(rep.save(carro));
	}

	public CarroDTO update(Carro carro, Long id) {

		Optional<Carro> optional = rep.findById(id);
		if (optional.isPresent()) {
			Carro carroDB = optional.get();
			carroDB.setNome(carro.getNome());
			carroDB.setTipo(carro.getTipo());

			rep.save(carroDB);

			return CarroDTO.create(carroDB);
		} else {
			return null;
		}

	}

	public void delete(Long id) {
		rep.deleteById(id);
	}
}
