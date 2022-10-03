package 草稿;

public class Computer {
    private String cup;
    private int memory;
    private String yp;

    public String getCup() {
        System.out.println("中央处理器为：  "+cup);
        return cup;
    }

    public void setCup(String cup) {
        this.cup = cup;
    }

    public int getMemory() {
        System.out.println("内存为： "+memory);
        return memory;
    }

    public Computer(String cup, int memory, String yp) {
        this.cup = cup;
        this.memory = memory;
        this.yp = yp;

    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public String getYp() {
        System.out.println("硬盘为：  "+yp);
        return yp;
    }

    public void setYp(String yp) {
        this.yp = yp;
    }
}
