### 新建 JMH 子模块 | 项目

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

### Build and Run JMH

产生模块后, 使用 `mvn install` 来生成 `benchmark.jar` 并使用以下命令执行 benchmarks(使用了 `@Benchmark` 注解的类):

```bash
java -jar target/benchmark.jar YourBenchMarkClass
```
上面代码示例里 `YourBenchMarkClass` 就是你需要执行的测试类

### 参考

* https://javadevcentral.com/jmh-benchmark-with-examples
* https://www.baeldung.com/java-microbenchmark-harness