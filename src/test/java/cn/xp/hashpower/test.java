package cn.xp.hashpower;

import java.lang.reflect.Executable;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class test  {

    public static void main(String[] args) {

        BigDecimal b1=new BigDecimal("2.07");
        long z=b1.longValue();
        int[] input = {2, 5, 1, 1, 1, 1, 4, 3, 7, 5, 7};
        int[] sums = new int[input.length];
        Map<Integer, Integer> hashMap = new HashMap<Integer, Integer>();

        int tmp = 0;
        for (int i = 0; i < input.length; ++i) {
            tmp += input[i];
            sums[i] = tmp;
            hashMap.put(tmp, i);
        }

        for (int pos1 = 1; pos1 < input.length; ++pos1) {
            int sum = sums[pos1] - input[pos1];
            int k = sum + sums[pos1];
            if (hashMap.containsKey(k)) {
                int pos2 = hashMap.get(k) + 1;
                int k2 = sum + sums[pos2];
                if (pos2 < input.length && hashMap.containsKey(k2)) {
                    int pos3 = hashMap.get(k2) + 1;
                    if (pos3 < input.length && sums[sums.length - 1] - sums[pos3] == sum) {
                        System.out.println("result:" + pos1 + "---" + pos2 + "----" + pos3);
                        System.out.println("sum:" + k + "---" + k2 + "----" + sum);
                        return;
                    }
                }
            }
        }


        //fixedThreadPool.execute();

    }
}