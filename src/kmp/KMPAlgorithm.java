package kmp;

/**
 * @ClassName KMPAlgorithm
 * @Description KMP算法
 * @Author WangXL
 * @Date 2020/1/13 22:19
 **/
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "SKDHEIH EICUBLDKP EIUOJEIKPD";
        String str2 = "EIUOJEI";

        int[] kmpNext = kmpNext(str2);
        System.out.println(kmpSearch(str1, str2, kmpNext));
    }

    /**
     * KMP查找
     *
     * @param str1 目标字符串
     * @param str2 搜索字符串
     * @param next 部分匹配值表
     * @return 下标
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
        for (int i = 0, j = 0; i < str1.length(); i++) {
            System.out.println("i:" + i + " j:" + j);
            // KMP算法的核心点
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            // 有字符匹配上了就给j加1
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            // 当j等于str2的长度，说明完全匹配了
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    /**
     * 获取字符串的部分匹配值表
     *
     * @param dest 目标字符串
     * @return 部分匹配值表
     */
    public static int[] kmpNext(String dest) {
        // 创建一个与字符串长度相等的数组
        int[] next = new int[dest.length()];
        // 数组第一个永远为0
        next[0] = 0;
        for (int i = 1, j = 0; i < next.length; i++) {
            // 当字符不相等时 j等于数组中j-1的值
            // KMP算法的核心点
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            // 当条件成立时 j加1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            // 将数组对应位置改为j的值
            next[i] = j;
        }
        return next;
    }
}
