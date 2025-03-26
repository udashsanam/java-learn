package com.learn.multilanguage.dto;

public interface InterfaceB {

    default void eat(){
        System.out.println("eat");
    }
}
