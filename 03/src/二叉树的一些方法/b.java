package 二叉树的一些方法;
//二叉树的方法


import javax.swing.tree.TreeNode;
import java.util.*;

public class b {

       static int i = 0;// 计数

       public static TreeNode createBinaryTree(int[] arr) {//二叉树的创建
           if (arr == null || arr.length == 0) {
               return null;
           }
           TreeNode node = new TreeNode();
           if (i < arr.length) {// 如果二叉树数据空值正确，此判断可以省略
               node.data = arr[i++];
               if (node.data == 0) {
                   return null;
               } else {
                   node.leftChild = createBinaryTree(arr);
                   node.rightChild = createBinaryTree(arr);
               }
           }
           return node;
       }

       static void pri1(TreeNode root)//先序遍历
       {
           if(root!=null)
           {
               System.out.print(root.data);
               pri1(root.leftChild);
               pri1(root.rightChild);
           }
       }
       static void pri2(TreeNode root)//中序遍历
       {
           if(root!=null)
           {

               pri2(root.leftChild);
               System.out.print(root.data);
               pri2(root.rightChild);
           }
       }
       static void pri3(TreeNode root)//后序遍历
       {
           if(root!=null)
           {

               pri3(root.leftChild);
               pri3(root.rightChild);
               System.out.print(root.data);
           }
       }


       static boolean isSubStructure(TreeNode A,TreeNode B)//判断B树是否是A的子树
       {
           return (A!=null&&B!=null)&&(recur(A,B)||isSubStructure(A.leftChild,B)||isSubStructure(A.rightChild,B));
       }
       static boolean recur(TreeNode A,TreeNode B)
       {
           if (B==null)
              return true;
           if (A==null||A.data!= B.data)
               return false;
           return recur(A.leftChild,B.leftChild)&&recur(A.rightChild,B.rightChild);
       }

       static public void exchange(TreeNode A)//交换左右子树
       {
           if(A!=null)
           {
               TreeNode temp=A.leftChild;
               A.leftChild=A.rightChild;
               A.rightChild=temp;
               exchange(A.leftChild);
               exchange(A.rightChild);
           }
       }

       static public boolean isSymmetric(TreeNode A)//判断是否对称
       {
           return A==null||solve(A.leftChild,A.rightChild);
       }

      static boolean solve(TreeNode L, TreeNode R)
       {
           if(L==null&&R==null)
               return true;
           if(L == null || R == null || L.data != R.data) return false;
           return recur(L.leftChild,R.rightChild) && recur(L.rightChild,R.rightChild);
       }

       static int[] leveler(TreeNode root)//层次遍历1
       {
           if(root == null) return new int[0];
           Queue<TreeNode> queue = new LinkedList<>();
           ArrayList<Integer> ans = new ArrayList<>();
           queue.add(root);
           while(!queue.isEmpty()) {
               TreeNode node = queue.poll();
               ans.add(node.data);
               if(node.leftChild != null) queue.add(node.leftChild);
               if(node.rightChild != null) queue.add(node.rightChild);
           }
           int[] res = new int[ans.size()];
           for(int i = 0; i < ans.size(); i++)
               res[i] = ans.get(i);
           return res;
       }

        static void levelorder(TreeNode root)//层序遍历2
        {
            Queue<TreeNode> queue =new LinkedList<>();
            List<List<Integer>> res= new LinkedList<>();
            queue.add(root);
            while(!queue.isEmpty())
            {
                List<Integer> temp=new ArrayList<>();
                for(int i= queue.size();i>0;i--)
                {
                    TreeNode node= queue.poll();
                    temp.add(node.data);
                    if(node.leftChild!=null) queue.add(node.leftChild);
                    if(node.rightChild!=null) queue.add(node.rightChild);
                }
                res.add(temp);
            }
             for (int i=0;i< res.size();i++)
             {
                 System.out.println(res.get(i));
             }
        }
        static  void Levelorder(TreeNode root)//层序遍历3
        {
            int k=1;
            Queue<TreeNode> queue=new LinkedList<>();
            List<LinkedList<Integer>> res=new ArrayList<>();
            queue.add(root);
            while (!queue.isEmpty())
            {
                LinkedList<Integer> temp=new LinkedList<>();
                for(int i= queue.size();i>0;i--)
                {
                    TreeNode node=queue.poll();
                    if(k%2==0)
                        temp.addFirst(node.data);
                    else
                        temp.addLast(node.data);
                    if(node.leftChild!=null)
                        queue.add(node.leftChild);
                    if(node.rightChild!=null)
                        queue.add(node.rightChild);
                }
                res.add(temp);
                k++;
            }
            for(int i=0;i< res.size();i++)
            {
                System.out.println(res.get(i));
            }
        }

        static void pathsum(TreeNode root,int target)
    {

    }
    static int  depth=0;
    static void Depth(TreeNode root,int h)//求高度h初值为1
    {
        if (root != null)
        {

            if(h>depth)
                depth=h;
            Depth(root.leftChild,h+1);
            Depth(root.rightChild,h+1);
        }
    }

    static int k;
    static int res;
    static int  kthLargest(TreeNode root,int key)//此方法用来求搜索二叉树的第key大的值
    {
        k=key;
        dfs(root);
        return res;
    }
    static void dfs(TreeNode root)
    {
        if(root==null)
            return ;
        dfs(root.leftChild);
        if(k==0) return ;
        if(--k==0) res=root.data;
        dfs(root.rightChild);


    }


    static boolean isBalanced(TreeNode root) {//判断此树是否是平很二叉树
        if(root==null) return true;
        else
            return Math.abs(depth(root.leftChild) - depth(root.rightChild)) <= 1 && isBalanced(root.leftChild) && isBalanced(root.rightChild);

    }
    static int depth(TreeNode root) {
    if(root!=null)
        return 0;
    return Math.max(depth(root.leftChild),depth(root.rightChild))+1;
    }


       public static class TreeNode {
           public int data;
           public TreeNode leftChild;
           public TreeNode rightChild;
       }



       public static void main(String[] args) {
           // 二叉树的值保存在数组中，以0作为分隔，数字0表示空节点，数组
           int[] arr1 = new int[]{1,2,0,3,0,4,0,0,5,0,0};//先序构造
           TreeNode root = createBinaryTree(arr1);
           System.out.print("撒多久啊是会计");
           /*int[] arr2 =new int[]{1,2,0,3,0,4,0,0,5,0,0};
           TreeNode test=createBinaryTree(arr2);
           int aa[]= leveler(root);
           //Levelorder(root);
           Depth(root,1);
           System.out.println(depth);

           exchange(root);
           System.out.println();
           pri1(root);
           System.out.println();
           pri2(root);
           System.out.println();
           pri3(root);
           System.out.println();
           System.out.println(isSubStructure(root,test));
           System.out.println(isSymmetric(root));*/
       }
   }





