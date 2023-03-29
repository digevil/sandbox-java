package org.digevil.sandbox.l.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;

import java.nio.charset.Charset;

/**
 * ref: https://www.baeldung.com/java-microbenchmark-harness
 */
public class ToBeMeasured {
    /**
     * @Benchmark indicates that this method will be measured by jmh
     */
    @Benchmark
    /**
     * @BenchmarkMode indicates what kind of benchmark to be run, should be one of the following:
     * - AverageTime
     * - Throughput
     * - SampleTime
     * - SingleShotTime
     */
    @BenchmarkMode(Mode.Throughput)
    /**
     * @Fork indicates that how many round and how many warmups to go
     */
    @Fork(value = 1, warmups = 1)
    public void benchMurmur3_128(ExecutionPlan plan) {
        for (int i = plan.iterations; i > 0; i--) {
            plan.murmur3.putString(plan.password, Charset.defaultCharset());
        }
        plan.murmur3.hash();
    }

}