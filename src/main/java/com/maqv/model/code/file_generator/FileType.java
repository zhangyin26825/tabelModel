package com.maqv.model.code.file_generator;

import com.maqv.model.code.base.Manager;
import com.maqv.model.code.file_generator.controller.ControllerFileOperation;
import com.maqv.model.code.file_generator.dao.DaoFileOperation;
import com.maqv.model.code.file_generator.daoimpl.DaoImplFileOperation;
import com.maqv.model.code.file_generator.entity.EntityFielOpeation;
import com.maqv.model.code.file_generator.error_code.ErrorCodeFileContent;
import com.maqv.model.code.file_generator.error_code.ErrorCodeOperation;
import com.maqv.model.code.file_generator.field_enum.FieldEnumFileOperation;
import com.maqv.model.code.file_generator.mapper.MapperFileOperation;
import com.maqv.model.code.file_generator.multiple.MultipleKeyFileOperation;
import com.maqv.model.code.file_generator.properties.PropertiesFileOperation;
import com.maqv.model.code.file_generator.service.ServiceFileOperation;
import com.maqv.model.code.file_generator.service_impl.ServiceImplFileOperation;

/**
 * @author zhangyin
 * @create 2020-02-19 10:54
 **/
public enum  FileType {

    ENTITY(1,true),
    FIELD_ENUM(2,true),
    MULTIPLE_KEY(3,true),
    PROPERTIES(4,true),
    MAPPER(5,true),
    DAO(6,false),
    DAO_IMPL(7,false),
    SERVICE(8,false),
    SERVICE_IMPL(9,false),
    CONTROLLER(10,false),
    ERROR_CODE(11,false),
    ;


    private int value;

    private boolean override;

    FileType(int value, boolean override) {
        this.value = value;
        this.override = override;
    }

    static {
        Manager.register(FileType.ENTITY,new EntityFielOpeation());
        Manager.register(FileType.FIELD_ENUM,new FieldEnumFileOperation());
        Manager.register(FileType.MULTIPLE_KEY,new MultipleKeyFileOperation());
        Manager.register(FileType.PROPERTIES,new PropertiesFileOperation());
        Manager.register(FileType.MAPPER,new MapperFileOperation());
        Manager.register(FileType.DAO,new DaoFileOperation());
        Manager.register(FileType.DAO_IMPL,new DaoImplFileOperation());
        Manager.register(FileType.SERVICE,new ServiceFileOperation());
        Manager.register(FileType.SERVICE_IMPL,new ServiceImplFileOperation());
        Manager.register(FileType.CONTROLLER,new ControllerFileOperation());
        Manager.register(FileType.ERROR_CODE,new ErrorCodeOperation());
    }

    public int getValue() {
        return value;
    }

    public boolean isOverride() {
        return override;
    }
}
