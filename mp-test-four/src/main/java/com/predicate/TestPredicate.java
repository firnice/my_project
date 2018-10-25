package com.predicate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class TestPredicate {

    public static void main(String[] args) {
        fun1();
        fun2();
        fun3();
        fun4();
    }

    private static void fun1() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Function<String, Date> toDate = s -> {
            try {
                return sf.parse(s);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        };

        String dateStr = "1988-09-20";

        Date date = toDate.apply(dateStr);

        System.out.println(date);
    }


    private static void fun2() {
        Consumer<String> formatOut = s -> {
            System.out.println("s:" + s);
        };

        formatOut.accept("Predicates 测试");
    }


    private static void fun3() {
        Predicate<Integer> isEvenNum = i -> i % 2 == 0;

        System.out.println(isEvenNum.test(10));
    }

    private static void fun4() {
        String name = "Supplier";
        Supplier<String> test = () -> name + " test!" ;

        System.out.println(test.get());
    }
}
