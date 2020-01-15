/**
 * @ClassName DivideAndConquerAlgorithm
 * @Description 分治算法
 * @Author wangxl
 * @Date 2020/1/6 22:10
 **/
public class DivideAndConquerAlgorithm {
    public static void main(String[] args) {
        hanoiTower(4, 'A', 'B', 'C');
    }

    /**
     * 汉诺塔游戏
     *
     * @param number 层数
     * @param a      A盘
     * @param b      B盘
     * @param c      C盘
     */
    public static void hanoiTower(int number, char a, char b, char c) {
        if (number == 1) {
            // 当一个盘子时从A->C
            System.out.println("第" + number + "个盘子：从" + a + "到" + c);
        } else {
            // 当盘子不为一时
            // 从A->B
            hanoiTower(number - 1, a, c, b);
            System.out.println("第" + number + "个盘子：从" + a + "到" + c);
            // 从B->C
            hanoiTower(number - 1, b, a, c);
        }
    }
}
