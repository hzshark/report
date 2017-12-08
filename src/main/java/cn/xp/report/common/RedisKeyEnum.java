package cn.xp.report.common;

public enum RedisKeyEnum {

    USER_ID, //用户信息
    TOKEN, //用户访问token
    SESSION_ID, //SESSION_ID
    ;

    private static final String APP_KEY = "weixin_"; //app redis key 前缀

    public String getCompositeKey(Object obj) {
        StringBuilder sb = new StringBuilder(APP_KEY+this.name());
        if (obj != null) {
            sb.append(':').append(obj);
        }
        return sb.toString();
    }

}
