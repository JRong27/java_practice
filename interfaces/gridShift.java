import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class gridShift {

    public static void main(String[] args) {
        int[][] grid = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("Before:");
        for (int i = 0; i < 3; i++) {
            System.out.println(Arrays.toString(grid[i]));
        };
        int k = 5;


        int[] nums = new int[grid.length * grid[0].length];
        // 外层行数，内层列数
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                // 可省去多于的重复步骤
                // 若k < num.length,则移动 k 位
                // 若k > num.length,则只移动余数位；
                // 若k = num.length,则不动。
                k = k % nums.length;
                nums[k] = grid[i][j];
                k++;
            }
        }
        // 新的顺序已经排列好，要转成集合类的方式返回
        int n = 0;
        List<List<Integer>> newGrid = new ArrayList<>(grid.length);
        for(int i = 0; i < grid.length; i++) {
            List<Integer> temp = new ArrayList<>(grid[0].length);
            for(int j = 0; j < grid[0].length; j++) {
                temp.add(nums[n++]);
            }
            newGrid.add(temp);
        }


        System.out.println("After:");
        for (int j = 0; j < newGrid.size(); j++) {
            // newGrid 的元素是 List<Integer> 类型
            System.out.println(newGrid.get(j));
        }
    }

}
