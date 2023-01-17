package com.learn.redis.repo;

import com.learn.redis.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface StudentRepo {
    Object save(Student student);
}
