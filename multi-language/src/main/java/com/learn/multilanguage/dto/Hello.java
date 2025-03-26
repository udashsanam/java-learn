package com.learn.multilanguage.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Arrays;
import java.util.logging.SimpleFormatter;

public class Hello {

    public static void main(String[] args) throws FileNotFoundException, SQLException {

        DateTimeFormatter dateTimeFormatter =DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String x = dateTimeFormatter.format(LocalDate.now());
        FileReader xss = new FileReader("test");

        Connection conn = DriverManager.getConnection("invalid-url", "user", "password");


        Hello y = new Hello();
        y.checkExtpion();
        Integer number = 10;
        number = number + 5; // This creates a new Integer object, number remains unchanged.
        System.out.println(number); // Prints 10, because the original number object is immutable.

        byte xxc = (byte) 129;
        System.out.println(x);
        Integer xs = 3;

        int[] inputs = {2, 3,5,0};
        Arrays.stream(sort(inputs)).forEach(System.out::println);
    }

    public static int[] sort(int[] input){

        Long x = 12l;
        int length= input.length;
        for (int i = 0; i<length; i ++){
            int temp = 0;
            for (int j = 0;j < length-i-1; j++){
                temp= input[j];
                if(temp > input[j+1]){
                    input[j] = input[j+1];
                    input[j+1]=temp;
                }
            }
        }
        return input;
    }

    public void checkExtpion(){
        try {
            throw new CheckedException("test");
        } catch (CheckedException e) {
            throw new RuntimeException(e);
        }
    }


}
