package ru.empat1.presentation.ui.fragment.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vk.id.VKID
import com.vk.id.auth.VKIDAuthUiParams
import com.vk.id.onetap.compose.onetap.OneTap
import com.vk.id.onetap.compose.onetap.OneTapTitleScenario
import ru.empat1.presentation.VkViewModel
import ru.empat1.presentation.ui.fragment.news.MainScreen
import ru.empat1.presentation.ui.theme.VkCloneTheme

class MainActivity : ComponentActivity() {

    val vkViewModel: VkViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        VKID.init(this)
        enableEdgeToEdge()
        setContent {
            VkCloneTheme {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically) {
                    registationButton()
                }
//                MainScreen(vkViewModel)
            }
        }
    }
}

@Composable
fun registationButton(){
    OneTap(
        onAuth = { oAuth, token ->
            Log.d("LoginScreen", "token: ${token.token}")
        },
        onFail = { oAuth, fail ->
            Log.d("LoginScreen", "token: ${fail.description}")
        },
        authParams = VKIDAuthUiParams {
            scopes = setOf("wall", "email", "phone_number", "friends")
        },
        scenario = OneTapTitleScenario.SignUp,
        signInAnotherAccountButtonEnabled = true,
    )
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


