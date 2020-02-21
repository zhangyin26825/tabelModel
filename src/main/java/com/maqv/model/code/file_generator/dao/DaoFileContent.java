package com.maqv.model.code.file_generator.dao;

import com.maqv.model.code.base.QualifiedName;
import com.maqv.model.code.database.Column;
import com.maqv.model.code.database.PrimarykeyUtils;
import com.maqv.model.code.database.Table;
import com.maqv.model.code.file_generator.ImportConstants;
import com.maqv.model.code.file_generator.TableFileContent;

import java.util.List;

/**
 * @author zhangyin
 * @create 2020-02-20 16:19
 **/
public class DaoFileContent extends TableFileContent {

    private List<Column> columnList;

    public DaoFileContent(QualifiedName qualifiedName, Table table) {
        super(qualifiedName, table);
        this.columnList=table.getColumns();
        addImports(ImportConstants.daoImports);
        addComment("Dao for "+table.getTableName());
    }

    @Override
    public String classIdentification() {
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append(PUBLIC+INTERFACE+getClassName()+EXTENDS);
        String daoSuperType = PrimarykeyUtils.getDaoSuperType(columnList);
        stringBuffer.append(daoSuperType);
        String primarykeyType = PrimarykeyUtils.getPrimarykeyType(columnList, table);
        stringBuffer.append("<"+getEntityName()+COMMA+primarykeyType+">");
        return stringBuffer.toString();
    }
}
