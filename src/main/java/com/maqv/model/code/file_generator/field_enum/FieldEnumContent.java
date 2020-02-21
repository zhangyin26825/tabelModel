package com.maqv.model.code.file_generator.field_enum;

import com.maqv.model.code.base.JavaCodeElement;
import com.maqv.model.code.base.QualifiedName;
import com.maqv.model.code.base.content.JavaFileClassBody;
import com.maqv.model.code.database.Column;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhangyin
 * @create 2020-02-19 11:29
 **/
public class FieldEnumContent  extends JavaFileClassBody {

    private Column column;

    public FieldEnumContent(QualifiedName qualifiedName, Column column) {
        super(qualifiedName);
        this.column = column;
    }

    @Override
    public String classIdentification() {
        return PUBLIC+ENUM+getClassName()+IMPLEMENTS+" CommonEnum";
    }

    @Override
    protected String beforeField() {
        String comment = column.getComment();
        String s = convertSourceCode(comment);
        return s+" ;\n" +
                "\n" +
                "\n" +
                "    "+getClassName()+"(int value) {\n" +
                "        this.value = value;\n" +
                "    }\n" +
                "\n" +
                "    private int value;\n" +
                "\n" +
                "    @Override\n" +
                "    @JsonValue"+NEWLINE+
                "    public int getValue() {\n" +
                "        return value;\n" +
                "    }\n" +
                "\n" +
                "\n" +
                " @JsonCreator"+NEWLINE+
                "    public static "+getClassName()+" get(Integer value) {\n" +
                "        if (null == value) {\n" +
                "            return null;\n" +
                "        }\n" +
                "        for ("+getClassName()+" status : values()) {\n" +
                "            if (value.equals(status.getValue())) {\n" +
                "                return status;\n" +
                "            }\n" +
                "        }\n" +
                "        return null;\n" +
                "    }";
    }


    private static Pattern pattern=Pattern.compile("([0-9]+):[a-zA-Z_]+:[^ ]+");
    private static List<EnumInstance> parse(String comment){
        List<EnumInstance> result=new ArrayList<>();
        Matcher matcher = pattern.matcher(comment);

        while (matcher.find()){
            String group = matcher.group(0);
            String[] s = group.split(":");
            EnumInstance enumInstance = new EnumInstance(Integer.parseInt(s[0]), s[1].toUpperCase(), s[2]);
            result.add(enumInstance);
        }
        return result;
    }

    private static String convertSourceCode(String comment){

        List<EnumInstance> parse = parse(comment);
        if(parse.isEmpty()){
            return " ";
        }
        try {
            StringBuffer stringBuffer=new StringBuffer();

            for (EnumInstance enumInstance : parse) {

                stringBuffer.append(JavaCodeElement.comment(enumInstance.getComment()));
                stringBuffer.append(enumInstance.getName());
                stringBuffer.append("("+enumInstance.getValue()+"),"+NEWLINE);
                stringBuffer.append(NEWLINE);
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            return " ";
        }
    }
}
