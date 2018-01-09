package cn.xp.hashpower.common.rule;



import cn.xp.hashpower.common.exception.BizException;
import cn.xp.hashpower.util.Utils;

import java.util.Map;
import java.util.Set;

public class BizResult {
    public static void ensureTrue(Object test, int errno, String errmsg) throws BizException {
        if (null != test && Boolean.class == test.getClass() && (Boolean) test == true) {
            return;
        }
        throwError(errno, errmsg);
    }

    public static void ensureMaxLength(String test, int maxLength, int errno, String errmsg) throws BizException {
        if (!Utils.isMaxLength(test, maxLength)) {
            return;
        }
        throwError(errno, errmsg);
    }

    public static void ensureNotEmpty(Object test, int errno, String errmsg) throws BizException {
        if (!Utils.isEmpty(test)) {
            return;
        }
        throwError(errno, errmsg);
    }

    public static void ensureNotNull(Object test, int errno, String errmsg) throws BizException {
        if (test != null) {
            return;
        }
        throwError(errno, errmsg);
    }

    public static void ensureNotBlank(String test, int errno, String errmsg) throws BizException {
        if (!Utils.isBlank(test)) {
            return;
        }
        throwError(errno, errmsg);
    }

    public static void ensureHasKey(Map<String, ?> test, String key, int errno, String errmsg) throws BizException {
        if (null != test && test.containsKey(key)) {
            return;
        }
        throwError(errno, errmsg);
    }

    public static void ensureHasKey(Set<?> test, String key, int errno, String errmsg) throws BizException {
        if (null != test && test.contains(key)) {
            return;
        }
        throwError(errno, errmsg);
    }

    public static void ensureIsInteger(String test, int errno, String errmsg) throws BizException {
        if (Utils.isInteger(test)) {
            return;
        }
        throwError(errno, errmsg);
    }

    public static void ensureIsLong(String test, int errno, String errmsg) throws BizException {
        if (Utils.isLong(test)) {
            return;
        }
        throwError(errno, errmsg);
    }

    public static void ensureIsFloat(String test, int errno, String errmsg) throws BizException {
        if (Utils.isFloat(test)) {
            return;
        }
        throwError(errno, errmsg);
    }

    public static void ensureIsPhone(String test, int errno, String errmsg) throws BizException {
        if (Utils.isPhone(test)) {
            return;
        }
        throwError(errno, errmsg);
    }

    public static void ensureIsMobile(String test, int errno, String errmsg) throws BizException {
        if (Utils.isMobile(test)) {
            return;
        }
        throwError(errno, errmsg);
    }

    public static void ensureIsQQ(String test, int errno, String errmsg) throws BizException {
        if (Utils.isQQ(test)) {
            return;
        }
        throwError(errno, errmsg);
    }

    public static void ensureIsMail(String test, int errno, String errmsg) throws BizException {
        if (Utils.isMail(test)) {
            return;
        }
        throwError(errno, errmsg);
    }

    public static void ensureIsDomain(String test, int errno, String errmsg) throws BizException {
        if (Utils.isDomain(test)) {
            return;
        }
        throwError(errno, errmsg);
    }

    public static void ensureIsIP(String test, int errno, String errmsg) throws BizException {
        if (Utils.isIp(test)) {
            return;
        }
        throwError(errno, errmsg);
    }

    public static void ensureIsUrl(String test, int errno, String errmsg) throws BizException {
        if (Utils.isUrl(test)) {
            return;
        }
        throwError(errno, errmsg);
    }

    public static void ensureIsTime(String test, int errno, String errmsg) throws BizException {
        if (Utils.isTime(test)) {
            return;
        }
        throwError(errno, errmsg);
    }

    public static void ensureIsDate(String test, int errno, String errmsg) throws BizException {
        if (Utils.isDate(test)) {
            return;
        }
        throwError(errno, errmsg);
    }

    public static void ensureIsDateTime(String test, int errno, String errmsg) throws BizException {
        if (Utils.isDateTime(test)) {
            return;
        }
        throwError(errno, errmsg);
    }

    public static void ensureIsIdentity(String test, int errno, String errmsg) throws BizException {
        if (Utils.isIdentity(test)) {
            return;
        }
        throwError(errno, errmsg);
    }

    public static void throwError(String errmsg) throws BizException {
        throwError(110000, errmsg);
    }

    public static void throwError(int errno, String errmsg) throws BizException {
        if (null == errmsg || errmsg.trim().length() == 0) {
            errmsg = "Unkonow Error";
        }
        throw new BizException(errno, errmsg);
    }
}
