package dev.robert.authentication.domain.repository

import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.AuthCredential
import dev.robert.authentication.data.model.SignInResult
import dev.robert.authentication.data.model.UserData
import dev.robert.authentication.domain.model.SignInResultModel
import dev.robert.authentication.domain.model.UserDataModel

interface SignInRepository {
    suspend fun signInWithGoogle(credential: AuthCredential): SignInResultModel
    fun getGoogleSignInOptions(): GoogleSignInOptions

    fun signInWithGoogleIntent(): Intent

    fun signOut()

    fun getSignedInUser(): UserDataModel?

}