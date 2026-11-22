# Marketplace

REST API на Spring Boot и клиент на React, TypeScript и Vite.

## Требования

- Java 17
- Node.js и npm
- PostgreSQL

## Подготовка базы данных

Создайте базу данных `marketplace-alibek` в PostgreSQL. По умолчанию бэкенд подключается к локальному серверу PostgreSQL со следующими параметрами:

| Параметр | Значение |
| --- | --- |
| Адрес | `localhost:5432` |
| База данных | `marketplace-alibek` |
| Пользователь | `postgres` |
| Пароль | `postgres` |

Эти настройки находятся в `src/main/resources/application.properties`. Если ваши параметры отличаются, измените их там перед запуском.

## Запуск бэкенда

Из корневой директории проекта выполните:

```bash
./mvnw spring-boot:run
```

На Windows используйте `mvnw.cmd spring-boot:run`.

API будет доступно по адресу `http://localhost:8080`. При старте Hibernate создаёт таблицы согласно текущей настройке `spring.jpa.hibernate.ddl-auto=create-only`.

## Запуск фронтенда

В отдельном терминале перейдите в директорию `frontend` и установите зависимости:

```bash
cd frontend
npm ci
npm run dev
```

Vite выведет адрес локального сайта; по умолчанию это `http://localhost:5173`.

Фронтенд обращается к API по адресу `http://localhost:8080`. Чтобы указать другой адрес, создайте файл `frontend/.env.local`:

```dotenv
VITE_API_BASE_URL=http://localhost:8080
```

Бэкенд разрешает CORS для origin `http://localhost:5173` (настройка находится в `src/main/java/kz/alibek/marketplace/config/WebConfig.java`). Если фронтенд запущен на другом адресе или порту, обновите разрешённый origin в этой конфигурации.

## Полезные ссылки

- Фронтенд: `http://localhost:5173`
- REST API: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`
