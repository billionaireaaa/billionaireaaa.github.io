package com.houserent.service;

import com.houserent.domain.House;

public class HouseService {
    private House[] houses;
    private int houseNums = 1;//记录房子的数量
    private int idCounter = 1;//用于房屋编号的计数 自增长

    public HouseService(int size) {
        houses = new House[size];
        houses[0] = new House(1, "小郭", "123", "时代广场", 1500, "未出租");//先假设一个数据
    }

    public House[] list() {
        return houses;
    }

    public boolean add(House newHouse) {
        if (houseNums == houses.length) {
            System.out.println("房屋信息已满，无法添加。");
            return false;
        }
        houses[houseNums++] = newHouse;//加入新的房子
        newHouse.setId(++idCounter);//保证了id的自增长
        return true;
    }

    public boolean del(int delId) {
        int index = -1;
        for (int i = 0; i < houseNums; i++) {
            if (delId == houses[i].getId()) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return false;
        } else {
            for (int i = index; i < houseNums - 1; i++) {
                houses[i] = houses[i + 1];//删除房子的关键步骤
            }
            houses[houseNums - 1] = null;//把删除后空余下来的最后一个位置置空
            houseNums--;
            return true;
        }
    }
    public House findById(int findId)
    {
        for (int i = 0; i < houseNums; i++) {
            if (findId==houses[i].getId())
                return houses[i];
        }
        return null;
    }

}
