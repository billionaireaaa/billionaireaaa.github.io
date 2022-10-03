package 数据结构与算法;

//迷宫
public class MiGong {
    public static void main(String[] args) {
        int[][] map = new int[8][7];
        //1表示墙壁
        //迷宫的建立
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        setWay(map, 1, 1);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    //说明：
    /*1.要规定好小球的出发位置【1】【1】，和结束位置【6】【5】
     * 2.1表示墙不能走，2表示通路，3表示死路，0表示还未走过的路
     * 3.按照一定的策略寻找出口例如 下 右 上 左*/
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2)
            return true;
        else {
            if (map[i][j] == 0) {
                map[i][j] = 2;
                if (setWay(map, i + 1, j))//下
                    return true;
                else if (setWay(map, i, j + 1))//右
                    return true;
                else if (setWay(map, i - 1, j))//上
                    return true;
                else if (setWay(map, i, j - 1))//左
                    return true;
                else {
                    map[i][j] = 3;
                    return false;
                }
            } else //表示此位置走不了了或已经选择过了
                return false;
        }
    }
}
