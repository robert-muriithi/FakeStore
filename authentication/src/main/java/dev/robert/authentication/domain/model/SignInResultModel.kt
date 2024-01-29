package dev.robert.authentication.domain.model


data class SignInResultModel(
    val data: UserDataModel?,
    val errorMessage: String?
)
