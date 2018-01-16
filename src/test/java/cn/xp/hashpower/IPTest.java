package cn.xp.hashpower;

import java.util.ArrayList;

public class IPTest {
    public static ArrayList<String> restoreIpAddresses(String s) {
        ArrayList<String> res = new ArrayList<String>();
        if (s == null || s.length() < 4 || s.length() > 12) {
            return res;
        }

        StringBuilder tmp = new StringBuilder();

        depthFS(0, 0, s, tmp, res);

        return res;
    }

    public static void depthFS(int count, int index, String s, StringBuilder tmp,
                               ArrayList<String> res) {
        if (count == 4 && index == s.length()) {
            res.add(tmp.toString().substring(0, tmp.length() - 1));
            return;
        } else {
            for (int i = 1; i <= 3 && index + i <= s.length(); i++) {
                String tmpStr = s.substring(index, index + i);
                if (isValid(tmpStr)) {
                    int bt = tmp.length();
                    int ed = tmp.length() + tmpStr.length();
                    tmp.append(tmpStr).append(".");
                    depthFS(count + 1, index + i, s, tmp, res);
                    tmp.delete(bt, ed + 1);
                }
            }
        }
    }

    public static boolean isValid(String s) {
        if (s.charAt(0) == '0') {
            return s.equals("0");
        }

        int num = Integer.parseInt(s);

        return num > 0 && num <= 255;
    }


    public static void main(String[] args) {
        System.out.println(restoreIpAddresses("23343"));
    }
}
