package data.structures._10_graph;



import java.util.ArrayList;

public class GraphTest {
    public static void main(String[] args) {
        int n = 5;
        String vertexs[] = {"A", "B", "C", "D", "E"};
        //创建图对象
        Graph graph = new Graph(n);
        //循环的添加顶点
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }

        //添加边
        //A-B A-C B-C B-D B-E
        graph.insertEdge(0, 1, 1); // A-B
        graph.insertEdge(0, 2, 1); //
        graph.insertEdge(1, 2, 1); //
        graph.insertEdge(1, 3, 1); //
        graph.insertEdge(1, 4, 1); //

        graph.insertEdge(2, 4, 1); //

        graph.showGraph();
        graph.dfs();

    }

}

/**
 * 邻接矩阵 图
 */
class Graph {
    private ArrayList<String> vertexList;//存储顶点集合
    private int[][] edges; //存储图对应的领结矩阵
    private int numOfEdges;//表示边的数目

    private boolean[] isVisited;//记录节点是否被访问

    /**
     * @param n 顶点数
     */
    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    public void dfs() {
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    /**
     * 深度优先遍历DFS
     *
     * @param isVisited
     * @param i         表示第几个节点   换算到行列式中第几行  从0开始  即第一行
     */
    private void dfs(boolean[] isVisited, int i) {
        //首先我们访问该结点,输出
        System.out.print(getValueByIndex(i) + "->");
        //将结点设置为已经访问
        isVisited[i] = true;

        //查找结点i的第一个邻接结点w
        int w = getFirstNeighbor(i);
        while (w != -1) {//说明有

            if (!isVisited[w]) {
                dfs(isVisited, w);
            }else
            //如果w结点已经被访问过
            w = getNextNeighbor(i, w);
        }
    }

    /**
     * 得到第一个邻接结点的下标 w
     *
     * @param index
     * @return 如果存在就返回对应的下标，否则返回-1
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }


    /**
     * 根据前一个邻接结点的下标来获取下一个邻接结点
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 插入结点
     */
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     *
     * @param v1     表示点的下标即使第几个顶点  "A"-"B" "A"->0 "B"->1
     * @param v2     第二个顶点对应的下标
     * @param weight 表示
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    //下面写几个图的常用方法
    //返回结点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }


    // 得到边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回结点i(下标)对应的数据 0->"A" 1->"B" 2->"C"
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    // 显示图对应的矩阵
    public void showGraph() {

        System.out.print("\t");
        for (String s : vertexList) {
            System.out.print(s + "\t");
        }
        System.out.println();

        for (int i = 0; i < edges.length; i++) {
            System.out.print(vertexList.get(i) + "\t");
            for (int j = 0; j < edges[i].length; j++) {
                System.out.print(edges[i][j] + "\t");
            }
            System.out.println();
        }
    }


}
