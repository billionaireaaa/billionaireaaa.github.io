package 草稿.house;

public class HouseService {
    House[] houses= new House[10];
    int size=10;

    public HouseService() {
       houses[0]=new House(1,"玉龙泉",2000);
        houses[1]=new House(2,"玉泉",2000);
        houses[2]=new House(3,"龙泉",2000);
    }
    public House[] list()
    {
        return houses;
    }
    public void addHouse(int index)
    {
        System.out.print("请输入地址名称： ");
        String name=Utility.readString(10);
        System.out.print("输入租金：  ");
        int price=Utility.readInt(10);
        houses[index]=new House(1,name,price);
    }
    public void delectHouse(int select)
    {
        char a=Utility.readConfirmSelection();

        if (a=='Y')
        {
            for (int i = select; i < House.count-1; i++) {
                houses[i]=houses[i+1];
            }
            houses[House.count-1]=null;
            House.count--;
            System.out.println("删除成功");
        }
        else {
            System.out.println("点错不删了");
        }
    }
}
