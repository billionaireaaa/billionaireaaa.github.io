package 数据结构与算法;

//普通计算器的实现（中缀表达式）
//char可以传给int（阿斯克码）  但是不能相反
//***类内的方法或者属性加上static则会让其属于类层级，即每个对象都共享这个变量，当一个对象改变了这个变量的值另一个的也会被改变，是一起变化的，类层级的变量或者方法可以直接用类名调用，如果不加static就是对象层级只能
//被实例化出来的对象调用，且个个对象的对这个变量是不共享的，每个对象有每个对象对应的这个变量的值
//所以本代码中的Stack类的方法和变量不能加上static，因为要创建两个不同对象他们存放的内容是不一样的，所以不能共享使用

public class Calculator {
    public static void main(String[] args) {
        String expression = "300+6*8-200";
        Stack numStack = new Stack(10);//数栈，存放的是数字
        Stack operStack = new Stack(10);//符号栈，存放的是符号
        int index = 0;//用来扫描表达式
        int num1 = 0;
        int num2 = 0;
        int oper = 0;//运算符号
        String keepNum = "";//用于拼接
        int res = 0;//存放计算后的结果
        char ch = ' ';//将每次扫描后得到的一个char值保存到ch
        while (true) {
            //依次去取exprion中的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);//取出字符串中的一个，再把他转化为char型
            if (operStack.isOper(ch))//如果是符号
            {
                //判断符号栈是否为空
                if (operStack.isEmpty())//如果是空则直接入栈
                {
                    operStack.push(ch);
                } else//如果不是空则看运算符优先级来讨论
                {
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek()))//如果当前运算符优先级小于等于栈顶符号的优先级则要进行相应的运算
                    {//此处的运算是：从数栈中取两个数从符号栈取一个符号进行运行，之后把结果放回数栈，在把当前的符号放入符号栈
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        numStack.push(res);
                        operStack.push(ch);
                    } else//如果优先级大于栈顶的则直接存入符号栈中
                    {
                        operStack.push(ch);
                    }
                }
            } else//如果是数，则直接入栈
            {
                //要进行判断因为如果真实的数可能是一个多位数则只读一位就入栈就会导致运算出错
                //所以要多看一位，如果下一位是数则进行拼接，如果是符号则直接入栈
                keepNum += ch;
                if (index == expression.length() - 1)//判断是否是表达式的最后一位如果是则直接入栈
                {
                    numStack.push(Integer.parseInt(keepNum));//把字符串转为int型
                } else {
                    //每次往后看一位，看其是数字还是符号，符号的话直接入栈，数字就保留下keepNum然后继续扫描
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));//把字符串转为int型
                        keepNum = "";//每次弄完之后一定要将其清空
                    }
                }

                // numStack.push(ch - 48);//ch是char，对应的int值是阿斯克码的值，减去48后的值才是真正的数的值，’1‘=>1
            }
            index++;//让index移动开始扫描下一个
            if (index == expression.length())//如果扫描完表达式则退出循环
                break;
        }
     /*   200	45	8	42	6	43	300
        200	45	8	42	6	43	300*/
        operStack.show();
        System.out.println();
        numStack.show();
        System.out.println();
        //扫描完毕后把数栈中的数依次弹出计算
        while (true) {
            if (operStack.isEmpty()) break;
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        System.out.printf("%s=%d", expression, numStack.peek());
    }
}

class Stack {
    private int maxsize;//栈的大小
    private int[] stack;//存放栈的数据的数组
    private int top = -1;//栈顶的指针默认为-1

    public Stack(int maxsize)//初始化栈
    {
        this.maxsize = maxsize;
        stack = new int[this.maxsize];
    }

    public boolean isFull() {
        return top == maxsize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int value)//入栈
    {
        if (isFull()) {
            System.out.println("栈满了");
            return;
        }
        top++;
        stack[top] = value;
    }

    public int pop()//出栈
    {
        if (isEmpty()) {
            System.out.println("栈为空");
            return -1;
        }
        int value = stack[top];
        top--;
        return value;
    }

    public void show() {//显示栈中的内容
        if (isEmpty()) {
            System.out.println("栈为空");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }

    public int peek() {//返回栈顶的元素但是不出栈
        return stack[top];
    }

    //判读符号的优先级
    //返回运算符的优先级，假设数字越大优先级越高
    public int priority(int oper)//符号暂时只有加减乘除
    {
        if (oper == '*' || oper == '/') return 1;
        else if (oper == '+' || oper == '-') return 0;
        else return -1;
    }

    //判断是否是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0;//存放计算后的结果
        switch (oper)//
        {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;//注意顺序，因为是栈的操作所以是后面减去前面的
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;//注意顺序
                break;
        }
        return res;
    }
}

