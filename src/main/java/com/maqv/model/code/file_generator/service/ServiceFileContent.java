package com.maqv.model.code.file_generator.service;

import com.maqv.model.code.base.QualifiedName;
import com.maqv.model.code.database.Table;
import com.maqv.model.code.file_generator.TableFileContent;

/**
 * @author zhangyin
 * @create 2020-02-21 11:46
 **/
public class ServiceFileContent extends TableFileContent {

    public ServiceFileContent(QualifiedName qualifiedName, Table table) {
        super(qualifiedName, table);
        addComment("service for "+table.getTableName());
    }

    @Override
    public String classIdentification() {
        return PUBLIC+INTERFACE+getClassName();
    }
}
