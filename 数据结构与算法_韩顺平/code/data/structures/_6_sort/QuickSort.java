package data.structures._6_sort;

import java.util.Arrays;

/**
 * 快速排序
 * LeetCode题解：https://leetcode.cn/problems/sort-an-array/solution/kuai-su-pai-xu-by-tinpo-mhyz/
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = new int[]{-9, 1, 2, 3, 4, 5, 6, 7, -9};
        int left = 0, right = arr.length - 1;
        System.out.println(Arrays.toString(arr));
        quickSort(arr, left, right);
        System.out.println(Arrays.toString(arr));
    }


    private static void quickSort(int[] nums, int left, int right) {

        if (left < right) {
            //找到坑的正确位置
            int pos = findPos(nums, left, right);
            quickSort(nums, left, pos - 1);
            quickSort(nums, pos + 1, right);

        }

    }

    /**
     * 首先将nums[l]保存到base中，这样nums[l]就被挖走了，我们从右往左找到第一个小于base的，交换到nums[l]，这样nums[r]又被挖了坑
     * 然后从左往右找到第一个大于base的交换到nums[r]，如此类推直到l>=r，最后将base放到nums[l]的位置，
     * nums[l]左侧都小于nums[l]，nums[l]右侧都大于nums[l] ,然后递归即可
     */
    private static int findPos(int[] nums, int left, int right) {

        int l = left;
        int r = right;
        int pivot = nums[left];//现把坑挖在左边第一个

        while (l < r) {
            while (nums[r] >= pivot && l < r) { //从有右边找到大于基准值，坑移动到右边r处   此处限定l < r防止越界
                r--;
            } //nums[r] < pivot

            if (l < r) {
                nums[l] = nums[r];
            }

            while (nums[l] <= pivot && l < r) { //从有左边找到小于基准值，坑移动到右边l处
                l++;
            } //nums[l] > pivot

            if (l < r) {
                nums[r] = nums[l];
            }
        }//l==r

        //填坑
        nums[l] = pivot;
        return l;
    }

}


