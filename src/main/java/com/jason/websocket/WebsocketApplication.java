package com.jason.websocket;

import com.jason.websocket.domain.ClassRoom;
import com.jason.websocket.domain.ClassRoomRepository;
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
		
		private final StudentRepository   studentRepository;
		private final ClassRoomRepository classRoomRepository;
		
		@Autowired
		public DatabaseLoader(StudentRepository studentRepository, ClassRoomRepository classRoomRepository) {
			this.studentRepository = studentRepository;
			this.classRoomRepository = classRoomRepository;
		}
		
		
		@Override
		public void run(String... strings) throws Exception {
			
			this.studentRepository.save(new Student("John", "Johnson", "john@john.com"));
			this.studentRepository.save(new Student("Mary", "Poppins", "pop@mary.com"));
			this.studentRepository.save(new Student("Rob", "Robber", "rob@bery.com"));
			this.studentRepository.save(new Student("Kate", "Robinson", "kate@robinson.com"));
			
			this.classRoomRepository.save(new ClassRoom("class3", "B1", 31));
			this.classRoomRepository.save(new ClassRoom("class2", "3", 32));
		}
	}
}
