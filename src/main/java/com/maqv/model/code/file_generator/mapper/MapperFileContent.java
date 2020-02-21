package com.maqv.model.code.file_generator.mapper;

import com.maqv.model.code.base.QualifiedName;
import com.maqv.model.code.database.Table;
import com.maqv.model.code.file_generator.ImportConstants;
import com.maqv.model.code.file_generator.TableFileContent;

/**
 * @author zhangyin
 * @create 2020-02-19 14:39
 **/
public class MapperFileContent extends TableFileContent {

    public MapperFileContent(QualifiedName qualifiedName, Table table) {
        super(qualifiedName, table);
        addImports(ImportConstants.mappperImports);
        addComment("MAPPER for "+table.getTableName());
    }

    @Override
    public String classIdentification() {
        return PUBLIC+INTERFACE+getEntityName()+"Mapper "+EXTENDS+"Mapper<"+getEntityName()+">";
    }
}
