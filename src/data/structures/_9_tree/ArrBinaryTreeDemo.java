package data.structures._9_tree;

public class ArrBinaryTreeDemo {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder(0);// 1 2 4 5 3 6 7

    }
}

/**
 * 实现顺序存储二叉树的遍历
 * 存储二叉树的特点：
 * <p>
 * 1. **顺序二叉树通常只考虑完全二叉树**
 * 2. 第 n 个元素的左子节点为 `2 * n + 1`
 * 3. 第 n 个元素的右子节点为 `2 * n + 2`
 * 4. 第 n 个元素的父节点为 `(n-1) / 2`
 * 5. n：表示二叉树中的第几个元素(**按 0 开始编号**)
 */

class ArrBinaryTree {
    private int[] arr;


    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //前序遍历
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能前序遍历二叉树");
            return;
        }

        // 1. 先输出当前节点（初始节点是 root 节点）
        System.out.println(arr[index]);
        // 2. 如果左子节点不为空，则递归继续前序遍历
        int left = 2 * index + 1;
        if (left < arr.length) {
            preOrder(left);
        }
        // 3. 如果右子节点不为空，则递归继续前序遍历
        int right = 2 * index + 2;
        if (right < arr.length) {
            preOrder(right);
        }
    }

    /**
     * 中序遍历：先遍历左子树，再输出父节点，再遍历右子树
     *
     * @param index
     */
    public void infixOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能前序遍历二叉树");
            return;
        }
        int left = 2 * index + 1;
        if (left < arr.length) {
            infixOrder(left);
        }
        System.out.println(arr[index]);
        int right = 2 * index + 2;
        if (right < arr.length) {
            infixOrder(right);
        }
    }

    /**
     * 后序遍历：先遍历左子树，再遍历右子树，最后输出父节点
     *
     * @param index
     */
    public void postOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能前序遍历二叉树");
            return;
        }
        int left = 2 * index + 1;
        if (left < arr.length) {
            postOrder(left);
        }
        int right = 2 * index + 2;
        if (right < arr.length) {
            postOrder(right);
        }
        System.out.println(arr[index]);
    }

}