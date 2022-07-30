package data.structures._9_tree.bst;

import org.testng.annotations.Test;

public class BinarySortTreeDemo {

    /**
     * 二叉排序树添加和遍历测试
     */
    @Test
    public void addTest() {
        BinarySortTree tree = new BinarySortTree();
        int[] arr = {7, 3, 10, 12, 5, 1, 9};
        for (int i = 0; i < arr.length; i++) {
            tree.add(new Node(arr[i]));
        }
        tree.infixOrder();
        int item = 2;
        tree.add(new Node(item));
        System.out.println("\n添加新节点：" + item + " 到二叉排序树中");
        System.out.println("添加之后的中序顺序：");
        tree.infixOrder();

        item = 4;
        tree.add(new Node(item));
        System.out.println("\n添加新节点：" + item + " 到二叉排序树中");
        System.out.println("添加之后的中序顺序：");
        tree.infixOrder();
    }

    /**
     * 删除：叶子节点
     */
    @Test
    public void delete1() {
        System.out.println("\n\n删除叶子节点：2，5，9，12");
        BinarySortTree tree = new BinarySortTree();
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        for (int i = 0; i < arr.length; i++) {
            tree.add(new Node(arr[i]));
        }
        tree.infixOrder();

        // 当只实现了删除叶子节点时，这步骤是删除不成功的
//        tree.delete(1);
//        System.out.println("删除非叶子节点后的内容：");
//        tree.infixOrder();

        tree.delNode(2);
        tree.delNode(5);
        tree.delNode(9);
        tree.delNode(12);
        System.out.println("删除后的内容：");
        tree.infixOrder();
    }

    /**
     * 删除：只有一颗叶子节点的节点
     */
    @Test
    public void delete2() {
        System.out.println("\n\n只有一颗叶子节点的节点：1");
        BinarySortTree tree = new BinarySortTree();
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        for (int i = 0; i < arr.length; i++) {
            tree.add(new Node(arr[i]));
        }
        tree.infixOrder();

        tree.delNode(1);
        System.out.println("删除后的内容：");
        tree.infixOrder();
    }

    /**
     * 删除：有两颗子节点的 节点
     */
    @Test
    public void delete3() {
        System.out.println("\n\n有两颗子节点的节点: 10");
        BinarySortTree tree = new BinarySortTree();
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        for (int i = 0; i < arr.length; i++) {
            tree.add(new Node(arr[i]));
        }
        tree.infixOrder();

        tree.delNode(10);
        System.out.println("删除节点后的内容：");
        tree.infixOrder();
    }

    /**
     * 删除  root 节点
     */
    @Test
    public void deleteRoot() {
        System.out.println("\n\n删除 root 节点:7");
        BinarySortTree tree = new BinarySortTree();
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        for (int i = 0; i < arr.length; i++) {
            tree.add(new Node(arr[i]));
        }
        tree.infixOrder();

        tree.delNode(7);
        System.out.println("删除节点后的内容：");
        tree.infixOrder();
    }

}


class BinarySortTree {
    private Node root;

    //添加结点的方法
    public void add(Node node) {
        if (root == null) {
            root = node;//如果root为空则直接让root指向node
        } else {
            root.add(node);
        }
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空，不能遍历");
        }
    }

    /**
     * 查找要删除的结点
     */
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    /**
     * 查找父结点
     */
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            //1.需求先去找到要删除的结点  targetNode
            Node targetNode = search(value);
            //如果没有找到要删除的结点
            if (targetNode == null) {
                return;
            }
            // 走到这里 = 找到了节点
            // 并且，root 没有子节点了，则说明当前只有 root 一个节点
            //如果我们发现当前这颗二叉排序树只有一个结点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }

            //去找到targetNode的父结点
            Node parent = searchParent(value);

            //如果要删除的结点是叶子结点
            if (targetNode.left == null && targetNode.right == null) {
                //判断targetNode 是父结点的左子结点，还是右子结点
                if (parent.left != null && parent.left.value == value) { //是左子结点
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {//是由子结点
                    parent.right = null;
                }
            }
            //删除有两颗子树的节点
            else if (targetNode.left != null && targetNode.right != null) {
//                (1) 需求先去找到要删除的结点 targetNode。
//                (2) 找到 targetNode 的 父结点 parent。
//                (3) 从 targetNode 的右子树找到最小的结点。
//                (4) 用一个临时变量，将最小结点的值保存到 temp
//                (5) 删除该最小结点
//                (6) targetNode.value = temp
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;

            }
            // 删除只有一颗子树的结点
            else {
                //如果要删除的结点有左子结点
                if (targetNode.left != null) {
                    if (parent != null) {
                        //如果 targetNode 是 parent 的左子结点
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else { //  targetNode 是 parent 的右子结点
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;//目标节点没有父节点即是root节点
                    }
                } else { //如果要删除的结点有右子结点
                    if (parent != null) {
                        //如果 targetNode 是 parent 的左子结点
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else { //如果 targetNode 是 parent 的右子结点
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;//目标节点没有父节点即是root节点
                    }
                }

            }

        }

    }


    /**
     * 编写方法:
     * 1. 返回的 以node 为根结点的二叉排序树的最小结点的值
     * 2. 删除node 为根结点的二叉排序树的最小结点
     *
     * @param node 传入的结点(当做二叉排序树的根结点)
     * @return 返回的 以node 为根结点的二叉排序树的最小结点的值
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        //循环的查找左子节点，就会找到最小值
        while (target.left != null) {
            target = target.left;
        }
        //这时 target就指向了最小结点
        //删除最小结点
        delNode(target.value);
        return target.value;
    }

}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    /**
     * 添加结点的方法
     * 递归的形式添加结点，注意需要满足二叉排序树的要求
     * @param node 待添加节点
     */
    public void add(Node node) {
        if (node == null) {
            return;
        }

        //判断传入的结点的值，和当前子树的根结点的值关系
        if (node.value < this.value) {
            //如果当前结点左子结点为null
            if (this.left == null) {
                this.left = node;
            } else {
                //递归的向左子树添加
                this.left.add(node);
            }
        } else { //添加的结点的值大于 当前结点的值
            if (this.right == null) {
                this.right = node;
            } else {
                //递归的向右子树添加
                this.right.add(node);
            }

        }
    }

    //中序遍历：刚好是从小到大的顺序
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }

        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }

    }

    @Override
    public String toString() {
        return "Node{" + "value=" + value + '}';
    }

    /**
     * 查找要删除的结点
     *
     * @param value 希望删除的结点的值
     * @return 如果找到返回该结点，否则返回null
     */
    public Node search(int value) {
        if (value == this.value) { //找到就是该结点
            return this;
        } else if (value < this.value) {//如果查找的值小于当前结点，向左子树递归查找
            //如果左子结点为空
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else { //如果查找的值不小于当前结点，向右子树递归查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }


    /**
     * 查找要删除结点的父结点
     *
     * @param value 要找到的结点的值
     * @return 返回的是要删除的结点的父结点，如果没有就返回null
     */
    public Node searchParent(int value) {

        //如果当前结点就是要删除的结点的父结点，就返回
        if ((this.left != null && this.left.value == value)
                || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //如果查找的值小于当前结点的值, 并且当前结点的左子结点不为空
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value); //向左子树递归查找
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value); //向右子树递归查找
            } else {
                return null; // 没有找到父结点
            }
        }

    }
}


