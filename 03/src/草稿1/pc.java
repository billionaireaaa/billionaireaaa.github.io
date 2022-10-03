package 草稿;

public class pc extends Computer {
    private String brand;

    public pc(String brand) {
        super("英特尔i5",999,"CMD");
        this.brand = brand;
    }
    public void pri()
    {
        System.out.println(brand+"\t"+this.getCup()+"\t"+this.getYp()+"\t"+getMemory());
    }
}
