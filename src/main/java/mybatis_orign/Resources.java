package mybatis_orign;

import java.io.File;
import java.io.InputStream;

public class Resources {
    public static String getResourceAsFile(String name){
        //Mybatis中加载类xml资源文件的方法getResourceAsStream()
       return Resources.class.getClassLoader().getResource(name).getPath();
    }

    public static InputStream getResourceAsStream(String name){
        //Mybatis中加载类xml资源文件的方法getResourceAsStream()
        return Resources.class.getClassLoader().getResourceAsStream(name);
    }
}
