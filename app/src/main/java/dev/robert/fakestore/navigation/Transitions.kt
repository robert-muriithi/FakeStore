package dev.robert.fakestore.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween

private const val AnimDurationLong = 500
private const val AnimDurationShort = 300

@ExperimentalAnimationApi
fun scaleInEnterTransition() = scaleIn(
    initialScale = .9f,
    animationSpec = tween(AnimDurationLong)
) + fadeIn(
    animationSpec = tween(AnimDurationShort)
)

@ExperimentalAnimationApi
fun scaleOutExitTransition() = scaleOut(
    targetScale = 1.1f,
    animationSpec = tween(AnimDurationShort)
) + fadeOut(
    animationSpec = tween(AnimDurationShort)
)

@ExperimentalAnimationApi
fun scaleInPopEnterTransition() = scaleIn(
    initialScale = 1.1f,
    animationSpec = tween(AnimDurationLong)
) + fadeIn(
    animationSpec = tween(AnimDurationShort)
)

@ExperimentalAnimationApi
fun scaleOutPopExitTransition() = scaleOut(
    targetScale = .9f,
    animationSpec = tween(AnimDurationShort)
) + fadeOut(
    animationSpec = tween(AnimDurationShort)
)