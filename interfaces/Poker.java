package java_0325;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class Card {
    private String rank;
    private String suit;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }
    public void setRank(String rank) {
        this.rank = rank;
    }
    public String getSuit() {
        return suit;
    }
    public void setSuit(String suit) {
        this.suit = suit;
    }

    @Override
    public String toString() {
//        return "Card{" +
//                "rank='" + rank + '\'' +
//                ", suit='" + suit + '\'' +
//                '}';
        return "[" + rank + suit + "]";
    }
}

public class Poker {

    private static List<Card> buyPoker() {
        List<Card> poker = new ArrayList<>(52);
        //设置花色种类
        String[] suits = {"♥", "♣", "♠", "♦"};
        //处理花色 suits[]
        for (int i = 0; i < 4; i++) {
            //处理点数 j
            // 转型 int => String
            // String.valueOf(j)  或   "" + j
            for (int j = 2; j <= 10; j++) {
                // 也可分开写成 先new对象 再add
                poker.add(new Card("" + j, suits[i]));
            }
            //Collection -> List-> Card -> poker
            // add方法 自动在向字符串数组中加入元素时让 i 更新，
            // suits[]就是一个长度可变的数组，最终会有4*13=52个元素
            poker.add(new Card("J", suits[i]));
            poker.add(new Card("Q", suits[i]));
            poker.add(new Card("K", suits[i]));
            poker.add(new Card("A", suits[i]));
        }
        //返回设定好的一套扑克
        return poker;
    }

    //基本的元素互换
    private static void swap(List<Card> poker, int i, int j) {
        //get（下标），返回元素
        Card t = poker.get(i);
        //set（下标，元素）
        poker.set(i, poker.get(j));
        poker.set(j, t);
    }

    private static void shuffle(List<Card> poker) {
        //生成随机位置
        Random random = new Random(20190905);
        //从后往前遍历List
        for (int i = poker.size() - 1; i > 0; i--) {
            //依次取出元素
            int r = random.nextInt(i);
            //交换当前和随机位置
            swap(poker, i, r);
        }
    }

    public static void main(String[] args) {
        //1. 创建一副牌
        List<Card> poker = buyPoker();
        System.out.println("买来的新牌：\n" + poker);

        //2. 洗牌 把List 中的元素随机打乱
        // 方法一： 直接使用 Colletion 已有方法最方便
        Collections.shuffle(poker);
        // 方法二：自己手动实现，从后往前遍历 List ，取出当前元素，再随机生成位置，
        //         最后交换当前元素和随机位置的元素
        shuffle(poker);
        System.out.println("洗过的牌：\n" + poker);

        //3. 发牌
        //   一个玩家就是一个 List ,存放各自的牌，
        //   于是就可以当做一套小List<Card>类型的poker。
        //   再把多个玩家的信息放到同一个 List 中，像二维数组，
        //   其一个元素（也是List<Card>类型）就是一个玩家的手牌。
        List<List<Card>> players = new ArrayList<>();
        players.add(new ArrayList<Card>());
        players.add(new ArrayList<Card>());
        players.add(new ArrayList<Card>());
        // 有3位玩家，每人5张牌，3人轮着发
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                List<Card> player = players.get(j);
                // remove 删除指定下标的元素，返回值表示删除的元素内容
                // 其返回值就是要给玩家的牌
                // 也可分两步来完成
                player.add(poker.remove(0));
            }
        }
        // 查看玩家手中的牌
        for (int i = 0; i < 3; i++) {
            System.out.println("玩家" + i + ":" + players.get(i));
        }
    }

}
