package dev.robert.authentication.domain.usecase

import dev.robert.authentication.domain.repository.SignInRepository

class SignInWithGoogleIntent(private val repository: SignInRepository) {
    operator fun invoke() = repository.signInWithGoogleIntent()
}