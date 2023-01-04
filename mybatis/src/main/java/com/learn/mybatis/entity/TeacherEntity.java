package com.learn.mybatis.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "teacher")
public class TeacherEntity  extends BaseEntity{

    private String name;

    private String address;
}
