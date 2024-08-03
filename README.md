# Translation Service

## Описание

Это веб-приложение для перевода набора слов с использованием стороннего сервиса перевода (например, Google Translate или Yandex Translate).

## Запуск

1. Склонируйте репозиторий:
    ```
    git clone https://github.com/yourusername/translation-service.git
    ```
2. Перейдите в папку проекта:
    ```
    cd translation-service
    ```
3. Соберите и запустите приложение:
    ```
    ./mvnw spring-boot:run
    ```

## Использование

Отправьте POST-запрос на `/translate` с телом запроса:
```json
{
    "text": "Hello World",
    "sourceLang": "en",
    "targetLang": "ru"
}