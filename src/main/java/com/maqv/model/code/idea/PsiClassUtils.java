package com.maqv.model.code.idea;

import com.intellij.lang.java.JavaLanguage;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.psi.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangyin
 * @create 2020-02-21 15:29
 **/
public class PsiClassUtils {

    /**
     * 是否存在某个名称的属性
     * @param psiClass
     * @param fieldName
     * @return
     */
    public  static  boolean existedField(PsiClass psiClass,String fieldName){
        PsiField[] allFields = psiClass.getAllFields();
        List<PsiField> psiFields = Arrays.stream(allFields).filter(f -> f.getName().equals(fieldName)).collect(Collectors.toList());
        return !psiFields.isEmpty();
    }

    /**
     * 增加方法
     * @param psiClass
     * @param methodString
     */
    public  static  void addMethod(PsiClass psiClass,String methodString){
        PsiElementFactory factory = JavaPsiFacade.getInstance(G.getProject()).getElementFactory();
        PsiMethod method = factory.createMethodFromText(methodString, null);
        WriteCommandAction.runWriteCommandAction(G.getProject(), () -> {
            psiClass.add(method);
        });
    }

    /**
     * 增加属性
     * @param psiClass
     * @param fieldString
     */
    public  static  void addField(PsiClass psiClass,String fieldString){
        PsiElementFactory factory = JavaPsiFacade.getInstance(G.getProject()).getElementFactory();
        PsiField psiField = factory.createFieldFromText(fieldString, null);
        WriteCommandAction.runWriteCommandAction(G.getProject(), () -> {
            psiClass.add(psiField);
        });
    }

    /**
     * 增加内部类
     * @param psiClass
     * @param innerClassString
     */
    public  static  void addInnerClass(PsiClass psiClass,String innerClassString){
        PsiFile psiFile = PsiFileFactory.getInstance(G.getProject()).createFileFromText(JavaLanguage.INSTANCE, innerClassString);
        PsiJavaFile javaFile = (PsiJavaFile) psiFile;
        WriteCommandAction.runWriteCommandAction(G.getProject(), () -> {
            psiClass.add(javaFile.getClasses()[0]);
        });
    }

    /**
     * 判断是否存在某个内部类
     * @param psiClass
     * @param className
     * @return
     */
    public static boolean existedInnerClass(PsiClass psiClass,String className){
        PsiClass[] psiClasses = psiClass.getInnerClasses();
        List<PsiClass> collect = Arrays.stream(psiClasses).filter(f -> f.getName().equals(className)).collect(Collectors.toList());
        return !collect.isEmpty();
    }

    /**
     * 获取内部类的数量
     * @param psiClass
     * @return
     */
    public static int countInnerClass(PsiClass psiClass){
        PsiClass[] psiClasses = psiClass.getInnerClasses();
        return  psiClasses.length;
    }


}
