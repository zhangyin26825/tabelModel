package com.maqv.model.code.file_generator.properties;

import com.maqv.model.code.base.JavaCodeElement;
import com.maqv.model.code.base.field.FieldFactory;
import com.maqv.model.code.base.field.FieldSource;
import com.maqv.model.code.database.Column;

/**
 * @author zhangyin
 * @create 2020-02-19 14:22
 **/
public class PropertiesFieldFactory extends FieldFactory {

    @Override
    public String fieldBody(FieldSource fieldSource) {
        Column column = (Column) fieldSource;
        return "Column "+column.getFieldName()+"= new Column(TABLE"+COMMA+ JavaCodeElement.doubleQuotes(column.getColumnName())+")"+SEMICOLON;
    }

    @Override
    public void initAnnotationAndComment(FieldSource fieldSource) {
        Column column = (Column) fieldSource;
        addComment(column.getComment());
    }
}
