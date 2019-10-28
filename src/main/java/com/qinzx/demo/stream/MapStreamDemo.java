package com.qinzx.demo.stream;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 映射流：
 * <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);
 * 通过前面的学习我们知道mapper是一个映射函数，它和map()方法也一样也会返回一个新流，我们把返回的新流称为映射流。
 * 我们提供的映射函数会处理原始流中的每一个元素，而映射流中包含了所有经过我们映射函数处理后产生的新元素。
 * 我们来看一下源码对flatMap()的注释:The flatMap() operation has the effect of applying a one-to-many
 * transformation to the elements of the stream, and then flattening the resulting elements into a new stream.
 * 大意就是：flatMap()操作能把原始流中的元素进行一对多的转换，并且将新生成的元素全都合并到它返回的流里面。
 * 根据我们所学的知识，他的这种一对多的转换功能肯定就是映射函数提供的，这一点没有疑问吧！然后源码的注释上面还提供了一个例子，
 * 通过注释加例子，我相信大家都能非常清楚地理解flatMap()了。
 *
 * @ClassName: MapStreamDemo
 * @Author qinzx
 * @Date 2019/07/11 16:42
 * @Copyright (C) 杭州同基汽车科技有限公司
 */
public class MapStreamDemo {

    //玩家使用的英雄以及当前获得的金币数
    static class HeroPlayerGold {
        /** 使用的英雄名字 */
        private String hero;
        /** 玩家的ID */
        private String player;
        /** 获得的金币数 */
        private int gold;
        /** 得分 **/
        private BigDecimal score;
        public HeroPlayerGold() {

        }
        public HeroPlayerGold(String hero, String player, int gold) {
            this.hero = hero;
            this.player = player;
            this.gold = gold;
        }

        public String getHero() {
            return hero;
        }

        public void setHero(String hero) {
            this.hero = hero;
        }

        public String getPlayer() {
            return player;
        }

        public void setPlayer(String player) {
            this.player = player;
        }

        public int getGold() {
            return gold;
        }

        public void setGold(int gold) {
            this.gold = gold;
        }

        public BigDecimal getScore() {
            return score;
        }

        public void setScore(BigDecimal score) {
            this.score = score;
        }

        //省略toString
    }

    //玩家获得的金币数
    static class Gold {
        /**
         * 获得的金币数
         */
        private int gold;

        public Gold(int gold) {
            this.gold = gold;
        }

        public int getGold() {
            return gold;
        }

        public void setGold(int gold) {
            this.gold = gold;
        }
        //省略get/set/toString
    }
    public static void main(String[] args) {
        learnMap2th();
    }

    private static void learnMap2th() {
        List<HeroPlayerGold> lists = new ArrayList<>();
        lists.add(new HeroPlayerGold("盖伦", "RNG-Letme", 100));
        lists.add(new HeroPlayerGold("诸葛亮", "RNG-Xiaohu", 300));
        lists.add(new HeroPlayerGold("露娜", "RNG-MLXG", 300));
        lists.add(new HeroPlayerGold("狄仁杰", "RNG-UZI", 500));
        lists.add(new HeroPlayerGold("牛头", "RNG-Ming", 500));

        //计算团队经济
        int teamMoney = lists.stream()
                .map(player -> new Gold(player.getGold()))//note1
                .mapToInt(Gold::getGold)
                .reduce(0, (a, b) -> a + b);
        System.out.println("团队经济：" + teamMoney);//1700


        //计算团队经济2
        double teamMoney2 = lists.stream()
                .mapToDouble(HeroPlayerGold::getGold)
                .reduce(0, (a, b) -> a + b);
        System.out.println("团队经济：" + teamMoney2);//1700.0
    }

    private static void learnFlatMap() {
        //(广州  深圳  上海  北京)的全拼的一些组合,下面我们就把每一个城市都划分一下
        List<String> citys = Arrays.asList("GuangZhou ShangHai", "GuangZhou ShenZhen",
                "ShangHai ShenZhen", "BeiJing ShangHai", "GuangZhou BeiJing", "ShenZhen BeiJing");

        //这里打印的数组对应的地址
        citys.stream().map(mCitys -> Arrays.stream(mCitys.split(" "))).forEach(System.out::println);//note1

        System.out.println();

        //流里面的元素还是一个数组
        citys.stream()
                .map(mCities -> Arrays.stream(mCities.split(" ")))//流里面的每个元素还是数组
                .forEach(cities ->cities.forEach(city-> System.out.print(city+" ")));//note2

        System.out.println();
        System.out.println();

        //直接一个flatMap()就把数组合并到映射流里面了
        citys.stream().flatMap(mCities->Arrays.stream(mCities.split(" "))).forEach(System.out::println);//note3

        System.out.println();

        //使用distinct()方法去重！
        citys.stream().flatMap(mCities->Arrays.stream(mCities.split(" "))).distinct().forEach(System.out::println);//note4
    }

    private static void treeMap() {
        //list分组并排序，输出有序的TreeMap
        List<HeroPlayerGold> manageExpensesDetailRatios = new ArrayList<>();
        HeroPlayerGold manageExpensesRatioDetailDTO = new HeroPlayerGold();
        manageExpensesRatioDetailDTO.setGold(1);
        manageExpensesRatioDetailDTO.setScore(new BigDecimal("500.00"));
        manageExpensesDetailRatios.add(manageExpensesRatioDetailDTO);
        HeroPlayerGold manageExpensesRatioDetailDTO1 = new HeroPlayerGold();
        manageExpensesRatioDetailDTO1.setGold(2);
        manageExpensesRatioDetailDTO1.setScore(new BigDecimal("300.00"));
        manageExpensesDetailRatios.add(manageExpensesRatioDetailDTO1);
        HeroPlayerGold manageExpensesRatioDetailDTO2 = new HeroPlayerGold();
        manageExpensesRatioDetailDTO2.setGold(3);
        manageExpensesRatioDetailDTO2.setScore(new BigDecimal("9000.00"));
        manageExpensesDetailRatios.add(manageExpensesRatioDetailDTO2);
        List<HeroPlayerGold> collect = manageExpensesDetailRatios.stream().sorted(Comparator.comparing(HeroPlayerGold::getScore)).collect(Collectors.toList());
        System.out.println(collect);
        Map<BigDecimal, List<HeroPlayerGold>> expensesEndMap = manageExpensesDetailRatios.stream().collect(Collectors.groupingBy(HeroPlayerGold::getScore, TreeMap::new,Collectors.toList()));
        System.out.println(expensesEndMap);
        for (Map.Entry<BigDecimal, List<HeroPlayerGold>> entry : expensesEndMap.entrySet()) {
            System.out.println(entry.getKey());
        }
    }
}