package dev.robert.authentication.domain.usecase

import dev.robert.authentication.domain.repository.SignInRepository

class SignOutUseCase(private val repository: SignInRepository) {
    operator fun invoke() = repository.signOut()
}