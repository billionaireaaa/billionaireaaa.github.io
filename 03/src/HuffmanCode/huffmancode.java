package HuffmanCode;

import java.util.*;
//哈夫曼编码

public class huffmancode {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        getNode(contentBytes);
        List<Node> a = getNode(contentBytes);
        Node root = createHuffmanTree(a);
        getCode(root, "", stringBuilder);
        System.out.println(huffmanCodes);

    }

    public static List<Node> getNode(byte[] bytes) {//内容转化为node结点并把每一个结点存入list数组中
        List<Node> nodes = new ArrayList<Node>();
        Map<Byte, Integer> counts = new HashMap<>();//用来统计个个字符出现了几次
        for (byte b : bytes) {
            Integer count = counts.get(b);//获取b出现的次数
            if (count == null)
                counts.put(b, 1);//第一次出现
            else
                counts.put(b, count + 1);
        }
        //把每一个键值对转化为Node对象，并加入到nodes中
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {//entrySet是把Map集合转换成set集合
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    public static Node createHuffmanTree(List<Node> nodes) {//生成一棵哈夫曼树
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node leftnode = nodes.get(0);
            Node rightnode = nodes.get(1);
            Node parent = new Node(null, leftnode.weight + rightnode.weight);
            parent.left = leftnode;
            parent.right = rightnode;
            nodes.remove(leftnode);
            nodes.remove(rightnode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    //把生成的哈夫曼树转为哈夫曼编码
    static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();//存放哈夫曼编码
    static StringBuilder stringBuilder = new StringBuilder();//用于拼接路径

    public static void getCode(Node node, String code, StringBuilder stringBuilder)//传入结点，路径（左0右1），拼接路径，生成哈夫曼编码
    {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if (node != null)//node为null不处理
        {
            if (node.data == null)//表示非叶子结点即不是需要编码的那个字符的结点
            {
                getCode(node.left, "0", stringBuilder2);
                getCode(node.right, "1", stringBuilder2);
            } else //表示叶子结点即需要编码的字符的结点
                huffmanCodes.put(node.data, stringBuilder2.toString());
        }
    }

    public static void perOrder(Node root) {//前序遍历
        if (root != null)
            root.perOrder();
        else
            System.out.println("-999");
    }
}

class Node implements Comparable<Node> {
    Byte data;//存放数据(字符）本身 如’a'=>97
    int weight;//存放权值
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;//从小到大排序
    }

    public void perOrder() {
        System.out.println(this);
        if (this.left != null)
            this.left.perOrder();
        if (this.right != null)
            this.right.perOrder();
    }
}
