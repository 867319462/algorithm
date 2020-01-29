package prim;

import java.util.Arrays;

/**
 * @ClassName PrimAlgorithm
 * @Description 普利姆算法
 * @Author WangXL
 * @Date 2020/1/19 21:13
 **/
public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = {'Q', 'W', 'E', 'R', 'T', 'Y'};
        int number = data.length;
        int[][] weight = {{100, 10, 7, 16, 100, 100, 100},
                {10, 100, 7, 8, 10, 100},
                {7, 7, 100, 10, 100, 13},
                {16, 8, 10, 100, 7, 7},
                {100, 10, 100, 7, 100, 10},
                {100, 100, 13, 7, 10, 100}};
        Graph graph = new Graph(number);
        MinTree minTree = new MinTree();
        minTree.createTree(graph, number, data, weight);
        minTree.showTree(graph);
        minTree.prim(graph, 0);
    }
}

/**
 * 最小生成树
 */
class MinTree {
    /**
     * 创建最小生成树
     *
     * @param graph  图对象
     * @param number 图的节点数量
     * @param data   图的节点
     * @param weight 图的边，邻接矩阵
     */
    public void createTree(Graph graph, int number, char[] data, int[][] weight) {
        for (int i = 0; i < number; i++) {
            graph.data[i] = data[i];
            System.arraycopy(weight[i], 0, graph.weight[i], 0, number);
        }
    }

    /**
     * 遍历邻接矩阵
     *
     * @param graph 图对象
     */
    public void showTree(Graph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 普利姆算法
     *
     * @param graph 图对象
     * @param start 开始的结点
     */
    public void prim(Graph graph, int start) {
        // 创建一个访问数组
        int[] visited = new int[graph.number];
        // 访问过的标记为1
        visited[start] = 1;
        // 顶点的坐标
        int h1 = -1;
        int h2 = -1;
        // 边的长度
        int minWeight = 100;
        for (int k = 1; k < graph.number; k++) {
            // i 访问过的结点
            for (int i = 0; i < graph.number; i++) {
                //确定每一次生成的子图，和哪个结点的距离比较近
                // j 未访问的结点
                for (int j = 0; j < graph.number; j++) {
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值为：" + minWeight);
            visited[h1] = 1;
            visited[h2] = 1;
            minWeight = 100;
        }
    }
}

/**
 * 图
 */
class Graph {
    /**
     * 图的结点个数
     */
    int number;
    /**
     * 图的结点
     */
    char[] data;
    /**
     * 边，邻接矩阵
     */
    int[][] weight;

    public Graph(int number) {
        this.number = number;
        this.data = new char[number];
        this.weight = new int[number][number];
    }
}
