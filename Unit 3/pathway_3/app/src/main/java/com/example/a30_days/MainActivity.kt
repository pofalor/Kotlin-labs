package com.example.a30_days

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import coil.compose.AsyncImage
import com.example.a30_days.ui.theme.ThirtyDaysTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            ThirtyDaysTheme {
                ThirtyDaysApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThirtyDaysApp() {
    var showSplash by remember { mutableStateOf(true) }

    // Splash screen animation
    LaunchedEffect(Unit) {
        delay(2000)
        showSplash = false
    }

    if (showSplash) {
        SplashScreen()
    } else {
        val tips = remember { generateTips() }
        val listState = rememberLazyListState()
        var selectedTip by remember { mutableStateOf<Tip?>(null) }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "30 дней творчества",
                            fontFamily = FontFamily(
                                Font(R.font.playfair_display_bold, FontWeight.Bold)
                            ),
                            fontSize = 20.sp
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        titleContentColor = MaterialTheme.colorScheme.primary
                    )
                )
            },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = {
                        // Scroll to top with animation
                        // Implementation below
                    },
                    icon = {
                        Icon(Icons.Default.ArrowUpward, contentDescription = "Наверх")
                    },
                    text = { Text("Наверх") },
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            }
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                TipsList(
                    tips = tips,
                    listState = listState,
                    onTipClick = { selectedTip = it }
                )
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
}

@Composable
fun SplashScreen() {
    val infiniteTransition = rememberInfiniteTransition()
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.secondary
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.AutoAwesome,
                contentDescription = null,
                modifier = Modifier.size(80.dp * scale),
                tint = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "30 дней\nосознанного творчества",
                fontFamily = FontFamily(
                    Font(R.font.playfair_display_bold, FontWeight.Bold)
                ),
                fontSize = 28.sp,
                color = Color.White,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "День за днём к вдохновению",
                fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                fontSize = 14.sp,
                color = Color.White.copy(alpha = 0.8f)
            )
        }
    }
}

@Composable
fun TipsList(
    tips: List<Tip>,
    listState: androidx.compose.foundation.lazy.LazyListState,
    onTipClick: (Tip) -> Unit
) {
    LazyColumn(
        state = listState,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(tips, key = { it.day }) { tip ->
            TipCard(tip = tip, onClick = { onTipClick(tip) })
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TipCard(tip: Tip, onClick: () -> Unit) {
    var isExpanded by remember { mutableStateOf(false) }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
            pressedElevation = 8.dp
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
                    .height(200.dp)
            ) {
                Image(
                    painter = painterResource(tip.imageUrl),
                    contentDescription = tip.title,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
                    contentScale = ContentScale.Crop
                )

                // Gradient overlay for text readability
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
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
                        .padding(16.dp)
                        .align(Alignment.TopEnd),
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colorScheme.primary,
                    shadowElevation = 4.dp
                ) {
                    Text(
                        text = "День ${tip.day}",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                        fontSize = 12.sp,
                        color = Color.White
                    )
                }
            }

            // Content
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = tip.title,
                    fontFamily = FontFamily(Font(R.font.playfair_display_medium)),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = tip.description,
                    fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 3,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

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
                            text = if (isExpanded) "Свернуть" else "Подробнее",
                            fontFamily = FontFamily(Font(R.font.montserrat_medium))
                        )
                        Icon(
                            imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp
                            else Icons.Default.KeyboardArrowDown,
                            contentDescription = null
                        )
                    }

                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Открыть",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun TipDetailDialog(tip: Tip, onDismiss: () -> Unit) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
    }

    androidx.compose.material3.AlertDialog(
        onDismissRequest = {
            visible = false
            onDismiss()
        },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 600.dp),
        shape = RoundedCornerShape(24.dp),
        containerColor = MaterialTheme.colorScheme.surface,
        title = {
            Column {
                Text(
                    text = "День ${tip.day}",
                    fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = tip.title,
                    fontFamily = FontFamily(Font(R.font.playfair_display_bold)),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                // Large image in dialog
                AsyncImage(
                    model = tip.imageUrl,
                    contentDescription = tip.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = tip.description,
                    fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Tip of the day quote
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                ) {
                    Text(
                        text = "✨ " + tip.affirmation,
                        modifier = Modifier.padding(16.dp),
                        fontFamily = FontFamily(Font(R.font.montserrat_italic)),
                        fontSize = 14.sp,
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
                Text("Закрыть")
            }
        }
    )
}

data class Tip(
    val day: Int,
    val title: String,
    val description: String,
    @DrawableRes val imageUrl: Int,
    val affirmation: String
)

fun generateTips(): List<Tip> {
    val tips = mutableListOf<Tip>()

    // Sample images from reliable sources (you can replace with your own)
    val baseImages = R.drawable.photo_1

    val tipsData = listOf(
        Triple("Организуйте уголок",
            "Найдите 15 минут, чтобы очистить рабочее место от лишнего. Творчество начинается с ясности пространства. Уберите все, что отвлекает, оставьте только необходимые материалы. Чистое пространство = чистый ум.",
            "Порядок вокруг создает порядок в голове"),
        Triple("Утренние страницы",
            "Напишите три страницы от руки, не задумываясь о стиле. Просто выгрузите мысли из головы на бумагу. Не оценивайте, не редактируйте, просто пишите все, что приходит в голову.",
            "Ваши мысли — это первый материал для творчества"),
        Triple("Наблюдение",
            "Выйдите на улицу и найдите 10 предметов определенного цвета (например, красного). Это тренирует фокус внимания и учит замечать детали, которые обычно ускользают.",
            "Внимательность — мать вдохновения"),
        Triple("Смена материала",
            "Если вы работаете в цифре, возьмите глину или краски. Если традиционно — попробуйте планшет. Тактильные ощущения будят новые нейронные связи и расширяют творческий диапазон.",
            "Новый материал = новый взгляд"),
        Triple("10 идей за 10 минут",
            "Не оценивая качество, запишите 10 идей на тему «Что я могу создать сегодня». Количество переходит в качество. Первые идеи будут банальными, но пятая и дальше — настоящие жемчужины.",
            "Гениальность скрывается за количеством"),
        Triple("Работа с ограничениями",
            "Используйте только 3 цвета или только квадратные формы. Ограничения парадоксальным образом освобождают фантазию и заставляют мыслить нестандартно.",
            "Свобода рождается из ограничений"),
        Triple("Вдохновение у мастеров",
            "Найдите одного художника или дизайнера, чей стиль вам откликается. Сделайте 3 быстрых наброска в его манере. Не копируйте, изучайте технику и подход.",
            "Учись у лучших, чтобы найти свой путь"),
        Triple("Тишина",
            "Работайте 30 минут в полной тишине, без музыки и подкастов. Дайте своим мыслям звучать громче. Тишина помогает услышать свой внутренний голос.",
            "В тишине рождаются лучшие идеи"),
        Triple("Несовершенство",
            "Создайте что-то намеренно «некрасивое» или сломанное. Освободитесь от перфекционизма. Совершенство — враг прогресса.",
            "Красота в несовершенстве"),
        Triple("Коллаж",
            "Вырежьте из старых журналов образы и создайте коллаж желаний или настроения. Это визуализация ваших мечтаний и целей.",
            "Визуализируй свои мечты")
    )

    for (i in 0 until 30) {
        val index = i % tipsData.size
        val (title, description, affirmation) = tipsData[index]
        tips.add(
            Tip(
                day = i + 1,
                title = title,
                description = description,
                imageUrl = baseImages,
                affirmation = affirmation
            )
        )
    }

    return tips
}

@Preview(showBackground = true)
@Composable
fun ThirtyDaysPreview() {
    ThirtyDaysTheme {
        ThirtyDaysApp()
    }
}