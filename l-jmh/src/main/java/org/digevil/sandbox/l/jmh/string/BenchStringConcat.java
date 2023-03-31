package org.digevil.sandbox.l.jmh.string;

import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class BenchStringConcat {
    @Benchmark
    @Fork(value = 2)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    @Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    public String stringConcat() {
        String result = "";
        for (int i = 0; i < 1000; i++) {
            result += String.valueOf(i);
        }
        return result;
    }

    @Benchmark
    @Fork(value = 2)
    @Measurement(iterations = 5, time = 1)
    @Warmup(iterations = 5, time = 1)
    public String concatUsingStringBuilder() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            result.append(i);
        }
        return result.toString();
    }
}
