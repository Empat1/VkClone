package ru.empat1.presentation.ui.fragment.auth

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vk.id.auth.VKIDAuthUiParams
import com.vk.id.onetap.compose.onetap.OneTap
import com.vk.id.onetap.compose.onetap.OneTapTitleScenario
import ru.empat1.presentation.R
import ru.empat1.presentation.VkViewModel

@Composable
fun LoginScreen(viewModel: VkViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            modifier = Modifier.size(94.dp),
            painter = painterResource(id = R.drawable.vk_logo),
            contentDescription = null,
        )

        Spacer(Modifier.size(100.dp))
        RegistrationButton(viewModel)
    }
}

@Composable
private fun RegistrationButton(viewModel: VkViewModel){
    OneTap(
        onAuth = { oAuth, token ->
            Log.d("LoginScreen", "token: ${token.token}")
            viewModel.saveToken(token)
        },
        onFail = { oAuth, fail ->
            Log.d("LoginScreen", "token: ${fail.description}")
        },
        authParams = VKIDAuthUiParams {
            scopes = setOf("wall", "email", "phone_number", "friends")
        },
        scenario = OneTapTitleScenario.SignIn,
        signInAnotherAccountButtonEnabled = true,
    )
}