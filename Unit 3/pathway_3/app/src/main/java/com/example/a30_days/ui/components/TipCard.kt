package com.example.a30_days.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.a30_days.R
import com.example.a30_days.data.Tip
import com.example.a30_days.ui.theme.Dimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipCard(
    tip: Tip,
    onClick: () -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(),
        shape = RoundedCornerShape(Dimensions.cardCornerRadius),
        elevation = CardDefaults.cardElevation(
            defaultElevation = Dimensions.cardElevation,
            pressedElevation = Dimensions.cardPressedElevation
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Image with gradient overlay
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimensions.cardImageHeight)
            ) {
                // Используем локальное изображение из ресурсов
                Image(
                    painter = painterResource(id = tip.imageRes),
                    contentDescription = stringResource(tip.titleRes),
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(
                            topStart = Dimensions.cardCornerRadius,
                            topEnd = Dimensions.cardCornerRadius
                        )),
                    contentScale = ContentScale.Crop
                )

                // Gradient overlay for text readability
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Dimensions.spacing32 * 2.5f)
                        .align(Alignment.BottomStart)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.7f)
                                )
                            )
                        )
                )

                // Day number badge
                Surface(
                    modifier = Modifier
                        .padding(Dimensions.spacing16)
                        .align(Alignment.TopEnd),
                    shape = RoundedCornerShape(Dimensions.badgeCornerRadius),
                    color = MaterialTheme.colorScheme.primary,
                    shadowElevation = Dimensions.spacing4
                ) {
                    Text(
                        text = stringResource(R.string.day_prefix, tip.day),
                        modifier = Modifier.padding(
                            horizontal = Dimensions.badgePaddingHorizontal,
                            vertical = Dimensions.badgePaddingVertical
                        ),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White
                    )
                }
            }

            // Content
            Column(
                modifier = Modifier.padding(Dimensions.spacing16)
            ) {
                Text(
                    text = stringResource(tip.titleRes),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(Dimensions.spacing8))

                Text(
                    text = stringResource(tip.descriptionRes),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 3,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(Dimensions.spacing8))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = { isExpanded = !isExpanded },
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(
                            text = if (isExpanded) stringResource(R.string.collapse)
                            else stringResource(R.string.expand),
                            style = MaterialTheme.typography.labelMedium
                        )
                        Icon(
                            imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp
                            else Icons.Default.KeyboardArrowDown,
                            contentDescription = null,
                            modifier = Modifier.size(Dimensions.spacing20)
                        )
                    }

                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(Dimensions.spacing20)
                    )
                }
            }
        }
    }
}