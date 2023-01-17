package com.learn.redis.controller;

import com.learn.redis.entity.Student;
import com.learn.redis.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentRepo studentRepo;

    @Autowired
    public StudentController(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }


    @PostMapping()
    public ResponseEntity<?> save(){
        Student student = new Student(
                "Eng2015001", "John Doe", Student.Gender.MALE, 1);

    return  ResponseEntity.ok(studentRepo.save(student));
    }

    @GetMapping()
    public ResponseEntity<?> getAllStudent(){

        Object o = studentRepo.findAll();

        return ResponseEntity.ok(studentRepo.findAll());
    }

    @GetMapping("/findById")
    public ResponseEntity<?> getById(@RequestParam("studentId") String studentId){
        return ResponseEntity.ok(studentRepo.findById(studentId));
    }

    @PutMapping()
    public ResponseEntity<?> updateStuddent(@RequestBody Student student){
        return ResponseEntity.ok(studentRepo.update(student));
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteById(@RequestParam("studentId") String studentId){

        return ResponseEntity.ok(studentRepo.deleteById(studentId));
    }
}
