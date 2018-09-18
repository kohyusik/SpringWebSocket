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
			
			ClassRoom classRoom1 = new ClassRoom("class1", "B1", 31);
			ClassRoom classRoom2 = new ClassRoom("class2", "B2", 31);
			ClassRoom classRoom3 = new ClassRoom("class3", "3", 32);
			ClassRoom classRoom4 = new ClassRoom("class4", "4", 32);
			
			this.classRoomRepository.save(classRoom1);
			this.classRoomRepository.save(classRoom2);
			this.classRoomRepository.save(classRoom3);
			this.classRoomRepository.save(classRoom4);
			
			this.studentRepository.save(new Student("John", "Johnson", "john@john.com", classRoom1));
			this.studentRepository.save(new Student("Mary", "Poppins", "pop@mary.com", classRoom2));
			this.studentRepository.save(new Student("Rob", "Robber", "rob@bery.com", classRoom3));
			this.studentRepository.save(new Student("Kate", "Robinson", "kate@robinson.com", classRoom4));
			
		}
	}
}
