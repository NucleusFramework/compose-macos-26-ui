package dev.nucleusframework.macoscompose.gallery.ksp

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider

class GalleryProcessorProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return GalleryProcessor(
            codeGenerator = environment.codeGenerator,
            logger = environment.logger,
        )
    }
}
