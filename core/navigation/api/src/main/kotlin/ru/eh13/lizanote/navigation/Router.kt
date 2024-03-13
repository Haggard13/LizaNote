package ru.eh13.lizanote.navigation

import java.io.Serializable

interface Router {
    fun navigateToFlow(flow: Flow)
    fun navigateToFlow(flow: Flow, args: Serializable?)
    fun navigateTo(route: String)
    fun navigateTo(route: String, args: Serializable?)
    fun back()
}