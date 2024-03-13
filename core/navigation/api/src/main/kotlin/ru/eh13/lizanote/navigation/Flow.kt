package ru.eh13.lizanote.navigation

interface Flow {
    val name: String get() = "${this::class.simpleName}_flow"
    val nameWithArgs: String get() = "$name/{${FLOW_ARGS_NAME}}"

    companion object {
        const val FLOW_ARGS_NAME = "flowArgs"
        const val START_FLOW_DESTINATION_ROUTE = "flowStarter"
        const val SCREEN_ARGS_NAME = "args"
    }
}