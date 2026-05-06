package com.example.kazan.domain

import com.example.kazan.domain.model.Recommendation

interface RecommendationsRepository {
    fun getRecommendations(category: Category): List<Recommendation>
}

