package 草稿.house;

public class House {
    private int no;
     static int count=0;
    private static int number=1;
    private String name;
    private int price;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public House(int no, String name, int price) {
        count++;
        setNo(number++);
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "House{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
