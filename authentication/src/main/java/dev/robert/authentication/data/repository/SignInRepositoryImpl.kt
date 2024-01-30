package dev.robert.authentication.data.repository

import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import dev.robert.authentication.R
import dev.robert.authentication.data.mappers.toDomainModel
import dev.robert.authentication.data.model.SignInResult
import dev.robert.authentication.data.model.UserData
import dev.robert.authentication.domain.model.SignInResultModel
import dev.robert.authentication.domain.model.UserDataModel
import dev.robert.authentication.domain.repository.SignInRepository
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.cancellation.CancellationException

class SignInRepositoryImpl(
    private val context: Context,
    private val auth: FirebaseAuth,
) : SignInRepository {

    override suspend fun signInWithGoogle(credential: AuthCredential): SignInResultModel {
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
            ).toDomainModel()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            SignInResult(
                data = null,
                errorMessage = e.message
            ).toDomainModel()
        }
    }

    override fun getGoogleSignInOptions(): GoogleSignInOptions {
        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestIdToken("")
            .requestEmail()
            .build()
    }

    override fun signInWithGoogleIntent(): Intent {
        return GoogleSignIn.getClient(context, getGoogleSignInOptions()).signInIntent
    }

    override fun signOut() {
        auth.signOut()
        val signInClient = GoogleSignIn.getClient(context, getGoogleSignInOptions())
        signInClient.signOut()
    }

    override fun getSignedInUser(): UserDataModel? {
        val user = auth.currentUser
        return user?.let {
            UserData(
                userId = it.uid,
                username = it.displayName,
                profilePictureUrl = it.photoUrl?.toString(),
                email = it.email!!
            ).toDomainModel()
        }
    }
}