package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel : ViewModel() {

    // Внутреннее изменяемое состояние
    private val _uiState = MutableStateFlow(DessertUiState())

    // Публичное неизменяемое состояние, на которое будет подписан UI
    val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()

    fun onDessertClicked() {
        _uiState.update { currentState ->
            val dessertsSold = currentState.dessertsSold + 1
            val revenue = currentState.revenue + currentState.currentDessertPrice

            // Определяем, какой десерт нужно показывать следующим
            val nextDessertIndex = determineDessertIndex(dessertsSold)
            val nextDessert = Datasource.dessertList[nextDessertIndex]

            // Копируем текущее состояние с новыми значениями
            currentState.copy(
                dessertsSold = dessertsSold,
                revenue = revenue,
                currentDessertIndex = nextDessertIndex,
                currentDessertPrice = nextDessert.price,
                currentDessertImageId = nextDessert.imageId
            )
        }
    }

    /**
     * Логика определения десерта, которая раньше была в MainActivity
     */
    private fun determineDessertIndex(dessertsSold: Int): Int {
        var dessertIndex = 0
        for ((index, dessert) in Datasource.dessertList.withIndex()) {
            if (dessertsSold >= dessert.startProductionAmount) {
                dessertIndex = index
            } else {
                break
            }
        }
        return dessertIndex
    }
}