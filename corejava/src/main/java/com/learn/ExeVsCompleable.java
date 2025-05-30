package com.learn;

import java.util.concurrent.*;

public class ExeVsCompleable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ExecutorService executor = Executors.newFixedThreadPool(2);
//        Future<String> future = executor.submit(() -> {
//            Thread.sleep(1000);
//            return "Result";
//        });
//
//// BLOCKING — waits for result
//        String result = future.get();
//        System.out.println(result);
//        System.out.println("Done");
//        executor.shutdown();

      CompletableFuture<Void> future =  CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Hello";
        }).thenApply(str -> {
            System.out.println( str + " World");
            return str + " World";
        }).thenAccept(System.out::println);
        System.out.println("Done");
        future.join();
    }
}
