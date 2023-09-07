package com.mubarak.blogrestapi;

import com.mubarak.blogrestapi.model.Role;
import com.mubarak.blogrestapi.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogRestApiApplication implements CommandLineRunner {

	@Bean
	public ModelMapper modelMapper(){
		return  new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(BlogRestApiApplication.class, args);
	}

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {
   com.mubarak.blogrestapi.model.Role adminRole = new Role();
   adminRole.setName("ROLE_ADMIN");
   roleRepository.save(adminRole);

   Role userRole = new Role();
   userRole.setName("ROLE_USER");
   roleRepository.save(userRole);
	}
}
