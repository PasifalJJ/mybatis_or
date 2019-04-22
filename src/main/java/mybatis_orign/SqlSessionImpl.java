package mybatis_orign;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SqlSessionImpl implements SqlSession {
    private  Connection conn;
    private Map<String, Mapper> mappers;

    public SqlSessionImpl(Connection conn, Map<String, Mapper> mappers) {
        this.conn = conn;
        this.mappers = mappers;
    }

    @Override
    public <T> T getMapper(Class<T> cls) {
        //获取传入userdao的动态代理
        T t = (T) Proxy.newProxyInstance(cls.getClassLoader(),
                cls.getInterfaces(), new MapperInvocationHandler(conn, mappers, cls));
        return t;
    }
}
