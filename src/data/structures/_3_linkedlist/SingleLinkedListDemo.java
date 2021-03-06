package data.structures._3_linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(10, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(20, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(30, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(40, "林冲", "豹子头");

        SingleLinkedList list = new SingleLinkedList();

        list.add(hero1);
        list.add(hero2);
        list.add(hero3);
        list.add(hero4);

        HeroNode hero11 = new HeroNode(11, "宋江", "及时雨");
        HeroNode hero21 = new HeroNode(22, "卢俊义", "玉麒麟");
        HeroNode hero31 = new HeroNode(23, "吴用", "智多星");
        HeroNode hero41 = new HeroNode(34, "林冲", "豹子头");

        SingleLinkedList list1 = new SingleLinkedList();
        list1.add(hero11);
        list1.add(hero21);
        list1.add(hero31);
        list1.add(hero41);

        SingleLinkedList singleLinkedList = mergerLinkedList(list1, list);
        singleLinkedList.list();

//        list.addByOrder(hero3);
//        list.addByOrder(hero4);
//        list.addByOrder(hero1);
//        list.addByOrder(hero2);
//        // list.addByOrder(hero4);
//
//        list.list();
//        System.out.println("size = " + list.size());
//        HeroNode lastIndexNode = list.findLastIndexNode(2);
//        System.out.println("lastIndexNode = " + lastIndexNode);
//
//
//        list.reversetList();
//        list.list();




    }

    public static SingleLinkedList mergerLinkedList(SingleLinkedList list1, SingleLinkedList list2) {
        SingleLinkedList res = new SingleLinkedList();
        HeroNode l1 = list1.getHead().next;
        HeroNode l2 = list2.getHead().next;

        while (true) {
            if (l1 == null || l2 == null) {
                break;
            }
            if (l1.no < l2.no) {
                HeroNode next = l1.next;
                l1.next = null;//下一个节点置空
                res.add(l1);//插入未部
                l1 = next;
            } else {
                HeroNode next = l2.next;
                l2.next = null;
                res.add(l2);
                l2 = next;
            }
        }

        //判断两个是否合并完毕
        if (l1 == null) {
            res.add(l2);
        } else {
            res.add(l1);
        }

        return res;
    }


    public static HeroNode reserver(SingleLinkedList root) {

        HeroNode newNode = new HeroNode(0, null, null);

        HeroNode next = root.getHead().next;//第一个元素

        while (true) {

            if (next == null) {
                break;
            }

            HeroNode noResver = next.next;//保存为排序部分

            next.next = newNode.next;//插入到头部
            newNode.next = next;

            next = noResver;//指向未排序头部

        }

        return newNode;
    }

}

//定义HeroNode ， 每个HeroNode 对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next; //指向下一个节点

    //构造器
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //为了显示方法，我们重新toString
    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }

}

//定义SingleLinkedList 管理我们的英雄
class SingleLinkedList {
    //先初始化一个头节点, 头节点不要动, 不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");


    //返回头节点
    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    //思路，当不考虑编号顺序时
    //1. 找到当前链表的最后节点
    //2. 将最后这个节点的next 指向 新的节点
    public void add(HeroNode heroNode) {

        //因为head节点不能动，因此我们需要一个辅助遍历 temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true) {
            //找到链表的最后
            if (temp.next == null) {//
                break;
            }
            //如果没有找到最后, 将将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        //将最后这个节点的next 指向 新的节点
        temp.next = heroNode;
    }

    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
    //(如果有这个排名，则添加失败，并给出提示)
    public void addByOrder(HeroNode heroNode) {
        //因为头节点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
        //因为单链表，因为我们找的temp 是位于 添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false; // flag标志添加的编号是否存在，默认为false
        while (true) {
            if (temp.next == null) {//说明temp已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) { //位置找到，就在temp的后面插入
                break;
            } else if (temp.next.no == heroNode.no) {//说明希望添加的heroNode的编号已然存在

                flag = true; //说明编号存在
                break;
            }
            temp = temp.next; //后移，遍历当前链表
        }
        //判断flag 的值
        if (flag) { //不能添加，说明编号存在
            System.out.printf("准备插入的英雄的编号 %d 已经存在了, 不能加入\n", heroNode.no);
        } else {
            //插入到链表中, temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点的信息, 根据no编号来修改，即no编号不能改.
    //说明
    //1. 根据 newHeroNode 的 no 来修改即可
    public void update(HeroNode newHeroNode) {
        //判断是否空
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        //找到需要修改的节点, 根据no编号
        //定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false; //表示是否找到该节点
        while (true) {
            if (temp == null) {
                break; //已经遍历完链表
            }
            if (temp.no == newHeroNode.no) {
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag 判断是否找到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else { //没有找到
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n", newHeroNode.no);
        }
    }


    //显示链表[遍历]
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点，不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
            //判断是否到链表最后
            if (temp == null) {
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            //将temp后移， 一定小心
            temp = temp.next;
        }
    }

    // 单链表中有效节点的个数
    public int size() {
        HeroNode curNode = head.next;
        int count = 0;
        while (true) {
            if (curNode == null) {
                break;
            }
            count++;
            curNode = curNode.next;
        }
        return count;
    }

    //查找单链表中的倒数第k个结点 【新浪面试题】
    //思路
    //1. 编写一个方法，接收head节点，同时接收一个index
    //2. index 表示是倒数第index个节点
    //3. 先把链表从头到尾遍历，得到链表的总的长度 getLength
    //4. 得到size 后，我们从链表的第一个开始遍历 (size-index)个，就可以得到
    //5. 如果找到了，则返回该节点，否则返回nulll
    public HeroNode findLastIndexNode(int index) {
        //判断如果链表为空，返回null
        if (head.next == null) {
            return null;//没有找到
        }
        //第一个遍历得到链表的长度(节点个数)
        int size = size();
        //第二次遍历  size-index 位置，就是我们倒数的第K个节点
        //先做一个index的校验
        if (index <= 0 || index > size) {
            return null;
        }
        //定义给辅助变量， for 循环定位到倒数的index
        HeroNode cur = head.next; //3 // 3 - 1 = 2
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;

    }

    //将单链表反转
    public void reversetList() {
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }

        //定义一个辅助的指针(变量)，帮助我们遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null;// 指向当前节点[cur]的下一个节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead 的最前端
        //动脑筋
        while (cur != null) {
            next = cur.next;//先暂时保存当前节点的下一个节点，因为后面需要使用
            {//插入节点
                cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前端
                reverseHead.next = cur; //将cur 连接到新的链表上
            }
            cur = next;//让cur后移
        }
        //将head.next 指向 reverseHead.next , 实现单链表的反转
        head.next = reverseHead.next;
    }

    //方式2：
    //可以利用栈这个数据结构，将各个节点压入到栈中，然后利用栈的先进后出的特点，就实现了逆序打印的效果
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;//空链表，不能打印
        }
        //创建要给一个栈，将各个节点压入栈
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        //将链表的所有节点压入栈
        while (cur != null) {
            stack.push(cur);
            cur = cur.next; //cur后移，这样就可以压入下一个节点
        }
        //将栈中的节点进行打印,pop 出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop()); //stack的特点是先进后出
        }
    }

}
