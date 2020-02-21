package com.maqv.model.code.file_generator;

import com.intellij.lang.Language;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.lang.xml.XMLLanguage;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.fileTypes.PlainTextLanguage;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.codeStyle.JavaCodeStyleManager;
import com.maqv.model.code.base.FileContent;
import com.maqv.model.code.base.FileNameAndPath;
import com.maqv.model.code.base.Manager;
import com.maqv.model.code.base.QualifiedName;
import com.maqv.model.code.base.content.JavaFileClassBody;
import com.maqv.model.code.idea.G;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;

/**
 * @author zhangyin
 * @create 2020-02-20 17:14
 **/
public class FileCreateUtils {

    private static Logger logger = Logger.getLogger(FileCreateUtils.class);

    /**
     * 这里需要判断文件支不支持覆盖
     * 如果不支持覆盖，就不能在新建文件了
     * @param fileType
     * @param object
     */
    public  static String createFile(FileType fileType, Object object, List<String> imports){
        QualifiedName qualifiedNameObject = Manager.getQualifiedName(object, fileType);
        String qualifiedName = qualifiedNameObject.getQualifiedName();
        PsiClass aClass = G.findClass(qualifiedName);
        if(aClass!=null&&!fileType.isOverride()){
            //文件以及存在 并且 文件不支持override

        }else {
            JavaFileClassBody fileContent = Manager.getFileContent(object, fileType);
            if(CollectionUtils.isNotEmpty(imports)) {
                fileContent.addImports(imports);
            }
            realCreateFiles(qualifiedNameObject, fileContent);
        }
        return qualifiedName;
    }
    public  static String createFile(FileType fileType, Object object){
        return createFile(fileType,object, Collections.EMPTY_LIST);
    }

    /**
     * 这里才是真正的创建文件
     * @param fileNameAndPath
     * @param fileContent
     */
    private static void realCreateFiles(FileNameAndPath fileNameAndPath, FileContent fileContent){
        String directory = fileNameAndPath.getDirectory();
        String fileName = fileNameAndPath.getFileName();
        PsiDirectory packageDirectory = G.findPackageDirectory(directory);
        if (packageDirectory != null) {
            createFileFromText(fileName,fileContent.getFileContent(),packageDirectory);
        }else {
            logger.error("没有找到"+directory+"这个目录");
        }

    }

    public static PsiFile createFileFromText(String fileName, String fileSourcesCode, PsiDirectory directory){
        logger.info("在"+directory.getVirtualFile().getPath()+"目录下创建"+fileName+"文件");
        if(directory==null){
            throw new RuntimeException("创建文件指定的目录为空，新建的文件为"+fileName);
        }
        Language language=getLanguage(fileName);
        PsiFile psiFile = PsiFileFactory.getInstance(G.getProject()).createFileFromText(fileName, language, fileSourcesCode);
        //如果这个目录下已经存在同名的文件，那么删除
        PsiFile[] files = directory.getFiles();
        for (PsiFile file : files) {
            if(file.getName().equals(fileName)){
                WriteCommandAction.runWriteCommandAction(G.getProject(), () -> {
                    file.delete();
                });
            }
        }
        WriteCommandAction.runWriteCommandAction(G.getProject(), () -> {
            if(language== JavaLanguage.INSTANCE) {
                JavaCodeStyleManager styleManager = JavaCodeStyleManager.getInstance(G.getProject());
                styleManager.optimizeImports(psiFile);
            }
            CodeStyleManager.getInstance(G.getProject()).reformat(psiFile);
            directory.add(psiFile);
        });
        return psiFile;
    }

    private static Language getLanguage(String fileName){
        Language language;
        if(fileName.endsWith("java")){
            language= JavaLanguage.INSTANCE;
        }else if(fileName.endsWith("xml")){
            language= XMLLanguage.INSTANCE;
        }else {
            language= PlainTextLanguage.INSTANCE;
        }
        return  language;
    }

}
