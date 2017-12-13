package cn.xp.report.common.util;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;


public class NumberUtil {
    public NumberUtil() {
    }

    public static double format(double d, String format) {
        DecimalFormat df = new DecimalFormat(format);
        String ds = df.format(d);
        return Double.parseDouble(ds);
    }

    public static double format2(double d) {
        return BigDecimalUtil.decimal(d, 2);
    }

    public static String format2Str(double d) {
        DecimalFormat df = new DecimalFormat("#####0.00");
        return df.format(BigDecimalUtil.decimal(d, 2));
    }

    public static String format3Str(double d) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(BigDecimalUtil.decimal(d, 2));
    }

    public static String format5Str(double d) {
        DecimalFormat df = new DecimalFormat("0.00000");
        return df.format(BigDecimalUtil.decimal(d, 5));
    }

    public static double format4(double d) {
        return BigDecimalUtil.decimal(d, 4);
    }

    public static double format6(double d) {
        return BigDecimalUtil.decimal(d, 6);
    }

    public static int compare(double x, double y) {
        BigDecimal val1 = new BigDecimal(x);
        BigDecimal val2 = new BigDecimal(y);
        return val1.compareTo(val2);
    }

    public static double ceil(double d, int len) {
        String str = Double.toString(d);
        int a = str.indexOf(".");
        if (a + 3 > str.length()) {
            a = str.length();
        } else {
            a += 3;
        }

        str = str.substring(0, a);
        return Double.parseDouble(str);
    }

    public static double ceil(double d) {
        return ceil(d, 2);
    }

    public static String format(double d) {
        if (d < 1.0E7D) {
            return d + "";
        } else {
            NumberFormat nf = NumberFormat.getInstance();
            nf.setGroupingUsed(false);
            return nf.format(d);
        }
    }

    public static long getLong(String str) {
        if (StringUtil.isBlank(str)) {
            return 0L;
        } else {
            long ret = 0L;

            try {
                ret = Long.parseLong(str);
            } catch (NumberFormatException var4) {
                ret = 0L;
            }

            return ret;
        }
    }

    public static int getInt(String str) {
        if (StringUtil.isBlank(str)) {
            return 0;
        } else {
            boolean var1 = false;

            int ret;
            try {
                ret = Integer.parseInt(str);
            } catch (NumberFormatException var3) {
                ret = 0;
            }

            return ret;
        }
    }

    public static double getDouble(String str) {
        if (StringUtil.isBlank(str)) {
            return 0.0D;
        } else {
            double ret = 0.0D;

            try {
                ret = Double.parseDouble(str);
            } catch (Exception var4) {
                ret = 0.0D;
            }

            return ret;
        }
    }
}
