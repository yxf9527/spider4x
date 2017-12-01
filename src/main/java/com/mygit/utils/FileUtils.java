package com.mygit.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by yxf on 2017/12/1.
 */
public class FileUtils {



    /**
     * 将字符串按格式写进制定路径文件
     *
     * */
    public static void string2File(final String strFilename, final String strBuffer){
        try
        {
            // 创建文件对象
            File fileText = new File(strFilename);
            // 向文件写入对象写入信息
            FileWriter fileWriter = new FileWriter(fileText);

            // 写文件
            fileWriter.write(strBuffer);
            // 关闭
            fileWriter.close();
        }
        catch (IOException e)
        {
            //
            e.printStackTrace();
        }

    }

    public static boolean createDir(String destDirName) {
       File dir = new File(destDirName);
           if (dir.exists()) {
                     System.out.println("创建目录" + destDirName + "失败，目标目录已经存在");
                    return false;
           }if (!destDirName.endsWith(File.separator)) {
                   destDirName = destDirName + File.separator;
               }
             //创建目录
           if (dir.mkdirs()) {
                System.out.println("创建目录" + destDirName + "成功！");
                return true;
                } else {
                  System.out.println("创建目录" + destDirName + "失败！");
                    return false;
                }
         }

}
