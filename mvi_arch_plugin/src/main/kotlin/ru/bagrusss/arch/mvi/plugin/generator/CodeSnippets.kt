package ru.bagrusss.arch.mvi.plugin.generator

import ru.bagrusss.arch.mvi.plugin.utils.camelToSnakeCase

const val EMPTY_INPUT = "IOData.EmptyInput"
const val EMPTY_OUTPUT = "IOData.EmptyOutput"
const val IMPORT_BINDS_INSTANCE = "import dagger.BindsInstance"
const val IMPORT_NAV_CONTROLLER = "import androidx.navigation.NavController"
const val IMPORT_PARCELIZE = "import kotlinx.parcelize.Parcelize"
const val IMPORT_IODATA = "import ru.bagrusss.architecture.mvi.common.IOData"
const val OUTPUT_CLASS_NAME = "OutputData"
const val INPUT_DATA_SETTER = """
                    .inputData(inputData)"""
const val INPUT_DATA_ARGUMENT = "inputData"
const val NAV_CONTROLLER_SETTER = """
                    .navController(findNavController())"""

const val DEFAULT_INPUT_DATA = """
    @Parcelize
    data class InputData(
        val value: Int
    ) : IOData.Input"""

const val DEFAULT_OUTPUT_DATA = """
    data class OutputData(
        val value: Int
    ) : IOData.Output"""

const val outputClassName = "OutputData"
const val inputClassName = "InputData"

const val inputClassParameter = """,
    private val inputData: $inputClassName"""

const val routerClassParameter = """,
    private val router: Router"""

fun createRouterImportsInModule(packageName: String, name: String) =
    """import ru.bagrusss.architecture.mvi.navigation.NavRouter
import $packageName.${name}Router"""

fun createInputImport(name: String) = "import ${name}Contract.InputData"

fun createOutputImport(name: String) = "import ${name}Contract.OutputData"

const val bindsInstance = """
        @BindsInstance
        fun inputData(inputData: InputData): Builder"""

const val bindNavController = """
        @BindsInstance
        fun navController(navController: NavController): Builder"""

fun createLayoutId(name: String, suffix: String) = "R.layout.${suffix.decapitalize()}_${name.decapitalize().camelToSnakeCase()}"

fun createRouter() = """
    interface Router : NavRouter {
    
    }"""

fun createRouterComponentsInModule(name: String) = """

    @Binds
    @FragmentScope
    abstract fun bind${name}Router(router: ${name}Router): Router

    @Binds
    @FragmentScope
    abstract fun bindRouter(${name.decapitalize()}Router: Router): NavRouter

"""