package com.jason.websocket;

import com.jason.websocket.domain.Student;
import com.jason.websocket.domain.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class WebsocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsocketApplication.class, args);
	}
	
	@Component
	public class DatabaseLoader implements CommandLineRunner {
		
		private final StudentRepository repository;
		
		@Autowired
		public DatabaseLoader(StudentRepository repository) {
			this.repository = repository;
		}
		
		
		@Override
		public void run(String... strings) throws Exception {
			
			this.repository.save(new Student("John", "Johnson", "john@john.com"));
			this.repository.save(new Student("Mary", "Poppins", "pop@mary.com"));
			this.repository.save(new Student("Rob", "Robber", "rob@bery.com"));
			this.repository.save(new Student("Kate", "Robinson", "kate@robinson.com"));
		}
	}
}
