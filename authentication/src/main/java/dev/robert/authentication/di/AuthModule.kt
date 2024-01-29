package dev.robert.authentication.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.robert.authentication.data.repository.SignInRepositoryImpl
import dev.robert.authentication.domain.repository.SignInRepository
import dev.robert.authentication.domain.usecase.GetGoogleSignInOptionsUseCase
import dev.robert.authentication.domain.usecase.SignInWithGoogleUseCase
import dev.robert.authentication.domain.usecase.SignOutUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideSignInRepository(
        firebaseAuth: FirebaseAuth,
        @ApplicationContext context: Context
    ): SignInRepository {
        return SignInRepositoryImpl(
            context,
            firebaseAuth
        )
    }
    @Provides
    @Singleton
    fun provideGetGoogleSignInOptionsUseCase(
        repository: SignInRepository
    ): GetGoogleSignInOptionsUseCase {
        return GetGoogleSignInOptionsUseCase(repository)
    }
    @Provides
    @Singleton
    fun provideSignInWithGoogleUseCase(
        repository: SignInRepository
    ): SignInWithGoogleUseCase {
        return SignInWithGoogleUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSignInWithGoogleIntentUseCase(
        repository: SignInRepository
    ): SignInWithGoogleUseCase {
        return SignInWithGoogleUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSignOutUseCase(
        repository: SignInRepository
    ): SignOutUseCase {
        return SignOutUseCase(repository)
    }
}