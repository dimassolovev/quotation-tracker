<div align="center">
    <img src="assets/introduction.gif" alt="Demo Quotation Tracker">
</div>

<div align="center">
    <h1>Quotation Tracker</h1>
</div>

**Quotation Tracker** – это система для мониторинга котировок с [Московской биржи](https://www.moex.com/en), которая
реализует автоматическую обработку данных. Проект предназначен для агрегирования, хранения и предоставления данных о
биржевых котировках по запросу пользователей.

<div align="center">
    <h1>Ключевые компоненты</h1>
</div>

1. **Quotation Data Aggregator Service** - сервис для парсинга данных с MOEX, который по установленному расписанию
   отправляет котировки в **Kafka**.
2. **Moex Data Service** - сервис, который получает данные из Kafka, сохраняет их в базу данных (**PostgreSQL**) и
   кэширует их в **Redis** для ускорения доступа к наиболее востребованной информации.
3. **Authentication Service** - сервис аутентификации, который предоставляет JWT-токены пользователям для доступа к
   данным.
4. **Инфраструктура Spring Cloud** - api gateway, config server, eureka server.

<div align="center">
    <h1>Архитектура проекта</h1>
</div>

<div align="center">
    <img src="assets/services.png" alt="Demo Quotation Tracker">
</div>

### 1. Quotation Data Aggregator Service

Этот сервис занимается парсингом котировок с MOEX с помощью планировщика задач. Пользователь может настроить частоту
выполнения задачи через конфигурационный файл application.yml. Данные о котировках отправляются в **Kafka**, который
используется как очередь сообщений для доставки котировок другим сервисам системы.

**Задачи**:

- Парсинг данных с MOEX по расписанию.
- Отправка котировок в Kafka.

### 2. Moex Data Service

Этот сервис получает сообщения о котировках из **Kafka**, сохраняет их в базу данных **PostgreSQL** и кэширует наиболее
запрашиваемые данные в **Redis** для ускорения обработки последующих запросов.

**Задачи**:

- Получение данных из Kafka.
- Сохранение данных в базе данных PostgreSQL.
- Кэширование данных в Redis.
- Обработка пользовательских запросов на получение котировок.

### 3. Authentication Service

Этот сервис управляет аутентификацией пользователей. Перед использованием **Quotation Tracker**, пользователю необходимо
пройти аутентификацию и получить JWT-токен. Этот токен используется для авторизации доступа к сервисам, предоставляющим
котировки.

**Задачи**:

- Аутентификация пользователя.
- Генерация JWT-токена для доступа к другим сервисам.

### 4. Observability

Для мониторинга состояния системы используются **Prometheus** и **Grafana**. Сервисы отправляют метрики в Prometheus, а
графическое представление данных доступно в Grafana, где можно отслеживать производительность системы, нагрузку на
сервисы и ошибки.

### 5. Infrastructure

Система полностью контейнеризирована с использованием **Docker**, что упрощает развертывание всех сервисов как в
локальной среде разработки, так и в производственной среде.

<div align="center">
    <h1>Технологический стек</h1>
</div>

<div style="text-align: center;">
    <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/29/Postgresql_elephant.svg/1985px-Postgresql_elephant.svg.png" width="100" />
    <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/redis/redis-original-wordmark.svg" alt="Redis" title="Redis" width="100" />
    <img src="https://estuary.dev/static/176f17fa158ab922c8009a7fd0d19ebf/67d4a/35ce20_kafka_300x300_f5c0fc1ac1.png" alt="Kafka" title="Kafka" width="100" />
    <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/3/38/Prometheus_software_logo.svg/2066px-Prometheus_software_logo.svg.png" title="Prometheus" width="100" />
    <img src="https://grafana.com/static/img/menu/grafana2.svg" alt="Grafana" title="Grafana" width="100" />
    <img src="https://upload.wikimedia.org/wikipedia/commons/a/ab/Swagger-logo.png" alt="Swagger" title="Swagger" width="100" />
    <img src="https://static-00.iconduck.com/assets.00/docker-icon-1024x1024-mv7uzno8.png" alt="Docker" title="Docker" width="100" />
    <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/7/79/Spring_Boot.svg/640px-Spring_Boot.svg.png" alt="Spring Cloud" title="Spring Cloud" width="100" />
</div>

<div align="center">
    <h1>Работа приложения</h1>
</div>

### Аутентификация:

```bash
curl --location 'http://localhost:8765/v1/authentication/register' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "dimas",
    "password": "dimas",
    "email": "gohasoxx@gmail.com"
}'
```

### Получение токена:

#### Запрос:

```bash
curl --location 'http://localhost:8765/v1/authentication/token' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "dimas",
    "password": "dimas",
    "email": "gohasoxx@gmail.com"
}'
```

#### Ответ:

```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImdvaGFzb3h4QGdtYWlsLmNvbSIsInJvbGVzIjpbIlJPTEVfVVNFUiJdLCJ1dWlkIjoiNjU3ZmM3M2QtZjg2OS00ZmQ1LTkzNzctZjAwNzBmY2Y3Y2JkIiwic3ViIjoiZGltYXMiLCJpYXQiOjE3Mjk2ODkzMzgsImV4cCI6MTcyOTc3NTczOH0.EbdQBdfJGRjOaupQBcBmG73KbMscfk1genOaSVwxytM"
}
```

### Валидация токена:

#### Запрос:

```
curl --location 'http://localhost:56782/v1/authentication/validate-token' \
--header 'Content-Type: application/json' \
--data '{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImdvaGFzb3h4QGdtYWlsLmNvbSIsInJvbGVzIjpbIlJPTEVfVVNFUiJdLCJ1dWlkIjoiNjU3ZmM3M2QtZjg2OS00ZmQ1LTkzNzctZjAwNzBmY2Y3Y2JkIiwic3ViIjoiZGltYXMiLCJpYXQiOjE3Mjk2ODkzMzgsImV4cCI6MTcyOTc3NTczOH0.EbdQBdfJGRjOaupQBcBmG73KbMscfk1genOaSVwxytM"
}'
```

#### Ответ:

```true```

### Получение котировок по дате:

#### Запрос:

```bash
curl --location 'http://localhost:8765/v1/currencies/list?tradeDate=2024-10-17' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwiZW1haWwiOiJnb2hhc294eEBnbWFpbC5jb20iLCJ1dWlkIjoiMGExMGMyYWEtYmU4MS00MTcwLWExMDEtMTVkZDg2NDhlYTFhIiwic3ViIjoiZGltYXMiLCJpYXQiOjE3Mjk2MTc1MDUsImV4cCI6MTcyOTcwMzkwNX0.07qYOKzaRhtQK-Phb4pxT3qmmIwxT2zzKOBQNnHWCiM'
```

#### Ответ:

```json
{
    "data": [
        {
            "tradedate": "2024-10-17",
            "tradetime": "13:45:00",
            "rate": 2.8411,
            "clearing": "pk",
            "secid": "TRY/RUB"
        },
        {
            "tradedate": "2024-10-17",
            "tradetime": "18:49:00",
            "rate": 2.8411,
            "clearing": "vk",
            "secid": "TRY/RUB"
        },
        {
            "tradedate": "2024-10-17",
            "tradetime": "13:45:00",
            "rate": 12.5379,
            "clearing": "pk",
            "secid": "HKD/RUB"
        },
        {
            "tradedate": "2024-10-17",
            "tradetime": "18:49:00",
            "rate": 12.5192,
            "clearing": "vk",
            "secid": "HKD/RUB"
        },
        {
            "tradedate": "2024-10-17",
            "tradetime": "13:45:00",
            "rate": 127.3481,
            "clearing": "pk",
            "secid": "GBP/RUB"
        },
        {
            "tradedate": "2024-10-17",
            "tradetime": "18:49:00",
            "rate": 126.3811,
            "clearing": "vk",
            "secid": "GBP/RUB"
        },
        {
            "tradedate": "2024-10-17",
            "tradetime": "13:45:00",
            "rate": 97.2568,
            "clearing": "pk",
            "secid": "USD/RUB"
        },
        {
            "tradedate": "2024-10-17",
            "tradetime": "18:49:00",
            "rate": 97.149,
            "clearing": "vk",
            "secid": "USD/RUB"
        },
        {
            "tradedate": "2024-10-17",
            "tradetime": "13:45:00",
            "rate": 106.0844,
            "clearing": "pk",
            "secid": "EUR/RUB"
        },
        {
            "tradedate": "2024-10-17",
            "tradetime": "18:49:00",
            "rate": 105.756,
            "clearing": "vk",
            "secid": "EUR/RUB"
        },
        {
            "tradedate": "2024-10-17",
            "tradetime": "13:45:00",
            "rate": 70.4504,
            "clearing": "pk",
            "secid": "CAD/RUB"
        },
        {
            "tradedate": "2024-10-17",
            "tradetime": "18:49:00",
            "rate": 70.5461,
            "clearing": "vk",
            "secid": "CAD/RUB"
        },
        {
            "tradedate": "2024-10-17",
            "tradetime": "13:45:00",
            "rate": 112.7877,
            "clearing": "pk",
            "secid": "CHF/RUB"
        },
        {
            "tradedate": "2024-10-17",
            "tradetime": "18:49:00",
            "rate": 112.285,
            "clearing": "vk",
            "secid": "CHF/RUB"
        }
    ]
}
```

### Получение котировок по дате и коду валюты:

#### Запрос:

```bash
curl --location 'http://localhost:8765/v1/currencies/list/USD?tradeDate=2024-10-17' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwiZW1haWwiOiJnb2hhc294eEBnbWFpbC5jb20iLCJ1dWlkIjoiMGExMGMyYWEtYmU4MS00MTcwLWExMDEtMTVkZDg2NDhlYTFhIiwic3ViIjoiZGltYXMiLCJpYXQiOjE3Mjk2MTc1MDUsImV4cCI6MTcyOTcwMzkwNX0.07qYOKzaRhtQK-Phb4pxT3qmmIwxT2zzKOBQNnHWCiM'
```

#### Ответ:

```json
{
    "data": [
        {
            "tradedate": "2024-10-17",
            "tradetime": "13:45:00",
            "rate": 97.2568,
            "clearing": "pk",
            "secid": "USD/RUB"
        },
        {
            "tradedate": "2024-10-17",
            "tradetime": "18:49:00",
            "rate": 97.149,
            "clearing": "vk",
            "secid": "USD/RUB"
        }
    ]
}
```

<div align="center">
    <h1>Конфиг приложения</h1>
</div>

### Конфигурацию приложения можно найти [здесь](https://github.com/dimassolovev/config)

<div align="center">
    <h1>Заключение</h1>
</div>

Проект **Quotation Tracker** предоставляет эффективное решение для мониторинга и анализа котировок с Московской биржи. С
использованием современных технологий, таких как Spring Cloud, Kafka, Redis, PostgreSQL и Docker, они обеспечивают
надежное и масштабируемое решение для обработки и хранения данных.

Если у вас есть вопросы, предложения или вы хотите обсудить проект, вы можете связаться со мной:

- **Telegram**: [dimassolovev](https://t.me/dimassolovev)
- **Email**: [gohasoxx@gmail.com](mailto:gohasoxx@gmail.com)

**Спасибо за внимание!**
