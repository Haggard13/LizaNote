package ru.eh13.processor.core.viewmodel

import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.*
import com.google.devtools.ksp.validate
import ru.eh13.core.viewmodel.annotations.ViewModel

class ViewModelEventProcessor(private val viewModelEventGenerator: ViewModelEventGenerator) : SymbolProcessor {

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val annotatedSymbols = resolver.getSymbolsWithViewModelAnnotation()

        annotatedSymbols.asSequence()
            .filterIsInstance<KSClassDeclaration>()
            .filter { it.classKind == ClassKind.CLASS && it.isCompanionObject.not() }
            .forEach { it.processClass() }

        return emptyList()
    }

    private fun Resolver.getSymbolsWithViewModelAnnotation(): Sequence<KSAnnotated> {
        val annotationName = ViewModel::class.qualifiedName ?: return emptySequence()
        return getSymbolsWithAnnotation(annotationName)
    }

    private fun KSClassDeclaration.processClass() {
        val classViewModelAnnotations = getClassViewModelAnnotations()
        if (classViewModelAnnotations.none()) return
        val eventsSymbols = classViewModelAnnotations.getEvents()
        viewModelEventGenerator.generateCode(this, eventsSymbols)
    }

    private fun KSClassDeclaration.getClassViewModelAnnotations(): Sequence<KSAnnotation> {
        return annotations
            .filter {
                it.annotationType.resolve().declaration.qualifiedName?.asString() == ViewModel::class.qualifiedName
            }
    }

    private fun Sequence<KSAnnotation>.getEvents(): Sequence<KSType> {
        return map { it.arguments.find { it.name?.asString() == "events" }?.value as KSType }
    }
}