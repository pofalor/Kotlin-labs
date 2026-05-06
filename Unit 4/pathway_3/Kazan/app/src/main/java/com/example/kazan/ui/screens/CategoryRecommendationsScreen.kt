package com.example.kazan.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kazan.domain.Category
import com.example.kazan.domain.RecommendationsRepository
import com.example.kazan.ui.components.RecommendationCard
import com.example.kazan.viewmodel.CategoryRecommendationsViewModel
import com.example.kazan.viewmodel.CategoryRecommendationsViewModelFactory
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass

@Composable
fun CategoryRecommendationsScreen(
    category: Category,
    repository: RecommendationsRepository,
    windowSizeClass: WindowWidthSizeClass,
) {
    val factory = remember(category, repository) {
        CategoryRecommendationsViewModelFactory(category, repository)
    }
    val viewModel: CategoryRecommendationsViewModel = viewModel(factory = factory)
    val uiState by viewModel.uiState.collectAsState()

    val padding = 16.dp

    val contentPadding = PaddingValues(
        start = padding,
        end = padding,
        top = padding,
        bottom = padding,
    )

    val selectedId = uiState.selectedRecommendationId
    val recommendations = uiState.recommendations

    if (windowSizeClass == WindowWidthSizeClass.Expanded) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = contentPadding,
            modifier = Modifier.fillMaxSize(),
        ) {
            items(
                items = recommendations,
                key = { recommendation -> recommendation.id },
            ) { recommendation ->
                RecommendationCard(
                    recommendation = recommendation,
                    isSelected = recommendation.id == selectedId,
                    onClick = { viewModel.onRecommendationClicked(recommendation.id) },
                )
            }
        }
    } else {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = contentPadding,
            modifier = Modifier.fillMaxSize(),
        ) {
            items(
                items = recommendations,
                key = { recommendation -> recommendation.id },
            ) { recommendation ->
                RecommendationCard(
                    recommendation = recommendation,
                    isSelected = recommendation.id == selectedId,
                    onClick = { viewModel.onRecommendationClicked(recommendation.id) },
                )
            }
        }
    }
}

