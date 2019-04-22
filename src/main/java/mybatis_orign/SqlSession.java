package mybatis_orign;

public interface SqlSession {

    /**
     * 生成一个Userdao接口的代理对象，使用spring生成
     * @param <T>
     * @return
     */
    <T> T getMapper(Class<T> cls);
}
