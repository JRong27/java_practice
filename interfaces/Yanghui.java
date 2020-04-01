package java_0325;

import java.util.ArrayList;
import java.util.List;

public class Yanghui {
    public static void main(String[] args) {
        System.out.println(generate(5));
    }

    public static List<List<Integer>> generate(int numRows) {
        if (numRows <= 0) {
            return new ArrayList<>();
        }
        //建立高级的二维数组
        List<List<Integer>> result = new ArrayList<>();
        //1. 准备好已知固定值的一\二行
        List<Integer> firstLine = new ArrayList<>();
        firstLine.add(1);
        result.add(firstLine);
        //2. 考虑特殊情况的返回值
        if (numRows == 1) {
            return result;
        }
        List<Integer> secondLine = new ArrayList<>();
        firstLine.add(1);
        firstLine.add(1);
        result.add(secondLine);
        if (numRows == 2) {
            return result;
        }
        //3. 处理有规律的第 i 行情况
        for (int row = 3; row <= numRows; row++) {
            // 先知道前一行的值，才能算出当前行的值
            // 因为.get()里需要下标，而且下标从 0 开始，所以row - 1
            List<Integer> prevLine = result.get((row - 1) - 1);
            List<Integer> curLine = new ArrayList<>();
            // 从左往右依次填数字
            curLine.add(1);
            // 第row行有row列，该行第 1 行和第 row 列都是 1
            // 则循环执行前一行两肩元素相加的次数只有 row - 2 次
            // 区分 下标 和 col row
            for (int col = 2; col < row; col++) {
                int curNum = prevLine.get((col -1) - 1) + prevLine.get(col - 1);
                curLine.add(curNum);
            }
            curLine.add(1);
            result.add(curLine);
        }
        return result;
    }
}
