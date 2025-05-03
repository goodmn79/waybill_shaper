# Waybill Bot - Telegram-бот для путевых листов транспортной компании

## Описание
Telegram-бот для автоматического формирования путевых листов в формате Excel на основе данных заказов. Предназначен для автоматизации документооборота в транспортных компаниях.

## Возможности
- Прием структурированных сообщений с данными заказа
- Генерация путевых листов в Excel по шаблону
- Отправка готового документа пользователю
- Хранение истории заказов в базе данных
- Поддержка H2
- Управление версиями БД через Liquibase

## Используемые технологии
- **Backend**: Spring Boot 3.x
- **База данных**: H2 (встроенная)
- **Миграции БД**: Liquibase
- **Telegram API**: java-telegram-bot-api
- **Работа с Excel**: Apache POI
- **Утилиты**: Lombok
- **Доступ к данным**: Spring Data JPA
- **Контейнеризация**: Docker

## Формат сообщения
Бот ожидает сообщение в формате:
```
Номер заказа: [номер]
Дата: [дата]
Водитель: [ФИО]
Транспорт: [марка и госномер]
Маршрут: [откуда] - [куда]
Груз: [описание груза]
Вес: [вес] кг
```

## Быстрый старт с Docker

### Требования
- Установленные Docker и Docker Compose
- Токен бота от @BotFather

### Настройка
1. Создайте файл `.env`:
```bash
cp .env.example .env
```
2. Заполните `.env` своими данными:
```ini
TELEGRAM_BOT_TOKEN=your_bot_token
SPRING_DATASOURCE_URL=./db/ws-db
SPRING_DATASOURCE_USERNAME=username
SPRING_DATASOURCE_PASSWORD=password
```

### Сборка Docker образа
```bash
docker build -t <имя образа>
```

### Запуск
```bash
docker-compose up -d
```

### Остановка
```bash
docker-compose down
```

## Структура проекта
```
src/
├── main/
│   ├── java/com/example/waybillbot/
│   │   ├── config/          - Классы конфигурации
│   │   ├── controller/      - Обработчики команд бота
│   │   ├── model/           - Сущности БД
│   │   ├── repository/      - Репозитории Spring Data JPA
│   │   ├── service/         - Бизнес-логика
│   │   ├── util/            - Вспомогательные классы
│   │   └── WaybillBotApplication.java
│   └── resources/
│       ├── db/changelog/    - Миграции Liquibase
│       ├── templates/       - Шаблоны Excel
│       └── application.yml  - Конфигурация
└── test/                    - Тесты
```

## Настройка базы данных

### H2
```yaml
spring.datasource.url=${SPRING_DATASOURCE_URL}
spring.datasource.username=${SPRING_DATASOURCE_USERNAME}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}
spring.datasource.driver-class-name=org.h2.Driver

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

## Миграции Liquibase
Изменения структуры БД управляются через changelogs в:
```
src/main/resources/liquibase
```
- `changelog-master.yaml` - Главный файл миграций
- `changelog/` - Отдельные изменения

## Сборка и развертывание

1. Сборка проекта:
```bash
mvn clean package
```

2. Сборка Docker-образа:
```bash
docker-compose build
```

3. Запуск сервисов:
```bash
docker-compose up -d
```

## Доступ
- Приложение: порт 8080
- Консоль H2 (только для разработки): `http://localhost:8080/h2-console`
- PostgreSQL: порт 5432