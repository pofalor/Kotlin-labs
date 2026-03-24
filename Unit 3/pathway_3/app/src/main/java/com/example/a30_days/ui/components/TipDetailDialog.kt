package com.example.a30_days.ui.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.a30_days.R
import com.example.a30_days.data.Tip
import com.example.a30_days.ui.theme.Dimensions

@Composable
fun TipDetailDialog(
    tip: Tip,
    onDismiss: () -> Unit
) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
    }

    AlertDialog(
        onDismissRequest = {
            visible = false
            onDismiss()
        },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = Dimensions.dialogMaxHeight),
        shape = RoundedCornerShape(Dimensions.dialogCornerRadius),
        containerColor = MaterialTheme.colorScheme.surface,
        title = {
            Column {
                Text(
                    text = stringResource(R.string.day_prefix, tip.day),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = stringResource(tip.titleRes),
                    style = MaterialTheme.typography.titleLarge,
                )
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                // Используем локальное изображение из ресурсов
                Image(
                    painter = painterResource(id = tip.imageRes),
                    contentDescription = stringResource(tip.titleRes),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Dimensions.dialogImageHeight)
                        .clip(RoundedCornerShape(Dimensions.spacing16)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(Dimensions.spacing16))

                Text(
                    text = stringResource(tip.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(Dimensions.spacing16))

                // Tip of the day quote
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(Dimensions.spacing16),
                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                ) {
                    Text(
                        text = "✨ ${stringResource(tip.affirmationRes)}",
                        modifier = Modifier.padding(Dimensions.spacing16),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                visible = false
                onDismiss()
            }) {
                Text(stringResource(R.string.close))
            }
        }
    )
}