package mybatis_orign;

/**
 * 映射配置信息类，解析xml文件，获取sql语句和查询返回结果类型
 */
public class Mapper {
    //
    private String resultType;
    private String queryString;
    private  Object obj;

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
