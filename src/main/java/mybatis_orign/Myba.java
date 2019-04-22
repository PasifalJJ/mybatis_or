/*
package mybatis_orign;

import com.jsc.dao.UserDao;
import com.jsc.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.util.Map;
import java.util.Set;

public class Myba {
    public static void main(String[] args) {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        mapper.findAll();
        Configuration configuration = XMLConfigBuilder.loadConfiguration(Resources.getResourceAsFile("SqlMapConfig.xml"));

        Map<String, Mapper> mappers = configuration.getMappers();
        Set<String> sets = mappers.keySet();
        for (String set : sets) {
            if (set.equals())
        }


    }
}
*/
