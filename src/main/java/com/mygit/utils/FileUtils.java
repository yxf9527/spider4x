package com.mygit.utils;

import java.io.*;

/**
 * Created by yxf on 2017/12/1.
 */
public class FileUtils {



    /**
     * 将字符串按格式写进制定路径文件
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


    /**
     * 读取本地文件
     * */
    public static String file2String(String filepath){
        StringBuffer content = new StringBuffer();
        try
        {
            File file = new File(filepath);
            //构造较麻烦 注意构造时传递的类型
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(file)));

            String s ;
            while((s=br.readLine())!=null){
                //每次换行时  并不能识别 所以用println
                content.append(s);
                System.out.println(s);
            }
            br.close();

        }
        catch (Exception e)
        {
            System.out.println("文件读取成字符串处理出现异常！");
            e.printStackTrace();
        }

        return content.toString();
    }

    /**
     * 复制功能
     * */
    public void copy() throws IOException{
        File file1 = new File("D:\\files\\demo.dat");

        File file2 = new File("D:\\files\\copy.dat");
        FileReader fr = new FileReader(file1);
        /**
         * 	FileWriter fw = new FileWriter(file2,true);
         * 在原来的文件上追加
         * */
        FileWriter fw = new FileWriter(file2);

        /**
         * 批量读取
         * */
        char[] buff = new char[8*1024];
        int a;
        while((a=fr.read(buff, 0, buff.length))!=-1){
            fw.write(buff, 0, a);
            fw.flush();
        }

        fw.close();
        fr.close();
    }

    public static void main(String[] args) {
        file2String("d:/test.html");
    }

}
