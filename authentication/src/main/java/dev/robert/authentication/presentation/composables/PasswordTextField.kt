package dev.robert.authentication.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.robert.authentication.R
import dev.robert.fakestore.ui.theme.md_theme_dark_onError
import dev.robert.fakestore.ui.theme.md_theme_light_primary
import dev.robert.fakestore.ui.theme.md_theme_light_tertiary

private fun String.Companion.empty(): String {
    return ""
}

fun strengthChecker(text: String) = when {
    text.length < 8 -> StrengthPasswordTypes.WEAK
    text.length < 12 -> StrengthPasswordTypes.STRONG
    else -> StrengthPasswordTypes.STRONG
}

enum class StrengthPasswordTypes {
    STRONG,
    WEAK
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    text: String,
    modifier: Modifier = Modifier,
    semanticContentDescription: String = "",
    labelText: String = "",
    validateStrengthPassword: Boolean = false,
    hasError: Boolean = false,
    onHasStrongPassword: (isStrong: Boolean) -> Unit = {},
    onTextChanged: (text: String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val showPassword = remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier
                .background(color = Color.White)
                .fillMaxWidth()
                .semantics { contentDescription = semanticContentDescription },
            value = text,
            onValueChange = onTextChanged,
            placeholder = {
                val multiFontFamily = FontFamily(
                    fonts = listOf(
                        Font(
                            resId = R.font.muli_italic,
                            weight = FontWeight.Light
                        ),
                        Font(
                            resId = R.font.muli_variable,
                            weight = FontWeight.Normal
                        ),
                        Font(
                            resId = R.font.muli_variable,
                            weight = FontWeight.Bold
                        )
                    )
                )
                Text(
                    text = labelText,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontFamily = multiFontFamily
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                autoCorrect = true,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            singleLine = true,
            isError = hasError,
            visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val (icon, iconColor) = if (showPassword.value) {
                    Pair(
                        Icons.Filled.Visibility,
                        md_theme_light_tertiary
                    )
                } else {
                    Pair(Icons.Filled.VisibilityOff, md_theme_light_primary)
                }

                IconButton(onClick = { showPassword.value = !showPassword.value }) {
                    Icon(
                        icon,
                        contentDescription = "Visibility",
                        tint = iconColor
                    )
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                cursorColor = Color.White,
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        if (validateStrengthPassword && text != String.empty()) {
            val strengthPasswordType = strengthChecker(text)
            if (strengthPasswordType == StrengthPasswordTypes.STRONG) {
                onHasStrongPassword(true)
            } else {
                onHasStrongPassword(false)
            }
            Text(
                modifier = Modifier.semantics { contentDescription = "StrengthPasswordMessage" },
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.White,
                            fontSize = 10.sp,
                            fontFamily = FontFamily.Default
                        )
                    ) {
                        append(stringResource(id = R.string.warning_password_level))
                        withStyle(style = SpanStyle(color = md_theme_dark_onError)) {
                            when (strengthPasswordType) {
                                StrengthPasswordTypes.STRONG ->
                                    append(" ${stringResource(id = R.string.warning_password_level_strong)}")
                                StrengthPasswordTypes.WEAK ->
                                    append(" ${stringResource(id = R.string.warning_password_level_weak)}")
                            }
                        }
                    }
                }
            )
        }
    }
}