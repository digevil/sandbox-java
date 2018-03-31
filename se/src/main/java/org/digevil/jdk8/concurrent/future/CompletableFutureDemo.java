package org.digevil.jdk8.concurrent.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 参考:
 * http://www.baeldung.com/java-completablefuture
 *
 * Created by toni on 2017/12/25.
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Future<String> completableFuture = calculateAsync();
        System.out.println("start");

        String result = completableFuture.get();
        System.out.format("result: %s\n", result);

        String result2 = calculateAsync2().get();
        System.out.format("result2: %s\n", result2);
    }

    private static Future<String> calculateAsync() throws InterruptedException {
        CompletableFuture<String> completableFuture
                = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            Thread.sleep(500);
            completableFuture.complete("Hello");
            return null;
        });

        return completableFuture;
    }

    private static Future<String> calculateAsync2() throws InterruptedException {
        return CompletableFuture.completedFuture("Hello");
    }
}
