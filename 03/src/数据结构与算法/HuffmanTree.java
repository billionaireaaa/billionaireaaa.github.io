package 数据结构与算法;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
        int arr[] = {13, 7, 8, 3, 29, 6, 1};
        Hnode a=createHuffmanTree(arr);
        perOrder(a);

    }
    public static void perOrder(Hnode root)
    {
        if(root!=null)
            root.perOrder();
        else
            System.out.println("-999");
    }
    public static Hnode createHuffmanTree(int arr[]) {
        //遍历数组把数组中的每一个元素转为Hnode并且存入Arraylist中
        List<Hnode> nodes = new ArrayList<Hnode>();
        for (int value : arr) {
            nodes.add(new Hnode(value));
        }
        while (nodes.size() > 1)//当数组中只有一个数时，树就创建完成了
        {
            Collections.sort(nodes);
            //取第一第二小的结点构建一个树
            Hnode leftnode = nodes.get(0);
            Hnode rightnode = nodes.get(1);
            //新建一个这两个结点构成的结点
            Hnode parent = new Hnode(leftnode.value + rightnode.value);
            parent.left = leftnode;
            parent.right = rightnode;
            //清楚数组中的这两个结点，并且把新的结点加入
            nodes.remove(leftnode);
            nodes.remove(rightnode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }
}

class Hnode implements Comparable<Hnode>//一个接口为了让结点的值是有顺序的
{
    int value;
    Hnode left;
    Hnode right;

    public void perOrder() {
        System.out.println(this.value);
        if (this.left != null)
            this.left.perOrder();
        if (this.right != null)
            this.right.perOrder();
    }

    public Hnode(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Hnode{" +
                "value=" + value +
                '}';
    }

    @Override//表示结点的顺序是从小到大排序的
    public int compareTo(Hnode o) {
        return this.value - o.value;
    }
}
