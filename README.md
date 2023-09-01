# Проект по API тестированию для <a target="_blank" href="https://reqres.in/">reqres.in</a>

## :pushpin: Содержание:

- [Технологии и инструменты](#rocket-технологии-и-инструменты)
- [Тест кейсы](#scroll-тест-кейсы)
- [Пример запуска из терминала](#computer-пример-запуска-из-терминала)
- [Сборка в Jenkins](#jenkins-job)
- [Allure отчет](#информация-о-тестах-в-allure-report)

## :rocket: Технологии и инструменты

<p align="center">
<a href="https://www.java.com/"><img src="images/logos/Java.svg" width="50" height="50"  alt="Java"/></a>
<a href="https://www.jetbrains.com/idea/"><img src="images/logos/Intelij_IDEA.svg" width="50" height="50"  alt="IDEA"/></a>
<a href="https://github.com/"><img src="images/logos/Github.svg" width="50" height="50"  alt="Github"/></a>
<a href="https://junit.org/junit5/"><img src="images/logos/JUnit5.svg" width="50" height="50"  alt="JUnit 5"/></a>
<a href="https://gradle.org/"><img src="images/logos/Gradle.svg" width="50" height="50"  alt="Gradle"/></a>
<a href="https://rest-assured.io/"><img src="images/logos/Rest-Assured.svg" width="50" height="50"  alt="Rest-assured"/></a>
<a href="https://github.com/allure-framework/allure2"><img src="images/logos/Allure_Report.svg" width="50" height="50"  alt="Allure"/></a>
<a href="https://www.jenkins.io/"><img src="images/logos/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a>
</p>

## :scroll: Тест кейсы

- ✓ Проверка списка пользователей.
- ✓ Проверка отдельного пользователя.
- ✓ Проверка ответа "Пользователь не найден".
- ✓ Проверка списка ресурсов.
- ✓ Проверка отдельного ресурса.
- ✓ Проверка ответа "Ресурс не найден".
- ✓ Проверка создания нового ресурса.
- ✓ Проверка создания нового пользователя.
- ✓ Проверка обновления данных пользователя PUT запрос.
- ✓ Проверка обновления данных пользователя PATCH запрос.
- ✓ Проверка удаления пользователя.
- ✓ Проверка успешной регистрации пользователя.
- ✓ Проверка ошибки регистрации пользователя.
- ✓ Проверка успешного входа пользователя.
- ✓ Проверка ошибки входа пользователя.

[К содержанию](#pushpin-содержание)

### :computer: Пример запуска из терминала

Запуск из терминала выглядит следующим образом: \
```gradle clean task``` \
```task``` - в данном случае один api_tests

Пример:
```bash
gradle clean test
```

[К содержанию](#pushpin-содержание)

## <img src="images/logos/Jenkins.svg" width="25" height="25"  alt="Jenkins"/></a>Jenkins job
#### Сборка в Jenkins

#### Главная страница в Jenkins:

<p align="center">
<a href="https://jenkins.autotests.cloud/job/berezkindv_diploma_rest_api_tests_project/"><img src="images/screenshots/jenk_1.png" alt="Jenkins"/></a>
</p>

#### Результаты сборки в Jenkins:

<p align="center">
<a href="https://jenkins.autotests.cloud/job/berezkindv_diploma_rest_api_tests_project/"><img src="images/screenshots/jenk_2.png" alt="Jenkins"/></a>
</p>

[К содержанию](#pushpin-содержание)

## <img src="images/logos/Allure_Report.svg" width="25" height="25"  alt="Allure"/></a>Информация о тестах в Allure report</a>

По завершении сборки можно обратиться к Allure отчету. \
В который входит:
- Протоколирование степов
- Графики прохождения тестов
- Таймлайн
- Различная сортировка тестов по статусу
- И многое другое


#### Основное окно

<p align="center">
<img title="Allure Overview Dashboard" src="images/screenshots/allr_main.png">
</p>

#### Вкладка тесты

<p align="center">
<img title="Allure Tests" src="images/screenshots/allr_suits.png">
</p>

#### Вкладка графики

<p align="center">
<img title="Allure Graphics" src="images/screenshots/allr_graphs.png">
</p>

#### В проекте используется кастомное оформление протоколирования запросов и ответов

<p align="center">
<img title="Allure Graphics" src="images/screenshots/alr_customreq.png">
</p>

[К содержанию](#pushpin-содержание)

