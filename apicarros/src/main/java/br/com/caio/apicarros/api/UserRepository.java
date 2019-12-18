package br.com.caio.apicarros.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.caio.apicarros.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findUserByLogin(String login);
	
}
