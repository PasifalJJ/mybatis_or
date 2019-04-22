package mybatis_orign;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class SqlSessionFactoryImpl implements SqlSessionFactory {
    private Configuration configuration;

    public SqlSessionFactoryImpl(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSqlSession() {
        try {
            //获取数据库连接参数
            String driver = configuration.getDriver();
            String url = configuration.getUrl();
            String username = configuration.getUsername();
            String password = configuration.getPassword();
            //获取映射配置文件参数
            Map<String, Mapper> mappers = configuration.getMappers();
            //加载数据库驱动字节码，里面有静态方法会将驱动加载入内存
            Class.forName(driver);
            
            Connection conn = DriverManager.getConnection(url, username, password);
            SqlSession sqlSession=new SqlSessionImpl(conn,mappers);
            return sqlSession;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
