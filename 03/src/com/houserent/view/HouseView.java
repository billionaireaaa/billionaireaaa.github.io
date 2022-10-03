package com.houserent.view;

import com.houserent.domain.House;
import com.houserent.service.HouseService;
import com.houserent.utils.Utility;

public class HouseView {

    private HouseService houseService = new HouseService(10);
    private boolean loop = true;
    private char key = ' ';

    public void MainMenu() {
        boolean loop = true;

        do {
            System.out.println("\n=========房屋出租系统=========");
            System.out.println("\t\t1.新 增 房 源");
            System.out.println("\t\t2.查 找 房 源");
            System.out.println("\t\t3.删 除 房 屋 信 息");
            System.out.println("\t\t4.修 改 房 屋 信 息");
            System.out.println("\t\t5.房 屋 列 表");
            System.out.println("\t\t6.退      出");
            System.out.print("请输入你的选择（1-6）");

            key = Utility.readChar();

            switch (key) {
                case '1':
                    addHouse();
                    break;
                case '2':
                    find();
                    break;
                case '3':
                    delHouse();
                    break;
                case '4':
                    update();
                    break;
                case '5':
                    listHouse();
                    break;
                case '6':
                    if (exit())
                        loop = false;
                    break;
            }
        } while (loop);
    }

    public void listHouse() {
        System.out.println("=========房屋列表=========");
        System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态（未出租/已出租）");
        House[] houses = houseService.list();
        for (int i = 0; i < houses.length; i++) {
            if (houses[i] == null)
                break;
            System.out.println(houses[i].toString());
        }
    }

    public void addHouse() {
        System.out.println("=========添加房屋=========");
        System.out.println("姓名： ");
        String name = Utility.readString(10);
        System.out.println("电话： ");
        String phone = Utility.readString(10);
        System.out.println("地址： ");
        String address = Utility.readString(10);
        System.out.println("月租： ");
        int rent = Utility.readInt(10);
        System.out.println("状态： ");
        String state = Utility.readString(10);

        House newHouse = new House(0, name, phone, address, rent, state);//id不用专门辅助系统会自增长分配

        if (houseService.add(newHouse))
            System.out.println("添加成功了！");
        else
            System.out.println("添加失败了！");
    }

    public void delHouse() {
        System.out.println("=========删除房屋=========");
        System.out.print("请输入需要删除的房屋编号（-1退出）： ");
        int c = Utility.readInt();
        if (c == -1) {
            System.out.println("放弃删除。。。");
            return;
        }
        System.out.println("是否真的要删除");
        char choice = Utility.readConfirmSelection();
        if (choice == 'Y') {
            if (houseService.del(c))
                System.out.println("=========删除房屋成功=========");
            else
                System.out.println("不存在此房屋，删除失败。");
        } else {
            System.out.println("放弃删除。。。");
        }
    }

    public boolean exit() {
        char choice = Utility.readConfirmSelection();
        if (choice == 'Y')
            return true;
        return false;
    }

    public void find() {
        System.out.println("=========查找房屋=========");
        System.out.print("请输入需要查找的房屋的编号： ");
        int fi = Utility.readInt();
        House h = houseService.findById(fi);
        if (h != null) {
            System.out.println(h.toString());
        } else {
            System.out.println("不存在此编号的房子，查找失败。");
        }
    }

    public void update() {
        System.out.println("=========修改房屋信息=========");
        System.out.println("请选择待修改房屋编号（-1表示退出）");
        int updateId = Utility.readInt();
        if (updateId == -1) {
            System.out.println("=========放弃修改房屋信息=========");
            return;
        }

        House house = houseService.findById(updateId);
        if (house == null) {
            System.out.println("=========修改房屋信息编号不存在=========");
            return;
        }

        System.out.print("姓名(" + house.getName() + "): ");
        //这里如果用户直接回车表示不修改信息 默认""
        String name = Utility.readString(8, "");
        if (!"".equals(name)) {//修改
            house.setName(name);
        }
        System.out.print("电话(" + house.getPhone() + "):");
        String phone = Utility.readString(12, "");
        if (!"".equals(phone)) {
            house.setPhone(phone);
        }
        System.out.print("地址(" + house.getAddress() + "):");
        String address = Utility.readString(18, "");
        if (!"".equals(address)) {
            house.setAddress(address);
        }
        System.out.print("租金(" + house.getRent() + "):");
        int rent = Utility.readInt(-1);
        if (rent != -1) {
            house.setRent(rent);
        }
        System.out.println("状态(" + house.getState() + "):");
        String state = Utility.readString(3, "");
        if (!"".equals(state)) {
            house.setState(state);
        }
        System.out.println("===========修改房屋信息成功=========");
    }
}

