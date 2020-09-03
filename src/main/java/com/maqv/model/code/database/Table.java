package com.maqv.model.code.database;



import com.maqv.model.code.mysql.JdbcUtil;
import com.maqv.model.code.mysql.MysqlColumnInfo;
import com.maqv.model.code.ui.listselect.ListValue;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Optional;

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


    public static  Table getTableByName(String tableName){
        List<MysqlColumnInfo> columns = JdbcUtil.queryColumns();
        List<Table> tables = MysqlColumnInfo.convert(columns);

        Optional<Table> first = tables.stream().filter(i -> StringUtils.equals(i.getTableName(), tableName)).findFirst();
        if(first.isPresent()){
            return first.get();
        }else {
            throw new RuntimeException("当前数据库没有"+tableName+"这个表");
        }
    }
}
