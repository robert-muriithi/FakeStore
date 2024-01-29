package dev.robert.authentication.domain.usecase

import com.google.firebase.auth.AuthCredential
import dev.robert.authentication.domain.repository.SignInRepository

class SignInWithGoogleUseCase(private val repository: SignInRepository) {
    suspend operator fun invoke(credential: AuthCredential) = repository.signInWithGoogle(credential)
}