package util;
import java.io.*;
public class newFileUtil {

    public static void makeNewFileInWebInf(String path){
        String temp ="web/WEB-INF/bookStore/book000";
        temp=temp+path;
        File file=new File(temp);
        System.out.println(temp);
        if(!file.exists()){
            file.mkdir();
        }else {
            System.out.println(temp+"文件夹未成功创建");
        }
    }

}
