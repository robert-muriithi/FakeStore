package dev.robert.authentication.data.mappers

import dev.robert.authentication.data.model.SignInResult
import dev.robert.authentication.data.model.UserData
import dev.robert.authentication.domain.model.SignInResultModel
import dev.robert.authentication.domain.model.UserDataModel


fun UserData.toDomainModel() = UserDataModel(
    email = email,
    username = username,
    profilePictureUrl = profilePictureUrl,
    userId = userId
)

fun UserDataModel.toDataModel() = UserData(
    email = email,
    username = username,
    profilePictureUrl = profilePictureUrl,
    userId = userId
)

fun SignInResultModel.toDataModel() = SignInResult(
    data = data?.toDataModel(),
    errorMessage = errorMessage
)

fun SignInResult.toDomainModel() = SignInResultModel(
    data = data?.toDomainModel(),
    errorMessage = errorMessage
)