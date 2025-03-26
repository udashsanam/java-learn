package com.learn;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NestedForLoop {

     public static void main(String[] args) {


         Integer[] firstArray ={1, 2};
         Integer[] secondArray = {1, 2, 4,5, 7};

         System.out.println("cartsien multiplication of two array");

//         for (int i = 0; i < firstArray.length; i++) {
//             for (int j = 0; j < secondArray.length; j++) {
//                 System.out.println(firstArray[i] + "*"  + secondArray[j] + "="+ firstArray[i] * secondArray[j]);
//             }
//         }

         // same using stream
//         Arrays.stream(firstArray).forEach(integer1 -> {
//             Arrays.stream(secondArray).forEach(integer2 -> System.out.println(integer1  + "*" + integer2 + "="+ integer1 *integer2));
//         });

         List<List<Integer>> ints = getAllPairsStream(List.of(1,2,34,4), List.of(45, 445,454,00));

         List<Integer> integers = ints.stream().flatMap(integers1 -> integers1.stream()).collect(Collectors.toList());;
         integers.forEach(System.out::println);
    }

    public static List<List<Integer>> getAllPairsStream(List<Integer> list1, List<Integer> list2) {
        return list1.stream()
                .flatMap(num1 -> list2.stream().map(num2 -> List.of( num1, num2 )))
                .collect(Collectors.toList());
    }

}
