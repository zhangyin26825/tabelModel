package com.maqv.model.code.base;

import com.maqv.model.code.base.content.JavaFileClassBody;
import com.maqv.model.code.file_generator.FileType;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zhangyin
 * @create 2020-02-18 10:46
 **/
public class Manager {

    private  static  Map<FileType,FileOperation>  map=new LinkedHashMap<>();

    public static void register(FileType type,FileOperation fileOperation){
        map.put(type, fileOperation);
    }

    /**
     * 获取生成的java文件的完全限定名
     * @param obj
     * @param type
     * @return
     */
    public static  QualifiedName getQualifiedName(Object obj,FileType type){
        if (!map.containsKey(type)) {
            throw new RuntimeException("未找到对应的文件操作器");
        }
        FileOperation fileOperation = map.get(type);
        return fileOperation.getQualifiedName(obj);
    }


    public static JavaFileClassBody getFileContent(Object obj,FileType type){
        if (!map.containsKey(type)) {
            throw new RuntimeException("未找到对应的文件操作器");
        }
        FileOperation fileOperation = map.get(type);
        JavaFileClassBody fileContent = fileOperation.getFileContent(obj);
        return fileContent;
    }




}
