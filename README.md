# lab-5
Information systems development. 5 lab

## Предварительные требования
Убедитесь, что у вас установлен **PostgreSQL** - для работы с базой данных.
## Компиляция проекта
Перейдите в корневой каталог проекта

Подключитесь к PostgreSQL
```
psql -U your_username
```

Создайте базу данных:
```
CREATE DATABASE jewelry;
```

Перейдите в созданную базу данных
```
\c jewelry
```

Создайте пользователя и предоставьте ему доступ.
```
CREATE USER customer WITH ENCRYPTED PASSWORD 'root';
GRANT ALL PRIVILEGES ON DATABASE jewelry TO customer;
```

Выдайте права на таблицу созданному пользователю.
```
ALTER TABLE public.jewelry_store OWNER TO customer;
```

Запустите программу:
```
java -jar target/jewelry-app-0.0.1-SNAPSHOT.jar --spring.config.location=classpath:/application.properties
```

