package com.example.lemonade

import android.graphics.ImageDecoder
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
@Preview
fun LemonadeApp() {
    var currentPage by remember { mutableIntStateOf(1) }
    val totalClicks = (2..4).random()
    var currentClicks = 0
    when(currentPage){
        1 -> ImageWithText(R.drawable.lemon_tree,
            R.string.lemon_tree_descr,
            R.string.lemon_tree,
            onImageClick = {
                currentPage = 2
            })
        2 -> {
            ImageWithText(
                R.drawable.lemon_squeeze,
                R.string.squeeze_lemon_descr,
                R.string.squeeze_lemon,
                onImageClick = {
                    currentClicks++
                    if(currentClicks == totalClicks)
                        currentPage = 3
                })
        }
        3 -> ImageWithText(R.drawable.lemon_drink,
            R.string.drink_lemonade_descr,
            R.string.drink_lemonade,
            onImageClick = {
                currentPage = 4
            })
        4 -> ImageWithText(R.drawable.lemon_restart,
            R.string.empty_glass_descr,
            R.string.empty_glass,
            onImageClick = {
                currentPage = 1
            })
    }
}

@Composable
fun ImageWithText(imageId : Int,
                  imageDescr: Int,
                  text: Int,
                  onImageClick: () -> Unit,
                  modifier: Modifier = Modifier){
    Column (
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onImageClick,
            shape = RoundedCornerShape(dimensionResource(R.dimen.image_corner_radius)),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer))
        {
            Image(
                painter = painterResource(imageId),
                contentDescription = stringResource(imageDescr),
                modifier = Modifier.size(180.dp)
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
        Text(stringResource(text))
    }
}