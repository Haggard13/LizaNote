package ru.eh13.processor.core.viewmodel

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.symbol.ClassKind
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSType
import com.google.devtools.ksp.symbol.KSValueParameter
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.ksp.toTypeName
import com.squareup.kotlinpoet.ksp.writeTo

class ViewModelEventGenerator(private val codeGenerator: CodeGenerator) {

    fun generateCode(viewModelSymbol: KSClassDeclaration, eventsSymbols: Sequence<KSType>) {
        val fileBuilder = createFile(viewModelSymbol) ?: return

        eventsSymbols.map { (it.declaration as KSClassDeclaration).getSealedSubclasses() }
            .flatMap { it }
            .forEach { declaration ->
                declaration.qualifiedName?.asString()?.let { fileBuilder.addImport(packageName = "", it) }
                fileBuilder.addFunction(buildFunction(viewModelSymbol, declaration))
            }

        writeFile(viewModelSymbol, fileBuilder.build())
    }

    private fun createFile(viewModelSymbol: KSClassDeclaration): FileSpec.Builder? {
        val qualifiedName = viewModelSymbol.qualifiedName?.getQualifier() ?: return null
        val simpleName = viewModelSymbol.simpleName.asString()

        return FileSpec.builder(
            packageName = qualifiedName,
            fileName = "$simpleName$GENERATED_FILE_SUFFIX"
        ).indent("    ")
    }

    private fun buildFunction(viewModelSymbol: KSClassDeclaration, event: KSClassDeclaration): FunSpec {
        val viewModelType = viewModelSymbol.asType(emptyList()).toTypeName()

        val parameters = event.primaryConstructor?.parameters?.mapNotNull {
            val name = it.name?.getShortName() ?: return@mapNotNull null
            val type = it.type.toTypeName()
            ParameterSpec.builder(name, type).build()
        }.orEmpty()

        return FunSpec.builder("on${event.simpleName.getShortName()}")
            .receiver(viewModelType)
            .addParameters(parameters)
            .clearBody()
            .addCode(
                "sendEvent(${event.simpleName.getShortName()}${
                    getEventArguments(
                        arguments = event.primaryConstructor?.parameters.orEmpty(),
                        isObject = event.classKind == ClassKind.OBJECT
                    )
                })"
            )
            .build()
    }

    private fun getEventArguments(arguments: List<KSValueParameter>, isObject: Boolean): String {
        if (isObject) return ""

        return buildString {
            append("(")
            arguments.forEach { append("${it.name?.getShortName()} = ${it.name?.getShortName()}, ") }
        }.removeSuffix(", ") + ")"
    }

    private fun writeFile(viewModelSymbol: KSClassDeclaration, fileSpec: FileSpec) {
        val viewModelFile = viewModelSymbol.containingFile ?: return
        val dependencies = Dependencies(aggregating = false, viewModelFile)
        fileSpec.writeTo(codeGenerator, dependencies)
    }

    private companion object {
        private const val GENERATED_FILE_SUFFIX = "_EventExtensions"
    }
}