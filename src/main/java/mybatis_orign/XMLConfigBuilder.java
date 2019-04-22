package mybatis_orign;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * 获取Congituration对象，对象中存有jdbc的配置和全路径方法名+sql语句+返回结果
 */
public class XMLConfigBuilder {
    public static Configuration loadConfiguration(String config){
        Configuration cfg=new Configuration();
        Document doc = null;
        try {
            doc = Jsoup.parse(new File(config), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements propertys = doc.select("dataSource property");
        for (Element property : propertys) {
            String name = property.attr("name");
            if ("driver".equalsIgnoreCase(name)){
                String value = property.attr("value");
                cfg.setDriver(value);
            }else if ("url".equalsIgnoreCase(name)){
                String value = property.attr("value");
                cfg.setUrl(value);
            }else if ("username".equalsIgnoreCase(name)){
                String value = property.attr("value");
                cfg.setUsername(value);
            }else if ("password".equalsIgnoreCase(name)){
                String value = property.attr("value");
                cfg.setPassword(value);
            }
        }

        Elements mappersEles = doc.select("mappers mapper");
        HashMap<String, Mapper> mappers=null;
        if (mappersEles!=null){
            for (Element mappersEle : mappersEles) {
                if (mappersEles!=null){
                    //获取映射文件路径，传入获取映射文件集合的方法，获得映射文件的集合
                    String resourcePath = mappersEle.attr("resource");
                    //
                    mappers = loadMapperConfiguration(resourcePath);
                    cfg.setMappers(mappers);
                }
            }
        }
        return cfg;
    }

    /**
     *
     * @param resourcePath 映射文件路径
     * @return HashMap<String,Mapper>  string为映射文件命名空间，Mapper为sql语句和sql语句返回结果
     */
    private static HashMap<String,Mapper> loadMapperConfiguration(String resourcePath){
        HashMap<String,Mapper> map=new HashMap<String,Mapper>();
        Mapper mapper=new Mapper();
        Document doc = null;
        try {
            String resourceAsFile = Resources.getResourceAsFile(resourcePath);
            doc = Jsoup.parse(new File(resourceAsFile), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String namespace = doc.select("mapper").attr("namespace");
        Elements selectsEle = doc.select("select");
        for (Element selectEle : selectsEle) {
            String sqlString = selectEle.text();
            String resultType = selectEle.attr("resultType");
            String id = selectEle.attr("id");
            String key=namespace+"."+id;
            String parameterType = selectEle.attr("parameterType");
            mapper.setObj(parameterType);
            mapper.setQueryString(sqlString);
            mapper.setResultType(resultType);
            map.put(key,mapper);
        }
        return map;
    }
}
