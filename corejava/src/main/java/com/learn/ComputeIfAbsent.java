package com.learn;

import java.util.Arrays;
import java.util.HashMap;

public class ComputeIfAbsent {
    public static void main(String[] args) {
        characterRepeatation("hello").entrySet().forEach(characterIntegerEntry ->{
            System.out.println(characterIntegerEntry.getKey() +" " + characterIntegerEntry.getValue());


        });
    }

    public  static HashMap<Character, Integer> characterRepeatation(String input){
        HashMap<Character, Integer> result = new HashMap<>();
        for (int i = 0; i <input.length(); i++) {
                result.compute(input.charAt(i), (character, integer) -> {
                    return integer ==null?1:++integer;
                });
        }
        return  result;
    }
}
