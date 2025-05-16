package com.learn;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

public class Example {
    public static void main(String[] args) {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            try {
                return fetchData1();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(new Date());
            String x= fetchData2();
            System.out.println(new Date());
            return x;
        } );

        f1.thenCombine(f2, (resultFromF1, resultFromF2) -> resultFromF1 + " & " + resultFromF2)
          .thenAccept(System.out::println) // once combined then print
          .join(); // Waits for everything to complete]
        System.out.println("Done");
    }

    static String fetchData1() throws InterruptedException {
        Thread.sleep(2000);
        return "A"; }
    static String fetchData2() {
        return "B"; }
}