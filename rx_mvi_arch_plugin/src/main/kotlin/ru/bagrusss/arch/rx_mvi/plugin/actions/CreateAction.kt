package ru.bagrusss.arch.rx_mvi.plugin.actions

import com.intellij.lang.java.JavaLanguage
import com.intellij.lang.xml.XMLLanguage
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.command.CommandProcessor
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.psi.*
import org.apache.commons.io.IOUtils
import org.jetbrains.jps.model.java.JavaModuleSourceRootTypes
import ru.bagrusss.arch.rx_mvi.plugin.dialogs.CreateDialog
import ru.bagrusss.arch.rx_mvi.plugin.generator.CodeGenSettings
import ru.bagrusss.arch.rx_mvi.plugin.generator.CodeGen
import ru.bagrusss.arch.rx_mvi.plugin.utils.camelToSnakeCase
import java.io.File
import java.io.IOException
import java.nio.charset.StandardCharsets
import javax.swing.JOptionPane

open class CreateAction(
    private val folder: String,
    private val suffix: String,
    private val codeGensCallBack: (CreateDialog.DialogResult) -> List<CodeGenSettings>
) : AnAction() {

    private val commandProcessor = CommandProcessor.getInstance()
    private val applicationManager = ApplicationManager.getApplication()
    private lateinit var dataContext: DataContext

    override fun actionPerformed(e: AnActionEvent) {
        CreateDialog(suffix) { result ->
            applicationManager.runWriteAction {
                val codeGens = codeGensCallBack(result)
                try {
                    generate(result, codeGens)
                } catch (ex: Exception) {
                    showError(ex.message.orEmpty())
                }
            }
        }.show()
    }

    override fun update(e: AnActionEvent) {
        dataContext = e.dataContext

        val enabled = isAvailable(dataContext)

        e.presentation.isVisible = enabled
        e.presentation.isEnabled = enabled
    }

    private fun isAvailable(dataContext: DataContext): Boolean {
        val project = CommonDataKeys.PROJECT.getData(dataContext) ?: return false
        val view = LangDataKeys.IDE_VIEW.getData(dataContext) ?: return false

        val projectFileIndex = ProjectRootManager.getInstance(project).fileIndex
        for (dir in view.directories) {
            if (projectFileIndex.isUnderSourceRootOfType(
                    dir.virtualFile,
                    JavaModuleSourceRootTypes.SOURCES
                ) && checkPackageExists(dir)
            ) {
                return true
            }
        }

        return false
    }

    private fun checkPackageExists(directory: PsiDirectory): Boolean {
        val pkg = JavaDirectoryService.getInstance().getPackage(directory) ?: return false
        val name = pkg.qualifiedName
        return name.isEmpty() || PsiNameHelper.getInstance(directory.project).isQualifiedName(name)
    }

    private fun generate(result: CreateDialog.DialogResult, codeGens: List<CodeGenSettings>) {
        val name = result.name
        val snakeCaseName = name.camelToSnakeCase()

        val project = CommonDataKeys.PROJECT.getData(dataContext)
            ?: throw IllegalStateException("Project is not initialized")

        val view = LangDataKeys.IDE_VIEW.getData(dataContext)
            ?: throw IllegalStateException("IdeView is not initialized")

        val parentDirectory = if (result.withPackage) {
            view.orChooseDirectory?.createSubdirectory(snakeCaseName)
        } else {
            view.orChooseDirectory
        } ?: throw IllegalStateException("ParentDirectory is not initialized")

        val diSubdirectory = parentDirectory.createSubdirectory(DI_SUBDIRECTORY_NAME)

        val psiPackage = JavaDirectoryService.getInstance().getPackage(parentDirectory)
            ?: throw IllegalStateException("psiPackage is not initialized")

        val generators = codeGens.map { settings ->
            CodeGen(
                folder = folder,
                suffix = suffix,
                name = name.removeSuffix(suffix).capitalize(),
                packageName = psiPackage.qualifiedName,
                templateName = settings.templateName,
                diSubdirectory = settings.diSubdirectory
            )
        }

        commandProcessor.executeCommand(null, {
            generators.forEach { generator ->
                createSourceFile(
                    project = project,
                    codeGen = generator,
                    directory = when {
                        generator.diSubdirectory -> diSubdirectory
                        else -> parentDirectory
                    },
                    withInputData = result.withInput,
                    withOutputData = result.withOutput,
                    withLayout = result.withLayout,
                    withRouter = result.withRouter
                )
            }

            if (result.withLayout) {
                val projectFileIndex = ProjectRootManager.getInstance(project).fileIndex
                projectFileIndex.getSourceRootForFile(parentDirectory.virtualFile)?.parent?.let { root ->
                    PsiManager.getInstance(project).findDirectory(root)
                        ?.findSubdirectory("res")
                        ?.findSubdirectory("layout")?.let { directory ->
                            createLayoutFile(
                                project = project,
                                directory = directory,
                                name = "${suffix.toLowerCase()}_$snakeCaseName"
                            )
                        } ?: showError("Can't find /res/layout inside source root")
                } ?: showError("Can't find source root")
            }

        }, "Generate new $name", null)
    }

    private fun createSourceFile(
        project: Project?,
        codeGen: CodeGen,
        directory: PsiDirectory,
        withInputData: Boolean,
        withOutputData: Boolean,
        withLayout: Boolean,
        withRouter: Boolean
    ) {
        val file = PsiFileFactory.getInstance(project)
            .createFileFromText(
                "${codeGen.getClassName()}.kt",
                JavaLanguage.INSTANCE,
                codeGen.generate(
                    withInputData = withInputData,
                    withOutputData = withOutputData,
                    withLayout = withLayout,
                    withRouter = withRouter
                )
            )

        directory.add(file)
    }

    private fun createLayoutFile(
        project: Project?,
        directory: PsiDirectory,
        name: String
    ) {
        val resource = "/templates/layout/layout.xml.template"
        try {
        val resourceAsStream = CreateAction::class.java.getResourceAsStream(resource)
            val layoutXml = IOUtils.toString(resourceAsStream, StandardCharsets.UTF_8.name())
            val fileName = "$name.xml"
            val newFile = File(directory.virtualFile.path, fileName)
            if (!newFile.exists()) {
                val file = PsiFileFactory.getInstance(project)
                    .createFileFromText(
                        fileName,
                        XMLLanguage.INSTANCE,
                        layoutXml
                    )
                directory.add(file)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun showError(error: String) {
        JOptionPane.showMessageDialog(null, error)
    }

    companion object {
        private const val DI_SUBDIRECTORY_NAME = "di"
    }
}