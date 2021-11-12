package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

//los diamantes de mongoRepository es la forma del documento y el tipo de dato para el ID
public interface StudentRepository
        extends MongoRepository<Student, String> {

    Optional<Student> findStudentByEmail(String email);
    /**
     * Asi como se pone abajo, incluso puedo user queries que se usarian en mongo, para hacer las propias
    @Query("")
    void test();
    */
}
