package data.structures._6_sort;

/**
 * 冒泡排序
 */
public class BubbleSort {

    public static void main(String[] args) {
        // int arr[] = {3, 9, -1, 10, 20};
        //
        // System.out.println("排序前");
        // System.out.println(Arrays.toString(arr));

        // 为了容量理解，我们把冒泡排序的演变过程，给大家展示

        // 测试一下冒泡排序的速度O(n^2), 给80000个数据，测试
        // 创建要给80000个的随机的数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
        }

        long start = System.currentTimeMillis();
        bubbleSort(arr); // 测试冒泡排序
        long end = System.currentTimeMillis();
        System.out.println("end-start = " + (end - start));
        // System.out.println("排序后");
        // System.out.println(Arrays.toString(arr));

        /* 手动模拟每次排序
         *
         * // 第二趟排序，就是将第二大的数排在倒数第二位
         *
         * for (int j = 0; j < arr.length - 1 - 1 ; j++) {
         * // 如果前面的数比后面的数大，则交换
         * if (arr[j] > arr[j + 1]) {
         * temp = arr[j];
         * arr[j] = arr[j + 1];
         * arr[j + 1] = temp;
         * }
         * }
         *
         * System.out.println("第二趟排序后的数组");
         * System.out.println(Arrays.toString(arr));
         *
         *
         * // 第三趟排序，就是将第三大的数排在倒数第三位
         *
         * for (int j = 0; j < arr.length - 1 - 2; j++) {
         * // 如果前面的数比后面的数大，则交换
         * if (arr[j] > arr[j + 1]) {
         * temp = arr[j];
         * arr[j] = arr[j + 1];
         * arr[j + 1] = temp;
         * }
         * }
         *
         * System.out.println("第三趟排序后的数组");
         * System.out.println(Arrays.toString(arr));
         *
         * // 第四趟排序，就是将第4大的数排在倒数第4位
         *
         * for (int j = 0; j < arr.length - 1 - 3; j++) {
         * // 如果前面的数比后面的数大，则交换
         * if (arr[j] > arr[j + 1]) {
         * temp = arr[j];
         * arr[j] = arr[j + 1];
         * arr[j + 1] = temp;
         * }
         * }
         *
         * System.out.println("第四趟排序后的数组");
         * System.out.println(Arrays.toString(arr));
         */

    }


    /**
     * (1) 一共进行 数组的大小-1 次 大的循环
     * (2)每一趟排序的次数在逐渐的减少，因为每一次都会把本轮最大值移动到后面
     * (3) 如果我们发现在某趟排序中，没有发生一次交换， 可以提前结束冒泡排序。这个就是优化
     */
    public static void bubbleSort(int[] arr) {
        // 冒泡排序 的时间复杂度 O(n^2), 自己写出
        int temp = 0; // 临时变量
        boolean flag = false; // 标识变量，表示是否进行过交换
        for (int i = 0; i < arr.length - 1; i++) {

            for (int j = 0; j < arr.length - 1 - i; j++) {
                // 如果前面的数比后面的数大，则交换
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            // System.out.println("第" + (i + 1) + "趟排序后的数组");
            // System.out.println(Arrays.toString(arr));

            if (!flag) { // 在一趟排序中，一次交换都没有发生过
                break;
            } else {
                flag = false; // 重置flag!!!, 进行下次判断
            }
        }
    }

}
