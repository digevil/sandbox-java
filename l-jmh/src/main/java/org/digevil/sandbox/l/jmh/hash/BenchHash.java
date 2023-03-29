package org.digevil.sandbox.l.jmh.hash;

import org.openjdk.jmh.annotations.*;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * ref: <a href="https://www.baeldung.com/java-microbenchmark-harness">...</a>
 */
public class BenchHash {
    /**
     * {@code @Benchmark} indicates that this method will be measured by jmh
     * <p>
     * {@code @BenchmarkMode} indicates what kind of benchmark to be run, should be one of the following:
     * - AverageTime
     * - Throughput
     * - SampleTime
     * - SingleShotTime
     * <p>
     * {@code @Fork} indicates that how many round and how many warmups to go
     */
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @Fork(value = 1, warmups = 1)
    @Measurement(iterations = 1, time = 1)
    @Warmup(iterations = 1, time = 1)
    public void benchMurmur3_128(HashExecutionPlan plan) {
        for (int i = plan.iterations; i > 0; i--) {
            plan.murmur3.putString(plan.password, Charset.defaultCharset());
        }
        plan.murmur3.hash();
    }

}