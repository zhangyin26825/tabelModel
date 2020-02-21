package com.maqv.model.code.task;

import com.intellij.psi.PsiDirectory;
import com.maqv.model.code.base.Manager;
import com.maqv.model.code.database.Column;
import com.maqv.model.code.database.Table;
import com.maqv.model.code.file_generator.FileCreateUtils;
import com.maqv.model.code.file_generator.FileType;
import com.maqv.model.code.file_generator.mapper_xml.MapperXmlFileContent;
import com.maqv.model.code.file_generator.mapper_xml.MapperXmlFileNameAndPath;
import com.maqv.model.code.idea.G;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangyin
 * @create 2020-02-20 17:03
 **/
public class TableGeneratorTask {

    private Table table;

    private List<Column> columns;

    public TableGeneratorTask(Table table) {
        this.table = table;
        this.columns=table.getColumns();
    }

    /**
     * 创建跟数据库表相关的类
     */
    public  void createTableRelationClasses(){
        /**
         * 属性需要生成枚举类
         */
        List<String> enumImports=new ArrayList<>();
        List<Column> enumColumns = columns.stream().filter(i -> i.createEnum()).collect(Collectors.toList());
        for (Column enumColumn : enumColumns) {
            enumImports.add(FileCreateUtils.createFile(FileType.FIELD_ENUM,enumColumn));
        }

        /**
         *  多主键
         */
        List<String> primaryKeyImport=new ArrayList<>();
        List<Column> primaryKeys = columns.stream().filter(i -> i.primarykey()).collect(Collectors.toList());
        if(primaryKeys.size()>1){
            primaryKeyImport.add(FileCreateUtils.createFile(FileType.MULTIPLE_KEY, table));
        }
        /**
         * 实体类
         */
        List<String> entityImports=new ArrayList<>();
        entityImports.addAll(enumImports);
        String entity = FileCreateUtils.createFile(FileType.ENTITY, table, entityImports);


        /**
         * Table类
         */
        String properties = FileCreateUtils.createFile(FileType.PROPERTIES, table);

        /**
         * Mapper
         */
        List<String> mapperImports=new ArrayList<>();
        mapperImports.add(entity);
        String mapper = FileCreateUtils.createFile(FileType.MAPPER, table, mapperImports);


        /**
         * Dao
         */
        String dao = FileCreateUtils.createFile(FileType.DAO, table, primaryKeyImport);


        /**
         * DaoImpl
         */
        List<String> daoImplImports=new ArrayList<>();
        daoImplImports.add(entity);
        daoImplImports.add(dao);
        daoImplImports.add(mapper);
        if(CollectionUtils.isNotEmpty(primaryKeyImport)){
            daoImplImports.addAll(primaryKeyImport);
        }
        FileCreateUtils.createFile(FileType.DAO_IMPL, table, daoImplImports);

        /**
         * Mapper XML
         */
        MapperXmlFileContent mapperXmlFile = new MapperXmlFileContent(table,mapper, entity);
        MapperXmlFileNameAndPath mapperXmlFileNameAndPath = new MapperXmlFileNameAndPath(table);
        PsiDirectory directory=getDirectory(table);
        FileCreateUtils.createFileFromText(mapperXmlFileNameAndPath.getFileName(),mapperXmlFile.getFileContent(),directory);
    }


    /**
     *  mapper xml 根据包名获取的目录在  java目录下，
     *  这里需要 resources下的目录
     * @param table
     * @return
     */
    private PsiDirectory getDirectory(Table table) {
        String packageName= Manager.getQualifiedName(table,FileType.MAPPER).getPackagePath();
        PsiDirectory packageDirectory = G.findPackageDirectory(packageName);
        PsiDirectory temp=packageDirectory;
        int i=1;
        while(!temp.getName().equals("main")||i>30){
            temp=temp.getParent();
            i++;
        }
        PsiDirectory sourcesRoot=null;
        if(temp.getName().equals("main")){
            sourcesRoot=temp.findSubdirectory("resources");
            PsiDirectory target=sourcesRoot;
            String[] split = packageName.split("\\.");
            for (String s : split) {
                PsiDirectory subdirectory = target.findSubdirectory(s);
                if (subdirectory == null) {
                    subdirectory= target.createSubdirectory(s);
                }
                target=subdirectory;
            }
            return target;
        }
        return packageDirectory;
    }


}
