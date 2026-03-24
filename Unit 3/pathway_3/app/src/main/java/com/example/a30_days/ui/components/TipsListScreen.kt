package com.example.a30_days.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.a30_days.R
import com.example.a30_days.data.Tip
import com.example.a30_days.ui.components.TipCard
import com.example.a30_days.ui.components.TipDetailDialog
import com.example.a30_days.ui.theme.Dimensions
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipsListScreen(
    tips: List<Tip>
) {
    val listState = rememberLazyListState()
    var selectedTip by remember { mutableStateOf<Tip?>(null) }
    val showScrollToTop by remember {
        derivedStateOf { listState.firstVisibleItemIndex > 0 }
    }
    val coroutineScope = rememberCoroutineScope() // Добавляем coroutineScope

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(Dimensions.spacing16),
            verticalArrangement = Arrangement.spacedBy(Dimensions.spacing16)
        ) {
            items(tips, key = { it.day }) { tip ->
                TipCard(
                    tip = tip,
                    onClick = { selectedTip = tip }
                )
            }
        }

        // Scroll to top button
        if (showScrollToTop) {
            FloatingActionButton(
                onClick = {
                    // Запускаем корутину для анимированной прокрутки
                    coroutineScope.launch {
                        listState.animateScrollToItem(0)
                    }
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(Dimensions.spacing16),
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowUpward,
                    contentDescription = stringResource(R.string.scroll_to_top)
                )
            }
        }
    }

    // Detail dialog
    selectedTip?.let { tip ->
        TipDetailDialog(
            tip = tip,
            onDismiss = { selectedTip = null }
        )
    }
}