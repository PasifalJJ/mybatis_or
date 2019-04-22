package mybatis_orign;

public interface SqlSessionFactory {
    /**
     * 获取SqlSession实现类对象
     * @return
     */
    SqlSession openSqlSession();
}
