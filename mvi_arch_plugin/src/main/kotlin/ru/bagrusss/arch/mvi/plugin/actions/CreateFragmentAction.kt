package ru.bagrusss.arch.mvi.plugin.actions

import ru.bagrusss.arch.mvi.plugin.generator.CodeGenSettings

class CreateFragmentAction : CreateNavigableAction(
    folder = "fragment",
    suffix = "MVI Fragment",
    codeGens = listOf(
        CodeGenSettings(
            templateName = "Fragment",
            diSubdirectory = false
        ),
        CodeGenSettings(
            templateName = "Contract",
            diSubdirectory = false
        ),
        CodeGenSettings(
            templateName = "ViewModel",
            diSubdirectory = false
        ),
        CodeGenSettings(
            templateName = "StateMapper",
            diSubdirectory = false
        ),
        CodeGenSettings(
            templateName = "Router",
            diSubdirectory = false
        ),
        CodeGenSettings(
            templateName = "FragmentComponent.kt.template",
            diSubdirectory = true
        ),
        CodeGenSettings(
            templateName = "FragmentModule",
            diSubdirectory = true
        ),
        CodeGenSettings(
            templateName = "FragmentInjector",
            diSubdirectory = true
        )
    )
)