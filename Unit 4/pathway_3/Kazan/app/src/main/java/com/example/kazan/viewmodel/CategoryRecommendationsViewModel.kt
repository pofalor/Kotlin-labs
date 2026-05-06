package com.example.kazan.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kazan.domain.Category
import com.example.kazan.domain.RecommendationsRepository
import com.example.kazan.domain.model.Recommendation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class CategoryRecommendationsUiState(
    val category: Category,
    val recommendations: List<Recommendation> = emptyList(),
    val selectedRecommendationId: String? = null,
)

class CategoryRecommendationsViewModel(
    private val category: Category,
    private val repository: RecommendationsRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(CategoryRecommendationsUiState(category = category))
    val uiState: StateFlow<CategoryRecommendationsUiState> = _uiState

    init {
        // Слой данных: берём рекомендации из репозитория, а UI рисует по uiState.
        val items = repository.getRecommendations(category)
        _uiState.update { it.copy(recommendations = items) }
    }

    fun onRecommendationClicked(recommendationId: String) {
        _uiState.update { it.copy(selectedRecommendationId = recommendationId) }
    }
}

class CategoryRecommendationsViewModelFactory(
    private val category: Category,
    private val repository: RecommendationsRepository,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CategoryRecommendationsViewModel(category, repository) as T
    }
}

