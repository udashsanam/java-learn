package com.learn.redis.repo;

import com.learn.redis.entity.Student;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepoImpl implements StudentRepo{

    private final RedisTemplate redisTemplate;

    private static final String key = "USER";

    public StudentRepoImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Object save(Student student) {

        for (Integer i = 2; i < 10; i++) {
            Student student1 = new Student();
            student1.setId(i.toString());
            student1.setGender(Student.Gender.MALE);
            student1.setGrade(i);
            student1.setName("name " + i);
            redisTemplate.opsForHash().put(key, student1.getId(), student1);


        }
        return student;
    }

    @Override
    public Object findAll() {
        return redisTemplate.opsForHash().entries(key);
    }

    @Override
    public Object findById(String studentId) {
        return redisTemplate.opsForHash().get(key, studentId);
    }

    @Override
    public Object update(Student student) {
        try{
            redisTemplate.opsForHash().put(key, student.getId(), student);
        } catch (Exception ex){
            System.out.println("Error updating the value ");
        }

        return student;
    }

    @Override
    public Object deleteById(String studentId) {

        try {
            redisTemplate.opsForHash().delete(key,studentId);
        } catch (Exception ex){
            System.out.println("Error deleting student");
        }
        return true;
    }
}
