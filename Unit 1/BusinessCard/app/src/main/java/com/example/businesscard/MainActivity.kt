package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                ComposeCard()
            }
        }
    }
}

@Composable
fun ComposeCard() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Header - по центру экрана
        ComposableHeader(
            modifier = Modifier.align(Alignment.Center)
        )

        // Footer - в самом низу экрана
        ComposableFooter(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun ComposableHeader(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val image = painterResource(R.drawable.im_header)
        Image(
            painter = image,
            contentDescription = null
        )
        Text(
            text = stringResource(R.string.full_name),
            fontSize = 50.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(R.string.tg_username),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
    }
}

@Composable
private fun ComposableFooter(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    )
    {
        Row(
            modifier = modifier.padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Filled.Phone,
                contentDescription = "Phone Icon",
                tint = Color.Gray
            )
            Text(
                text = stringResource(R.string.phone_number),
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Row(
            modifier = modifier.padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val image = painterResource(R.drawable.tg_airplain_icon)
            Icon(
                painter = image,
                contentDescription = "Telegram Icon",
                tint = Color.Unspecified,
                modifier = Modifier.size(25.dp).scale(1.5f)
            )
            Text(
                text = "@" + stringResource(R.string.tg_username),
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Row(
            modifier = modifier.padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Filled.Email,
                contentDescription = "Email Icon",
                tint = Color.Gray
            )
            Text(
                text = stringResource(R.string.email),
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BusinessCardTheme {
        ComposeCard()
    }
}