package com.maqv.model.code.file_generator.controller;

import com.maqv.model.code.base.JavaCodeElement;
import com.maqv.model.code.base.QualifiedName;
import com.maqv.model.code.database.Table;
import com.maqv.model.code.file_generator.ImportConstants;
import com.maqv.model.code.file_generator.TableFileContent;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhangyin
 * @create 2020-02-21 11:54
 **/
public class ControllerFileContent extends TableFileContent {


    public ControllerFileContent(QualifiedName qualifiedName, Table table) {
        super(qualifiedName, table);
        addImports(ImportConstants.controllerImports);
        addAnnotation("@RestController");
        addAnnotation("@RequestMapping(\"{version}/api/"+getEntityName().toLowerCase()+"\")");
        addAnnotation("@Validated");
        addAnnotation("@Api(tags ="+ JavaCodeElement.doubleQuotes(table.getTableComment())+")");
        addComment("controller for "+table.getTableName());
    }

    @Override
    public String classIdentification() {
        return PUBLIC+CLASS+getClassName();
    }

    @Override
    protected String beforeField() {
        return  "@Autowired\n private " + getEntityName() + "Service " + StringUtils.uncapitalize(getEntityName() + "Service; ");
    }

}
