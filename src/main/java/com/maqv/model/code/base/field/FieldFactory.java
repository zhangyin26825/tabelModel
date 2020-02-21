package com.maqv.model.code.base.field;

import com.maqv.model.code.base.BaseJavaUnit;
import com.maqv.model.code.base.JavaCodeElement;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhangyin
 * @create 2020-02-18 15:36
 **/
public abstract class FieldFactory extends BaseJavaUnit implements AbstractFieldFactory {

    public abstract String fieldBody(FieldSource fieldSource);

    public abstract  void initAnnotationAndComment(FieldSource fieldSource);

    @Override
    public String generatorFieldString(FieldSource fieldSource) {
        initAnnotationAndComment(fieldSource);
        StringBuffer stringBuffer=new StringBuffer();
        String comment = JavaCodeElement.commentCollections(comments);
        if(StringUtils.isNotEmpty(comment)){
            stringBuffer.append(comment);
        }
        if(CollectionUtils.isNotEmpty(annotations)){
            for (String annotation : annotations) {
                stringBuffer.append(annotation);
                stringBuffer.append(NEWLINE);
            }
        }
        stringBuffer.append(fieldBody(fieldSource));
        clear();
        return  stringBuffer.toString();
    }
}
