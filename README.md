# Подготовка
## 1. Склонировать репозиторий:
git clone git@github.com:Visens/Diplomas.git

## 2. Перейти в папку Diplomas

## 3. Для запуска контейнеров Docker:
Выполнить команду docker-compose -f docker-compose.yml up -dс

## 4. Для запуска приложения с базой данный MySQL:
Выполнить команду java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar .\artifacts\aqa-shop.jar

Для запуска тестов выполнить команду ./gradlew test "-Ddb.url=jdbc:mysql://localhost:3306/app"

## 5. Для запуска приложения с базой данный PostgresSQL:
Выполнить команду java "-Dspring.datasource.url=postgresql://localhost:5432/app" -jar .\artifacts\aqa-shop.jar

Для запуска тестов выполнить команду ./gradlew test "-Ddb.url=jdbc:postgresql://localhost:5432/app"

## 6. Формирование отчётных документов:
Для формирования и запуска отчётных документов после прохода тестов выполнить команду ".gradlew allureServe"