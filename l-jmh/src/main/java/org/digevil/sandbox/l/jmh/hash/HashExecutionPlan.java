package org.digevil.sandbox.l.jmh.hash;

import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import org.openjdk.jmh.annotations.*;

/**
 * The State annotation accepts the scope field. The value for this indicates the scope of the class annotated with @Scope. The values can be one of the following:
 * <p>
 * - Benchmark: All benchmark threads will share the same object.
 * <p>
 * - Group: The object is shared between all threads of a thread group.
 * <p>
 * - Thread: The state object is thread specific (the thread running the benchmark).
 */
@State(Scope.Benchmark)
public class HashExecutionPlan {
    @Param({"100", "200", "300", "500", "1000"})
    public int iterations;

    public Hasher murmur3;

    public String password = "4v3rys3kur3p455w0rd";

    /**
     * The Setup method runs to set up the state object
     * <p>
     * The Setup and Teardown annotations accept a level. It indicates the level at which the Setup and Teardown will be executed. There are three levels:
     * <p>
     * - Trial: Called once per trial (fork).
     * <p>
     * - Iteration: The method is called once per iteration (including warmup).
     * <p>
     * - Invocation: Called per invocation of the method (use it with care as it can affect the benchmark results if not careful of what we do in the Setup/Teardown logic).
     */
    @Setup(Level.Invocation)
    public void setUp() {
        murmur3 = Hashing.murmur3_128().newHasher();
    }

    /**
     * The JMH runs the Teardown method to clean up the resources (if any) of the State object.
     */
    @TearDown(Level.Invocation)
    public void tearDown() {
        murmur3 = null;
    }
}
