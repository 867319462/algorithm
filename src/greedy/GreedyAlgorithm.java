package greedy;

import java.util.*;

/**
 * @ClassName GreedyAlgorithm
 * @Description 贪心算法
 * @Author WangXL
 * @Date 2020/1/15 21:02
 **/
public class GreedyAlgorithm {
    public static void main(String[] args) {
        // 存放电台的表
        Map<String, Set<String>> map = new HashMap<>(10);

        // 电台k1所包含的频道
        Set<String> k1 = new HashSet<>();
        k1.add("北京");
        k1.add("杭州");
        k1.add("济南");

        // 电台k2所包含的频道
        Set<String> k2 = new HashSet<>();
        k2.add("上海");
        k2.add("广州");
        k2.add("天津");

        // 电台k3所包含的频道
        Set<String> k3 = new HashSet<>();
        k3.add("深圳");
        k3.add("南京");

        // 电台k4所包含的频道
        Set<String> k4 = new HashSet<>();
        k4.add("北京");
        k4.add("天津");

        // 电台k5所包含的频道
        Set<String> k5 = new HashSet<>();
        k5.add("广州");
        k5.add("济南");

        // 将电台添加到表中
        map.put("K1", k1);
        map.put("K2", k2);
        map.put("K3", k3);
        map.put("K4", k4);
        map.put("K5", k5);

        // 目标频道
        Set<String> all = new HashSet<>();
        all.add("北京");
        all.add("上海");
        all.add("广州");
        all.add("深圳");
        all.add("天津");
        all.add("南京");
        all.add("杭州");
        all.add("济南");

        List<String> list = getStrings(map, all);

        // 输出最后的结果
        System.out.println(list);
    }

    /**
     * 贪心算法获取值
     *
     * @param map 所有的频道
     * @param all 目标频道
     * @return 电台集合
     */
    private static List<String> getStrings(Map<String, Set<String>> map, Set<String> all) {
        // 临时变量
        HashSet<String> temp = new HashSet<>();
        // 存储最大值
        Object[] max = new Object[2];
        // 相交为0的值
        List<String> zero = new ArrayList<>();
        // 需要的值
        List<String> list = new ArrayList<>();

        while (!all.isEmpty()) {
            max[0] = null;
            max[1] = null;
            zero.clear();

            // 如果所有表都没有了，就退出
            if (map.isEmpty()) {
                System.out.println("组合不全~~");
                break;
            }

            // 遍历所有电视台
            for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
                // 获取key
                String key = entry.getKey();
                // 将 entry 的值都给临时变量 temp
                temp.addAll(entry.getValue());
                // temp 与 all 取交集
                temp.retainAll(all);
                // 查看 temp 与 all 的交集数量
                int size = temp.size();

                if (size == 0) {
                    // 交集为0就放到集合中，等待删除
                    zero.add(key);
                } else {
                    // 与之前的集合比较，把大的放到max中
                    if (max[0] == null || Integer.parseInt(max[1].toString()) < size) {
                        max[0] = key;
                        max[1] = size;
                    }
                }
                // 清除临时变量中的数据
                temp.clear();
            }

            // 将最多交集的值添加到list中
            if (max[0] != null) {
                list.add(max[0].toString());
                all.removeAll(map.get(max[0].toString()));
            }

            // 删除与目标集合交集为0的值
            for (String string : zero) {
                map.remove(string);
            }
        }
        // 返回所有需要的电台
        return list;
    }
}
