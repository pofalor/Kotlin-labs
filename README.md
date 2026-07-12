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
- JUnit, Compose UI Testing — модульные и инструментальные тесты

## Структура репозитория

### Unit 1 — основы Kotlin и первые Compose-приложения

| Проект | Что изучается |
|---|---|
| `example` | Основы Kotlin: `val`/`var`, вывод типов, строковые шаблоны, функции |
| `greeting_card` | Первый проект на Compose: `@Composable`-функции, `@Preview`, дерево композиции |
| `HappyBirthday` | Открытка: `Text` и `Image`, компоновка через `Column`/`Box`, модификаторы (`Modifier.padding`, выравнивание), строковые ресурсы |
| `ComposeArticle` | Экран статьи: вертикальная компоновка `Column`, `painterResource`, параметры текста (`textAlign`, `fontSize`) |
| `ComposeSuccess` | Экран завершённых задач: центрирование контента, комбинирование `Image` + `Text` |
| `ComposeQuadrant` | Экран из четырёх квадрантов: вложенные `Row`/`Column`, распределение места через `Modifier.weight` |
| `BusinessCard` | Визитка — самостоятельная вёрстка экрана: композиция переиспользуемых composable-функций, иконки Material |

### Unit 2 — интерактивность и основы ООП в Kotlin

**Android-приложения:**

| Проект | Что изучается |
|---|---|
| `DiceRoller` | Состояние в Compose: `remember` + `mutableStateOf`, рекомпозиция, обработка нажатий `Button` |
| `Lemonade` | Приложение-«автомат состояний»: переключение шагов через `when`, кликабельные изображения, связка состояния и ресурсов |
| `Gallery` | Галерея (Art Space): хранение индекса в состоянии, навигация «вперёд/назад» кнопками, композиция экрана |
| `basic-...-tip-calculator-starter` | Калькулятор чаевых: `OutlinedTextField`, паттерн **state hoisting** (подъём состояния), `KeyboardOptions`, форматирование чисел. Тесты: модульные (`TipCalculatorTests`, JUnit) и UI-тесты Compose (`TipUITests`, `createComposeRule`) |

**Консольные упражнения (основы Kotlin):**

| Проект | Что изучается |
|---|---|
| `test_fundamentals` | Операторы сравнения, проверки `in` и `is` |
| `traffic light`, `traffic color with when value` | Условная логика: `when` по значению, ветка `else` |
| `cinema_price` | `when` с диапазонами (`in 0..12`), возврат результата выражения |
| `mobile_notifications` | Функции с параметрами, `if`/`else` |
| `example_class` | ООП: открытые классы и наследование (`open`/`override`), видимость сеттера (`protected set`), делегированные свойства (`ReadWriteProperty`) |
| `song_catalog` | Класс с вычисляемым свойством (кастомный `get()`) |
| `foldable_phone` | Наследование и переопределение поведения, параметры конструктора со значениями по умолчанию |
| `internet_profile` | Null-безопасность: nullable-типы, безопасные вызовы `?.`, проверки на `null` |
| `special_auction` | Nullable-объекты как параметры функций |
| `temperature_converter` | Функции высшего порядка и лямбды: параметр типа `(Double) -> Double` |

### Unit 3 — коллекции, списки и Material Design

**Консольные упражнения:**

| Проект | Что изучается |
|---|---|
| `collections_exercices` | `data class`, `enum class`, свойства-расширения, операции над коллекциями: `filter`, `groupBy`, `sortedBy`, `forEach` |
| `test_colors` | Обобщённые (generic) функции-расширения для `List<T>` |

**Android-приложения:**

| Проект | Что изучается |
|---|---|
| `basic-...-affirmations-starter` / `-intermediate` | Списки: `LazyColumn`, карточки `Card`, модель `data class` с аннотациями `@StringRes`/`@DrawableRes`, слой данных `Datasource` |
| `Courses` | Сетки: `LazyVerticalGrid`, адаптивные ячейки, разделение на `model`/`data`/UI |
| `SuperheroApp` | Темизация Material Design 3: собственные `Color`, `Shape`, `Typography` (кастомные шрифты), `Scaffold` + `TopAppBar`, объект-репозиторий с данными |
| `pathway_3` (приложение «30 дней») | Собственный проект — 30 советов: сплэш-экран, диалог деталей (`Dialog`), анимация разворачивания карточки (`animateContentSize` + `tween`), вынос размеров в тему (`Dimensions`), паттерн Repository |
| `pathway_3/WoofApp` | Woof: комплексная тема Material 3 (цвета, формы, типографика), `Scaffold`/`TopAppBar`, пружинная анимация раскрытия карточки (`animateContentSize` + `spring`) |

### Unit 4 — архитектура приложения и навигация

**pathway 1 — жизненный цикл и ViewModel:**

| Проект | Что изучается |
|---|---|
| `basic-...-dessert-clicker-starter` | Жизненный цикл Activity: колбэки `onStart`/`onResume`/`onPause` и др., логирование в Logcat (`Log.d`), затем рефакторинг — вынос состояния в `ViewModel` + `UiState` |
| `basic-...-unscramble-starter` / `-main` | Игра «Собери слово»: архитектура **MVVM** — `ViewModel`, `StateFlow`/`MutableStateFlow`, `collectAsState`, неизменяемый `UiState` (`data class` + `copy`), `AlertDialog`. В `-main` — модульные тесты `GameViewModelTest`: схема success/error/boundary case |

**pathway 2 — навигация между экранами:**

| Проект | Что изучается |
|---|---|
| `basic-...-cupcake-starter` | Заказ кексов: **Navigation Compose** — `NavHost`, `NavController`, маршруты через `enum`, общий для всех экранов `OrderViewModel` (паттерн shared ViewModel), кнопка «назад» в `AppBar`, отправка заказа через неявный `Intent`. Тесты навигации: `CupcakeScreenNavigationTest`, `CupcakeOrderScreenTest`, свои расширения для тестов (`ComposeRuleExtensions`) |
| `basic-...-lunch-tray-main` | Заказ обеда — самостоятельное закрепление: граф навигации с нуля, переиспользуемый экран меню (`BaseMenuScreen`), `OrderViewModel` с подсчётом итога |

**pathway 3 — адаптивные макеты:**

| Проект | Что изучается |
|---|---|
| `basic-...-reply-app-main` | Почтовый клиент Reply: адаптация под размер экрана — `calculateWindowSizeClass`, `WindowWidthSizeClass`, разные типы навигации (нижняя панель / `NavigationRail` / постоянный drawer) |
| `basic-...-reply-app-nav-update` | Вариант Reply с доработанной адаптивной навигацией и раздельными экранами списка/деталей |
| `basic-...-sports-main` | Sports: адаптивный паттерн **list-detail** — на широких экранах список и детали одновременно, обработка `BackHandler`, `ViewModel` + `UiState` |
| `Kazan` | Собственный итоговый проект — путеводитель по Казани: слоистая архитектура `data` / `domain` / `ui` / `viewmodel`, паттерн **Repository** (интерфейс `RecommendationsRepository` + in-memory реализация), MVVM со `StateFlow`, `Scaffold`, `LazyColumn`, экраны категорий и рекомендаций |

## Как запустить

**Android-проекты.** Каждое приложение — отдельный Gradle-проект: в Android Studio откройте папку конкретного проекта (например, `Unit 2/DiceRoller`), а не корень репозитория. После синхронизации Gradle запустите конфигурацию `app` на эмуляторе или устройстве (Android 7.0+, API 24).

**Консольные упражнения.** Папки со структурой `src`/`out` открываются в IntelliJ IDEA; запуск — через функцию `main` нужного файла.

**Тесты.** В проектах с тестами (Tip Calculator, Unscramble, Cupcake) они запускаются из Android Studio или командой:

```bash
./gradlew test           # модульные тесты
./gradlew connectedCheck # инструментальные тесты (нужен эмулятор/устройство)
```
