package ru.eh13.lizanote.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.google.gson.Gson
import java.io.Serializable

@JvmName("navigation1")
inline fun <reified T : Serializable> NavGraphBuilder.navigation(
    route: Flow,
    startDestination: String,
    noinline builder: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = startDestination,
        route = route.nameWithArgs,
        arguments = listOf(
            navArgument(Flow.FLOW_ARGS_NAME) {
                defaultValue = null
                nullable = true
                type = NavType.StringType
            }
        ),
        builder = builder
    )
}

fun NavGraphBuilder.navigation(
    route: Flow,
    startDestination: String,
    builder: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = startDestination,
        route = route.name,
        builder = builder
    )
}

inline fun <reified T : Serializable> NavGraphBuilder.composableWithArgs(
    route: String,
    crossinline content: @Composable (T?) -> Unit
) {
    composable(
        route = route,
        arguments = listOf(navArgument(Flow.SCREEN_ARGS_NAME) {
            type = NavType.StringType
            defaultValue = null
            nullable = true
        }),
        content = { content(it.getScreenArgs<T>()) },
    )
}

inline fun <reified T : Serializable> NavGraphBuilder.composableWithFlowArgs(
    route: String,
    crossinline content: @Composable (T?) -> Unit
) {
    composable(
        route = route,
        arguments = listOf(navArgument(Flow.SCREEN_ARGS_NAME) {
            type = NavType.StringType
            defaultValue = null
            nullable = true
        }),
        content = { content(it.getFlowArgs<T>()) },
    )
}

inline fun <reified T : Serializable> NavBackStackEntry.getArgs(name: String) =
    arguments?.getString(name)?.let { Gson().fromJson(it, T::class.java) }

inline fun <reified T : Serializable> NavBackStackEntry.getFlowArgs() = getArgs<T>(Flow.FLOW_ARGS_NAME)
inline fun <reified T : Serializable> NavBackStackEntry.getScreenArgs() = getArgs<T>(Flow.SCREEN_ARGS_NAME)