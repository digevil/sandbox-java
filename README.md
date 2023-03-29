# sandbox-java
sandbox java project, 用于测试和demo所有的java语法, 功能, 库等

## 一些约定

* 模块名称: `b` 开头的都是java语法基础的模块, b=basic
* 模块名称: `l` 开头的都是库相关的模块, l=library
* 模块名称: `s` 开头的都是spring模块, s=spring/springboot

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

## then creat a user test/test and create db for test
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