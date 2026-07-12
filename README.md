# Kotlin-labs

Лабораторные работы по курсу «Программирование мобильных систем».

Проекты выполнены по программе Google [Android Basics with Compose](https://developer.android.com/courses/android-basics-compose/course) и сгруппированы по юнитам. В репозитории два типа проектов:

- **Android-приложения** на Kotlin + Jetpack Compose (каждое — самостоятельный Gradle-проект);
- **консольные упражнения** на Kotlin (папки со структурой `src`/`out`) — практика по основам языка.

## Технологии

- Kotlin 2.0+
- Jetpack Compose (Material 3, Compose BOM 2024.x)
- Android Gradle Plugin 8.x (Gradle wrapper в каждом проекте)
- minSdk 24, compileSdk/targetSdk 35
- ViewModel, StateFlow, Navigation Compose (юнит 4)
- JUnit — модульные и инструментальные тесты

## Структура репозитория

### Unit 1 — основы Kotlin и первые Compose-приложения

| Проект | Описание |
|---|---|
| `greeting_card` | Первое приложение на Compose |
| `HappyBirthday` | Поздравительная открытка: текст и изображение |
| `BusinessCard` | Визитная карточка |
| `ComposeArticle` | Экран статьи о Compose |
| `ComposeQuadrant` | Экран из четырёх квадрантов |
| `ComposeSuccess` | Экран завершённых задач |
| `example` | Примеры кода на Kotlin |

### Unit 2 — интерактивность и основы Kotlin

| Проект | Описание |
|---|---|
| `DiceRoller` | Бросок кубика: кнопки и состояние |
| `Lemonade` | Приготовление лимонада по шагам |
| `Gallery` | Галерея изображений (Art Space) |
| `basic-...-tip-calculator-starter` | Калькулятор чаевых: поля ввода и вычисления |
| `cinema_price`, `temperature_converter`, `song_catalog`, `special_auction`, `foldable_phone`, `internet_profile`, `mobile_notifications`, `example_class`, `test_fundamentals`, `traffic light`, `traffic color with when value` | Консольные упражнения: классы, наследование, лямбды, `when` и др. |

### Unit 3 — списки, коллекции и Material Design

| Проект | Описание |
|---|---|
| `basic-...-affirmations-starter` / `-intermediate` | Affirmations: списки и карточки (LazyColumn) |
| `Courses` | Сетка курсов (LazyVerticalGrid) |
| `SuperheroApp` | Список супергероев: Material-темизация |
| `pathway_3` (WoofApp) | Woof: Material Design 3, анимации |
| `collections_exercices`, `test_colors` | Консольные упражнения по коллекциям |

### Unit 4 — архитектура и навигация

| Проект | Описание |
|---|---|
| **pathway_1** | |
| `basic-...-dessert-clicker-starter` | Dessert Clicker: жизненный цикл Activity |
| `basic-...-unscramble-starter` / `-main` | Unscramble: ViewModel, StateFlow, модульные тесты |
| **pathway_2** | |
| `basic-...-cupcake-starter` | Cupcake: Navigation Compose, тесты навигации |
| `basic-...-lunch-tray-main` | Lunch Tray: заказ блюд с навигацией по экранам |
| **pathway_3** | |
| `basic-...-reply-app-main` / `-nav-update` | Reply: адаптивные макеты под разные размеры экрана |
| `basic-...-sports-main` | Sports: адаптивная навигация (список/детали) |
| `Kazan` | Собственный проект: путеводитель по Казани с рекомендациями по категориям (MVVM: data / domain / ui / viewmodel) |

## Как запустить

**Android-проекты.** Каждое приложение — отдельный Gradle-проект: в Android Studio откройте папку конкретного проекта (например, `Unit 2/DiceRoller`), а не корень репозитория. После синхронизации Gradle запустите конфигурацию `app` на эмуляторе или устройстве (Android 7.0+, API 24).

**Консольные упражнения.** Папки со структурой `src`/`out` открываются в IntelliJ IDEA; запуск — через функцию `main` нужного файла.

**Тесты.** В проектах юнита 4 (Unscramble, Cupcake) тесты запускаются из Android Studio или командой:

```bash
./gradlew test           # модульные тесты
./gradlew connectedCheck # инструментальные тесты (нужен эмулятор/устройство)
```
