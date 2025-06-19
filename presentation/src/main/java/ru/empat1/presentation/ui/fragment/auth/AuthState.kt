package ru.empat1.presentation.ui.fragment.auth

sealed class AuthState{
    object Authorized: AuthState()

    object NotAuthorized: AuthState()

    object Initial: AuthState()
}