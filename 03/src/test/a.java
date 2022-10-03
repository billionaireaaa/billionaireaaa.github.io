package test;

import java.util.LinkedList;
import java.util.Queue;

public class a {

    //定义一个节点
    static class BitNode {
        int data;
        BitNode lchild;
        BitNode rchild;

        public void setNode(int data, BitNode lc, BitNode rc) {
            this.data = data;
            lchild = lc;
            rchild = rc;
        }
    }

    static int counter = 0;//定义一个静态计数变量

    /**
     * 构造二叉树
     *
     * @param root根节点
     * @param a数据源
     * @param i计数器
     * @return 根节点
     */
    public static BitNode createBiTree(BitNode root, int[] a, int i) {
        if (i < a.length) {
            if (a[i] == 0) {
                root = null;
            } else {
                BitNode lchild = new BitNode();
                BitNode rchild = new BitNode();
                root.data = a[i];
                root.lchild = createBiTree(lchild, a, ++counter);
                root.rchild = createBiTree(rchild, a, ++counter);
            }
        }
        return root;
    }

    // 访问节点
    public static void visitTNode(BitNode node) {
        System.out.print(node.data + " ");
    }

    // 层次遍历
    public static void levelTraverse(BitNode root) {
        Queue<BitNode> queue = new LinkedList<BitNode>();
        queue.offer(root);// 从根节点入队列
        while (!queue.isEmpty()) {// 在队列为空前反复迭代
            BitNode bitNode = queue.poll();// 取出队列首节点
            visitTNode(bitNode);
            if (bitNode.lchild != null)
                queue.offer(bitNode.lchild);// 左孩子入列
            if (bitNode.rchild != null)
                queue.offer(bitNode.rchild);// 右孩子入列
        }
    }
    //先序遍历
    static void preorder(BitNode root)
    {
        if(root!=null)
        {
            System.out.print(root.data);
            preorder(root.lchild);
            preorder(root.rchild);
        }
    }
    //中序遍历
    static void preorder2(BitNode root)
    {
        if(root!=null)
        {
            preorder2(root.lchild);
            System.out.print(root.data);
            preorder2(root.rchild);
        }
    }
    //后序遍历
    static void preorder3(BitNode root)
    {
        if(root!=null)
        {
            preorder3(root.lchild);
            preorder3(root.rchild);
            System.out.print(root.data);
        }

    }
    static int k;
    static int res;
    static void kthLargest(){

    }
    static void solve(BitNode root)
    {
        if(root==null)
            return ;
            preorder3(root.lchild);
            if(k==0) return ;
            if(--k==0) res=root.data;
            preorder3(root.rchild);


    }

    public static void main(String[] args) {
        BitNode root = new BitNode();
        int[] a = {1, 2, 3, 0, 0,0, 4, 0 , 5, 0, 0};//先序遍历的结果
        root = createBiTree(root, a, counter);
        levelTraverse(root);
        System.out.println();
        preorder(root);
        System.out.println();
        preorder2(root);
        System.out.println();
        preorder3(root);
    }
}
