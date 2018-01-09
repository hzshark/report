package cn.xp.hashpower.common.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class StringUtil extends StringUtils {
    private static final Logger logger = Logger.getLogger(StringUtil.class);

    public StringUtil() {
    }

    public static boolean isBlank(Object o) {
        return isNull(o).equals("");
    }

    public static boolean isNotBlank(Object o) {
        return !isNull(o).equals("");
    }

    public static String isNull(String str) {
        return str == null ? "" : str.trim();
    }

    public static String isNull(Object o) {
        if (o == null) {
            return "";
        } else {
            String str = "";
            if (o instanceof String) {
                str = (String)o;
            } else {
                str = o.toString();
            }

            return str.trim();
        }
    }

    public static String firstCharUpperCase(String s) {
        StringBuffer sb = new StringBuffer(s.substring(0, 1).toUpperCase());
        sb.append(s.substring(1, s.length()));
        return sb.toString();
    }

    public static String firstCharLowerCase(String s) {
        StringBuffer sb = new StringBuffer(s.substring(0, 1).toLowerCase());
        sb.append(s.substring(1, s.length()));
        return sb.toString();
    }

    public static String hideFirstChar(String str, int len) {
        if (str == null) {
            return null;
        } else {
            char[] chars = str.toCharArray();
            int i;
            if (str.length() <= len) {
                for(i = 0; i < chars.length; ++i) {
                    chars[i] = '*';
                }
            } else {
                for(i = 0; i < 1; ++i) {
                    chars[i] = '*';
                }
            }

            str = new String(chars);
            return str;
        }
    }

    public static String hideLastChar(String str, int len) {
        if (str == null) {
            return null;
        } else {
            char[] chars = str.toCharArray();
            if (str.length() <= len) {
                return hideLastChar(str, str.length() - 1);
            } else {
                for(int i = chars.length - 1; i > chars.length - len - 1; --i) {
                    chars[i] = '*';
                }

                str = new String(chars);
                return str;
            }
        }
    }

    public static String hideStr(String str, int index1, int index2) {
        if (str == null) {
            return null;
        } else {
            String str1 = str.substring(index1, index2);
            String str2 = str.substring(index2);
            String str3 = "";
            if (index1 > 0) {
                str1 = str.substring(0, index1);
                str2 = str.substring(index1, index2);
                str3 = str.substring(index2);
            }

            char[] chars = str2.toCharArray();

            for(int i = 0; i < chars.length; ++i) {
                chars[i] = '*';
            }

            str2 = new String(chars);
            String str4 = str1 + str2 + str3;
            return str4;
        }
    }

    public static String contact(Object[] args) {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < args.length; ++i) {
            sb.append(args[i]);
            if (i < args.length - 1) {
                sb.append(",");
            }
        }

        return sb.toString();
    }

    public static String contact(long[] args) {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < args.length; ++i) {
            sb.append(args[i]);
            if (i < args.length - 1) {
                sb.append(",");
            }
        }

        return sb.toString();
    }

    public static String array2Str(int[] arr) {
        StringBuffer s = new StringBuffer();

        for(int i = 0; i < arr.length; ++i) {
            s.append(arr[i]);
            if (i < arr.length - 1) {
                s.append(",");
            }
        }

        return s.toString();
    }

    public static int[] strarr2intarr(String[] strarr) {
        int[] result = new int[strarr.length];

        for(int i = 0; i < strarr.length; ++i) {
            result[i] = Integer.parseInt(strarr[i]);
        }

        return result;
    }



    public static String toUnderline(String str) {
        char[] charArr = str.toCharArray();
        StringBuffer sb = new StringBuffer();
        sb.append(charArr[0]);

        for(int i = 1; i < charArr.length; ++i) {
            if (charArr[i] >= 'A' && charArr[i] <= 'Z') {
                sb.append('_').append(charArr[i]);
            } else {
                sb.append(charArr[i]);
            }
        }

        return sb.toString().toLowerCase();
    }

    public static String clearUnderline(String str) {
        char[] charArr = isNull(str).toLowerCase().toCharArray();
        StringBuffer sb = new StringBuffer();
        sb.append(charArr[0]);
        boolean isClear = false;

        for(int i = 1; i < charArr.length; ++i) {
            if (charArr[i] == '_') {
                isClear = true;
            } else if (isClear && charArr[i] >= 'a' && charArr[i] <= 'z') {
                char c = (char)(charArr[i] - 32);
                sb.append(c);
                isClear = false;
            } else {
                sb.append(charArr[i]);
            }
        }

        return sb.toString();
    }

    public static int toInt(String str) {
        if (isBlank(str)) {
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

    public static byte toByte(String str) {
        if (isBlank(str)) {
            return 0;
        } else {
            boolean var1 = false;

            byte ret;
            try {
                ret = Byte.parseByte(str);
            } catch (NumberFormatException var3) {
                ret = 0;
            }

            return ret;
        }
    }

    public static long toLong(String str) {
        if (isBlank(str)) {
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

    public static long[] toLongs(String[] str) {
        if (str != null && str.length >= 1) {
            long[] ret = new long[str.length];
            ret = (long[])((long[]) ConvertUtils.convert(str, Long.TYPE));
            return ret;
        } else {
            return new long[]{0L};
        }
    }

    public static double[] toDoubles(String[] str) {
        if (str != null && str.length >= 1) {
            double[] ret = new double[str.length];

            for(int i = 0; i < str.length; ++i) {
                ret[i] = toDouble(str[i]);
            }

            return ret;
        } else {
            return new double[]{0.0D};
        }
    }

    public static double toDouble(String str) {
        if (isBlank(str)) {
            return 0.0D;
        } else {
            try {
                return BigDecimalUtil.round(str);
            } catch (Exception var2) {
                return 0.0D;
            }
        }
    }

    public static String getRandomString(int length) {
        String val = "";
        Random random = new Random();

        for(int i = 0; i < length; ++i) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            if ("char".equalsIgnoreCase(charOrNum)) {
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val = val + (char)(random.nextInt(26) + temp);
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val = val + String.valueOf(random.nextInt(10));
            }
        }

        return val;
    }

    public static String removeHtml(String str) {
        str = isNull(str);
        Pattern regex = Pattern.compile("<.+?>");
        Matcher matcher = regex.matcher(str);
        return matcher.replaceAll("");
    }

    public static String reBuildReqParamsToJson(String jsonString) {
        if (isNotBlank(jsonString)) {
            JSONObject jsonObject = JSONObject.parseObject(jsonString);
            Iterator<Map.Entry<String, Object>> iterator = jsonObject.entrySet().iterator();
            HashMap rtMap = new HashMap();

            while(iterator.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry)iterator.next();
                if (entry.getValue().getClass().equals(String.class)) {
                    if (isNotBlank(entry.getValue().toString())) {
                        rtMap.put(entry.getKey(), entry.getValue());
                    }
                } else if (entry.getValue().getClass().isArray()) {
                    Object[] objs = (Object[])((Object[])entry.getValue());
                    if (objs.length > 0) {
                        rtMap.put(entry.getKey(), entry.getValue());
                    }
                } else {
                    rtMap.put(entry.getKey(), entry.getValue());
                }
            }

            jsonString = JSONObject.toJSONString(rtMap);
            return jsonString;
        } else {
            return "";
        }
    }

    public static String urlDecoderUTF8(String str) {
        try {
            if (isNotBlank(str)) {
                return URLDecoder.decode(str, "utf-8");
            }
        } catch (UnsupportedEncodingException var2) {
            logger.info("URL解码异常：" + str + ":" + var2.getMessage());
        }

        return "";
    }

    public static String urlEncoderUTF8(String str) {
        try {
            if (isNotBlank(str)) {
                return URLEncoder.encode(str, "utf-8");
            }
        } catch (UnsupportedEncodingException var2) {
            var2.printStackTrace();
        }

        return "";
    }

    public static String unicodeDecoder(String str) {
        int len = str.length();
        StringBuffer outBuffer = new StringBuffer(len);
        int x = 0;

        while(true) {
            while(true) {
                while(x < len) {
                    char aChar = str.charAt(x++);
                    if (aChar == '\\') {
                        aChar = str.charAt(x++);
                        if (aChar == 'u') {
                            int value = 0;

                            for(int i = 0; i < 4; ++i) {
                                aChar = str.charAt(x++);
                                switch(aChar) {
                                    case '0':
                                    case '1':
                                    case '2':
                                    case '3':
                                    case '4':
                                    case '5':
                                    case '6':
                                    case '7':
                                    case '8':
                                    case '9':
                                        value = (value << 4) + aChar - 48;
                                        break;
                                    case ':':
                                    case ';':
                                    case '<':
                                    case '=':
                                    case '>':
                                    case '?':
                                    case '@':
                                    case 'G':
                                    case 'H':
                                    case 'I':
                                    case 'J':
                                    case 'K':
                                    case 'L':
                                    case 'M':
                                    case 'N':
                                    case 'O':
                                    case 'P':
                                    case 'Q':
                                    case 'R':
                                    case 'S':
                                    case 'T':
                                    case 'U':
                                    case 'V':
                                    case 'W':
                                    case 'X':
                                    case 'Y':
                                    case 'Z':
                                    case '[':
                                    case '\\':
                                    case ']':
                                    case '^':
                                    case '_':
                                    case '`':
                                    default:
                                        throw new IllegalArgumentException("Malformed encoding.");
                                    case 'A':
                                    case 'B':
                                    case 'C':
                                    case 'D':
                                    case 'E':
                                    case 'F':
                                        value = (value << 4) + 10 + aChar - 65;
                                        break;
                                    case 'a':
                                    case 'b':
                                    case 'c':
                                    case 'd':
                                    case 'e':
                                    case 'f':
                                        value = (value << 4) + 10 + aChar - 97;
                                }
                            }

                            outBuffer.append((char)value);
                        } else {
                            if (aChar == 't') {
                                aChar = '\t';
                            } else if (aChar == 'r') {
                                aChar = '\r';
                            } else if (aChar == 'n') {
                                aChar = '\n';
                            } else if (aChar == 'f') {
                                aChar = '\f';
                            }

                            outBuffer.append(aChar);
                        }
                    } else {
                        outBuffer.append(aChar);
                    }
                }

                return outBuffer.toString();
            }
        }
    }

    public static String yuanConvertFen(double money) {
        double fenMoney = BigDecimalUtil.mul(new double[]{money, 100.0D});
        return String.valueOf((long)fenMoney);
    }

    public static String yuanConvertFen(String money) {
        double fenMoney = BigDecimalUtil.mul(new double[]{toDouble(money), 100.0D});
        return String.valueOf((long)fenMoney);
    }

    public static long yuanConvertFenToLong(String money) {
        double fenMoney = BigDecimalUtil.mul(new double[]{toDouble(money), 100.0D});
        return (long)fenMoney;
    }

    public static String fenConvertYuan(String money) {
        double fenMoney = toDouble(money);
        return NumberUtil.format2Str(BigDecimalUtil.div(fenMoney, 100.0D));
    }

    public static String fenConvertYuan(double money) {
        return NumberUtil.format2Str(BigDecimalUtil.div(money, 100.0D));
    }

    public static String fenConvertYuan(long money) {
        return fenConvertYuan(String.valueOf(money));
    }

    public static String replaceString(String strData, String regex, String replacement) {
        if (strData == null) {
            return null;
        } else {
            int index = strData.indexOf(regex);
            String strNew = "";
            if (index < 0) {
                return strData;
            } else {
                while(index >= 0) {
                    strNew = strNew + strData.substring(0, index) + replacement;
                    strData = strData.substring(index + regex.length());
                    index = strData.indexOf(regex);
                }

                strNew = strNew + strData;
                return strNew;
            }
        }
    }

    public static boolean isNumber(String str) {
        if (isBlank(str)) {
            return false;
        } else {
            Pattern regex = Pattern.compile("(-)?\\d*(.\\d*)?");
            Matcher matcher = regex.matcher(str);
            boolean isMatched = matcher.matches();
            return isMatched;
        }
    }

    public static boolean isChineseChar(char c) throws UnsupportedEncodingException {
        return String.valueOf(c).getBytes("UTF-8").length > 1;
    }

    public static String getGBKString(String orignal, int count, int beginInx) throws UnsupportedEncodingException {
        if (orignal != null && !"".equals(orignal)) {
            orignal = new String(orignal.getBytes(), "UTF-8");
            if (count > 0 && count <= orignal.getBytes("UTF-8").length) {
                StringBuffer buff = new StringBuffer();

                for(int i = beginInx; i < count + beginInx; ++i) {
                    char c = orignal.charAt(i);
                    buff.append(c);
                    if (isChineseChar(c)) {
                        --count;
                    }
                }

                return new String(buff.toString().getBytes(), "UTF-8");
            }
        }

        return orignal;
    }

    public static String translateCode(String[] tppCodes, String[] upsCodes, String code, boolean flag) {
        String transCode = "";
        if (tppCodes.length == upsCodes.length && !isBlank(code)) {
            int i;
            String tppcode;
            if (flag) {
                for(i = 0; i < tppCodes.length; ++i) {
                    tppcode = tppCodes[i];
                    if (tppcode.equals(code)) {
                        transCode = upsCodes[i];
                        break;
                    }
                }
            } else {
                for(i = 0; i < upsCodes.length; ++i) {
                    tppcode = upsCodes[i];
                    if (tppcode.equals(code)) {
                        transCode = tppCodes[i];
                        break;
                    }
                }
            }

            return transCode;
        } else {
            return transCode;
        }
    }
}
