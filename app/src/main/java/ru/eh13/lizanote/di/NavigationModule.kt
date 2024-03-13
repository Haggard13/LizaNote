package ru.eh13.lizanote.di

import androidx.navigation.NavController
import ru.eh13.lizanote.navigation.Router
import ru.eh13.lizanote.navigation.RouterImpl

interface NavigationModule {
    val router: Router
}

class NavigationModuleImpl : NavigationModule {
    override val router: Router = RouterImpl()
}
