package ru.gnkoshelev.jbreak2018.perf_tests.vector;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

/**
 * Created by kgn on 20.03.2018.
 */
@Fork(value = 5, warmups = 0)
@Warmup(iterations = 5, time = 1_000, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 1_000, timeUnit = TimeUnit.MILLISECONDS)
@OutputTimeUnit(value = TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
public class ComputationOnlyBenchmark {
    private double x1, y1, z1;
    private double x2, y2, z2;

    @Setup(value = Level.Iteration)
    public void setup() {
        x1 = 123.4;
        y1 = 234.5;
        z1 = 345.6;
        x2 = 456.7;
        y2 = 567.8;
        z2 = 678.9;
    }

    @Benchmark
    public void computeWithRawScalars(Blackhole bh) {
        bh.consume(VectorAlgebra.computeWithRawScalars(x1, y1, z1, x2, y2, z2));
    }

    @Benchmark
    public void computeWithVectors(Blackhole bh) {
        bh.consume(VectorAlgebra.computeWithVectors(x1, y1, z1, x2, y2, z2));
    }
}
