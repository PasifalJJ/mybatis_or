package mybatis_orign;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * sql语句的执行对象
 */
public class Executor {
    public <E> List<E> selectList(Mapper mapper, Connection conn) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //获取sql语句，将其中的参数转换为?
            String queryString = mapper.getQueryString();
            String resultType = mapper.getResultType();
            Class domainClass = Class.forName(resultType);
            preparedStatement = conn.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            List<E> list = new ArrayList<E>();
            while (resultSet.next()) {
                E domain = extraction(resultSet, domainClass);
                list.add(domain);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            close(conn, resultSet, preparedStatement);
        }
    }

    public <E> E selectone(Mapper mapper, Connection conn) {
        //获取sql语句，将其中的参数转换为?
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            String queryString = mapper.getQueryString();
            String resultType = mapper.getResultType();
            Class domainClass = Class.forName(resultType);
            preparedStatement = conn.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                E domain = extraction(resultSet, domainClass);
                return domain;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            close(conn, resultSet, preparedStatement);
        }
    }

    private void close(Connection conn, ResultSet resultSet, PreparedStatement preparedStatement) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private <E> E extraction(ResultSet resultSet, Class domainClass) {
        E e = null;
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            e = (E) domainClass.newInstance();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                Field field = domainClass.getDeclaredField(columnName);
                field.setAccessible(true);
                field.set(e, resultSet.getObject(columnName));
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return e;
    }
}
