package data.algorithm._9_floyd;

import java.util.Arrays;

/**
 * **弗洛伊德算法**(Floyd)计算图中**各个顶点之间的最短路径**
 */
public class FloydAlgorithm {

    public static final int N = 65535;//表示不连通

    public static void main(String[] args) {
        // 测试看看图是否创建成功
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //创建邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];

        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};

        //创建 Graph 对象
        Graph graph = new Graph(vertex.length, matrix, vertex);

        //调用弗洛伊德算法
        graph.floyd();
        graph.show();


    }


}

// 创建图
class Graph {
    /**
     * 存放顶点的数组
     */
    private char[] vertex;
    /**
     * 保存，从各个顶点出发到其它顶点的距离，最后的结果，也是保留在该数组
     */
    private int[][] dis;
    /**
     * 保存到达目标顶点的前驱顶点
     */
    private int[][] pre;

    // 构造器

    /**
     * @param length 大小
     * @param matrix 邻接矩阵
     * @param vertex 顶点数组
     */
    public Graph(int length, int[][] matrix, char[] vertex) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];
        // 对pre数组初始化, 注意存放的是前驱顶点的下标
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    // 显示pre数组和dis数组
    public void show() {

        //为了显示便于阅读，我们优化一下输出
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        for (int k = 0; k < dis.length; k++) {
            // 先将pre数组输出的一行
            for (int i = 0; i < dis.length; i++) {
                System.out.print(vertex[pre[k][i]] + " ");
            }
            System.out.println();
            // 输出dis数组的一行数据
            for (int i = 0; i < dis.length; i++) {
                System.out.print("(" + vertex[k] + "到" + vertex[i] + "的最短路径是" + dis[k][i] + ") ");
            }
            System.out.println();
            System.out.println();

        }

    }

    /**
     * 弗洛伊德算法, 比较容易理解，而且容易实现
     */
    public void floyd() {
        int len = 0; //变量保存距离
        //对中间顶点遍历， mid 就是中间顶点的下标 [A, B, C, D, E, F, G]
        for (int mid = 0; mid < dis.length; mid++) { //
            //从start顶点开始出发 [A, B, C, D, E, F, G]
            for (int start = 0; start < dis.length; start++) {
                //到达end顶点 // [A, B, C, D, E, F, G]
                for (int end = 0; end < dis.length; end++) {
                    len = dis[start][mid] + dis[mid][end];// => 求出从i 顶点出发，经过 k中间顶点，到达 end 顶点距离
                    if (len < dis[start][end]) {//如果len小于 dis[i][end]
                        dis[start][end] = len;//更新距离
                        pre[start][end] = pre[mid][end];//更新前驱顶点
                    }
                }
            }
        }
    }

}
