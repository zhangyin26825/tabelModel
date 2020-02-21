package com.maqv.model.code.database;



import com.maqv.model.code.ui.listselect.ListValue;

import java.util.List;

public interface Table  extends ListValue {

    /**
     * 获取表名
     * @return
     */
    String getTableName();

    /**
     * 表注释
     * @return
     */
    String getTableComment();

    /**
     *
     * @return
     */
    List<Column> getColumns();

    @Override
    default  String getStringValue(){
        return  getTableName()+"("+getTableComment()+")";
    }
}
