
**Приложение для управления деятельностью сети магазинов электроники**
**EStore-microservice**

Технологический стек (смотреть Набор ПО):
-	Языкпрограммирования: Java 11;
-	Frameworks: Spring Boot Starter (2.7), Spring Web, Spring JPA, Spring Hibernate;
-	Базаданных: PostgreSQL;
-	Библиотека для генерации документации: springdoc-openapi
- Vue.js
### 1️ Установить зависимости
Перед запуском убедитесь, что у вас установлены:
- **JDK 11**
- **Maven**
- **PostgreSQL**
- NodeJs, npm (https://nodejs.org/en/download) !!Необходим для Frontend части!!

### 2️ Настроить базу данных
Создайте базу данных в PostgreSQL:
```sql
CREATE DATABASE name;
```
#### И заполните файл с помощью скрипта schema.sql в папке resources
Настройте подключение в `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/some_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### 3️ Запуск Backend части на Windows
1. Откройте **Командную строку (cmd)** или **PowerShell**.
2. Перейдите в папку с проектом:
   ```sh
   cd C:\путь_к_проекту
   ```
3. Соберите и запустите проект:
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```
4. После успешного запуска API будет доступно по адресу:
   ```
   http://localhost:8081
   ```

### 4️ Запуск на Linux / macOS
1. Откройте терминал и перейдите в папку с проектом:
   ```sh
   cd /path/to/project
   ```
2. Соберите и запустите проект:
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```

---

##  Документация API (Swagger UI)
После запуска перейдите по адресу:
🔗 [Swagger UI](http://localhost:8081/swagger-ui.html)
**Доступ к swagger-ui:** http://localhost:8081/estore-api.html

### 5 Запуск FronEnd части на Windows
1. Откройте **Командную строку (cmd)** или **PowerShell**.
2. Перейдите в папку с front:
   ```sh
   cd C:\путь_к_проекту\estore-front
   ```
3. Соберите и установите проект:
   ```sh
   npm install
   npm run build
   ```
4. После успешной сборки нужно провалиться в папку dist:
   ```sh
   cd dist
   ```
5. Теперь нужно запустить сервер :
   ```sh
   npm install -g serve //если отсутствует
   serve
   ```
   **Доступ к странице:** http://localhost:3000 (Пишется в консоли после запуска)

### 6 Запуск FronEnd части на Linux
1. Откройте **Командную строку (cmd)** или **PowerShell**.
2. Перейдите в папку с front:
   ```sh
   cd C:\путь_к_проекту\estore-front
   ```
3. Соберите и установите проект:
   ```sh
   npm install
   npm run build
   ```
4. После успешной сборки нужно провалиться в папку dist:
   ```sh
   cd dist
   ```
5. Теперь нужно запустить сервер :
   ```sh
   sudo npm install -g serve  // если еще не установлен
   serve
   ```

---

### 7 Выполенные задачи
1. Реализовать хранение основных реестров для добавления, отображения и редактирования информации.
2. Реализовать хранение вспомогательных справочников (с возможностью их редактирования).
3. Реализовать хранение вспомогательных связующих по типу вспомогательных связей.
4. Реализовать возможность постраничного просмотра реестров/справочников.
5. При просмотре реестра покупок добавить возможность сортировкипо дате осуществления покупки.
6. Реализовать вывод информации о лучших сотрудниках в зависимости от занимаемой должности по критериям.

## Итог 
Чтобы увидеть весь реализованный функционал лучше смотреть через Swagger.
     





