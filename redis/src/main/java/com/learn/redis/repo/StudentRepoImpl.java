package com.learn.redis.repo;

import com.learn.redis.entity.Student;
import org.springframework.data.redis.core.RedisTemplate;

public class StudentRepoImpl implements StudentRepo{

    private final RedisTemplate redisTemplate;

    private static final String key = "USER";

    public StudentRepoImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Object save(Student student) {
        redisTemplate.opsForHash().put(key, student.getId(), student);
        return student;
    }
}
