package dev.robert.authentication.data.helper

import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import dev.robert.authentication.R
import dev.robert.authentication.data.model.SignInResult
import dev.robert.authentication.data.model.UserData
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.cancellation.CancellationException

/*
class GoogleSignInClient(
    private val context: Context
) {
    private val auth = FirebaseAuth.getInstance()

    private fun getGoogleSignInOptions(): GoogleSignInOptions {
        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
    }

    fun signInWithGoogleIntent(): Intent {
        val signInClient = GoogleSignIn.getClient(context, getGoogleSignInOptions())
        return signInClient.signInIntent
    }

    fun signOut() {
        auth.signOut()
        val signInClient = GoogleSignIn.getClient(context, getGoogleSignInOptions())
        signInClient.signOut()
    }

    fun getSignedInUser(): UserData? {
        val user = auth.currentUser
        return user?.let {
            UserData(
                userId = it.uid,
                username = it.displayName,
                profilePictureUrl = it.photoUrl?.toString(),
                email = it.email!!
            )
        }
    }
    suspend fun signInWithCredential(credential: AuthCredential): SignInResult {
        return try {
            val authResult = auth.signInWithCredential(credential).await()
            val user = authResult.user
            SignInResult(
                data = user?.let {
                    UserData(
                        userId = it.uid,
                        username = it.displayName,
                        profilePictureUrl = it.photoUrl?.toString(),
                        email = it.email!!
                    )
                },
                errorMessage = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            SignInResult(
                data = null,
                errorMessage = e.message
            )
        }
    }
}*/
