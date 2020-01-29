package kruskal;

/**
 * @ClassName KruskalAlgorithm
 * @Description 克鲁斯卡尔算法
 * @Author WangXL
 * @Date 2020/1/27 11:54
 **/
public class KruskalAlgorithm {
    /**
     * 边的数量
     */
    private int edgeNum;
    /**
     * 顶点
     */
    private char[] vertexs;
    /**
     * 邻接矩阵
     */
    private int[][] matrix;
    /**
     * 不能联通的边
     */
    private static final int N = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                {0, 6, 10, N, N, N, N},
                {6, 0, 8, 10, 13, 9, 13},
                {10, 8, 0, N, 11, N, N},
                {N, 10, N, 0, N, N, N, 7},
                {N, 13, 11, N, 0, 10, N},
                {N, 9, N, N, 10, 0, N},
                {N, 13, N, 7, N, N, 0},
        };

        KruskalAlgorithm kruskalAlgorithm = new KruskalAlgorithm(vertexs, matrix);
        kruskalAlgorithm.print();
        kruskalAlgorithm.kruskal();
    }

    public KruskalAlgorithm(char[] vertexs, int[][] matrix) {
        int length = vertexs.length;

        // 初始化顶点数组
        this.vertexs = new char[length];
        System.arraycopy(vertexs, 0, this.vertexs, 0, length);

        // 初始化连接顶点数据
        this.matrix = new int[length][length];
        System.arraycopy(matrix, 0, this.matrix, 0, length);

        // 统计边的数量
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (matrix[i][j] != N && matrix[i][j] != 0) {
                    edgeNum++;
                }
            }
        }
    }

    /**
     * 克鲁斯卡尔算法
     */
    private void kruskal() {
        // 下标标记
        int index = 0;
        // 终点数组
        int[] ends = new int[edgeNum];
        // 结果边数组
        Edge[] results = new Edge[edgeNum];

        //获取所有边
        Edge[] edges = getEdges();
        //对所有边进行排序
        sortDate(edges);

        for (int i = 0; i < edgeNum; i++) {
            int startIndex = getPosition(edges[i].getStart());
            int endIndex = getPosition(edges[i].getEnd());

            int des1 = getEnds(ends, startIndex);
            int des2 = getEnds(ends, endIndex);

            if (des1 != des2) {
                ends[des1] = des2;
                results[index++] = edges[i];
            }
        }

        // 打印所有用到的所有边
        for (Edge ret : results) {
            if (ret != null) {
                System.out.println(ret);
            }
        }
    }

    private void print() {
        System.out.println("邻接矩阵为：");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%12d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 对所有边进行排序
     *
     * @param edges 所有边
     */
    private void sortDate(Edge[] edges) {
        if (edges == null) {
            return;
        }
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].getWeight() > edges[j + 1].getWeight()) {
                    Edge temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 获取顶点下标
     *
     * @param ch 顶点名称
     * @return 顶点下标
     */
    private int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取所有边
     *
     * @return 所有边的数组
     */
    private Edge[] getEdges() {
        Edge[] edges = new Edge[edgeNum];
        int index = 0;
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                if (matrix[i][j] != N && matrix[i][j] != 0) {
                    edges[index] = new Edge(vertexs[i], vertexs[j], matrix[i][j]);
                    index++;
                }
            }
        }
        return edges;
    }

    /**
     * 获取下标为i的顶点的终点，用于后面判断两个顶点的终点是否相同
     *
     * @param ends 记录各个顶点对应的终点是哪个
     * @param i    传入的顶点对应的下标
     * @return 下标为i的这个顶点的对应的终点的下标
     */
    private int getEnds(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }
}

/**
 * 边
 */
class Edge {
    /**
     * 起始顶点
     */
    private char start;
    /**
     * 结束顶点
     */
    private char end;
    /**
     * 边的权值
     */
    private int weight;

    public Edge(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "kruskal.Edge{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }

    public char getStart() {
        return start;
    }

    public void setStart(char start) {
        this.start = start;
    }

    public char getEnd() {
        return end;
    }

    public void setEnd(char end) {
        this.end = end;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
