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
            String driver = configuration.getDriver();
            String url = configuration.getUrl();
            String username = configuration.getUsername();
            String password = configuration.getPassword();
            Map<String, Mapper> mappers = configuration.getMappers();

            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, username, password);
            SqlSession sqlSession=new SqlSessionImpl(conn,mappers);
            return sqlSession;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
