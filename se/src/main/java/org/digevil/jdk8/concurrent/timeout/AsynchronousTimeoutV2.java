package org.digevil.jdk8.concurrent.timeout;

import java.util.concurrent.*;

/**
 * Created by huangtao729 on 2017/12/25.
 */
public class AsynchronousTimeoutV2 {

    public static void main(String[] args) {
        Integer taskNumber = 10;
        Integer timeOutMilli = 1000;
        ExecutorService executor = Executors.newFixedThreadPool(taskNumber);
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        CompletableFuture[] allFutures = new CompletableFuture[taskNumber];

        for (int i = 0; i < taskNumber; i++) {
            final Integer v = new Integer(i);
            allFutures[i] = CompletableFuture.supplyAsync(() -> {
                Future future = executor.submit(() -> someOperation(v));
                scheduler.schedule(() -> future.cancel(true), timeOutMilli, TimeUnit.MILLISECONDS);
                try {
                    return future.get();
                } catch (InterruptedException | ExecutionException | CancellationException e) {
                    // pass
                }
                // You can choose to return a dummy value here
                return null;
            });
        }
        // Finally wait for all futures to join
        CompletableFuture.allOf(allFutures).join();
        System.out.println("All futures completed");
        System.out.println(executor.toString());

        executor.shutdown();
        scheduler.shutdown();
    }

    public static Integer someOperation(Integer i) {
        long start = System.currentTimeMillis();
        System.out.format("some operation ... %d\n", i);
        try {
            // simulation of long call
            Thread.sleep(2000);
        } catch (InterruptedException e) {
//            e.printStackTrace();
            System.out.format("some operation cancel ... %d, cost %d milli sec\n", i, System.currentTimeMillis() - start);
            return 0;
        }
        System.out.format("some operation done ... %d, cost %d milli sec\n", i, System.currentTimeMillis() - start);
        return 10;
    }

}
