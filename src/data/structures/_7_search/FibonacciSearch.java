package data.structures._7_search;

import java.util.Arrays;

/**
 * <pre>
 * 斐波拉契查找
 *  1、找到一个fib(k)-1 大于数组长度，定为临时数组长度
 *  2、对原始数组拷贝到临时数组中，不能填充的位数用最高位填充
 *  3、计算mid = low + f[k - 1] - 1;
 *      等于目标值直接返回
 *       小于  取F[k-1] - 1继续进行
 *       大于 取F[k-2] - 1继续进行
 *
 * <------------------F[k] - 1---------------------->
 * |low|--------------------|mid|--------------|high|
 * <--------F[k-1] - 1----->    <---F[k-2] - 1--->
 *
 *
 *   推导：
 *    F[k-1] - 1 + F[k-2] - 1 = F[k] - 2
 *    考虑mid占一位，临时数组长度  F[k] - 2 + 1 = F[k] - 1
  * </pre>
 */
public class FibonacciSearch {

    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};

        System.out.println("index=" + fibSearch(arr, 189));// 0

    }

    //因为后面我们mid=low+F(k-1)-1，需要使用到斐波那契数列，因此我们需要先获取到一个斐波那契数列
    //非递归方法得到一个斐波那契数列
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }



    /**
     *    编写斐波那契查找算法
     *    使用非递归的方式编写算法
     * @param a   数组
     * @param key 我们需要查找的关键码(值)
     * @return 返回对应的下标，如果没有-1
     */
    public static int fibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0; //表示斐波那契分割数值的下标
        int mid = 0; //存放mid值
        int f[] = fib(); //获取到斐波那契数列
        //获取到斐波那契分割数值的下标  获取 最小斐波拉契数-1 大于数组长度的值
        while (high > f[k] - 1) {
            k++;
        }//k=5  f[k] = 8  high=5

        //因为 f[k] 值 可能大于 a 的 长度，因此我们需要使用Arrays类，构造一个新的数组，并指向temp[]
        //不足的部分会使用0填充  [1, 8, 10, 89, 1000, 1234, 0, 0]
        int[] temp = Arrays.copyOf(a, f[k]);
        //实际上需求使用a数组最后的数填充 temp
        //举例:
        //temp = {1,8, 10, 89, 1000, 1234, 0, 0}  => {1,8, 10, 89, 1000, 1234, 1234, 1234,}
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }

        // 使用while来循环处理，找到我们的数 key
        while (low <= high) { // 只要这个条件满足，就可以找
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) { //我们应该继续向数组的前面查找(左边)
                high = mid - 1;
                //为甚是 k--
                //说明
                //1. 全部元素 = 前面的元素 + 后边元素
                //2. f[k] = f[k-1] + f[k-2]
                //因为 前面有 f[k-1]个元素,所以可以继续拆分 f[k-1] = f[k-2] + f[k-3]
                //即 在 f[k-1] 的前面继续查找 k--
                //即下次循环 mid = f[k-1-1]-1
                k--;
            } else if (key > temp[mid]) { // 我们应该继续向数组的后面查找(右边)
                low = mid + 1;
                //为什么是k -=2
                //说明
                //1. 全部元素 = 前面的元素 + 后边元素
                //2. f[k] = f[k-1] + f[k-2]
                //3. 因为后面我们有f[k-2] 所以可以继续拆分 f[k-1] = f[k-3] + f[k-4]
                //4. 即在f[k-2] 的前面进行查找 k -=2
                //5. 即下次循环 mid = f[k - 1 - 2] - 1
                k -= 2;
            } else { //找到
                //需要确定，返回的是哪个下标
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}
