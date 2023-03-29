package org.digevil.sandbox.l.jmh.deadcode;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class BenchDeadCode {
    @Benchmark
    @Fork(value = 2)
    @Measurement(iterations = 5, time = 1)
    @Warmup(iterations = 5, time = 1)
    public void doNothing() {
    }

    @Benchmark
    @Fork(value = 2)
    @Measurement(iterations = 5, time = 1)
    @Warmup(iterations = 5, time = 1)
    public void objectCreation() {
        new Object();
    }

    @Benchmark
    @Fork(value = 2)
    @Measurement(iterations = 5, time = 1)
    @Warmup(iterations = 5, time = 1)
    public Object pillarsOfCreation() {
        return new Object();
    }

    @Benchmark
    @Fork(value = 2)
    @Measurement(iterations = 5, time = 1)
    @Warmup(iterations = 5, time = 1)
    public void blackHole(Blackhole blackhole) {
        blackhole.consume(new Object());
    }
}
