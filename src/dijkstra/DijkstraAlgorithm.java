package dijkstra;

import java.util.Arrays;

/**
 * @ClassName DijkstraAlgorithm
 * @Description 地杰斯特拉算法
 * @Author WangXL
 * @Date 2020/1/28 12:17
 **/
public class DijkstraAlgorithm {
    /**
     * 不能联通的边
     */
    private static final int N = 65535;

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                {0, 6, 10, N, N, N, N},
                {6, 0, 8, 10, 13, 9, 13},
                {10, 8, 0, N, 11, N, N},
                {N, 10, N, 0, N, N, 7},
                {N, 13, 11, N, 0, 10, N},
                {N, 9, N, N, 10, 0, N},
                {N, 13, N, 7, N, N, 0},
        };
        Graph graph = new Graph(vertexs, matrix);
        graph.showGraph();
        graph.dijkstra(0);
        System.out.println(Arrays.toString(graph.visitedVertex.distance));
    }
}

/**
 * 图
 */
class Graph {
    /**
     * 顶点数组
     */
    private char[] vertexs;
    /**
     * 邻接矩阵
     */
    private int[][] matrix;
    /**
     * 顶点类
     */
    VisitedVertex visitedVertex;

    public Graph(char[] vertex, int[][] matrix) {
        this.vertexs = vertex;
        this.matrix = matrix;
    }

    /**
     * 显示图
     */
    public void showGraph() {
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%12d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 迪杰斯特拉算法
     *
     * @param index 出发结点的下标
     */
    public void dijkstra(int index) {
        visitedVertex = new VisitedVertex(vertexs.length, index);
        // 更新出发顶点到周围顶点的距离
        updateDate(index);
        for (int i = 1; i < vertexs.length; i++) {
            // 获取新的顶点
            index = visitedVertex.updateArray();
            // 更新当前顶点到周围顶点的距离
            updateDate(index);
        }
    }

    /**
     * 更新当前顶点到周围顶点的距离和周围顶点的前驱顶点
     *
     * @param index 顶点下标
     */
    public void updateDate(int index) {
        int length;
        for (int i = 0; i < matrix[index].length; i++) {
            // 获取出发顶点到当前顶点的距离 + 当前顶点到 j 顶点的距离
            length = visitedVertex.getDistance(index) + matrix[index][i];
            // 更新数据
            if (!visitedVertex.in(i) && length < visitedVertex.getDistance(i)) {
                // 更新距离
                visitedVertex.updateDistance(i, length);
                // 更新前驱
                visitedVertex.updatePrevious(index, i);
            }
        }
    }
}

/**
 * 已访问过的结点
 */
class VisitedVertex {
    /**
     * 访问标记：1为访问过；0为未访问过
     */
    public int[] access;
    /**
     * 前一个结点
     */
    public int[] previous;
    /**
     * 出发结点到其他结点的距离
     */
    public int[] distance;

    /**
     * 构造器
     *
     * @param length 顶点个数
     * @param index  目标顶点的下标
     */
    public VisitedVertex(int length, int index) {
        this.access = new int[length];
        this.previous = new int[length];
        this.distance = new int[length];
        // 将目标顶点到其他顶点的距离默认设置为最大值
        Arrays.fill(distance, 65535);
        access[index] = 1;
        this.distance[index] = 0;
    }

    /**
     * 判断顶点是否访问过
     *
     * @param index 顶点下标
     * @return 是否访问过
     */
    public boolean in(int index) {
        return access[index] == 1;
    }

    /**
     * 更新目标顶点到其他顶点的距离
     *
     * @param index  顶点下标
     * @param length 目标顶点到其他顶点的距离
     */
    public void updateDistance(int index, int length) {
        distance[index] = length;
    }

    /**
     * 更新顶点的前驱为index结点
     *
     * @param index 顶点下标
     * @param pre   前一个顶点
     */
    public void updatePrevious(int index, int pre) {
        previous[pre] = index;
    }

    /**
     * 获取目标顶点到顶点的距离
     *
     * @param index 顶点下标
     * @return 目标顶点到顶点的距离
     */
    public int getDistance(int index) {
        return distance[index];
    }

    /**
     * 更新当前顶点
     *
     * @return 新的顶点
     */
    public int updateArray() {
        int min = 65535;
        int index = 0;
        for (int i = 0; i < access.length; i++) {
            if (access[i] == 0 && distance[i] < min) {
                min = distance[i];
                index = i;
            }
        }
        access[index] = 1;
        return index;
    }
}
