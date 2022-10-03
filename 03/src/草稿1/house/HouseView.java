package 草稿.house;

public class HouseView {
    HouseService houseService = new HouseService();

    public void menu() {

        int key = 0;
        boolean loop = true;
        while (loop) {
            System.out.println("=========1.房屋详情=========");
            System.out.println("=========2.添加房屋=========");
            System.out.println("=========3.删除房屋=========");
            System.out.println("=========4.查找房屋=========");
            System.out.println("=========0.退出系统=========");
            System.out.println("请输入选择： ");
            key = Utility.readInt();
            switch (key) {
                case 1:
                    show();
                    break;
                case 2:
                    add();
                    break;
                case 3:
                    del();
                    break;
                case 4:
                    find();
                    break;
                case 0:
                    loop = false;
                    break;
                default:
                    System.out.println("请输入正确选项");
            }
            System.out.println();
        }
    }

    public void show() {
        for (int i = 0; i < houseService.houses.length; i++) {
            if (houseService.houses[i] == null)
                break;
            System.out.println(houseService.houses[i]);
        }
    }

    public void add() {
        House[] h = houseService.list();
        int i = 0;
        for (i = 0; i < h.length; i++) {
            if (h[i] == null)
                break;
        }
        if (i == h.length) {
            System.out.println("房子已满，无法添加");
            return;
        }
        houseService.addHouse(i);
        System.out.println("添加成功");
    }

    public void del() {
        System.out.print("请输入要删除的房屋编号： ");
        int select = Utility.readInt();
        House[] h = houseService.list();
        int i=0;
        for ( i = 0; i < h.length; i++) {
            if (h[i] == null) {
                System.out.println("不存在！");
                return;
            }
            if (h[i].getNo() == select)
                break;
        }
        houseService.delectHouse(i);
    }

    public void find() {
        System.out.print("请输入要查找的房屋编号： ");
        int select = Utility.readInt();
        House[] h = houseService.list();
        for (int i = 0; i < h.length; i++) {

            if (h[i] == null) {
                return;
            }
            if (h[i].getNo() == select)
            {
                System.out.println(h[i]);
                break;
            }
        }
    }
}
