package mybatis_orign;

import java.io.InputStream;

public interface SqlSessionFactoryBuilder {

    SqlSessionFactory builder(String pathname);
}
