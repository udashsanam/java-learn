package com.learn;

import java.util.concurrent.CompletableFuture;

public class NonBlockingMultithread {

    public static void main(String[] args) {
        CompletableFuture<Void> task =  CompletableFuture.runAsync(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Hello World");
        });
        System.out.println("Done");

        task.join();

    }
}
