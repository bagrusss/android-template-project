package ru.bagrusss.arch.rx_mvi.plugin.generator

import org.apache.commons.io.IOUtils
import org.apache.commons.lang.text.StrSubstitutor
import java.io.IOException
import java.nio.charset.StandardCharsets
import javax.swing.JFrame
import javax.swing.JOptionPane

class CodeGen(
    folder: String,
    private val suffix: String,
    private val name: String,
    private val packageName: String,
    private val templateName: String,
    val diSubdirectory: Boolean
) {
    private lateinit var templateString: String
    private val templateValuesMap = mutableMapOf<String, String?>()

    init {
        templateValuesMap[KEY_PACKAGE_NAME] = packageName
        templateValuesMap[KEY_ARCH_PACKAGE_NAME] = ARCH_PACKAGE
        templateValuesMap[KEY_COMMON_PACKAGE_NAME] = COMMON_PACKAGE
        templateValuesMap[KEY_MVI_COMMON_PACKAGE_NAME] = MVI_COMMON_PACKAGE
        templateValuesMap[KEY_RX_PACKAGE] = RX_PACKAGE
        templateValuesMap[KEY_NAME] = name
        templateValuesMap[KEY_INJECTOR_PROP_PREFIX] = name.decapitalize()

        val resource = "/templates/$folder/$templateName.kt.template"
        try {
            val resourceAsStream = javaClass.getResourceAsStream(resource)
            templateString = IOUtils.toString(resourceAsStream, StandardCharsets.UTF_8.name())
        } catch (e: IOException) {
            JOptionPane.showMessageDialog(JFrame(), e.message)
            e.printStackTrace()
        }
    }

    fun getClassName() = name + templateName

    fun generate(withInputData: Boolean, withOutputData: Boolean, withLayout: Boolean, withRouter: Boolean): String {
        templateValuesMap[KEY_INPUT_DATA] = if (withInputData) DEFAULT_INPUT_DATA else null
        templateValuesMap[KEY_OUTPUT_DATA] = if (withOutputData) DEFAULT_OUTPUT_DATA else null
        templateValuesMap[KEY_INPUT_DATA_REF] = if (withInputData) inputClassName else EMPTY_INPUT
        templateValuesMap[KEY_INPUT_DATA_BINDS_INSTANCE] = if (withInputData) bindsInstance else null
        templateValuesMap[KEY_INPUT_DATA_SETTER] = if (withInputData) INPUT_DATA_SETTER else null
        templateValuesMap[KEY_INPUT_DATA_PARAMETER_MODEL] = if (withInputData) inputClassParameter else ""
        templateValuesMap[KEY_INPUT_DATA_IMPORT] = if (withInputData) createInputImport("$packageName.$name") else null
        templateValuesMap[KEY_OUTPUT_DATA_REF] = if (withOutputData) outputClassName else EMPTY_OUTPUT
        templateValuesMap[KEY_OUTPUT_DATA_CLASS_NAME] = if (withOutputData) OUTPUT_CLASS_NAME else EMPTY_OUTPUT
        templateValuesMap[KEY_OUTPUT_DATA_IMPORT] = if (withOutputData) createOutputImport("$packageName.$name") else null
        templateValuesMap[KEY_IMPORT_BINDS_INSTANCE] = if (withInputData || withRouter) IMPORT_BINDS_INSTANCE else null
        templateValuesMap[KEY_IMPORT_PARCELIZE] = if (withInputData) IMPORT_PARCELIZE else null
        templateValuesMap[KEY_IMPORT_IODATA] = if (!withInputData || !withOutputData) IMPORT_IODATA else null
        templateValuesMap[KEY_IMPORT_IODATA_MODEL] = if (!withOutputData) IMPORT_IODATA else null
        templateValuesMap[KEY_INPUT_DATA_ARGUMENT] = if (withInputData) INPUT_DATA_ARGUMENT else EMPTY_INPUT

        templateValuesMap[KEY_LAYOUT_ID] = if (withLayout) createLayoutId(name, suffix) else ""

        templateValuesMap[KEY_ROUTER] = if (withRouter) router else null
        templateValuesMap[KEY_NAV_CONTROLLER_BINDS_INSTANCE] = if (withRouter) bindNavController else null
        templateValuesMap[KEY_ROUTER_COMPONENTS_IN_MODULE] = if (withRouter) createRouterComponentsInModule(name) else null
        templateValuesMap[KEY_ROUTER_PARAMETER_MODEL] = if (withRouter) routerClassParameter else null
        templateValuesMap[KEY_ROUTER_IMPORTS_IN_MODULE] = if (withRouter) createRouterImportsInModule(packageName, name) else null
        templateValuesMap[KEY_NAV_CONTROLLER_SETTER] = if (withRouter) NAV_CONTROLLER_SETTER else null
        templateValuesMap[KEY_IMPORT_NAV_CONTROLLER] = if (withRouter) IMPORT_NAV_CONTROLLER else null

        return StrSubstitutor(templateValuesMap)
            .replace(templateString)
            .removeLinesWithPlaceholder()
    }

    private fun String.removeLinesWithPlaceholder() = this.replace(
        regex = Regex("(?m)^.*\\$\\{.*}.*\n"),
        replacement = ""
    )

    companion object {
        const val KEY_PACKAGE_NAME = "package_name"
        const val KEY_ARCH_PACKAGE_NAME = "arch_package_name"
        const val KEY_COMMON_PACKAGE_NAME = "common_package_name"
        const val KEY_MVI_COMMON_PACKAGE_NAME = "mvi_common_package_name"
        const val KEY_NAME = "name"
        const val KEY_INJECTOR_PROP_PREFIX = "injector_prop_prefix"
        const val KEY_INPUT_DATA = "input_data"
        const val KEY_OUTPUT_DATA = "output_data"
        const val KEY_INPUT_DATA_REF = "input_data_ref"
        const val KEY_INPUT_DATA_BINDS_INSTANCE = "input_data_binds_instance"
        const val KEY_INPUT_DATA_SETTER = "input_data_setter"
        const val KEY_INPUT_DATA_PARAMETER_MODEL = "input_data_parameter_model"
        const val KEY_INPUT_DATA_IMPORT = "input_data_import"
        const val KEY_INPUT_DATA_ARGUMENT = "input_data_argument"
        const val KEY_OUTPUT_DATA_REF = "output_data_ref"
        const val KEY_OUTPUT_DATA_CLASS_NAME = "output_data_class_name"
        const val KEY_OUTPUT_DATA_IMPORT = "output_data_import"
        const val KEY_IMPORT_BINDS_INSTANCE = "import_binds_instance"
        const val KEY_IMPORT_NAV_CONTROLLER = "import_nav_controller"
        const val KEY_IMPORT_PARCELIZE = "import_parcelize"
        const val KEY_RX_PACKAGE = "rx_package"
        const val KEY_IMPORT_IODATA = "import_iodata"
        const val KEY_IMPORT_IODATA_MODEL = "import_iodata_model"
        const val KEY_LAYOUT_ID = "layout_id"
        const val KEY_ROUTER = "router"
        const val KEY_NAV_CONTROLLER_BINDS_INSTANCE = "nav_controller_binds_instance"
        const val KEY_ROUTER_COMPONENTS_IN_MODULE = "router_components_in_module"
        const val KEY_ROUTER_PARAMETER_MODEL = "router_parameter_model"
        const val KEY_ROUTER_IMPORTS_IN_MODULE = "router_imports_in_module"
        const val KEY_NAV_CONTROLLER_SETTER = "nav_controller_setter"
    }
}