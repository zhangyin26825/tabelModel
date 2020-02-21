package com.maqv.model.code.base;

public class QualifiedName  implements FileNameAndPath{

    private String qualifiedName;

    public QualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
    }

    @Override
    public String getFileName(){
        return getClassName()+".java";
    }

    @Override
    public String getDirectory(){
        return getPackagePath();
    }
    /**
     * 获取类名
     * @return
     */
    public String getClassName(){
        String qualifiedName = getQualifiedName();
        int index = qualifiedName.lastIndexOf('.');
        return  qualifiedName.substring(index+1);
    }

    /**
     * 获取包名
     * @return
     */
    public String getPackagePath(){
        String qualifiedName = getQualifiedName();
        int index = qualifiedName.lastIndexOf('.');
        return qualifiedName.substring(0,index);
    }

    /**
     * 获取java类的完全限定名称
     * @return
     */
    public String getQualifiedName(){
        return qualifiedName;
    }
}
