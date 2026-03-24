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
            ),
            Triple(
                R.string.tip_11_title,
                R.string.tip_11_description,
                R.string.tip_11_affirmation
            ),
            Triple(
                R.string.tip_12_title,
                R.string.tip_12_description,
                R.string.tip_12_affirmation
            ),
            Triple(
                R.string.tip_13_title,
                R.string.tip_13_description,
                R.string.tip_13_affirmation
            ),
            Triple(
                R.string.tip_14_title,
                R.string.tip_14_description,
                R.string.tip_14_affirmation
            ),
            Triple(
                R.string.tip_15_title,
                R.string.tip_15_description,
                R.string.tip_15_affirmation
            ),
            Triple(
                R.string.tip_16_title,
                R.string.tip_16_description,
                R.string.tip_16_affirmation
            ),
            Triple(
                R.string.tip_17_title,
                R.string.tip_17_description,
                R.string.tip_17_affirmation
            ),
            Triple(
                R.string.tip_18_title,
                R.string.tip_18_description,
                R.string.tip_18_affirmation
            ),
            Triple(
                R.string.tip_19_title,
                R.string.tip_19_description,
                R.string.tip_19_affirmation
            ),
            Triple(
                R.string.tip_20_title,
                R.string.tip_20_description,
                R.string.tip_20_affirmation
            ),

            // Декада 3: Рефлексия и выход в свет (Дни 21-30)
            Triple(
                R.string.tip_21_title,
                R.string.tip_21_description,
                R.string.tip_21_affirmation
            ),
            Triple(
                R.string.tip_22_title,
                R.string.tip_22_description,
                R.string.tip_22_affirmation
            ),
            Triple(
                R.string.tip_23_title,
                R.string.tip_23_description,
                R.string.tip_23_affirmation
            ),
            Triple(
                R.string.tip_24_title,
                R.string.tip_24_description,
                R.string.tip_24_affirmation
            ),
            Triple(
                R.string.tip_25_title,
                R.string.tip_25_description,
                R.string.tip_25_affirmation
            ),
            Triple(
                R.string.tip_26_title,
                R.string.tip_26_description,
                R.string.tip_26_affirmation
            ),
            Triple(
                R.string.tip_27_title,
                R.string.tip_27_description,
                R.string.tip_27_affirmation
            ),
            Triple(
                R.string.tip_28_title,
                R.string.tip_28_description,
                R.string.tip_28_affirmation
            ),
            Triple(
                R.string.tip_29_title,
                R.string.tip_29_description,
                R.string.tip_29_affirmation
            ),
            Triple(
                R.string.tip_30_title,
                R.string.tip_30_description,
                R.string.tip_30_affirmation
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
            R.drawable.photo_16,
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