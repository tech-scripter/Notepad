# Notepad

Инструкция по запуску:

Скачайте и установите PostgresSql

Чтобы запустить серверную чаcть веб приложения в файле applications.properties (src/main/resources) нужно поменять значения следующих параметров:
1. spring.datasource.url=jdbc:postgresql://localhost:5432/НАЗВАНИЕ_ВАШЕЙ_БАЗЫ_ДАННЫХ  (рекомендуется назвать ее `notepad`)
2. spring.datasource.username=ВАШ_ЛОГИН
3. spring.datasource.password=ВАШ_ПАРОЛЬ

Запустить сервер бэкенда:
1. cd путь_к_папке_проекта
2. mvn package
3. cd target
4. java -jar app-0.0.1-SNAPSHOT.jar

Запустить сервер фронтенда: 
1. npm run serve
