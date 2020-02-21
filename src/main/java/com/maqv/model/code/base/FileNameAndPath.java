package com.maqv.model.code.base;

/** 文件的名称与目录
 *
 * @author zhangyin
 * @create 2020-02-17 16:06
 **/
public interface FileNameAndPath {
    /**
     * 文件名称
     * @return
     */
      String getFileName();

    /**
     *
     * 文件的目录
     * idea中是通过包名去寻找对应的目录的
     * 这里返回完整的包名就可以了;
     * @return
     */
     String getDirectory();


}
