package br.com.caio.apicarros.domain.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.caio.apicarros.domain.Role;
import br.com.caio.apicarros.domain.User;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

	private String login;
    private String nome;
    private String email;

    private String token;

    private List<String> roles;

    public static UserDTO create(User user) {
        ModelMapper modelMapper = new ModelMapper();
        UserDTO dto = modelMapper.map(user, UserDTO.class);
        dto.roles = user.getRoles().stream().map(Role::getNome).collect(Collectors.toList());
        return dto;
    }

    public static UserDTO create(User user, String token) {
        UserDTO dto = create(user);
        dto.token = token;
        return dto;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper m = new ObjectMapper();
        return m.writeValueAsString(this);
    }
}
