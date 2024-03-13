package ru.eh13.lizanote

import android.app.Application
import ru.eh13.lizanote.di.AppComponent

class LizaNoteApplication : Application() {
    private val appComponent = AppComponent(this)
}