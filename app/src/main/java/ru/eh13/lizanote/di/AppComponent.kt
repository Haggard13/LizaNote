package ru.eh13.lizanote.di

import android.content.Context

class AppComponent(context: Context) : MainActivityComponent.Deps,
    ContextModule by ContextModuleImpl(context) {
    init {
        MainActivityComponent.deps = this
    }
}