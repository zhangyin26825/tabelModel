package com.maqv.model.code.file_generator.mapper_xml;


import com.maqv.model.code.base.FileContent;
import com.maqv.model.code.base.JavaCodeElement;
import com.maqv.model.code.database.Column;
import com.maqv.model.code.database.Table;

import java.util.List;

/**
 * @author zhangyin
 * @create 2020-02-20 16:36
 **/
public class MapperXmlFileContent implements FileContent {

    private Table table;

    private List<Column> columnList;

    private String mapperClassFullPath;

    private String entityClassFullPath;

    public MapperXmlFileContent(Table table,  String mapperClassFullPath, String entityClassFullPath) {
        this.table = table;
        this.columnList = table.getColumns();
        this.mapperClassFullPath = mapperClassFullPath;
        this.entityClassFullPath = entityClassFullPath;
    }

    @Override
    public String getFileContent() {
        String poClass= JavaCodeElement.convertTableNameToClassName(table.getTableName());
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        stringBuffer.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n");
        stringBuffer.append("<mapper namespace=\""+ mapperClassFullPath+"\">\n");
        stringBuffer.append("<resultMap id=\"resultMap\" type=\""+entityClassFullPath+"\">\n");
        for (Column columnInfo : columnList) {
            if(columnInfo.primarykey()){
                stringBuffer.append("<id ");
            }else{
                stringBuffer.append("<result ");
            }
            stringBuffer.append("column=\"").append(columnInfo.getColumnName()).append("\" jdbcType=\"")
                    .append(columnInfo.getXmlJdbcType())
                    .append("\" property=\"")
                    .append(columnInfo.getFieldName())
                    .append("\" ");
            if(columnInfo.createEnum()){
                stringBuffer.append("typeHandler=\"tech.ibit.mybatis.type.CommonEnumTypeHandler\"");
            }
            stringBuffer.append("/>\n");
        }
        stringBuffer.append("</resultMap>\n");
        stringBuffer.append("</mapper>\n");
        return stringBuffer.toString();
    }



}
