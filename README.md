# Курсовой проект "Сетевой чат"

## Описание проекта

Вам нужно разработать два приложения для обмена текстовыми сообщениями по сети с помощью консоли (терминала) между двумя и более пользователями.

**Первое приложение - сервер чата**, должно ожидать подключения пользователей.

**Второе приложение - клиент чата**, подключается к серверу чата и осуществляет доставку и получение новых сообщений.

Все сообщения должны записываться в file.log как на сервере, так и на клиентах. File.log должен дополняться при каждом запуске, а также при отправленном или полученном сообщении. Выход из чата должен быть осуществлен по команде exit.

## Требования к серверу

- Установка порта для подключения клиентов через файл настроек (например, settings.txt);
- Возможность подключиться к серверу в любой момент и присоединиться к чату;
- Отправка новых сообщений клиентам;
- Запись всех отправленных через сервер сообщений с указанием имени пользователя и времени отправки.

## Требования к клиенту

- Выбор имени для участия в чате;
- Прочитать настройки приложения из файла настроек - например, номер порта сервера;
- Подключение к указанному в настройках серверу;
- Для выхода из чата нужно набрать команду выхода - “/exit”;
- Каждое сообщение участников должно записываться в текстовый файл - файл логирования. При каждом запуске приложения файл должен дополняться.

## Требования в реализации

- Сервер должен уметь одновременно ожидать новых пользователей и обрабатывать поступающие сообщения от пользователей;
- Использован сборщик пакетов gradle/maven;
- Код размещен на github;
- Код покрыт unit-тестами.

## Шаги реализации:

1. Нарисовать схему приложений;
2. Описать архитектуру приложений (сколько потоков за что отвечают, придумать протокол обмена сообщениями между приложениями);
3. Создать репозиторий проекта на github;
4. Написать сервер;
5. Провести интеграционный тест сервера, например с помощью telnet;
6. Написать клиент;
7. Провести интеграционный тест сервера и клиента;
8. Протестировать сервер при подключении нескольких клиентов;
9. Написать README.md к проекту;
10. Отправить на проверку.

----
`clientLog.log Пользователь - 1`
```text
[2023-05-02T22:03:48.570517100] - UserClient >> Hello who is here? 
[2023-05-02T22:04:07.471067] - UserCMD>> Hello, i'm here! My name is UserCMD, r u? 
[2023-05-02T22:04:16.345745300] - UserClient >> My name is UserClient! 
[2023-05-02T22:04:19.106728100] - UserClient >> How r u? 
[2023-05-02T22:04:22.141912600] - UserCMD>> I 
[2023-05-02T22:04:32.685458100] - UserCMD>> I'm nice, r u? 
[2023-05-02T22:04:39.306646200] - UserClient >> I'm too! 
[2023-05-02T22:04:42.455292700] - UserClient >> Bye! 
[2023-05-02T22:04:44.998201300] - UserClient >> Bye! 
[2023-05-02T22:04:50.492234300] - UserClient >> Wow 
[2023-05-02T22:04:56.442347300] - UserCMD>> :) 
[2023-05-02T22:20:15.520457500] - Ynix >> hi! 
[2023-05-02T22:20:18.754325600] - Ynix >> hello! 
```

`clientLog.log Пользователь - 2`
```text
[2023-05-02T22:03:40.157153900] - К серверу подключился - UserClient 
[2023-05-02T22:03:48.563518600] - UserClient>> Hello who is here? 
[2023-05-02T22:04:07.439757600] - UserCMD >> Hello, i'm here! My name is UserCMD, r u? 
[2023-05-02T22:04:16.346744400] - UserClient>> My name is UserClient! 
[2023-05-02T22:04:19.107728600] - UserClient>> How r u? 
[2023-05-02T22:04:22.141912600] - UserCMD >> I 
[2023-05-02T22:04:32.685458100] - UserCMD >> I'm nice, r u? 
[2023-05-02T22:04:39.307647300] - UserClient>> I'm too! 
[2023-05-02T22:04:42.456291700] - UserClient>> Bye! 
[2023-05-02T22:04:44.998201300] - UserClient>> Bye! 
[2023-05-02T22:04:50.493243500] - UserClient>> Wow 
[2023-05-02T22:04:56.441334500] - UserCMD >> :) 
```

`serverLog.log`
```text
[2023-05-02T22:03:26.107740300] - Подключился клиент/127.0.0.1:2701 
[2023-05-02T22:03:30.216399100] - Добро пожаловать на наш сервер - UserCMD 
[2023-05-02T22:03:30.262969900] - К серверу подключился - UserCMD 
[2023-05-02T22:03:33.119944500] - Подключился клиент/127.0.0.1:2703 
[2023-05-02T22:03:40.122155100] - Добро пожаловать на наш сервер - UserClient 
[2023-05-02T22:03:40.169241800] - К серверу подключился - UserClient 
[2023-05-02T22:03:48.562519] - UserClient>> Hello who is here? 
[2023-05-02T22:04:07.439757600] - UserCMD>> Hello, i'm here! My name is UserCMD, r u? 
[2023-05-02T22:04:16.346744400] - UserClient>> My name is UserClient! 
[2023-05-02T22:04:19.106728100] - UserClient>> How r u? 
[2023-05-02T22:04:22.141912600] - UserCMD>> I 
[2023-05-02T22:04:32.685458100] - UserCMD>> I'm nice, r u? 
[2023-05-02T22:04:39.307647300] - UserClient>> I'm too! 
[2023-05-02T22:04:42.455292700] - UserClient>> Bye! 
[2023-05-02T22:04:44.998201300] - UserClient>> Bye! 
[2023-05-02T22:04:50.492234300] - UserClient>> Wow 
[2023-05-02T22:04:56.442347300] - UserCMD>> :) 
[2023-05-02T22:05:05.905474300] - Клиент разорвал соединение/127.0.0.1:2701 
[2023-05-02T22:05:16.000126] - Клиент разорвал соединение/127.0.0.1:2703 
[2023-05-02T22:19:07.076106100] - Подключился клиент/127.0.0.1:2848 
[2023-05-02T22:19:10.418420100] - Добро пожаловать на наш сервер - faf 
[2023-05-02T22:19:10.430117900] - К серверу подключился - faf 
[2023-05-02T22:19:13.601018800] - faf>> hi! 
[2023-05-02T22:20:10.586940500] - Подключился клиент/127.0.0.1:2863 
[2023-05-02T22:20:13.330858100] - Добро пожаловать на наш сервер - Ynix 
[2023-05-02T22:20:13.342858700] - К серверу подключился - Ynix 
[2023-05-02T22:20:15.516457500] - Ynix>> hi! 
[2023-05-02T22:20:18.754325600] - Ynix>> hello! 
[2023-05-02T22:20:20.827591400] - Клиент разорвал соединение/127.0.0.1:2863 
```