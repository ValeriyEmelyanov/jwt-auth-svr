# jwt-auth-svr
Сервис аутентификации. 
Refresh токены хранятся в Redis.
Пользователи хранятся в базе данных PostgreSQL.

### Требования
Для запуска программы необходимо:
* JVM 11
* на хосте localhost на порту 6379 
должен быть доступен Redis 
или необходимо изменить соответствующие настройки в application.yml
* на хосте localhost на порту 5432
должна быть доступна СУБД PostgreSQL 
и создана база данных db_jwtauthsvr
  или необходимо изменить соответствующие настройки в application.yml

Начально создается пользователь admin с паролем 12345

### Технологии
* Java 11
* Spring Boot
* JWT
* Redis

### Запуск Redis в контейнере
Первый запуск
```
docker run -d --name l2-redis -p 6379:6379 -p 8001:8001 redis/redis-stack:latest
```
Повторный запуск
```
docker start l2-redis
```
Остановка
```
docker stop l2-redis
```
Подключение к Redis с помощью консольного клиента 
(для подключения из Windows добавлен ```winpty```)
```
winpty docker exec -it l2-redis bash
redis-cli
```
Просмотр всех ключей
```
key *
```
Завершение работы с консольным клиентом
```
quit
exit
```
