package com.example.a30_days.data

import com.example.a30_days.R

class TipsRepository {

    fun getTips(): List<Tip> {
        val tips = mutableListOf<Tip>()

        val tipsData = listOf(
            Triple(
                R.string.tip_1_title,
                R.string.tip_1_description,
                R.string.tip_1_affirmation
            ),
            Triple(
                R.string.tip_2_title,
                R.string.tip_2_description,
                R.string.tip_2_affirmation
            ),
            Triple(
                R.string.tip_3_title,
                R.string.tip_3_description,
                R.string.tip_3_affirmation
            ),
            Triple(
                R.string.tip_4_title,
                R.string.tip_4_description,
                R.string.tip_4_affirmation
            ),
            Triple(
                R.string.tip_5_title,
                R.string.tip_5_description,
                R.string.tip_5_affirmation
            ),
            Triple(
                R.string.tip_6_title,
                R.string.tip_6_description,
                R.string.tip_6_affirmation
            ),
            Triple(
                R.string.tip_7_title,
                R.string.tip_7_description,
                R.string.tip_7_affirmation
            ),
            Triple(
                R.string.tip_8_title,
                R.string.tip_8_description,
                R.string.tip_8_affirmation
            ),
            Triple(
                R.string.tip_9_title,
                R.string.tip_9_description,
                R.string.tip_9_affirmation
            ),
            Triple(
                R.string.tip_10_title,
                R.string.tip_10_description,
                R.string.tip_10_affirmation
            )
        )

        // Локальные изображения из ресурсов
        // Повторяем изображения циклически, чтобы покрыть 30 дней
        val imageResources = listOf(
            R.drawable.photo_1,      // photo_1.jpg
            R.drawable.photo_2,
            R.drawable.photo_3,
            R.drawable.photo_4,
            R.drawable.photo_5,
            R.drawable.photo_6,
            R.drawable.photo_7,
            R.drawable.photo_8,
            R.drawable.photo_9,
            R.drawable.photo_10,
            R.drawable.photo_11,
            R.drawable.photo_12,
            R.drawable.photo_13,
            R.drawable.photo_14,
            R.drawable.photo_15,
        )

        for (i in 0 until 30) {
            val tipsIndex = i % tipsData.size
            val imageIndex = i % imageResources.size
            val (titleRes, descriptionRes, affirmationRes) = tipsData[tipsIndex]

            tips.add(
                Tip(
                    day = i + 1,
                    titleRes = titleRes,
                    descriptionRes = descriptionRes,
                    imageRes = imageResources[imageIndex],
                    affirmationRes = affirmationRes
                )
            )
        }

        return tips
    }
}