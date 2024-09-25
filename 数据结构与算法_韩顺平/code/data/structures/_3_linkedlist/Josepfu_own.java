package data.structures._3_linkedlist;

/**
 * 自己实现的约瑟夫环形链表
 */
public class Josepfu_own {

    public static void main(String[] args) {

        CircleNodeLinkedList boyNodeList = new CircleNodeLinkedList();
        for (int i = 0; i < 5; i++) {
            BoyNode_own boyNode = new BoyNode_own(i + 1);
            boyNodeList.add(boyNode);
        }
        boyNodeList.show();
        int step = 2;

        BoyNode_own pre;//前一个
        BoyNode_own cur = boyNodeList.root; //当前节点
        int i = 1;
        while (true) {
            i++;
            pre = cur;
            cur = cur.next;
            if (cur.next == cur) { // 只剩下一个节点
                System.out.println("end" + cur);
                break;
            }

            if (i % step == 0) {
                //删除当前节点
                System.out.println("取出" + cur);
                pre.next = cur.next;
            }

        }
    }
}

/**
 * 循环单向链表
 */
class CircleNodeLinkedList {
    BoyNode_own root;
    int size = 0;//长度

    /**
     * 添加到尾巴
     *
     * @param node
     */
    public void add(BoyNode_own node) {
        if (size == 0) {
            size++;
            root = node;
            node.next = node;
            return;
        }
        BoyNode_own temp = root;

        while (true) {

            if (temp.next == root) {//尾巴
                temp.next = node;
                node.next = root;
                size++;
                break;
            }
            temp = temp.next;

        }
    }

    public void show() {
        BoyNode_own temp = root;
        while (true) {
            System.out.println(temp);
            if (temp.next == root) {//尾巴
                break;
            }
            temp = temp.next;
        }
    }


}


class BoyNode_own {
    int no;
    BoyNode_own next;

    BoyNode_own(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "BoyNode_own{" +
                "no=" + no +
                '}';
    }
}