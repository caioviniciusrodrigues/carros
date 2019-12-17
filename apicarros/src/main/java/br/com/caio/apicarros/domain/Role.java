package br.com.caio.apicarros.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Entity
@Data
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// USER, ADMIN, etc
	private String nome;

	@Override
	public String getAuthority() {
		return nome;
	}

	@ManyToMany(fetch = FetchType.EAGER)	
	private List<User> users;

}
