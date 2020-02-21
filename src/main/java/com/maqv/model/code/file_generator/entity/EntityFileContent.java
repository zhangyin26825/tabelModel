package com.maqv.model.code.file_generator.entity;

import com.maqv.model.code.base.JavaCodeElement;
import com.maqv.model.code.base.QualifiedName;
import com.maqv.model.code.database.Table;
import com.maqv.model.code.file_generator.ImportConstants;
import com.maqv.model.code.file_generator.TableFileContent;

/**
 * @author zhangyin
 * @create 2020-02-19 10:59
 **/
public class EntityFileContent extends TableFileContent {



    public EntityFileContent(QualifiedName qualifiedName, Table table) {
        super(qualifiedName,table.getColumns(),new EntityFieldFactory(),table);
        addImports(ImportConstants.entityImports);
        addAnnotation("@Data");
        addAnnotation("@DbTable(name ="+ JavaCodeElement.doubleQuotes(table.getTableName())+COMMA+"alias = "
                +JavaCodeElement.doubleQuotes(getAlias())+")");
        addComment("entity for "+table.getTableName());
    }

    @Override
    public String classIdentification() {
        return PUBLIC+CLASS+ getClassName();
    }
}
