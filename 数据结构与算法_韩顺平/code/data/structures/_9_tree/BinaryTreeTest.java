package data.structures._9_tree;

public class BinaryTreeTest {
    public static void main(String[] args) {
        //先需要创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的结点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        //说明，我们先手动创建该二叉树，后面我们学习递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);
//测试遍历
//        System.out.println("\n 前序遍历：");
//        binaryTree.preOrder();
//        System.out.println("\n 中序遍历：");
//        binaryTree.infixOrder();
//        System.out.println("\n 后序遍历：");
//        binaryTree.postOrder();

//测试查找
//        System.out.println("找到测试：");
//        int id = 5;
//        System.out.println("\n前序遍历查找 id=" + id);
//        System.out.println(binaryTree.preOrderSearch(id));
//        System.out.println("\n中序遍历查找 id=" + id);
//        System.out.println(binaryTree.infixOrderSearch(id));
//        System.out.println("\n后序遍历查找 id=" + id);
//        System.out.println(binaryTree.postOrderSearch(id));
//
//        System.out.println("找不到测试：");
//        id = 15;
//        System.out.println("\n前序遍历查找 id=" + id);
//        System.out.println(binaryTree.preOrderSearch(id));
//        System.out.println("\n中序遍历查找 id=" + id);
//        System.out.println(binaryTree.infixOrderSearch(id));
//        System.out.println("\n后序遍历查找 id=" + id);
//        System.out.println(binaryTree.postOrderSearch(id));

        //测试删除
//        binaryTree.delete(3);
//        binaryTree.preOrder();
//        binaryTree.delete(5);
//        binaryTree.preOrder();

        binaryTree.deleteByQuestion(3);
        binaryTree.preOrder();


    }

}

//定义BinaryTree 二叉树
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /**
     * 前序查找
     */
    public HeroNode preOrderSearch(int id) {
        if (root == null) {
            System.out.println("二叉树为空");
            return null;
        }
        return root.preOrderSearch(id);
    }

    /**
     * 中序查找
     */
    public HeroNode infixOrderSearch(int id) {
        if (root == null) {
            System.out.println("二叉树为空");
            return null;
        }
        return root.infixOrderSearch(id);
    }

    /**
     * 后序查找
     */
    public HeroNode postOrderSearch(int id) {
        if (root == null) {
            System.out.println("二叉树为空");
            return null;
        }
        return root.postOrderSearch(id);
    }
    // BinaryTree 新增删除方法

    /**
     * 删除节点
     */
    public HeroNode delete(int id) {
        if (root == null) {
            System.out.println("树为空");
            return null;
        }
        HeroNode target = null;
        // 如果 root 节点就是要删除的节点，则直接置空
        if (root.getNo() == id) {
            target = root;
            root = null;
        } else {
            target = this.root.delete(id);
        }

        return target;

    }

    public HeroNode deleteByQuestion(int id) {
        if (root == null) {
            System.out.println("树为空");
            return null;
        }
        HeroNode target = null;
        // 如果 root 节点就是要删除的节点，则直接置空
        if (root.getNo() == id) {
            target = root;
            root = null;
        } else {
            target = this.root.deleteByQuestion(id);
        }

        return target;
    }


}

//先创建HeroNode 结点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left; //默认null
    private HeroNode right; //默认null

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + "]";
    }

    //编写前序遍历的方法
    public void preOrder() {
        System.out.println(this); //先输出父结点
        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {

        //递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        //输出父结点
        System.out.println(this);
        //递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }


    /**
     * 前序遍历查找
     *
     * @param no 查找no
     * @return 如果找到就返回该Node ,如果没有找到返回 null
     */
    public HeroNode preOrderSearch(int no) {
        System.out.println("进入前序遍历");
        //比较当前结点是不是
        if (this.no == no) {
            return this;
        }
        //1.则判断当前结点的左子节点是否为空，如果不为空，则递归前序查找
        //2.如果左递归前序查找，找到结点，则返回
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {//说明我们左子树找到
            return resNode;
        }
        //1.左递归前序查找，找到结点，则返回，否继续判断，
        //2.当前的结点的右子节点是否为空，如果不空，则继续向右递归前序查找
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        //判断当前结点的左子节点是否为空，如果不为空，则递归中序查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入中序查找");
        //如果找到，则返回，如果没有找到，就和当前结点比较，如果是则返回当前结点
        if (this.no == no) {
            return this;
        }
        //否则继续进行右递归的中序查找
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;

    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {

        //判断当前结点的左子节点是否为空，如果不为空，则递归后序查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {//说明在左子树找到
            return resNode;
        }

        //如果左子树没有找到，则向右子树递归进行后序遍历查找
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入后序查找");
        //如果左右子树都没有找到，就比较当前结点是不是
        if (this.no == no) {
            return this;
        }
        return resNode;
    }

    /**
     * 删除节点，老师的思路写法，先看左右，看完再递归
     *
     * @param id
     * @return 如果删除成功，则返回删除的节点
     */
    public HeroNode delete(int id) {
        // 判断左子节点是否是要删除的节点
        HeroNode target = null;
        if (this.left != null && this.left.no == id) {
            target = this.left;
            this.left = null;
            return target;
        }

        if (this.right != null && this.right.no == id) {
            target = this.right;
            this.right = null;
            return target;
        }

        // 尝试左递归
        if (this.left != null) {
            target = this.left.delete(id);
            if (target != null) {
                return target;
            }
        }

        // 尝试右递归
        if (this.right != null) {
            target = this.right.delete(id);
            if (target != null) {
                return target;
            }
        }
        return null;
    }

    public HeroNode deleteByQuestion(int id) {
        // 判断左子节点是否是要删除的节点
        HeroNode target = null;
        if (this.left != null && this.left.no == id) {
            target = this.left;
            if (target.left == null && target.right == null) {
                this.left = null;
                return target;
            } else if (target.left == null && target.right != null) {
                this.right = target.right;
                return target;
            } else if (target.left != null && target.right == null) {
                this.left = target.left;
                return target;
            } else {
                this.left = target.left;
                target.left.right = target.right;
                return target;
            }

        }

        if (this.right != null && this.right.no == id) {
            target = this.right;
            if (target.left == null && target.right == null) {
                this.right = null;
                return target;
            } else if (target.left == null && target.right != null) {
                this.right = target.right;
                return target;
            } else if (target.left != null && target.right == null) {
                this.left = target.left;
                return target;
            } else {
                this.right = target.left;
                target.left.right = target.right;
                return target;
            }
        }

        // 尝试左递归
        if (this.left != null) {
            target = this.left.delete(id);
            if (target != null) {
                return target;
            }
        }

        // 尝试右递归
        if (this.right != null) {
            target = this.right.delete(id);
            if (target != null) {
                return target;
            }
        }
        return null;
    }

//    //递归删除结点
//    //1.如果删除的节点是叶子节点，则删除该节点
//    //2.如果删除的节点是非叶子节点，则删除该子树
//    public void delNode(int no) {
//
//        //思路
//		/*
//		 * 	1. 因为我们的二叉树是单向的，所以我们是判断当前结点的子结点是否需要删除结点，而不能去判断当前这个结点是不是需要删除结点.
//			2. 如果当前结点的左子结点不为空，并且左子结点 就是要删除结点，就将this.left = null; 并且就返回(结束递归删除)
//			3. 如果当前结点的右子结点不为空，并且右子结点 就是要删除结点，就将this.right= null ;并且就返回(结束递归删除)
//			4. 如果第2和第3步没有删除结点，那么我们就需要向左子树进行递归删除
//			5.  如果第4步也没有删除结点，则应当向右子树进行递归删除.
//
//		 */
//        //2. 如果当前结点的左子结点不为空，并且左子结点 就是要删除结点，就将this.left = null; 并且就返回(结束递归删除)
//        if (this.left != null && this.left.no == no) {
//            this.left = null;
//            return;
//        }
//        //3.如果当前结点的右子结点不为空，并且右子结点 就是要删除结点，就将this.right= null ;并且就返回(结束递归删除)
//        if (this.right != null && this.right.no == no) {
//            this.right = null;
//            return;
//        }
//        //4.我们就需要向左子树进行递归删除
//        if (this.left != null) {
//            this.left.delNode(no);
//        }
//        //5.则应当向右子树进行递归删除
//        if (this.right != null) {
//            this.right.delNode(no);
//        }
//    }

}
