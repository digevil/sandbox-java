package org.digevil.sandbox.l.jmh.hash;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;

import java.nio.charset.Charset;

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
    @Fork(value = 1, warmups = 1)
    public void benchMurmur3_128(HashExecutionPlan plan) {
        for (int i = plan.iterations; i > 0; i--) {
            plan.murmur3.putString(plan.password, Charset.defaultCharset());
        }
        plan.murmur3.hash();
    }

}