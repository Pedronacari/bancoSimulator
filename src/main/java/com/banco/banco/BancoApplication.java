package com.banco.banco;

import com.banco.banco.models.Users;
import com.banco.banco.repository.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class BancoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UsersRepository repository){
		return args -> {

			Users pedro = new Users("pedro", 1000L, "205102139", "pedro123@gmail.com", "pedro123", false);
			Users paulo = new Users("paulo", 1000L, "20213432", "PAULO123@GMAILC,OM", "paulo123", true);

			repository.save(paulo);
			repository.save(pedro);
		};
	}

}
