package com.learn.mybatis.repository;

import com.learn.mybatis.entity.StudentEntity;
import com.learn.mybatis.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ActiveProfiles("test")
@ComponentScan(excludeFilters = {@ComponentScan.Filter(classes = StudentMapper.class)})
class StudentRepositoryTest {

    @Autowired StudentRepository studentRepository;


    @Test
    void saveStudent() {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(1l);
        studentEntity.setName("student");
        studentEntity.setAddress("address");
        studentEntity.setRollNo(2);

        assertEquals(studentEntity, studentRepository.save(studentEntity));
    }
}