package com.maqv.model.code.file_generator.service;

import com.maqv.model.code.base.FileOperation;
import com.maqv.model.code.base.JavaCodeElement;
import com.maqv.model.code.base.QualifiedName;
import com.maqv.model.code.base.content.JavaFileClassBody;
import com.maqv.model.code.database.Table;
import com.maqv.model.code.idea.G;
import com.maqv.model.code.idea.PropertiesKey;

/**
 * @author zhangyin
 * @create 2020-02-21 11:48
 **/
public class ServiceFileOperation implements FileOperation<Table> {

    @Override
    public QualifiedName getQualifiedName(Table table) {
        String packageName = G.getProperties(PropertiesKey.ServiceKey);
        String className = JavaCodeElement.convertTableNameToClassName(table.getTableName());
        packageName=packageName+ JavaCodeElement.DOT+className.toLowerCase();
        return new QualifiedName(packageName+ JavaCodeElement.DOT+className+"Service");
    }

    @Override
    public JavaFileClassBody getFileContent(Table table) {
        return new ServiceFileContent(getQualifiedName(table),table);
    }
}
