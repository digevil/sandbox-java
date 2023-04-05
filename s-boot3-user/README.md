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