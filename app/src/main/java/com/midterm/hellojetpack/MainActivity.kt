package com.midterm.hellojetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.midterm.hellojetpack.ui.theme.HelloJetpackTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloJetpackTheme {
                val counter = remember { mutableStateOf(0) }
                val shouldCount = remember { mutableStateOf(true) }

                LaunchedEffect(shouldCount.value) { // Effects coroutine
                    while (shouldCount.value) { // Only run while shouldCount is true
                        counter.value++
                        delay(1000) // Delay between increments
                    }
                }

                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .fillMaxWidth(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "${counter.value}", style = TextStyle(color = Color.Blue,
                            fontSize = 35.sp,
                            fontWeight = FontWeight.ExtraBold))
                        Spacer(modifier = Modifier.height(130.dp))
                        CreateCircle(shouldCount) {
                            shouldCount.value = !shouldCount.value
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CreateCircle(shouldCount: MutableState<Boolean>, onClick: () -> Unit){
    Card(
        modifier = Modifier
            .padding(3.dp)
            .size(150.dp)
            .clickable { onClick.invoke() },
        shape = CircleShape
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = if (shouldCount.value) "Stop" else "Start")
        }
    }
}