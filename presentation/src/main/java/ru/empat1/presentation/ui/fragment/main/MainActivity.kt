package ru.empat1.presentation.ui.fragment.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.empat1.presentation.VkViewModel
import ru.empat1.presentation.ui.fragment.news.MainScreen
import ru.empat1.presentation.ui.theme.VkCloneTheme

class MainActivity : ComponentActivity() {

    val vkViewModel: VkViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VkCloneTheme {
                MainScreen(vkViewModel)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VkCloneTheme {
    }
}


