# Telegram Bot for Waybill Generation

## Описание

Этот Telegram-бот предназначен для автоматического заполнения путевых листов на основе полученных от пользователей сообщений. Бот использует шаблон Excel, хранящийся в `resources/template`, и возвращает готовый документ пользователю.

## Функциональность

- Прием сообщений от пользователей
- Анализ и обработка текста сообщения
- Заполнение шаблона путевого листа (Excel)
- Отправка готового документа пользователю

## Технологии

- Spring Boot
- Telegram Bot API (java-telegram-bot-api)
- Apache POI для работы с Excel
- H2 Database (встроенная)
- Liquibase для управления миграциями БД
- Lombok для сокращения boilerplate кода

## Требования

- Java 17+
- Maven 3.6+

## Установка и запуск

1. Клонируйте репозиторий:
   ```bash
   git clone <repository-url>
   ```

2. Перейдите в директорию проекта:
   ```bash
   cd <project-directory>
   ```

3. Соберите проект:
   ```bash
   mvn clean package
   ```

4. Запустите приложение:
   ```bash
   java -jar target/your-app-name.jar
   ```

## Конфигурация

Перед запуском необходимо настроить следующие параметры в `application.properties` или `application.yml`:

```properties
# Telegram Bot Configuration
telegram.bot.token=YOUR_BOT_TOKEN
telegram.bot.username=YOUR_BOT_USERNAME

# Database Configuration (H2)
spring.datasource.url=jdbc:h2:file:./data/waybillbot
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# JPA Configuration
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=none
spring.jpa.properties.hibernate.format_sql=true
```

## Использование

1. Найдите бота в Telegram по имени пользователя
2. Отправьте боту сообщение с необходимыми данными для путевого листа
3. Бот обработает сообщение и вернет заполненный путевой лист в формате Excel

## Структура проекта

```
src/
├── main/
│   ├── java/
│   │   └── com/example/waybillbot/
│   │       ├── config/          # Конфигурационные классы
│   │       ├── controller/      # Обработчики Telegram-команд
│   │       ├── model/           # Сущности базы данных
│   │       ├── repository/      # Репозитории JPA
│   │       ├── service/         # Бизнес-логика
│   │       ├── util/            # Вспомогательные утилиты
│   │       └── WaybillBotApplication.java # Главный класс приложения
│   └── resources/
│       ├── template/            # Шаблоны Excel
│       ├── db/changelog/        # Миграции Liquibase
│       └── application.properties # Конфигурация приложения
└── test/                        # Тесты
```

## Разработка

Для разработки рекомендуется использовать IntelliJ IDEA или другую современную Java IDE.

### Зависимости

Все зависимости указаны в файле `pom.xml`. Основные:
- Spring Boot Starter
- Telegram Bot API
- Apache POI для работы с Excel
- H2 Database
- Liquibase
- Lombok

## Лицензия

Лицензич MIT
