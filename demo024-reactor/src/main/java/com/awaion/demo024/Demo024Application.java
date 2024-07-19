package com.awaion.demo024;

import ch.qos.logback.core.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.function.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@SpringBootApplication
public class Demo024Application {

    public static void main(String[] args) {
//		SpringApplication.run(Demo024Application.class, args);

        // 函数式接口:自己创建实现类
        System.out.println("函数式接口:自己创建实现类:" + new FunctionalInterface1Impl().sum(1, 2));

        // 函数式接口:匿名函数实现完全写法
        FunctionalInterface1 functionalInterface1 = new FunctionalInterface1() {
            @Override
            public int sum(int i, int j) {
                return i + j;
            }
        };
        System.out.println("函数式接口:匿名函数实现完全写法:" + functionalInterface1.sum(2, 3));

        // 函数式接口:匿名函数 lambda 表达式写法
        FunctionalInterface1 functionalInterface12 = (x, y) -> {
            System.out.println("lambda 表达式");
            return x + y;
        };
        System.out.println("函数式接口:匿名函数 lambda 表达式写法:" + functionalInterface12.sum(2, 3));

        // 函数式接口:只有一行实现的极简写法
        FunctionalInterface2 functionalInterface2 = () -> 1;
        System.out.println("函数式接口:只有一行实现的极简写法:" + functionalInterface2.add());
        System.out.println("函数式接口:只有一行实现的极简写法:不声明实现:" + ((FunctionalInterface2) () -> -1).subtract());

        // 声明式函数式接口:规范检查
        FunctionalInterface3 functionalInterface3 = (x, y) -> x * y;
        System.out.println("声明式函数式接口:规范检查:" + functionalInterface3.multiply(4, 5));
        System.out.println("声明式函数式接口:规范检查:不声明实现:" + ((FunctionalInterface3) (i, j) -> i * j).multiply(6, 7));

        // Java8 语法糖:lambda 表达式:(参数表) -> {方法体}
        // 函数式接口可以用 lambda 表达式

        System.out.println("==============================================");

        // 创建集合
        var names = new ArrayList<String>();
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");
        names.add("David");

        // 集合排序
        names.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println("排序后集合:" + names);


        names.sort((o1, o2) -> o1.compareTo(o2));
        System.out.println("排序后集合:lambda写法:" + names);

        names.sort(String::compareTo);
        System.out.println("排序后集合:调用默认定义方法:" + names);

        System.out.println("==============================================");

        // 消费者 有入参,无出参
        BiConsumer<String, String> consumer = (a, b) -> {
            System.out.println("消费者 有入参,无出参 " + a + "," + b);
        };
        // 调用
        consumer.accept("q", "a");

        // 多功能函数 有入参,有出参
        Function<String, String> function = (String x) -> x.replace(" ", "").toUpperCase(Locale.ROOT);
        System.out.println("多功能函数 有入参,有出参:" + function.apply("q a  W"));

        // 多功能函数 有两个入参,有出参 传递两个以上参数要用对象
        BiFunction<String, Integer, String> biFunction = (a, b) -> a + b;
        System.out.println("多功能函数 有两个入参,有出参:" + biFunction.apply("q", 9));

        // 断言
        Predicate<String> predicate = StringUtil::isNullOrEmpty;
        System.out.println(predicate.negate().test("qa z"));

        // 两个参数断言 传递两个以上参数要用对象
        BiPredicate<String, String> biPredicate = (a, b) -> StringUtil.isNullOrEmpty(a) && StringUtil.isNullOrEmpty(b);
        System.out.println(biPredicate.test(" ", " "));

        // 提供者 无入参,有出参
        Supplier<String> supplier = () -> UUID.randomUUID().toString();
        System.out.println("提供者 无入参,有出参:" + supplier.get());

        // 普通函数 无入参,无出参
        Runnable runnable = () -> System.out.println("这是一个线程");
        runnable.run();

        System.out.println("================================================");

        // 函数式和正常方式实现常规业务逻辑对比
        checkNumber(() -> "66");
        checkNumber(() -> "77a");
        checkNumber2("66");
        checkNumber2("77");

        System.out.println("================================================");

        List<Person> list = List.of(
                new Person("张 三", "男", 16),
                new Person("王 五", "女", 20),
                new Person("李 四", "男", 22),
                new Person("孙 七", "女", 15),
                new Person("钱 六儿", "女", 35));

        // Stream API 处理方式
        Stream<String> sorted = list.stream()
                .limit(7) // 限制条数
//                .parallel() // 并发执行,有状态数据会有线程安全问题,一般处理无状态数据
//                .filter(person -> person.age > 18) // 过滤条件
                .takeWhile(person -> person.age > 15) // 顺序过滤,不满足条件就直接退出
                .peek(person -> System.out.println("filter peek:" + person)) // 简单打印
                .map(person -> person.getName() + person.getAge()) // 取出结果
                .peek(s -> System.out.println("map peek:" + s)) // 打印取出结果
                .flatMap(ele -> { // 对每一个数据展开
                    String[] s = ele.split(" ");
                    return Arrays.stream(s);
                })
                .distinct() // 去重
                .sorted(String::compareTo); // 排序
        System.out.println("lazy....终止方法不调用不会开始处理流");
        System.out.println("Stream API:" + sorted.toList());

    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    static class Person {
        private String name;
        private String gender;
        private Integer age;
    }

    /**
     * 使用函数式接口实现:判断奇偶数
     *
     * @param supplier
     */
    private static void checkNumber(Supplier<String> supplier) {
        // 验证是否一个数字
        Predicate<String> isNumber = str -> str.matches("-?\\d+(\\.\\d+)?");
        if (isNumber.test(supplier.get())) {
            // 把字符串变成数字
            Function<String, Integer> change = Integer::parseInt;
            Integer apply = change.apply(supplier.get());

            // 打印数字
            Consumer<Integer> consumer = integer -> {
                if (integer % 2 == 0) {
                    System.out.println("使用函数式接口实现:判断奇偶数:偶数：" + integer);
                } else {
                    System.out.println("使用函数式接口实现:判断奇偶数:奇数：" + integer);
                }
            };
            consumer.accept(apply);
        } else {
            System.out.println("使用函数式接口实现:判断奇偶数:非法的数字");
        }
    }

    /**
     * 正常实现:判断奇偶数
     *
     * @param param
     */
    private static void checkNumber2(String param) {
        boolean isNumeric = Pattern.matches("-?\\d+(\\.\\d+)?", param);
        if (isNumeric) {
            int parseInt = Integer.parseInt(param);
            if (parseInt % 2 == 0) {
                System.out.println("正常实现:判断奇偶数:偶数：" + parseInt);
            } else {
                System.out.println("正常实现:判断奇偶数:奇数：" + parseInt);
            }
        } else {
            System.out.println(":正常实现:判断奇偶数非法的数字");
        }
    }

    /**
     * 函数式接口:接口中有且只有一个未实现的方法
     */
    interface FunctionalInterface1 {
        int sum(int i, int j);
    }

    /**
     * 函数式接口:带有默认实现
     */
    interface FunctionalInterface2 {
        int subtract();

        default int add() {
            return 2;
        }
    }

    /**
     * 声明式函数式接口:规范检查
     */
    @FunctionalInterface
    interface FunctionalInterface3 {
        int multiply(int i, int j);
    }

    /**
     * 函数式接口实现
     */
    static class FunctionalInterface1Impl implements FunctionalInterface1 {
        @Override
        public int sum(int i, int j) {
            return i + j;
        }
    }

}
