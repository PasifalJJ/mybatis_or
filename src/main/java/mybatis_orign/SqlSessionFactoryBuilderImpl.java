package mybatis_orign;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;

public class SqlSessionFactoryBuilderImpl implements SqlSessionFactoryBuilder {


    /**
     * 获取SqlSessionFactory
     *
     * @param pathname
     * @return
     */
    @Override
    public SqlSessionFactory builder(String pathname) {
        Configuration configuration = XMLConfigBuilder.loadConfiguration(pathname);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryImpl(configuration);
        return sqlSessionFactory;
    }
}
