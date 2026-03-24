package com.example.a30_days.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.a30_days.R
import com.example.a30_days.data.Tip
import com.example.a30_days.ui.components.TipCard
import com.example.a30_days.ui.components.TipsListScreen
import com.example.a30_days.ui.theme.ThirtyDaysTheme

@Preview(showBackground = true, name = "Light Theme")
@Composable
fun PreviewTipsListScreenLight() {
    ThirtyDaysTheme(darkTheme = false) {
        TipsListScreen(tips = getPreviewTips())
    }
}

@Preview(showBackground = true, name = "Dark Theme")
@Composable
fun PreviewTipsListScreenDark() {
    ThirtyDaysTheme(darkTheme = true) {
        TipsListScreen(tips = getPreviewTips())
    }
}

@Preview(showBackground = true, name = "Tip Card Preview")
@Composable
fun PreviewTipCard() {
    ThirtyDaysTheme {
        TipCard(
            tip = Tip(
                day = 1,
                titleRes = R.string.tip_1_title,
                descriptionRes = R.string.tip_1_description,
                imageRes = R.drawable.photo_1,  // Используем локальное изображение
                affirmationRes = R.string.tip_1_affirmation
            ),
            onClick = {}
        )
    }
}

private fun getPreviewTips(): List<Tip> {
    return listOf(
        Tip(
            day = 1,
            titleRes = R.string.tip_1_title,
            descriptionRes = R.string.tip_1_description,
            imageRes = R.drawable.photo_1,
            affirmationRes = R.string.tip_1_affirmation
        ),
        Tip(
            day = 2,
            titleRes = R.string.tip_2_title,
            descriptionRes = R.string.tip_2_description,
            imageRes = R.drawable.photo_2,
            affirmationRes = R.string.tip_2_affirmation
        )
    )
}