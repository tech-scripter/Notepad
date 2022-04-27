# Notepad

Инструкция по запуску:

1. Скачайте и установите PostgresSql, если ее нет на вашем ПК

2. Чтобы запустить серверную чаcть веб приложения в файле applications.properties (src/main/resources) нужно поменять значения следующих параметров:
A) spring.datasource.url=jdbc:postgresql://localhost:5432/НАЗВАНИЕ_ВАШЕЙ_БАЗЫ_ДАННЫХ  (рекомендуется назвать ее `notepad`)
B) spring.datasource.username=ВАШ_ЛОГИН
C) spring.datasource.password=ВАШ_ПАРОЛЬ

3. Запустить сервер бэкенда:
A) cd путь_к_папке_проекта
B) mvn package
C) cd target
D) java -jar app-0.0.1-SNAPSHOT.jar

4. Запустить сервер фронтенда: 
A) npm run serve
