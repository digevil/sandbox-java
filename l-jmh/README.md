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

## Build and Run JMH

产生模块后, 使用 `mvn install` 来生成 `benchmark.jar` 并使用以下命令执行 benchmarks(使用了 `@Benchmark` 注解的类):

```bash
java -jar target/benchmark.jar YourBenchMarkClass
```
上面代码示例里 `YourBenchMarkClass` 就是你需要执行的测试类

## 几个测试说明

### hash
执行:
```bash
java -jar target/benchmark.jar BenchHash
```
结果:
```bash
...

Benchmark                   (iterations)   Mode  Cnt  Score   Error   Units
BenchHash.benchMurmur3_128           100  thrpt       0.369          ops/us
BenchHash.benchMurmur3_128           200  thrpt       0.176          ops/us
BenchHash.benchMurmur3_128           300  thrpt       0.105          ops/us
BenchHash.benchMurmur3_128           500  thrpt       0.070          ops/us
BenchHash.benchMurmur3_128          1000  thrpt       0.031          ops/us
```

### deadCode
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

## 概念

### Trial | Fork

### Warmup

### Iteration

### 

## 参考

* https://javadevcentral.com/jmh-benchmark-with-examples
* https://www.baeldung.com/java-microbenchmark-harness
* https://jenkov.com/tutorials/java-performance/jmh.html