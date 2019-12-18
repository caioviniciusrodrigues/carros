package br.com.caio.apicarros.api.carros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.caio.apicarros.domain.Carro;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {

	public List<Carro> findByTipo(String tipo);

}
