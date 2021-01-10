package ru.bagrusss.arch.rx_mvi.plugin.actions

import ru.bagrusss.arch.rx_mvi.plugin.generator.CodeGenSettings

class CreateFragmentAction : CreateAction(
    folder = "fragment",
    suffix = "Fragment",
    codeGensCallBack = { result ->
        listOfNotNull(
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
            ).takeIf { result.withRouter },
            CodeGenSettings(
                templateName = "FragmentComponent",
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
    }
)