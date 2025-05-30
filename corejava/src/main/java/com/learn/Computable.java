package com.learn;

import java.util.concurrent.CompletableFuture;

public class Computable {

    public static void main(String[] args) {
        CompletableFuture.runAsync(() -> {
            System.out.println("🔧 First task running...");
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
        }).thenRun(() -> {
            System.out.println("✅ Second task runs after first, non-blocking");
        });

        // ⚠️ Without join(), main() will exit unless you keep the JVM alive
        System.out.println("👋 Main thread not blocked, may exit before async tasks finish");
    }
}
