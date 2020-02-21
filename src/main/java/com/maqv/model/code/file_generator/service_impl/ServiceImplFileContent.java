package com.maqv.model.code.file_generator.service_impl;

import com.maqv.model.code.base.QualifiedName;
import com.maqv.model.code.database.Table;
import com.maqv.model.code.file_generator.ImportConstants;
import com.maqv.model.code.file_generator.TableFileContent;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhangyin
 * @create 2020-02-21 11:50
 **/
public class ServiceImplFileContent extends TableFileContent {

    public ServiceImplFileContent(QualifiedName qualifiedName, Table table) {
        super(qualifiedName, table);
        addImports(ImportConstants.serviceImplImports);
        addAnnotation("@Service");
        addComment("service for "+table.getTableName());
    }

    @Override
    public String classIdentification() {
        return PUBLIC+CLASS+getClassName()+IMPLEMENTS+getEntityName()+"Service";
    }

    @Override
    protected String beforeField() {
        return "@Autowired\n private "+getEntityName()+"Dao "+ StringUtils.uncapitalize(getEntityName() + "Dao; ");
    }
}
