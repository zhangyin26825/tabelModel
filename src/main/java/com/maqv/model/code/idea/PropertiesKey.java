package com.maqv.model.code.idea;

/**
 * @author zhangyin
 * @create 2019-12-13 11:04
 **/
public enum PropertiesKey {

    ControllerKey("controller.package"),

    ServiceKey("service.package"),

    EntityKey("entity.package"),

    MapperKey("mapper.package"),

    DaoKey("dao.package"),

    DaoImplKey("dao.impl.package"),

    XmlKey("xml.package"),

    JdbcUrlKey("spring.datasource.url"),

    UsernameKey("spring.datasource.username"),

    PasswordKey("spring.datasource.password"),

    FieldEnumKey("field.enum.package"),

    PropertiesKey("properties.package"),
    /**
     * 错误码的全路径名称
     */
    ErrorCodeFullName("errorCode.full.class.name"),

    ;

    private String key;


    PropertiesKey(String key ) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
