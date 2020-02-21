package com.maqv.model.code.base.field;

/**
 * @author zhangyin
 * @create 2020-02-18 15:33
 **/
public  interface AbstractFieldFactory{

    /**
     * 生成 field的字符串格式
     * @param fieldSource
     * @return
     */
    String  generatorFieldString(FieldSource fieldSource);
}
