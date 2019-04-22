package mybatis_orign;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapperInvocationHandler implements InvocationHandler  {
    private Connection conn;
    private Map<String, Mapper> mappers;
    private Class cls;

    public MapperInvocationHandler(Connection conn, Map<String, Mapper> mappers, Class cls) {
        this.conn = conn;
        this.mappers = mappers;
        this.cls = cls;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //返回dao的代理对象
        Class<?> clss = method.getDeclaringClass();
//        Object o = clss.newInstance();
        String methodName = method.getName();
        String className = method.getDeclaringClass().getName();
        String methodTotalName = className + "." + methodName;
        Set<String> keys = mappers.keySet();
        Executor executor = new Executor();

        for (String key : keys) {
            if (key.equals(methodTotalName)) {
                Mapper mapper = mappers.get(key);
                Class returnType = method.getReturnType();
                if (returnType.equals(List.class)){
                    List<Object> objects = executor.selectList(mapper, conn);
                    return objects;
                }else {
                    Object selectone = executor.selectone(mapper, conn);
                    return selectone;
                }
            }
        }
        return method.invoke(proxy, args);
    }
}
