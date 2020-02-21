package com.maqv.model.code.base.content;

import com.maqv.model.code.base.BaseJavaUnit;
import com.maqv.model.code.base.FileContent;
import com.maqv.model.code.base.JavaCodeElement;
import com.maqv.model.code.base.QualifiedName;
import lombok.experimental.Delegate;

/**
 * @author zhangyin
 * @create 2020-02-18 15:08
 **/
public  abstract class JavaFileString extends BaseJavaUnit implements FileContent {

    @Delegate
    private QualifiedName qualifiedName;


    public JavaFileString(QualifiedName qualifiedName) {
        this.qualifiedName = qualifiedName;
    }

    /**
     * 获取java文件的字符串
     * @return
     */
    @Override
    public final String getFileContent() {
        StringBuffer sb=new StringBuffer();
        /**
         * 这里需要把  classbody提前的原因是
         * classBody某个属性可能需要新增一个导入，
         * 先调用 classBody可以使  importCode不出现问题，
         */
        String classbody = classbody();
        sb.append(packageCode()+NEWLINE);
        sb.append(NEWLINE);
        sb.append(importCode());
        sb.append(NEWLINE);
        sb.append(classComment());
        sb.append(NEWLINE);
        sb.append(classAnnotation());
        sb.append(classIdentification()+LEFT_BRACES+NEWLINE);
        sb.append(NEWLINE);
        sb.append(classbody);
        sb.append(NEWLINE);
        sb.append(RIGHT_BRACES);
        return sb.toString();
    }

    /**
     * 包
     * @return
     */
    private final String  packageCode(){
        return PACKAGE+getPackagePath()+SEMICOLON;
    }

    /**
     * 导入
     * @return
     */
    private final String importCode() {
        StringBuffer stringBuffer=new StringBuffer();
        for (String importDesc : imports ) {
            stringBuffer.append(IMPORT+importDesc+SEMICOLON+NEWLINE);
        }
        return stringBuffer.toString();
    }
    /**
     * 类注释
     * @return
     */
    private final String classComment(){
        addAuthorAndCreateComment();
        return JavaCodeElement.commentCollections(comments);
    }

    /**
     * 类注解
     * @return
     */
    public final String  classAnnotation(){
        StringBuffer stringBuffer=new StringBuffer();
        for (String annotation : annotations) {
            stringBuffer.append(annotation+NEWLINE);
        }
        return stringBuffer.toString();
    }

    /**
     * 类标识信息
     * @return
     */
    public abstract String  classIdentification();

    /**
     * 类体的内容
     * @return
     */
    public abstract String classbody();
}
