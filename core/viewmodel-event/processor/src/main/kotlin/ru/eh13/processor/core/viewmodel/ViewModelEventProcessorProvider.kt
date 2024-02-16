package ru.eh13.processor.core.viewmodel

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider

class ViewModelEventProcessorProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return ViewModelEventProcessor(ViewModelEventGenerator(environment.codeGenerator))
    }
}