package com.maqv.model.code.file_generator.error_code;

import com.maqv.model.code.database.Column;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhangyin
 * @create 2020-02-21 16:15
 **/
public class ErrorCodeUtils {

   public static String getInnerClassErrorCode(Column column){
        return getInnerClassErrorCode(column,1);
   }

    public static String getInnerClassErrorCode(Column column,int index){
        String s = StringUtils.capitalize(column.getFieldName()) + "Error";
        String innerClassString="interface "+s+" {\n" +
                "        int CODE = CP + "+index+";\n" +
                "        String MSG = CODE + SPLIT + \""+column.getComment()+"错误\";\n" +
                "    }";
        return innerClassString;
    }
}
