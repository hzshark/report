package cn.xp.report.common.rule;


import cn.xp.report.common.exception.BizException;
import cn.xp.report.common.util.StringUtil;

import java.util.Map;

//******************************************************************************
// 参数检查类
//******************************************************************************
// 都是调用BizResult进行检查抛异常, 抛出的都是BizException;
// errno全都是 ERR_SYS_PARAMS_WRONG;
// errmsg 可以自定义

public class ParamsChecker {
    private static final int errno = 110009;

    /**
     * 检查是否为空. (null, "", "0", "false", 0, false 这些都是空)
     * 
     * @param test
     * @param errmsg
     * @return
     */
    public static void checkNotEmpty(Object test, String errmsg) throws BizException {
        BizResult.ensureNotEmpty(test, errno, errmsg);
    }

    /**
     * 检查是否为null
     * 
     * @param test
     * @param errmsg
     * @return
     */
    public static void checkNotNull(Object test, String errmsg) throws BizException {
        BizResult.ensureNotNull(test, errno, errmsg);
    }

    /**
     * 检查是否为空字符串
     * 
     * @param test
     * @param errmsg
     * @return
     */
    public static void checkNotBlank(String test, String errmsg) throws BizException {
        BizResult.ensureNotBlank(test, errno, errmsg);
    }

    /**
     * 检查map是否contains key
     * 
     * @param test
     * @param key
     * @param errmsg
     * @return
     */
    public static void checkHasKey(Map<String, ?> test, String key, String errmsg) throws BizException {
        BizResult.ensureHasKey(test, key, errno, errmsg);
    }

    /**
     * 检查是否是Integer
     * 
     * @param test
     * @param errmsg
     * @return
     */
    public static void checkIsInteger(String test, String errmsg) throws BizException {
        BizResult.ensureIsInteger(test, errno, errmsg);
    }

    /**
     * 检查是否是Long
     * 
     * @param test
     * @param errmsg
     * @return
     */
    public static void checkIsLong(String test, String errmsg) throws BizException {
        BizResult.ensureIsLong(test, errno, errmsg);
    }

    public static void checkIsFloat(String test, String errmsg) throws BizException {
        BizResult.ensureIsFloat(test, errno, errmsg);
    }

    public static void checkIsPhone(String test, String errmsg) throws BizException {
        BizResult.ensureIsPhone(test, errno, errmsg);
    }

    public static void checkIsMobile(String test, String errmsg) throws BizException {
        BizResult.ensureIsMobile(test, errno, errmsg);
    }

    public static void checkIsMail(String test, String errmsg) throws BizException {
        BizResult.ensureIsMail(test, errno, errmsg);
    }

    public static void checkIsDomain(String test, String errmsg) throws BizException {
        BizResult.ensureIsDomain(test, errno, errmsg);
    }

    public static void checkIsIP(String test, String errmsg) throws BizException {
        BizResult.ensureIsIP(test, errno, errmsg);
    }

    public static void checkIsUrl(String test, String errmsg) throws BizException {
        BizResult.ensureIsUrl(test, errno, errmsg);
    }

    public static void checkIsQQ(String test, String errmsg) throws BizException {
        BizResult.ensureIsQQ(test, errno, errmsg);
    }

    public static void checkIsDate(String test, String errmsg) throws BizException {
        BizResult.ensureIsDate(test, errno, errmsg);
    }

    public static void checkIsTime(String test, String errmsg) throws BizException {
        BizResult.ensureIsTime(test, errno, errmsg);
    }

    public static void checkIsDateTime(String test, String errmsg) throws BizException {
        BizResult.ensureIsDateTime(test, errno, errmsg);
    }

    public static void checkIsIdentity(String test, String errmsg) throws BizException {
        BizResult.ensureIsIdentity(test, errno, errmsg);
    }

    public static void checkIsMaxLength(String test, int maxLength, String errmsg) throws BizException {
        BizResult.ensureMaxLength(test, maxLength, errno, errmsg);
    }

    public static Integer Conver2Int(String param, int defvale)  {
        if (StringUtil.isNumber(param))
            return  Integer.parseInt(param);
        return defvale;
    }

    public static Double Conver2Double(String param, Double defvale)  {
        if (StringUtil.isNumber(param))
            return  Double.parseDouble(param);
        return defvale;
    }

    public static Integer Conver2AbsInt(String param, int defvale)  {

        return Math.abs(Conver2Int(param,defvale));
    }
}
