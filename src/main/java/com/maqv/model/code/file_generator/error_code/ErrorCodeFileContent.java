package com.maqv.model.code.file_generator.error_code;

import com.maqv.model.code.base.QualifiedName;
import com.maqv.model.code.database.Column;
import com.maqv.model.code.database.Table;
import com.maqv.model.code.file_generator.TableFileContent;
import com.maqv.model.code.idea.G;
import com.maqv.model.code.idea.PropertiesKey;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangyin
 * @create 2020-02-21 14:35
 **/
public class ErrorCodeFileContent extends TableFileContent {


    public ErrorCodeFileContent(QualifiedName qualifiedName, Table table) {
        super(qualifiedName, table);
        addImport(G.getProperties(PropertiesKey.ErrorCodeFullName));
        addComment("errorCode for "+table.getTableName());
    }

    @Override
    public String classIdentification() {
         return PUBLIC+INTERFACE+getClassName()+EXTENDS+"ErrorCode";
    }

    @Override
    protected String beforeField() {
        return " /**\n" +
                "     * 前缀\n" +
                "     */\n" +
                "    int CP = "+table.getTableName().toUpperCase()+"_CODE_PREFIX"+";";
    }

    @Override
    protected String afterField() {
        List<Column> columns = table.getColumns();
        List<Column> collect = columns.stream().filter(i -> i.primarykey()).collect(Collectors.toList());
        if(collect.size()!=1){
            return super.afterField();
        }
        Column column = collect.get(0);
        String innerClassErrorCode = ErrorCodeUtils.getInnerClassErrorCode(column);
        return innerClassErrorCode;
    }
}
