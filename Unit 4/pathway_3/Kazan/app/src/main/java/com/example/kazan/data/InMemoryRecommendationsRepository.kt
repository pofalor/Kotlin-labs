package com.example.kazan.data

import com.example.kazan.domain.Category
import com.example.kazan.domain.RecommendationsRepository
import com.example.kazan.domain.model.Recommendation

class InMemoryRecommendationsRepository : RecommendationsRepository {

    private val coffee = listOf(
        Recommendation(
            id = "coffee_like",
            title = "Coffee Like",
            shortDescription = "Быстрый сервис, бодрые вкусы и десерты на каждый день."
        ),
        Recommendation(
            id = "coffee_lab",
            title = "Caffeine Lab",
            shortDescription = "Авторские напитки и альтернативное молоко для разных предпочтений."
        ),
        Recommendation(
            id = "ciferblat",
            title = "Циферблат",
            shortDescription = "Кофе + пространство с настолками: можно и поговорить, и сыграть."
        ),
        Recommendation(
            id = "cozy_windows",
            title = "Кофейня «Уют у окна»",
            shortDescription = "Тёплая атмосфера и мягкие завтраки для спокойного утра."
        ),
        Recommendation(
            id = "barista_corner",
            title = "Barista Corner",
            shortDescription = "Латте-арт, интересные зерна и вкусные напитки «на вынос»."
        ),
    )

    private val restaurants = listOf(
        Recommendation(
            id = "tatar_kitchen",
            title = "Татарская кухня",
            shortDescription = "Национальные блюда и домашний вкус: идеальный вариант для знакомства с Казанью."
        ),
        Recommendation(
            id = "volga_grill",
            title = "Volga Grill",
            shortDescription = "Мясо на гриле, сезонные предложения и приятная подача."
        ),
        Recommendation(
            id = "sushi_nori",
            title = "Sushi Nori",
            shortDescription = "Роллы, вок и быстрые обеды: удобно зайти по пути."
        ),
        Recommendation(
            id = "hinkalnaya",
            title = "Хинкальная",
            shortDescription = "Хинкали с разными начинками и атмосфера грузинского гостеприимства."
        ),
        Recommendation(
            id = "family_table",
            title = "Семейный стол",
            shortDescription = "Ресторан с понятным меню, комфортными порциями и вариантами для детей."
        ),
    )

    private val kids = listOf(
        Recommendation(
            id = "masterslavl",
            title = "Мастерславль",
            shortDescription = "Интерактивные занятия и мастерские, где ребёнок пробует и создаёт сам."
        ),
        Recommendation(
            id = "kids_planet",
            title = "Kids Planet",
            shortDescription = "Большая игровая территория, лабиринты и активности для разного возраста."
        ),
        Recommendation(
            id = "happy_childhood",
            title = "Музей счастливого детства",
            shortDescription = "Экспозиции, которые можно трогать: увлечённое обучение через игру."
        ),
        Recommendation(
            id = "kukol_theatre",
            title = "Детский театр кукол",
            shortDescription = "Спектакли с интерактивом: хороший вариант для семейного вечера."
        ),
        Recommendation(
            id = "adventure_rope",
            title = "Веревочный парк «Приключение»",
            shortDescription = "Маршруты разной сложности: безопасные приключения под присмотром инструкторов."
        ),
    )

    private val parks = listOf(
        Recommendation(
            id = "gorky_park",
            title = "Парк Горького",
            shortDescription = "Прогулки по аллеям, зоны отдыха и развлечения для разных сезонов."
        ),
        Recommendation(
            id = "millennium_park",
            title = "Парк Тысячелетия",
            shortDescription = "Набережная и красивые виды: хорошо гулять, фотографироваться и отдыхать."
        ),
        Recommendation(
            id = "kabans_lake",
            title = "Озеро Кабан",
            shortDescription = "Простор для неспешных маршрутов, особенно приятно вечером."
        ),
        Recommendation(
            id = "youth_square",
            title = "Сквер для прогулок",
            shortDescription = "Зелёные дорожки и лавочки: удобно зайти на короткую прогулку между планами."
        ),
        Recommendation(
            id = "riverwalk",
            title = "Набережная реки",
            shortDescription = "Воздух, набережные дорожки и точки для отдыха на пути."
        ),
    )

    private val malls = listOf(
        Recommendation(
            id = "tandem",
            title = "ТРЦ «Тандем»",
            shortDescription = "Большой выбор магазинов, фуд-корт и развлечения на весь день."
        ),
        Recommendation(
            id = "korston",
            title = "ТРЦ «Корстон»",
            shortDescription = "Удобно для семейного отдыха: кафе, развлечения и комфортные зоны."
        ),
        Recommendation(
            id = "ring",
            title = "ТРЦ «Кольцо»",
            shortDescription = "Шопинг и перекусы в одном месте — удобно, если нужно быстро собрать план."
        ),
        Recommendation(
            id = "suvar_plaza",
            title = "ТРЦ «Сувар Плаза»",
            shortDescription = "Плотный ассортимент и приятные кафе: хорошо заходить между прогулками."
        ),
        Recommendation(
            id = "mega",
            title = "ТРЦ «МЕГА»",
            shortDescription = "Большие площади, много точек питания и развлечения для всей семьи."
        ),
    )

    override fun getRecommendations(category: Category): List<Recommendation> {
        return when (category) {
            Category.COFFEES -> coffee
            Category.RESTAURANTS -> restaurants
            Category.KIDS -> kids
            Category.PARKS -> parks
            Category.MALLS -> malls
        }
    }
}

