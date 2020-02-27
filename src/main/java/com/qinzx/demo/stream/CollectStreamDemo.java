package com.qinzx.demo.stream;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 收集：
 * 有时候我们也是需要从流中收集起一些元素，并以集合的方式返回，我们把这种反向操作称为收集。流API也给我们提供了相应的方法。
 *
 * @author qinzx
 * @date 2019/07/11 16:55
 */
public class CollectStreamDemo {
    //玩家使用的英雄以及当前获得的金币数
    static class HeroPlayerGold {
        /** 使用的英雄名字 */
        private String hero;
        /** 玩家的ID */
        private String player;
        /** 获得的金币数 */
        private int gold;


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

        @Override
        public String toString() {
            return "HeroPlayerGold{" +
                    "hero='" + hero + '\'' +
                    ", player='" + player + '\'' +
                    ", gold=" + gold +
                    '}';
        }
    }
    //出场的英雄
    static class Hero {
        /** 使用的英雄名字 */
        private String hero;

        public Hero(String hero) {
            this.hero = hero;
        }

        public String getHero() {
            return hero;
        }

        public void setHero(String hero) {
            this.hero = hero;
        }

        @Override
        public String toString() {
            return "Hero{" +
                    "hero='" + hero + '\'' +
                    '}';
        }
    }
    static class PlayerGold{
        /** 玩家的ID */
        private String player;
        /** 获得的金币数 */
        private int gold;
        PlayerGold(String player, Integer gold) {
            this.player = player;
            this.gold = gold;
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

        @Override
        public String toString() {
            return "PlayerGold{" +
                    "player='" + player + '\'' +
                    ", gold=" + gold +
                    '}';
        }
    }
    //测试类
    public static void main(String[] args) {
        learnCollect();
    }

    private static void learnCollect() {
        List<HeroPlayerGold> lists = new ArrayList<>();
        lists.add(new HeroPlayerGold("盖伦", "RNG-Letme", 100));
        lists.add(new HeroPlayerGold("诸葛亮", "RNG-Xiaohu", 300));
        lists.add(new HeroPlayerGold("露娜", "RNG-MLXG", 300));
        lists.add(new HeroPlayerGold("狄仁杰", "RNG-UZI", 500));
        lists.add(new HeroPlayerGold("牛头", "RNG-Ming", 500));

        List<PlayerGold> playerGolds = lists.stream()
                .map(plary -> new PlayerGold(plary.getPlayer(), plary.getGold()))
                .collect(Collectors.toList());
        System.out.println("============PlayerGold begin==============");
        playerGolds.forEach(System.out::println);
        System.out.println("============PlayerGold end================\n");

        Set<Hero> heroes = lists.stream().map(player -> new Hero(player.getHero())).collect(Collectors.toSet());
        System.out.println("============Hero begin==============");
        heroes.forEach(System.out::println);
        System.out.println("============Hero end================");
    }
    /**
     * 第二种收集方式，不使用lambda表达式
     * @author  qinzx
     * @date  2019/7/11 17:18
     * @param
     * @return  void
     */
    private static void learnCollect1() {
        List<HeroPlayerGold> lists = new ArrayList<>();
        lists.add(new HeroPlayerGold("盖伦", "RNG-Letme", 100));
        lists.add(new HeroPlayerGold("诸葛亮", "RNG-Xiaohu", 300));
        lists.add(new HeroPlayerGold("露娜", "RNG-MLXG", 300));
        lists.add(new HeroPlayerGold("狄仁杰", "RNG-UZI", 500));
        lists.add(new HeroPlayerGold("牛头", "RNG-Ming", 500));


        lists.stream().collect(new Supplier<HashSet<HeroPlayerGold>>() {
                                   @Override
                                   public HashSet<HeroPlayerGold> get() {
                                       return new HashSet<>();
                                   }
                               },//第一个参数
                new BiConsumer<HashSet<HeroPlayerGold>, HeroPlayerGold>() {
                    @Override
                    public void accept(HashSet<HeroPlayerGold> heroPlayerGolds, HeroPlayerGold heroPlayerGold) {
                        heroPlayerGolds.add(heroPlayerGold);
                    }
                },//第二个参数
                new BiConsumer<HashSet<HeroPlayerGold>, HashSet<HeroPlayerGold>>() {
                    @Override
                    public void accept(HashSet<HeroPlayerGold> heroPlayerGolds, HashSet<HeroPlayerGold> heroPlayerGolds2) {
                        heroPlayerGolds.addAll(heroPlayerGolds2);
                    }
                }//第三个参数
        ).forEach(System.out::println);
    }

    /**
     * 第二种收集方式，使用lambda表达式
     * @author  qinzx
     * @date  2019/7/11 17:18
     * @param
     * @return  void
     */
    private static void learnCollect2() {
        List<HeroPlayerGold> lists = new ArrayList<>();
        lists.add(new HeroPlayerGold("盖伦", "RNG-Letme", 100));
        lists.add(new HeroPlayerGold("诸葛亮", "RNG-Xiaohu", 300));
        lists.add(new HeroPlayerGold("露娜", "RNG-MLXG", 300));
        lists.add(new HeroPlayerGold("狄仁杰", "RNG-UZI", 500));
        lists.add(new HeroPlayerGold("牛头", "RNG-Ming", 500));


        lists.stream().collect(() -> new HashSet<>(),
                (set,elem)->set.add(elem),
                (setA,setB)->setA.addAll(setB)
        ).forEach(System.out::println);

    }

    /**
     * 第二种收集方式，使用lambda表达式,使用方法引用和构造函数引用来简化：
     * @author  qinzx
     * @date  2019/7/11 17:19
     * @param
     * @return  void
     */
    private static void learnCollect3() {
        List<HeroPlayerGold> lists = new ArrayList<>();
        lists.add(new HeroPlayerGold("盖伦", "RNG-Letme", 100));
        lists.add(new HeroPlayerGold("诸葛亮", "RNG-Xiaohu", 300));
        lists.add(new HeroPlayerGold("露娜", "RNG-MLXG", 300));
        lists.add(new HeroPlayerGold("狄仁杰", "RNG-UZI", 500));
        lists.add(new HeroPlayerGold("牛头", "RNG-Ming", 500));

        lists.stream().collect(HashSet::new,
                HashSet::add,
                HashSet::addAll
        ).forEach(System.out::println);
    }
}