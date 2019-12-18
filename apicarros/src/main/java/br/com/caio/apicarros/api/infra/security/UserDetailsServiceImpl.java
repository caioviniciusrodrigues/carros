package br.com.caio.apicarros.api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.caio.apicarros.api.UserRepository;
import br.com.caio.apicarros.domain.User;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRep;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRep.findUserByLogin(username);
        if(user == null) {
            throw new UsernameNotFoundException("user not found");
        }

        return user;

    }
}
