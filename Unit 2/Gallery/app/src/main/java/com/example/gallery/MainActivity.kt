package com.example.gallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gallery.ui.theme.GalleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GalleryTheme {
                GalleryApp()
            }
        }
    }
}

@Composable
@Preview
fun GalleryApp() {
    var currentPage by remember { mutableIntStateOf(1) }
    when(currentPage){
        1 -> ImageWithButton(R.drawable.forest_bridge,
            R.string.forest_bridge_descr,
            onNextClick = {
                currentPage = 2
            },
            onPrevClick = {
                currentPage = 4
            })
        2 -> ImageWithButton(R.drawable.forest_brod,
            R.string.forest_brod_descr,
            onNextClick = {
                currentPage = 3
            },
            onPrevClick = {
                currentPage = 1
            })
        3 -> ImageWithButton(R.drawable.forest_river,
            R.string.forest_river_descr,
            onNextClick = {
                currentPage = 4
            },
            onPrevClick = {
                currentPage = 2
            })
        4 -> ImageWithButton(R.drawable.town,
            R.string.town_im_descr,
            onNextClick = {
                currentPage = 1
            },
            onPrevClick = {
                currentPage = 3
            })
    }
}

@Composable
fun ImageWithButton(imageId : Int,
                    imageDescr: Int,
                    onNextClick: () -> Unit,
                    onPrevClick: () -> Unit,
                    modifier: Modifier = Modifier){
    Column (
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
                painter = painterResource(imageId),
                contentDescription = stringResource(imageDescr)
            )
        Spacer(modifier = Modifier.height(40.dp))
        Row(){
            Button(onClick = onPrevClick,
                modifier = Modifier
                    .width(120.dp)) {
                Text(stringResource(R.string.prevImage))
            }
            Spacer(modifier = Modifier.width(20.dp))
            Button(onClick = onNextClick,
                modifier = Modifier
                    .width(120.dp)) {
                Text(stringResource(R.string.nextImage))
            }
        }
    }
}