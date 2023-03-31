## JMH 简介
Java Microbenchmark Harness（`JMH`）是一个由`OpenJDK`提供的开源基准测试框架。它可以帮助Java开发人员编写高度精确的基准测试，并提供一些优点，如下：

提供高度精确的基准测试：`JMH`利用了Java虚拟机（JVM）的多种技术，包括JIT编译器、垃圾回收器等，以提供准确的基准测试结果。

可以在多种环境中使用：`JMH`可以在不同的Java虚拟机和操作系统上运行，这使得基准测试的结果更具可靠性和可移植性。

提供丰富的测试工具和选项：`JMH`提供了许多选项和测试工具，可以对基准测试进行微调和优化。例如，可以测试不同的JVM参数和垃圾回收器选项，以了解它们如何影响应用程序的性能。

支持多线程测试：`JMH`支持多线程测试，这对于测试并发应用程序的性能非常有用。它可以帮助您确定并发代码的瓶颈，并为优化提供线索。

总的来说，`JMH`是Java开发人员进行基准测试的强大工具，可以提供高度精确的基准测试结果，并具有可移植性和可扩展性。它可以帮助开发人员识别应用程序的瓶颈，并提供一些优化的建议。

## 新建 JMH 子模块 | 项目

建议使用以下命令产生 jmh project/module(替换 `your.group.id` 及 `your.artifact.id`):

```bash
mvn archetype:generate \
    -DinteractiveMode=false \
    -DarchetypeGroupId=org.openjdk.jmh \
    -DarchetypeArtifactId=jmh-java-benchmark-archetype \
    -DgroupId=your.group.id \
    -DartifactId=your.artifact.id \
    -Dversion=1.0-SNAPSHOT
```
这么产生模块的好处是可以 mvn 命令生成 benchmark.jar, 然后可以指定运行的 benchmark 类

## Build and Run JMH

产生模块后, 使用 `mvn install` 来生成 `benchmark.jar` 并使用以下命令执行 benchmarks(使用了 `@Benchmark` 注解的类):

```bash
java -jar target/benchmark.jar YourBenchMarkClass
```
上面代码示例里 `YourBenchMarkClass` 就是你需要执行的测试类, 记得每次变更你的 benchmark 类都要重新`mvn install`一下, 重新生成 `benchmark.jar`

## 几个测试说明

### hash

测试Guava的murmur3_128函数面对不同长度字符串时候的性能表现

执行:
```bash
java -jar target/benchmark.jar BenchHash
```
结果:
```bash
...

Benchmark                   (iterations)   Mode  Cnt  Score   Error   Units
BenchHash.benchMurmur3_128           100  thrpt       0.376          ops/us
BenchHash.benchMurmur3_128           200  thrpt       0.165          ops/us
BenchHash.benchMurmur3_128           300  thrpt       0.105          ops/us
BenchHash.benchMurmur3_128           500  thrpt       0.072          ops/us
BenchHash.benchMurmur3_128          1000  thrpt       0.037          ops/us
```

### deadCode

这个测试里有一个Benchmark使用了`Blackhole`对象, 这是JMH应对死代码(dead code)的一种处理方式, 使得测试更具参考性

执行:
```bash
java -jar target/benchmark.jar BenchDeadCode
```
输出:
```bash
...

Result "org.digevil.sandbox.l.jmh.deadcode.BenchDeadCode.blackHole":
  4.456 ±(99.9%) 0.169 ns/op [Average]
  (min, avg, max) = (4.300, 4.456, 4.640), stdev = 0.111
  CI (99.9%): [4.287, 4.624] (assumes normal distribution)
  
...

Result "org.digevil.sandbox.l.jmh.deadcode.BenchDeadCode.doNothing":
  0.562 ±(99.9%) 0.001 ns/op [Average]
  (min, avg, max) = (0.561, 0.562, 0.563), stdev = 0.001
  CI (99.9%): [0.561, 0.564] (assumes normal distribution)
  
...

Result "org.digevil.sandbox.l.jmh.deadcode.BenchDeadCode.objectCreation":
  0.562 ±(99.9%) 0.002 ns/op [Average]
  (min, avg, max) = (0.561, 0.562, 0.564), stdev = 0.001
  CI (99.9%): [0.560, 0.564] (assumes normal distribution)
  
...  
  
Result "org.digevil.sandbox.l.jmh.deadcode.BenchDeadCode.pillarsOfCreation":
  4.430 ±(99.9%) 0.073 ns/op [Average]
  (min, avg, max) = (4.366, 4.430, 4.493), stdev = 0.049
  CI (99.9%): [4.357, 4.503] (assumes normal distribution)

...

Benchmark                        Mode  Cnt  Score   Error  Units
BenchDeadCode.blackHole          avgt   10  4.456 ± 0.169  ns/op
BenchDeadCode.doNothing          avgt   10  0.562 ± 0.001  ns/op
BenchDeadCode.objectCreation     avgt   10  0.562 ± 0.002  ns/op
BenchDeadCode.pillarsOfCreation  avgt   10  4.430 ± 0.073  ns/op
```

### string

测试普通字符串常量拼接和使用 `StringBuilder` 拼接的性能差异

执行:
```bash
java -jar target/benchmark.jar BenchStringConcat
```
结果:
```bash
...

Benchmark                                   Mode  Cnt    Score   Error  Units
BenchStringConcat.concatUsingStringBuilder  avgt   10    4.302 ± 0.014  us/op
BenchStringConcat.stringConcat              avgt   10  236.072 ± 5.300  us/op
```

## 术语 Terminology

### Trial | Fork

一次 JMH benchmark 会执行多次 `trials`, `trails` 也被称为 `forks`, 是执行单位.

### Warmup

每一个 fork 里, 会有几个迭代(`Iteration`)会被配置为 `warmups` 热身, 是为了让JVM预热我们准备测量的代码,
这对于避免真正开始测量迭代后的波动和变化非常重要.

### Iteration

benchmark代码实际执行的迭代, 在这些迭代里, 运行过程性能数据会被记录, 统计和运算, 输出成最终 JMH benchmark 结果. 
其中, `warmup iteration` 的数据只会被记录, 不会进入最终结果, `measurement iteration` 的数据会作为最终结果.

`fork`, `warmups`, `measurements`, `iteration` 关系如下图所示:

![JMH Fork](https://javadevcentral.com/wp-content/uploads/2020/01/Java-microbenchmark-harness-Terminology.png)

## 注解 Annotations

JMH 主要使用 annotation 来定义和控制 benchmark

### @Benchmark

被 `@Benchmark` 修饰的方法就是 benchmark 入口方法, 需要满足以下条件:

1. 必须是 public
2. 参数必须是以下几种JMH类型的组合(一个或多个): 
   1. `State`: 
   2. `Control`: 
   3. `Blackhole`: 

### @BenchmarkMode

1. `Throughput`: (默认)测量一段代码的单位时间吞吐量/执行次数, 如果方法执行很快(毫秒级), 推荐使用这个.
2. `AverageTime`: 测量这段代码的平均执行时间.
3. `SampleTime`: 测量这段代码的执行时间分布, 如果方法抖动比较大或执行时间不太规律, 可以使用这个, 会计算p50, p90, p99, min 和 max 执行时间.
4. `SingleShotTime`: 测量一个方法和代码片段的单独执行时间, 如果想测试冷启动性能, 可以用这个模式.
5. `All`: 所有模式都会被输出.

### @OutputTimeUnit

这是将在 benchmark 结果中的默认时间单元。它的参数是Java的 `TimeUnit` 枚举类.

### @Fork

`value` 参数定义 forks 数量, `warmups` 参数定义 warmups 数量

例子: 
```java
@Fork(value = 5, warmups = 2)
```
例子里定义了 5 个 `forks` 而且里面有 2 个是 `warmups`.

### @Measurement

用于设置 measurement iterations 的测量参数, 可以设置iteration数量和每个iteration执行时间.

例子: 
```java
@Measurement(iterations = 3, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
```

上面例子里我们指定了 3 个 iteration, 每个运行 1000 毫秒(milliseconds). 默认的 TimeUnit 为 秒(seconds).

#### @Warmup

和 @Measurement 注解的作用几乎相同, 区别是用于设置 warmups 迭代的参数.

例子: 
```java
@Warmup(iterations = 3, time = 2, timeUnit = TimeUnit.SECONDS)
```
例子里定义了3个warmup iteration, 每个执行2秒.

## 参考

* https://javadevcentral.com/jmh-benchmark-with-examples
* https://www.baeldung.com/java-microbenchmark-harness
* https://jenkov.com/tutorials/java-performance/jmh.html