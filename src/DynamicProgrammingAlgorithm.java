import java.util.Arrays;

/**
 * @ClassName DynamicProgrammingAlgorithm
 * @Description 动态规划算法(01背包)
 * @Author WangXL
 * @Date 2020/1/11 20:19
 **/
public class DynamicProgrammingAlgorithm {
    public static void main(String[] args) {
        // 物品的重量
        int[] weight = {1, 4, 3, 2, 5};
        // 物品的价值
        int[] value = {1500, 3000, 3000, 1700, 4000};
        // 背包的容量
        int capacity = 10;
        // 物品的数量
        int number = value.length;

        // 新建一个表，行为物品的数量+1，列为背包的容量+1
        int[][] table = new int[number + 1][capacity + 1];
        // 建一个表，来记录放入的物品
        int[][] path = new int[number + 1][capacity + 1];

        // 初始化，将背包容量为0时，所有行全部置空
        for (int i = 0; i < table.length; i++) {
            table[i][0] = 0;
        }
        // 初始化，将物品数量为0时，所有列全部置空
        Arrays.fill(table[0], 0);

        for (int i = 1; i < table.length; i++) {
            for (int j = 1; j < table[0].length; j++) {
                if (weight[i - 1] <= j && table[i - 1][j] < (value[i - 1] + table[i - 1][j - weight[i - 1]])) {
                    // 当物品的重量大于等于背包容量且自身的价值与剩余空间最大价值之和大于上一行的值时
                    table[i][j] = value[i - 1] + table[i - 1][j - weight[i - 1]];
                    // 放入物品的路径做一个标志
                    path[i][j] = 1;
                } else {
                    // 使其等于上一行的值
                    table[i][j] = table[i - 1][j];
                }
            }
        }

        // 行的最大下标
        int row = path.length - 1;
        // 列的最大下标
        int column = path[0].length - 1;
        // 保持行、列的下标都大于0
        while (row > 0 && column > 0) {
            // 找被标志路径的物品
            if (path[row][column] == 1) {
                System.out.printf("第%d个物品放进背包\n", row);
                // 找到后该物品的重量减去
                column -= weight[row - 1];
            }
            // 减少行
            row--;
        }

        int max = 0;
        for (int i = 0; i < table[0].length; i++) {
            if (max < table[table.length - 1][i]) {
                max = table[table.length - 1][i];
            }
        }
        System.out.println("最大值为：" + max);
    }
}
