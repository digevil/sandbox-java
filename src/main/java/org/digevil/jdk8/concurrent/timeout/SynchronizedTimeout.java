package org.digevil.jdk8.concurrent.timeout;

import java.util.concurrent.*;

/**
 * 同步的超时，主要依赖于 future 的 block 特性
 *
 * 参考：
 * https://crondev.wordpress.com/2017/01/23/timeouts-with-java-8-completablefuture-youre-probably-doing-it-wrong/
 *
 * Created by toni on 2017/12/24.
 */
public class SynchronizedTimeout {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future timeoutFuture = executor.submit(() -> {
            System.out.println("running task");
            Thread.sleep(1001);
            return "complete";
        });
        try {
            timeoutFuture.get(1000, TimeUnit.MILLISECONDS);
        } catch (TimeoutException te) {
            System.out.format("task time out: %s", te.getStackTrace());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
