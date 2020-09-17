package com.firnice.test;

import java.util.Arrays;

/**
 * @author: liyl
 * @date: 2018/4/9 下午9:40
 * @since 1.0.0
 */
public class Test1 {

    public static void main(String[] args) {
        System.out.println(solution(0,0,0,7,8,9));
    }

    public static String solution(int A, int B, int C, int D, int E, int F) {
         //先排序
        int[] num = {A, B, C, D, E, F};
        Arrays.sort(num);
        String result = "NOT POSSIBLE";
        String format = "%d%d:%d%d:%d%d";

        if (num[4] < 6) { //如果只有一个大于6的 只需要判定是否大于24小时
            if (10 * num[0] + num[1] < 24)
                return String.format(format,num[0], num[1] , num[2] , num[3], num[4] , num[5] );
            else
                return result;
        } else if (num[3] < 6) {//如果两个大于6的 只能塞到分、秒最后一位
            if (10 * num[0] + num[1] < 24)
                return String.format(format,num[0], num[1] , num[2] , num[4], num[3] , num[5] );
            else
                return result;
        } else if (num[2] < 6) { // 如果三个大于6的 只能塞到时、分、秒最后一位
            if (10 * num[0] + num[3] < 24)
                return String.format(format,num[0], num[3] , num[1] , num[4], num[2] , num[5] );
            else
                return result;
        }
        return result;
    }
}
