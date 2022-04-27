# Notepad

Инструкция по запуску:

1. Скачайте и установите PostgresSql, если ее нет на вашем ПК

2. Чтобы запустить серверную чаcть веб приложения в файле applications.properties (src/main/resources) нужно поменять значения следующих параметров:
a. spring.datasource.url=jdbc:postgresql://localhost:5432/НАЗВАНИЕ_ВАШЕЙ_БАЗЫ_ДАННЫХ  (рекомендуется назвать ее `notepad`)
b. spring.datasource.username=ВАШ_ЛОГИН
c. spring.datasource.password=ВАШ_ПАРОЛЬ

3. Запустить сервер бэкенда:
a. cd путь_к_папке_проекта
b. mvn package
c. cd target
d. java -jar app-0.0.1-SNAPSHOT.jar

4. Запустить сервер фронтенда: 
a. npm run serve
