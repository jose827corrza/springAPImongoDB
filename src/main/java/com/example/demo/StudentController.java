package com.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //hace la clase para recibir http requests
@RequestMapping("api/v1/students")
@AllArgsConstructor //Es de lombock, hace q no nos toque escribir todo el constructor
public class StudentController {

    private final StudentService studentService;
    //API layer
    @GetMapping //Estas anotaciones son ls que hacen los GET,POST,PUT y asi..
    public List<Student> fetchAllStudents(){
        return studentService.getAllStudents();
    }

}
