package org.digevil.jdk8.concurrent.timeout;

import java.util.concurrent.*;

/**
 * 使用 CompletableFuture 代替 Future, 使用一个额外的 ScheduledExecutorService 来触发超时返回
 *
 * 参考
 * https://crondev.wordpress.com/2017/01/23/timeouts-with-java-8-completablefuture-youre-probably-doing-it-wrong/
 *
 * Created by toni on 2017/12/24.
 */
public class AsynchronousTimeout {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        CompletableFuture timeoutFuture = new CompletableFuture();
        CompletableFuture future = new CompletableFuture();
        executor.submit(() -> {
            scheduler.schedule(() -> {
                timeoutFuture.completeExceptionally(new TimeoutException());
            }, 1000, TimeUnit.MILLISECONDS);
            System.out.println("running task");
            Thread.sleep(1910);
            System.out.println("still running");
            future.complete("complete");
            return null;
        });

        CompletableFuture finalFuture = CompletableFuture.anyOf(future, timeoutFuture);
        try {
            System.out.format("final result: %s", finalFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            if (e.getCause() instanceof TimeoutException) {
                System.out.println(" > timeout");
            }
        } finally {
            executor.shutdown();
            scheduler.shutdown();
        }
    }

}
