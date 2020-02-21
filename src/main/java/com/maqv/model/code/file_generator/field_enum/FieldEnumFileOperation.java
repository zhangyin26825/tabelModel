package com.maqv.model.code.file_generator.field_enum;

import com.maqv.model.code.base.FileOperation;
import com.maqv.model.code.base.JavaCodeElement;
import com.maqv.model.code.base.QualifiedName;
import com.maqv.model.code.base.content.JavaFileClassBody;
import com.maqv.model.code.database.Column;
import com.maqv.model.code.idea.G;
import com.maqv.model.code.idea.PropertiesKey;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhangyin
 * @create 2020-02-19 11:32
 **/
public class FieldEnumFileOperation implements FileOperation<Column> {

    static {

    }

    @Override
    public QualifiedName getQualifiedName(Column column) {
        String packageName = G.getProperties(PropertiesKey.FieldEnumKey);
        String className = StringUtils.capitalize(column.getFieldName());
        return new QualifiedName(packageName+ JavaCodeElement.DOT+className);
    }

    @Override
    public JavaFileClassBody getFileContent(Column column) {
        return new FieldEnumContent(getQualifiedName(column),column);
    }
}
