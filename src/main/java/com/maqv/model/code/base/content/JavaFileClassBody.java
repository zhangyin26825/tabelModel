package com.maqv.model.code.base.content;

import com.maqv.model.code.base.QualifiedName;
import com.maqv.model.code.base.field.FieldFactory;
import com.maqv.model.code.base.field.FieldSource;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyin
 * @create 2020-02-18 15:41
 **/
public abstract class JavaFileClassBody extends JavaFileString {


    private List<? extends FieldSource> fieldSources;

    private FieldFactory fieldFactory;

    public JavaFileClassBody(QualifiedName qualifiedName) {
        super(qualifiedName);
        fieldSources=new ArrayList<>();
    }


    public JavaFileClassBody(QualifiedName qualifiedName, List<? extends FieldSource> fieldSources, FieldFactory fieldFactory) {
        super(qualifiedName);
        this.fieldSources = fieldSources;
        this.fieldFactory = fieldFactory;
    }

    public void initField(FieldFactory fieldFactory, List<? extends FieldSource> fieldSources){
        this.fieldFactory=fieldFactory;
        this.fieldSources=fieldSources;
    }


    @Override
    public String classbody() {
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append(beforeField()+NEWLINE);
        stringBuffer.append(fields());
        stringBuffer.append(afterField()+NEWLINE);
        return stringBuffer.toString();
    }


    protected String beforeField() {
        return "";
    }

    protected String fields(){
        StringBuffer stringBuffer=new StringBuffer();
        if(CollectionUtils.isNotEmpty(fieldSources)&&fieldFactory!=null){
            for (FieldSource column : fieldSources) {
                stringBuffer.append(fieldFactory.generatorFieldString(column));
                stringBuffer.append(NEWLINE);
            }
        }
        return stringBuffer.toString();
    }

    protected String afterField() {
        return "";
    }


}
