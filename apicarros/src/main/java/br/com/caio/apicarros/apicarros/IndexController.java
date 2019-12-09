package br.com.caio.apicarros.apicarros;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {

	@GetMapping
	public String hello() {
		return "Hello";
	}
	
	@GetMapping("teste")
	public String hello1() {
		return "Hello";
	}
	
	@GetMapping("/{id}")
	public String porId(@PathVariable("id") String id) {
		return "<b>Id:</b> " + id;
	}
}
