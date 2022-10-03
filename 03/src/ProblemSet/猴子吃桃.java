package ProblemSet;
//猴子在第10天剩下一个桃子，他每天吃一半的桃子之后在多吃一个，问第一天有几个桃子
public class 猴子吃桃 {
    public static void main(String[] args) {
        peach(10,1);
    }
    public static void peach(int day,int count)
    {

        if(day>1)
        {
            int a=(count+1)*2;
            peach(day-1,a);
        }
        else {
            System.out.println("在第一天有： "+count);
            return;
        }
    }
}
