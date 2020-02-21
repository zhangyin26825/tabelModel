package com.maqv.model.code.file_generator;

import com.maqv.model.code.base.JavaCodeElement;
import com.maqv.model.code.base.QualifiedName;
import com.maqv.model.code.base.content.JavaFileClassBody;
import com.maqv.model.code.base.field.FieldFactory;
import com.maqv.model.code.base.field.FieldSource;
import com.maqv.model.code.database.Table;

import java.util.List;

/**
 * @author zhangyin
 * @create 2020-02-19 13:39
 **/
public abstract class TableFileContent  extends JavaFileClassBody {

    protected Table table;

    public TableFileContent(QualifiedName qualifiedName, Table table) {
        super(qualifiedName);
        this.table = table;
    }

    public TableFileContent(QualifiedName qualifiedName, List<? extends FieldSource> fieldSources, FieldFactory fieldFactory, Table table) {
        super(qualifiedName, fieldSources, fieldFactory);
        this.table = table;
    }

    public String getEntityName(){
        return JavaCodeElement.convertTableNameToClassName(table.getTableName());
    }

    public String getAlias(){
        return JavaCodeElement.getAlias(table.getTableName());
    }

}
