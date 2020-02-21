package com.maqv.model.code.base;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/** 基本的java字符串单元
 *   包含导入  注解 注释 三个基本元素
 *   目前做的，应该只有  java类，java属性 这两个类型
 * @author zhangyin
 * @create 2020-02-18 14:54
 **/
public  abstract class BaseJavaUnit implements JavaCodeElement {

    protected List<String> imports;

    protected List<String> annotations;

    protected List<String> comments;

    public BaseJavaUnit() {
         imports=new ArrayList<>();
         annotations=new ArrayList<>();
         comments=new ArrayList<>();
    }

    public  void clear(){
        imports.clear();
        annotations.clear();
        comments.clear();
    }

    public void addImport(String importString){
        this.imports.add(importString);
    }

    public void addImports(Collection<String> importStrings){
        this.imports.addAll(importStrings);
    }

    public void addAnnotation(String annotation){
        this.annotations.add(annotation);
    }

    public void addComment(String comment){
        this.comments.add(comment);
    }
    public void addAuthorAndCreateComment(){
        String user = System.getProperty("user.name");
        this.comments.add("@author "+user);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");
        LocalDateTime localDateTime = LocalDateTime.now();
        this.comments.add("@create "+dateTimeFormatter.format(localDateTime));
    }
}
