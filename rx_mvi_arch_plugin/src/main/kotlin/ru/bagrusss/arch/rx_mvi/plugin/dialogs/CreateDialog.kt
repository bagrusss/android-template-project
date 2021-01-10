package ru.bagrusss.arch.rx_mvi.plugin.dialogs

import com.intellij.openapi.ui.DialogWrapper
import javax.swing.*

class CreateDialog(
    titleSuffix: String,
    private val onOkClicked: (DialogResult) -> Unit
) : DialogWrapper(null) {

    private lateinit var contentPane: JPanel
    private lateinit var nameField: JTextField
    private lateinit var labelField: JLabel
    private lateinit var addOutputDataCheckBox: JCheckBox
    private lateinit var addInputDataCheckBox: JCheckBox
    private lateinit var addLayoutCheckBox: JCheckBox
    private lateinit var addPackageCheckBox: JCheckBox
    private lateinit var addRouterCheckBox: JCheckBox

    init {
        super.init()
        title = "Create $titleSuffix"
        labelField.text = "Enter name"
    }

    override fun createCenterPanel(): JComponent = contentPane

    override fun getPreferredFocusedComponent(): JComponent = nameField

    override fun processDoNotAskOnOk(exitCode: Int) {
        super.processDoNotAskOnOk(exitCode)
        onOkClicked(
            DialogResult(
                name = nameField.text,
                withOutput = addOutputDataCheckBox.isSelected,
                withInput = addInputDataCheckBox.isSelected,
                withLayout = addLayoutCheckBox.isSelected,
                withPackage = addPackageCheckBox.isSelected,
                withRouter = addRouterCheckBox.isSelected
            )
        )
    }

    data class DialogResult(
        @JvmField val name: String,
        @JvmField val withOutput: Boolean,
        @JvmField val withInput: Boolean,
        @JvmField val withLayout: Boolean,
        @JvmField val withPackage: Boolean,
        @JvmField val withRouter: Boolean,
    )
}