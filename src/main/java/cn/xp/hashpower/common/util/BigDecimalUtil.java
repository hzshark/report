package cn.xp.hashpower.common.util;

import java.math.BigDecimal;

public class BigDecimalUtil {

    private static int DEF_DIV_SCALE = 10;

    public BigDecimalUtil() {
    }

    public static double add(double... params) {
        BigDecimal b1 = new BigDecimal(0);
        double[] arr$ = params;
        int len$ = params.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            double param = arr$[i$];
            BigDecimal b2 = new BigDecimal(Double.toString(param));
            b1 = b1.add(b2);
        }

        return b1.doubleValue();
    }

    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    public static double sub(double... params) {
        int len = params.length;
        if (len < 1) {
            return 0.0D;
        } else {
            BigDecimal b1 = new BigDecimal(Double.toString(params[0]));

            for(int i = 1; i < len; ++i) {
                BigDecimal b2 = new BigDecimal(Double.toString(params[i]));
                b1 = b1.subtract(b2);
            }

            return b1.doubleValue();
        }
    }

    public static double mul(double... params) {
        BigDecimal b1 = new BigDecimal(1);
        double[] arr$ = params;
        int len$ = params.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            double param = arr$[i$];
            BigDecimal b2 = new BigDecimal(Double.toString(param));
            b1 = b1.multiply(b2);
        }

        return b1.doubleValue();
    }

    public static double div(double v1, double v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        } else {
            BigDecimal b1 = new BigDecimal(Double.toString(v1));
            BigDecimal b2 = new BigDecimal(Double.toString(v2));
            return b1.divide(b2, scale, 4).doubleValue();
        }
    }

    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        } else {
            BigDecimal b = new BigDecimal(Double.toString(v));
            BigDecimal one = new BigDecimal("1");
            return b.divide(one, scale, 4).doubleValue();
        }
    }

    public static double round(double v) {
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, 2, 4).doubleValue();
    }

    public static double round(String v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        } else {
            BigDecimal b = new BigDecimal(v);
            BigDecimal one = new BigDecimal("1");
            return b.divide(one, scale, 4).doubleValue();
        }
    }

    public static double round(String v) {
        if (StringUtil.isBlank(v)) {
            return 0.0D;
        } else {
            BigDecimal b = new BigDecimal(v);
            BigDecimal one = new BigDecimal("1");
            return b.divide(one, 2, 4).doubleValue();
        }
    }

    public static double decimal(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        } else {
            BigDecimal b = new BigDecimal(Double.toString(v));
            BigDecimal one = new BigDecimal("1");
            return b.divide(one, scale, 1).doubleValue();
        }
    }

    public static BigDecimal getBigDecimal(String str) {
        if (!StringUtil.isNumber(str)) {
            str = "0";
        }

        BigDecimal decimal = new BigDecimal(str);
        return decimal.setScale(2, 6);
    }
}
