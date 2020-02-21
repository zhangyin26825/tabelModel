package com.maqv.model.code.file_generator.multiple;

import com.maqv.model.code.base.FileOperation;
import com.maqv.model.code.base.JavaCodeElement;
import com.maqv.model.code.base.QualifiedName;
import com.maqv.model.code.base.content.JavaFileClassBody;
import com.maqv.model.code.database.Table;
import com.maqv.model.code.idea.G;
import com.maqv.model.code.idea.PropertiesKey;

/**
 * @author zhangyin
 * @create 2020-02-19 14:00
 **/
public class MultipleKeyFileOperation implements FileOperation<Table> {



    @Override
    public QualifiedName getQualifiedName(Table table) {
        String packageName = G.getProperties(PropertiesKey.EntityKey);
        String className = JavaCodeElement.convertTableNameToClassName(table.getTableName());
        return new QualifiedName(packageName+ JavaCodeElement.DOT+className+"Key");
    }

    @Override
    public JavaFileClassBody getFileContent(Table table) {
        return new MultipleKeyFileContent(getQualifiedName(table),table);
    }
}
