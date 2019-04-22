package mybatis_orign;

public interface SqlSessionFactoryBuilder {

    /**
     * 传入配置文件，生成sqlSessionFactory
     * @param pathname  SqlMapConfig.xml
     * @return
     */
    SqlSessionFactory builder(String pathname);
}
