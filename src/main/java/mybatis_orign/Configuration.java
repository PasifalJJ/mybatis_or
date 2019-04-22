package mybatis_orign;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取mybits的核心配置文件
 */
public class Configuration {
    //jdbc配置信息
    private String driver;
    private String url;
    private String username;
    private String password;
    //映射文件信息
    private Map<String,Mapper> mappers=new HashMap<String,Mapper>();

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, Mapper> getMappers() {
        return mappers;
    }

    public void setMappers(Map<String, Mapper> mappers) {
        this.mappers = mappers;
    }
}
