package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class MongodbrestApplication {

	public static void main(String[] args) {

		SpringApplication.run(MongodbrestApplication.class, args);
	}
	@Bean
	CommandLineRunner runner(
			StudentRepository repository, MongoTemplate mongoTemplate){
		return args -> {
			Address address = new Address(
					"Colombia",
					"Soacha",
					"111011"
			);
			String email = "prueba@mail.com";
			Student student = new Student(
					"Jose",
					"Corredor",
					"prueba@mail.com",
					Gender.MALE,
					address,
					Arrays.asList("Computer Science"),
					BigDecimal.TEN,
					LocalDateTime.now()
			);
			//Query personalizada
			//usingMongoTemplateAndQuery(repository, mongoTemplate, email, student);
			//La logica la puede reemplazar por esta simpleza, otorgada por los grandes dioses del olimpo

			repository.findStudentByEmail(email)
					.ifPresent(student1 -> System.out.println(student1 + "Already exists"));
		};
	}

	private void usingMongoTemplateAndQuery(StudentRepository repository, MongoTemplate mongoTemplate, String email, Student student) {
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(email));

		//Mongo templates usa queries personalizadas
		List<Student> studentList = mongoTemplate.find(query, Student.class);

		if(studentList.size() >1){
			throw new IllegalStateException("Found many student with email" + email);
		}

		if(studentList.isEmpty()){
			System.out.println("Inserting student" + student);
			repository.insert(student);
		}else{
			System.out.println(student + "Already exists");
		}
	}
}
