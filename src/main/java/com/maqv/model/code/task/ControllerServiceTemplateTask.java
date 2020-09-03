package com.maqv.model.code.task;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.maqv.model.code.base.JavaCodeElement;
import com.maqv.model.code.base.Manager;
import com.maqv.model.code.base.QualifiedName;
import com.maqv.model.code.database.Table;
import com.maqv.model.code.file_generator.FileCreateUtils;
import com.maqv.model.code.file_generator.FileType;
import com.maqv.model.code.idea.G;
import com.maqv.model.code.idea.PropertiesKey;
import com.maqv.model.code.idea.PsiClassUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyin
 * @create 2020-02-21 14:18
 **/
public class ControllerServiceTemplateTask {

    private Table table;

    public ControllerServiceTemplateTask(Table table) {
        this.table = table;
    }

    public void createControllerServiceTemplate(){

        /**
         * service
         */
        String service = FileCreateUtils.createFile(FileType.SERVICE, table);

        /**
         * controller
         */
        List<String> controllerImports=new ArrayList();
        controllerImports.add(service);
        FileCreateUtils.createFile(FileType.CONTROLLER, table,controllerImports);

        /**
         * ServiceImpl
         */
        QualifiedName dao = Manager.getQualifiedName(table, FileType.DAO);
        List<String> serviceImplImports=new ArrayList();
        serviceImplImports.add(service);
        serviceImplImports.add(dao.getQualifiedName());
        FileCreateUtils.createFile(FileType.SERVICE_IMPL, table,serviceImplImports);
        /**
         * ErrorCode
         */
        String errorCodeFullName = G.getProperties(PropertiesKey.ErrorCodeFullName);
        if(StringUtils.isNotEmpty(errorCodeFullName)) {
            creatErrorCodePrefix(errorCodeFullName,table);
            FileCreateUtils.createFile(FileType.ERROR_CODE, table);
        }
    }

    /**
     * 错误码前缀
     * @param errorCodeFullName
     * @param table
     */
    private void creatErrorCodePrefix(String errorCodeFullName,Table table){
        PsiClass aClass = G.findClass(errorCodeFullName);
        if (aClass == null) {
            return;
        }
        PsiField[] fields = aClass.getFields();
        int fieldCount=fields.length+1;
        int code=(1000+fieldCount)*10000;
        String fieldName=table.getTableName().toUpperCase()+"_CODE_PREFIX";
        if(PsiClassUtils.existedField(aClass,fieldName)){
            return;
        }
        String field="/**"+ JavaCodeElement.NEWLINE
                    +"*  "+table.getTableComment()+ JavaCodeElement.NEWLINE
                    +"*/"+ JavaCodeElement.NEWLINE
                    +"int "+fieldName+"="+code+JavaCodeElement.SEMICOLON;
        PsiClassUtils.addField(aClass,field);
    }

}
