package com.maqv.model.code.file_generator.entity;


import com.maqv.model.code.base.JavaCodeElement;
import com.maqv.model.code.base.Manager;
import com.maqv.model.code.base.QualifiedName;
import com.maqv.model.code.base.field.FieldFactory;
import com.maqv.model.code.base.field.FieldSource;
import com.maqv.model.code.database.Column;
import com.maqv.model.code.file_generator.FileType;

/**
 * @author zhangyin
 * @create 2019-12-13 17:17
 **/
public class EntityFieldFactory extends FieldFactory {

    @Override
    public void initAnnotationAndComment(FieldSource fieldSource) {
        Column column = (Column) fieldSource;
        if(column.createEnum()){
            QualifiedName qualifiedName = Manager.getQualifiedName(column, FileType.FIELD_ENUM);
            addImport(qualifiedName.getQualifiedName());
        }

        addComment(column.getComment());
        StringBuffer stringBuffer=new StringBuffer();
        if (column.primarykey()) {
            stringBuffer.append("@DbId");
            String name = "name=" + JavaCodeElement.doubleQuotes(column.getColumnName());
            String autoIncrease="autoIncrease="+column.autoIncrease();
            stringBuffer.append(JavaCodeElement.parentheses(name+COMMA+autoIncrease));
        }else {
            stringBuffer.append("@DbColumn");
            String name = "name=" + JavaCodeElement.doubleQuotes(column.getColumnName());
            String nullable="nullable="+column.nullable();

            String annotation=name;
            if(column.nullable()){
                annotation+=COMMA+nullable;
            }
            stringBuffer.append(JavaCodeElement.parentheses(annotation));
        }
        addAnnotation(stringBuffer.toString());
    }

    @Override
    public String fieldBody(FieldSource fieldSource) {
        Column column = (Column) fieldSource;
        return  PRIVATE+column.getJavaString()+" "+column.getFieldName()+SEMICOLON;
    }


}
