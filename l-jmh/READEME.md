建议使用以下命令产生 jmh project/module:

```bash
mvn archetype:generate \
    -DinteractiveMode=false \
    -DarchetypeGroupId=org.openjdk.jmh \
    -DarchetypeArtifactId=jmh-java-benchmark-archetype \
    -DgroupId=your.group.id \
    -DartifactId=your.artifact.id \
    -Dversion=1.0-SNAPSHOT
```

产生模块后, 使用 `mvn install` 来生成 `benchmark.jar` 并使用以下命令执行 benchmarks(使用了 `@Benchmark` 注解的类):

```bash
java -jar target/benchmark.jar YourBenchMarkClass
```