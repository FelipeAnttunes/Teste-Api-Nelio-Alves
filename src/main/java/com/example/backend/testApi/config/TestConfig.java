package com.example.backend.testApi.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.backend.testApi.entities.User;
import com.example.backend.testApi.repositories.UserRepository;

//classe usada para popular o nosso banco de dados utilizando objetos. //

@Configuration // definindo classe especifica de configuração da nossa aplicação. //
@Profile("test") // definindo que se trata de uma classe especifica p/ config de test. //
public class TestConfig implements CommandLineRunner {

	// fazendo a injeção de depencia para acessar o repository e salvar os dados. //
	
	// declarando atributo e associando uma instancia do repository ao config. //
	@Autowired
	private UserRepository userRepository;

	@Override // metodo usado para executar essa classe qnd o programa for iniciado. //
	public void run(String... args) throws Exception {
		
		User u1 = new User(null, "Usuario1", "usuario1@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Usuario2", "usuario2@gmail.com", "977777777", "123456");
		
		// salvando os objetos criados no banco de dados. //
		userRepository.saveAll(Arrays.asList(u1, u2));
		
	}
}
