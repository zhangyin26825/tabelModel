package com.maqv.model.code.base;

import com.maqv.model.code.base.content.JavaFileClassBody;

/**
 * 这里有两种情况，
 *  1 需要生成一个类的具体类型
 *  2 需要找到生成的类
 *
 * @param <T>
 */
public interface FileOperation<T> {


     QualifiedName getQualifiedName(T t);


     JavaFileClassBody getFileContent(T t);
}
