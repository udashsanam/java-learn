package com.learn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceNonBlocking {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        // Submit an async task (Task 1)
        executor.submit(() -> {
            try {
                Thread.sleep(2000); // Simulate delay
                System.out.println("Task 1 completed");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Main thread continues without waiting for Task 1
        System.out.println("Task 2 running (non-blocking)");

        // Don't shutdown immediately if you want tasks to finish
    }
}
