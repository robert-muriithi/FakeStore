package dev.robert.authentication.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.robert.authentication.data.model.SignInResult
import dev.robert.authentication.domain.model.SignInResultModel
import dev.robert.authentication.domain.usecase.GetGoogleSignInOptionsUseCase
import dev.robert.authentication.domain.usecase.SignInWithGoogleIntent
import dev.robert.authentication.domain.usecase.SignInWithGoogleUseCase
import dev.robert.authentication.domain.usecase.SignOutUseCase
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInWithGoogleIntent: SignInWithGoogleIntent,
    private val signOutUseCase: SignOutUseCase,
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    private val getGoogleSignInOptionsUseCase: GetGoogleSignInOptionsUseCase
) : ViewModel() {

    private val _loadingState = mutableStateOf(false)
    val loadingState: State<Boolean> = _loadingState

    private val _googleSignInOptionsState = mutableStateOf(getGoogleSignInOptionsUseCase())
    val googleSignInOptionsState: State<GoogleSignInOptions> = _googleSignInOptionsState

    private val _signInResultState = mutableStateOf<SignInResultModel?>(null)
    val signInResultState: State<SignInResultModel?> = _signInResultState

    /*fun signInWithIntent()  {
        _loadingState.value = true
        _signInResultState.value = null
        _googleSignInOptionsState.value.let {
            _signInResultState.value = signInWithGoogleIntent(it)
        }
        _loadingState.value = false
    }

    fun signInWithGoogle(idToken: String) {
        _loadingState.value = true
        _signInResultState.value = null
        _signInResultState.value = signInWithGoogleUseCase(idToken)
        _loadingState.value = false
    }

    fun signOut() {
        _loadingState.value = true
        signOutUseCase()
        _loadingState.value = false
    }*/


}