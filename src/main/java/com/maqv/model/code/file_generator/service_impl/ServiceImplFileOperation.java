package com.maqv.model.code.file_generator.service_impl;

import com.maqv.model.code.base.FileOperation;
import com.maqv.model.code.base.JavaCodeElement;
import com.maqv.model.code.base.QualifiedName;
import com.maqv.model.code.base.content.JavaFileClassBody;
import com.maqv.model.code.database.Table;
import com.maqv.model.code.idea.G;
import com.maqv.model.code.idea.PropertiesKey;

/**
 * @author zhangyin
 * @create 2020-02-21 11:52
 **/
public class ServiceImplFileOperation implements FileOperation<Table> {


    @Override
    public QualifiedName getQualifiedName(Table table) {
        String packageName = G.getProperties(PropertiesKey.ServiceKey);
        String className = JavaCodeElement.convertTableNameToClassName(table.getTableName());
        return new QualifiedName(packageName+ JavaCodeElement.DOT+"impl"+JavaCodeElement.DOT+className+"ServiceImpl");
    }

    @Override
    public JavaFileClassBody getFileContent(Table table) {
        return new ServiceImplFileContent(getQualifiedName(table),table);
    }
}
