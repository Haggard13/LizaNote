package ru.eh13.core.viewmodel.annotations

import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
@Suppress("konsist.viewmodel with suffix")
annotation class ViewModel(val events: KClass<*>)
