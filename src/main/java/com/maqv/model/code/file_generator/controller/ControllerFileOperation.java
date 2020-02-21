package com.maqv.model.code.file_generator.controller;

import com.maqv.model.code.base.FileOperation;
import com.maqv.model.code.base.JavaCodeElement;
import com.maqv.model.code.base.QualifiedName;
import com.maqv.model.code.base.content.JavaFileClassBody;
import com.maqv.model.code.database.Table;
import com.maqv.model.code.idea.G;
import com.maqv.model.code.idea.PropertiesKey;

/**
 * @author zhangyin
 * @create 2020-02-21 11:56
 **/
public class ControllerFileOperation implements FileOperation<Table> {



    @Override
    public QualifiedName getQualifiedName(Table table) {
        String packageName = G.getProperties(PropertiesKey.ControllerKey);
        String className = JavaCodeElement.convertTableNameToClassName(table.getTableName());
        return new QualifiedName(packageName+ JavaCodeElement.DOT+className+"Controller");
    }

    @Override
    public JavaFileClassBody getFileContent(Table table) {
        return new ControllerFileContent(getQualifiedName(table),table);
    }
}
