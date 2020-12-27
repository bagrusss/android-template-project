package ru.bagrusss.arch.mvi.plugin.generator

import ru.bagrusss.arch.mvi.plugin.utils.camelToSnakeCase

const val EMPTY_INPUT = "IOData.EmptyInput"
const val EMPTY_OUTPUT = "IOData.EmptyOutput"
const val IMPORT_BINDS_INSTANCE = "import dagger.BindsInstance"
const val IMPORT_PARCELIZE = "import kotlinx.android.parcel.Parcelize"
const val IMPORT_IODATA = "import com.revolut.business.architecture.common.IOData"
const val OUTPUT_CLASS_NAME = "OutputData"
const val INPUT_DATA_SETTER = ".inputData(inputData)"
const val INPUT_DATA_ARGUMENT = "inputData"

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
const val inputDataParameter = "(inputData: $inputClassName)"

const val inputClassParameter = """,
    private val inputData: $inputClassName"""

const val routerClassParameter = """,
    private val router: Router"""

fun createInputImport(prefix: String) = "import ${prefix}Contract.InputData"

fun createOutputImport(prefix: String) = "import ${prefix}Contract.OutputData"

const val bindsInstance = """ {
        @BindsInstance
        fun inputData(inputData: InputData): Builder
    }"""

const val bindNavController = """
            @BindsInstance
        fun navController(navController: NavController): Builder"""

fun createLayoutId(name: String) = "R.layout.${name.decapitalize().camelToSnakeCase()}"

fun createRouter(name: String) = """
    class ${name}Router : NavRouter {
    
    }"""

fun createRouterComponentsInModule(name: String) = """
    @Binds
    @FragmentScope
    abstract fun bind${name}Router(router: ${name}Router): Router

    @Binds
    @FragmentScope
    abstract fun bindRouter(${name.decapitalize()}Router: Router): NavRouter
"""