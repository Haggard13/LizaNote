package ru.eh13.lizanote.di

import android.content.Context

interface ContextModule {
    val context: Context
}

class ContextModuleImpl(override val context: Context) : ContextModule