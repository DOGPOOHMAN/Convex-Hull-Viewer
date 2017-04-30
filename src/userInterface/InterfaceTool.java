package userInterface;

import convexAlgorithm.Point;

import java.time.Clock;
import java.util.List;
import java.util.Random;

/**
 * Created by rick-lee on 2017/4/30.
 */
public class InterfaceTool {

    public static int autoAddPoint(List<Point>pointList, int addAmount, int xAxelBound, int yAxelBound){

        if (addAmount <= 0) return 0;
        int maxPoint = (xAxelBound + 1) * (yAxelBound + 1);
        int maxAddAmount = maxPoint - pointList.size();//還能加入的數量

        if (addAmount > maxAddAmount) return -1;//無法加入那麼多數量的點

        int xAxel, yAxel, genAmount;
        Random random = new Random();

        genAmount = 0;

        while(genAmount != addAmount){

            long seed = System.currentTimeMillis();
            random.setSeed(seed);
            //nextInt(z) return [0,z)
            xAxel = random.nextInt(xAxelBound + 1);
            yAxel = random.nextInt(yAxelBound + 1);

            Point temp = new Point(xAxel, yAxel);
            boolean exist = false;
            //test random point had existed in list?
            for (Point point : pointList) {
                if (temp.equals(point)){
                    exist = true;
                    break;
                }
            }

            if (!exist) {
                pointList.add(temp);
                genAmount++;
            }
        }

        return addAmount;
    }
}
