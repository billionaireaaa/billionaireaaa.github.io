package 数据结构与算法;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        binaryTree binarytree = new binaryTree();
        treeNode root = new treeNode(1, "a");
        treeNode node1 = new treeNode(2, "b");
        treeNode node2 = new treeNode(3, "c");
        treeNode node3 = new treeNode(4, "d");

        root.setLeft(node1);//手动创建二叉树，因为是变量类型是private型所以不能是root.left=node1
        root.setRight(node2);
        node2.setRight(node3);

        binarytree.setRoot(root);
        binarytree.postOrder();
        System.out.println();
        binarytree.preOrderSearch(5);

        binarytree.delNode(1 );
        binarytree.preOrder();
    }
}

class treeNode {
    public int no;
    public String name;
    private treeNode left;
    private treeNode right;

    public treeNode getLeft() {
        return left;
    }

    public void setLeft(treeNode left) {
        this.left = left;
    }

    public treeNode getRight() {
        return right;
    }

    public void setRight(treeNode right) {
        this.right = right;
    }

    public treeNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "treeNode{" +
                "no=" + no +
                ", name=" + name +
                '}';
    }
    //删除节点
    public void delNode(int no)
    {
        if (this.left!=null&&this.left.no==no)
        {
            this.left=null;
            return;
        }
        if (this.right!=null&&this.right.no==no)
        {
            this.right=null;
            return;
        }
        if (this.left!=null)
        {
            this.left.delNode(no);
        }
        if (this.right!=null)
        {
            this.right.delNode(no);
        }
    }
    //前序遍历
    public void preOrder() {
        System.out.println(this);//当前节点,就是说那个节点调用此函数就从哪里开始
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);//当前节点
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
        System.out.println(this);//当前节点
    }
    //前序查找
    public treeNode preOrderSearch(int no)
    {
        if (this.no==no)//对比当前节点
        {
            return this;
        }
        treeNode t=null;
        if (this.left!=null)
        {
            t=this.left.preOrderSearch(no);
        }
        if (t!=null)
        {
            return t;//表示左子树这边已经找到不需要去查询右子树了
        }
        if (this.right!=null)
        {
            t=this.right.preOrderSearch(no);
        }
        return t;
    }
    //中序和后序查找只是改变一下前序查找的代码顺序
}

class binaryTree {
    private treeNode root;

    public void setRoot(treeNode root) {
        this.root = root;
    }

    public void delNode(int no) {
        if(root!=null)
        {
            if (root.no==no)//判断root是否是要删除的节点
            {
                root=null;
            }
            else
            {
                root.delNode(no);//开始递归删除
            }
        }
        else
            System.out.println("null");
    }

    public void preOrder() {
        if (root != null)
            root.preOrder();
        else
            System.out.println("null");
    }

    public void infixOrder() {
        if (root != null)
            root.infixOrder();
        else
            System.out.println("null");
    }

    public void postOrder() {
        if (root != null)
            root.postOrder();
        else
            System.out.println("null");
    }

    public void preOrderSearch(int no) {
        treeNode t=root.preOrderSearch(no);
        if (t!=null)
        {
            System.out.println(t.no+"  "+t.name);
        }
        else
        {
            System.out.println("null");
        }
    }
}