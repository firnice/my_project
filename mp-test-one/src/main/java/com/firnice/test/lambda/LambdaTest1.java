package com.firnice.test.lambda;

import org.junit.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LambdaTest1 {

    public static void main(String[] args) {
    }

    @Test
    public void fun1() {
        List<Double> cost = Arrays.asList(10.0, 20.0, 30.0);
        double allCost = cost.stream().map(x -> x + x * 0.05).reduce((sum, x) -> sum + x).get();
        System.out.println(allCost);
    }


    @Test
    public void fun2() {
        //Before Java 8:
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Before Java8 ");
            }
        }).start();

        /**
         * todo 怎么识别是run函数的？
         */
        new Thread(() ->
                System.out.println("after Java8")
        ).start();
    }


    @Test
    public void fun3() {
        // Before Java 8:
        JButton show = new JButton("Show");
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("without lambda expression is boring");
            }
        });


        JButton show1 = new JButton("Show");
        show1.addActionListener(e -> System.out.println("without lambda expression is boring"));

    }


    @Test
    public void fun4() {
        //Prior Java 8 :
        List<String> features = Arrays.asList("Lambdas", "Default Method",
                "Stream API", "Date and Time API");
        for (String feature : features) {
            System.out.println(feature);
        }


        features.forEach(str -> System.out.println(str));
        /**
         * todo ::
         */
        features.forEach(System.out::println);

    }

    @Test
    public void fun5() {
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        System.out.println("Languages which starts with J :");
        filter(languages, (str) -> str.startsWith("J"));

        System.out.println("Languages which ends with a ");
        filter(languages, (str) -> str.endsWith("a"));

        System.out.println("Print all languages :");
        filter(languages, (str) -> true);

        System.out.println("Print no language : ");
        filter(languages, (str) -> false);

        System.out.println("Print language whose length greater than 4:");
        filter(languages, (str) -> str.length() > 4);
    }

    public void filter(List<String> names, Predicate<String> condition) {
        for (String name : names) {
            if (condition.test(name)) {
                System.out.println(name + " ");
            }
        }
    }


    @Test
    public void fun6() {
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        Predicate<String> startsWithJ = (n) -> n.startsWith("J");
        Predicate<String> fourLetterLong = (n) -> n.length() == 4;
        languages.stream()
                .filter(startsWithJ.and(fourLetterLong))
                .forEach((n) -> System.out.print("nName, which starts with 'J' and four letter long is : " + n));

    }


    @Test
    public void fun7() {
        // 不使用lambda表达式为每个订单加上12%的税
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        for (Integer cost : costBeforeTax) {
            double price = cost + .12 * cost;
            System.out.println(price);
        }

        costBeforeTax.stream().map((cost) -> cost + .12 * cost).forEach(System.out::println);

    }


    @Test
    public void fun8() {
        // 为每个订单加上12%的税
        // 老方法：
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        double total = 0;
        for (Integer cost : costBeforeTax) {
            double price = cost + .12 * cost;
            total = total + price;
        }
        System.out.println("Total : " + total);

        System.out.println("Total : " + costBeforeTax.stream().map((cost) -> cost + 0.12 * cost).reduce((sum, cost) -> sum + cost).get());


    }


    @Test
    public void fun9(){
        // 创建一个字符串列表，每个字符串长度大于2
        List<String> strList = Arrays.asList("va", "ala", "dd", "we", "sdfsd");

        List<String> filtered = strList.stream().filter(x -> x.length()> 2).collect(Collectors.toList());
        System.out.printf("Original List : %s, filtered list : %s %n", strList, filtered);

    }

    @Test
    public void fun10(){
        // 将字符串换成大写并用逗号链接起来
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.","Canada");
        String G7Countries = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(", "));
        System.out.println(G7Countries);


    }

    @Test
    public void fun11(){

    }

}
