## SpringBoot 3 User Service



## 建立 DB DataBase(Mysql8)

简单说明

```bash
## pull image
docker pull mysql:8

## create a container with mysql:8
run -itd --name mysql-8-test -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 mysql:8

## start / stop container
docker start mysql-8-test
docker stop mysql-8-test

## then create a user test/test and create db for test
```

## Redis

```bash
## pull image
docker pull redis:6

## create a container with redis:5.0
docker run -itd --name redis-6-test -p 6379:6379 -d redis:6 redis-server --save 60 1 --loglevel warning

## start / stop container
docker start redis-6-test
docker stop redis-6-test
```

## Swagger3

#### 添加依赖 webmvc

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.0.0</version>
</dependency>
```

#### api schema 
```
# 访问
http://localhost:8080/v3/api-docs
```

#### swagger ui
```
# 访问
http://localhost:8080/swagger-ui/index.html#/
```

#### 配置类

```java
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI restfulOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("digevil studio s-boot3-user User API")
                        .description("User API")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringDoc Wiki Documentation")
                        .url("https://springdoc.org/v2"));

    }
}
```

#### 参考链接:
https://blog.csdn.net/renpeng301/article/details/124660893