package dev.robert.authentication.domain.model

data class UserDataModel (
    val email: String,
    val username : String?,
    val profilePictureUrl : String?,
    val userId : String
)