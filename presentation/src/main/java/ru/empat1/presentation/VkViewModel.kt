package ru.empat1.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vk.api.sdk.VK
import com.vk.dto.common.id.UserId
import com.vk.id.AccessToken
import ru.empat1.presentation.ui.fragment.auth.AuthState

class VkViewModel : ViewModel() {

    private val _authState: MutableLiveData<AuthState> = MutableLiveData(AuthState.Initial)
    val authState: LiveData<AuthState>
        get() = _authState

    init {
        updateAuthState()
    }

    fun saveToken(token: AccessToken) {
        VK.saveAccessToken(
            userId = UserId(token.userID),
            accessToken = token.token,
            expiresInSec = token.expireTime.toInt(),
            createdMs = 0L,
            secret = null
        )
        updateAuthState()
    }

    private fun updateAuthState() {
        _authState.value = if (VK.isLoggedIn()) AuthState.Authorized else AuthState.NotAuthorized
    }

}