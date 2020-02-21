package com.maqv.model.code.file_generator.daoimpl;

import com.maqv.model.code.base.QualifiedName;
import com.maqv.model.code.database.Column;
import com.maqv.model.code.database.PrimarykeyUtils;
import com.maqv.model.code.database.Table;
import com.maqv.model.code.file_generator.ImportConstants;
import com.maqv.model.code.file_generator.TableFileContent;

import java.util.List;

/**
 * @author zhangyin
 * @create 2020-02-20 16:27
 **/
public class DaoImplFileContent extends TableFileContent {

    private List<Column> columnList;

    public DaoImplFileContent(QualifiedName qualifiedName, Table table) {
        super(qualifiedName, table);
        addAnnotation("@Repository");
        addImports(ImportConstants.daoImplImports);
        addComment("DaoImpl for "+table.getTableName());
        this.columnList=table.getColumns();
    }

    @Override
    public String classIdentification() {
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append(PUBLIC+CLASS+getClassName()+EXTENDS);
        String daoSuperType = PrimarykeyUtils.getDaoSuperType(columnList);
        stringBuffer.append(daoSuperType+"Impl");
        stringBuffer.append("<"+getEntityName()+COMMA);
        String primarykeyType = PrimarykeyUtils.getPrimarykeyType(columnList, table);
        stringBuffer.append(primarykeyType+">");
        stringBuffer.append(IMPLEMENTS+getEntityName()+"Dao");
        return stringBuffer.toString();
    }

    @Override
    protected String beforeField() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("@Autowired\n" +
                "    private "+getEntityName()+"Mapper mapper;\n" +
                "\n" +
                "    @Override\n" +
                "    public Mapper<"+getEntityName()+"> getMapper() {\n" +
                "    return mapper;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public Class<"+getEntityName()+"> getPoClazz() {\n" +
                "        return "+getEntityName()+".class;\n" +
                "    }");
        return stringBuffer.toString();
    }
}
