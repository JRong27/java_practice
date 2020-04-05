package homework;

import java.util.Scanner;

public class MathProof {
    private static void mathTest() {
        long sum = 0;

        System.out.println("（基础部分）验证一些相续正整数的立方和正好等于另一个整数的立方。");
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入第一个正整数：");
        int a = scanner.nextInt();
        System.out.print("请输入最后一个正整数：");
        int b = scanner.nextInt();
        System.out.print("请输入需验证的正整数：");
        int n = scanner.nextInt();

        for (int i = a; i <= b; i++) {
            sum += i * i * i;
        }

        if (sum == n * n * n) {
            System.out.println("等式成立");
        } else {
            System.out.println("等式不成立");
        }
    }

    public static void main(String[] args) {
        mathTest();
    }
}
