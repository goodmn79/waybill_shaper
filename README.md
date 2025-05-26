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
   git clone https://github.com/goodmn79/waybill_shaper
   ```

2. Перейдите в директорию проекта:
   ```bash
   cd waybill_shaper
   ```

3. Соберите проект:
   ```bash
   mvn clean package
   ```

4. Запустите приложение:
   ```bash
   java -jar target/waybill_shaper-2.1.jar
   ```

## Конфигурация

Перед запуском необходимо создать файл '.env', добавив значения для следуюзих переменных окужения:

```env
# Telegram Bot Configuration
TELEGRAM_BOT_TOKEN=<your_telegram-bot_token>

# Database Configuration (H2)
SPRING_DATASOURCE_USERNAME=<your_db_username>
SPRING_DATASOURCE_PASSWORD=<your_db_password>
```

Так же необходимо создать файл по пути: src/main/resources/liquibase/scripts/data_load.sql, с INSERT запросом на добавление данных о водителях и транспортных средствах в таблицы DRIVERS, VEHICLES, передав в качестве id водителя его telegram user id, иначе, при отсутствии данных, будет сформирован пустой путевой лист.

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
│       ├── liquibase/           # Миграции Liquibase
│       │    └──scripts/          # SQL-скрипты на создание таблиц и добавление данных
│       │  
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
