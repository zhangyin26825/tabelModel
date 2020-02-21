package com.maqv.model.code.file_generator.properties;

import com.maqv.model.code.base.JavaCodeElement;
import com.maqv.model.code.base.QualifiedName;
import com.maqv.model.code.database.Table;
import com.maqv.model.code.file_generator.ImportConstants;
import com.maqv.model.code.file_generator.TableFileContent;

/**
 * @author zhangyin
 * @create 2020-02-19 14:13
 **/
public class PropertiesFileContent extends TableFileContent {

    public PropertiesFileContent(QualifiedName qualifiedName, Table table) {
        super(qualifiedName,
                table.getColumns(),
                new PropertiesFieldFactory(),
                table);

        addImports(ImportConstants.tableImports);
        addComment("DBTable for "+table.getTableName());
    }

    @Override
    public String classIdentification() {
        return PUBLIC+INTERFACE+getClassName();
    }


    @Override
    protected String beforeField() {
        return "Table TABLE = new Table("+JavaCodeElement.doubleQuotes(table.getTableName())+COMMA+ JavaCodeElement.doubleQuotes(getAlias())+")"+SEMICOLON;
    }
}
