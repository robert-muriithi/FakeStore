package dev.robert.authentication.data.model

data class SignInResult (
    val data: UserData?,
    val errorMessage: String?
)