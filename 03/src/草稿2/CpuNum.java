package 草稿2;

import java.util.Scanner;

public class CpuNum {
    public static void main(String[] args) throws InterruptedException {
        A a = new A();
        Thread thread1 = new Thread(a);
        thread1.setName("1");
        thread1.start();

        B b = new B(a);
        b.start();



    }
}
class A implements Runnable
{
    private static boolean loop=true;
    private int count=10000;

    public boolean isLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    @Override
    public void run() {
            while (loop) {
                if (count < 50)
                    break;

               synchronized (this){
                   count -= 1;
                System.out.println( count + "  " + Thread.currentThread().getName());
               }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
    class B extends Thread
    {
        A a=null;
        public B(A a)
        {
            this.a=a;
        }
        @Override
        public void run() {
            while (true) {
                System.out.println("请输入： ");
                Scanner scanner = new Scanner(System.in);
                String key=scanner.next();
                if (key.equals("q"))
                {
                    a.setLoop(false);
                    break;
                }
            }
        }
    }


