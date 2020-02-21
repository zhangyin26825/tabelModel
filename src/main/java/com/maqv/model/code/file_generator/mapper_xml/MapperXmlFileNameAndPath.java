package com.maqv.model.code.file_generator.mapper_xml;

import com.maqv.model.code.base.FileNameAndPath;
import com.maqv.model.code.base.JavaCodeElement;
import com.maqv.model.code.database.Table;

/**
 * @author zhangyin
 * @create 2020-02-20 16:40
 **/
public class MapperXmlFileNameAndPath implements FileNameAndPath {

    private Table table;

    public MapperXmlFileNameAndPath(Table table) {
        this.table = table;
    }

    @Override
    public String getFileName() {
        String className = JavaCodeElement.convertTableNameToClassName(table.getTableName());
        return className+"Mapper.xml";
    }

    @Override
    public String getDirectory() {
        return null;
    }
}
