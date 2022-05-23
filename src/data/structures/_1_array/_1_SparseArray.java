package data.structures._1_array;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * 稀疏数组
 */
public class _1_SparseArray {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 原始数据
        // 模拟 11*11 棋盘，0 表示没有棋子，1 表示黑子，2 表示白子
        int[][] chessArr = new int[11][11];
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;
        chessArr[4][5] = 2;
        chessArr[5][5] = 2;

        for (int[] row : chessArr) {
            for (int item : row) {
                System.out.printf("%d\t", item);
            }
            System.out.println();
        }

        //转换成稀疏数组
        //1、查找有多少个值
        int num = 0;
        for (int row = 0; row < chessArr.length; row++) {
            for (int col = 0; col < chessArr[row].length; col++) {
                int i = chessArr[row][col];
                if (i != 0) {
                    num++;
                }
            }
        }
        //2、创建稀疏数组
        int[][] sparseArr = new int[num + 1][3];

        //3、给稀疏数组赋值
        sparseArr[0][0] = chessArr.length;
        sparseArr[0][1] = chessArr[0].length;
        sparseArr[0][2] = num;

        //4、遍历二维数组保存到稀疏数组中
        int count = 1;//用于记录是第几个非0数据
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[i].length; j++) {
                int item = chessArr[i][j];
                if (item != 0) {
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = item;
                    count++;
                }
            }
        }

        System.out.println("压缩数据");
        for (int[] ints : sparseArr) {
            System.out.println(Arrays.toString(ints));
        }

        //写出到文件
        String filePath = "D:\\map.data";
        ObjectOutput output =   new ObjectOutputStream(Files.newOutputStream(Paths.get(filePath)));
        output.writeObject(sparseArr);
        output.close();


        ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(Paths.get(filePath)));
        int[][] o = (int[][])objectInputStream.readObject();
        objectInputStream.close();

        sparseArr = o;
        //将稀疏数组 --》 恢复成 原始的二维数组
		/*
		 *  1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如上面的  chessArr2 = int [11][11]
			2. 在读取稀疏数组后几行的数据，并赋给 原始的二维数组 即可.
		 */

        //1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];

        for (int i = 1; i <= sparseArr[0][2]; i++) {
            int row = sparseArr[i][0];
            int col = sparseArr[i][1];
            int val = sparseArr[i][2];
            chessArr2[row][col] = val;
        }
        System.out.println("恢复后的数据");
        for (int[] ints : chessArr2) {
            System.out.println(Arrays.toString(ints));
        }
    }

}
