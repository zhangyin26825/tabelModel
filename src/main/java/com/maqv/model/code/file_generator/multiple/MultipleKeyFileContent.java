package com.maqv.model.code.file_generator.multiple;

import com.maqv.model.code.base.JavaCodeElement;
import com.maqv.model.code.base.QualifiedName;
import com.maqv.model.code.database.Table;
import com.maqv.model.code.file_generator.ImportConstants;
import com.maqv.model.code.file_generator.TableFileContent;
import com.maqv.model.code.file_generator.entity.EntityFieldFactory;

import java.util.stream.Collectors;

/**
 * @author zhangyin
 * @create 2020-02-19 13:44
 **/
public class MultipleKeyFileContent extends TableFileContent {


    public MultipleKeyFileContent(QualifiedName qualifiedName, Table table) {
        super(qualifiedName,
                table.getColumns().stream().filter(i->i.primarykey()).collect(Collectors.toList()),
                new EntityFieldFactory(),table);
        addImports(ImportConstants.entityImports);
        addAnnotation("@Data");
        addAnnotation("@DbTable(name ="+ JavaCodeElement.doubleQuotes(table.getTableName())+COMMA+"alias = "
                +JavaCodeElement.doubleQuotes(getAlias())+")");
        addComment("entity for "+table.getTableName());
    }

    @Override
    public String classIdentification() {
         return PUBLIC+CLASS+getClassName()+IMPLEMENTS+"DBId";
    }
}
