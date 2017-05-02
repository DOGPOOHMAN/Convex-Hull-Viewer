package implementAlgorithm;


/**
 * Created by B612B612 on 2017/5/2.
 */
public class GrahamScanBody {

    //宣告儲存convex hull上之點的stack
    class Point_Stack
    {
        int topindex;
        int index[];

        Point_Stack(int max)
        {
            index = new int[max];
            topindex = -1;
        }
        void Pop()
        {
            topindex--;
        }
        void Push(int i)
        {
            topindex++;
            index[topindex] = i;
        }
    }

    private int Maxpointnumber;
    private static final int Left_side = 1;
    private static final int Right_side = -1;
    private static final int collinear = 0;
    private boolean finalcheck = false;

    //宣告存放points之座標的array
    int xpoints[];
    int ypoints[];


    //宣告紀錄convex hull中所包含的點之stack
    Point_Stack extreme;
    //宣告紀錄目前輸入之點數的變數
    private int currentpoints = 0;


    public GrahamScanBody(int maxPointNumber)
    {
        this.Maxpointnumber = maxPointNumber;

        xpoints = new int[Maxpointnumber];
        ypoints = new int[Maxpointnumber];
        extreme = new Point_Stack(Maxpointnumber + 1);
    }


    //實際處理滑鼠在DrawingSection上畫點的函式
    void addpoint(int x, int y)
    {
        xpoints[currentpoints] = x;
        ypoints[currentpoints] = y;
        //處理完一點就累加已經畫上的點數
        currentpoints++;
        extreme.topindex = -1;
    }

    //處理清除螢幕與reset變數資料的動作
    void clear()
    {
        finalcheck = false;
        currentpoints = 0;
        extreme.topindex = -1;

    }

    //算出Graham_Scan所要scan的順序,以quick sort來排序
    public void sort()
    {

        //輸入的點至少要大於兩點以上
        if (currentpoints > 2)
        {
            //找出y軸上最低(數字少)且最左邊(你的左手邊)的點當作Graham_Scan的起點
            Find_YLowest();

            Quick_Sort(1, currentpoints - 1);
            //啟動Graham scan, 找出所有在convex hull上的點把他們存到stack裡面
            extreme.topindex = -1;
            Graham_Scan();
            //把找出來的convex hull上的點與點間畫線連接起來
        }

    }

    //找出y軸上最低且最右邊的點當作Graham_Scan的起點
    void Find_YLowest()
    {
        int xlowest;
        int ylowest;

        int k = 0;
        for (int i = 1; i < currentpoints; i++)
        {
            if (ypoints[i] < ypoints[k])
                k = i;
            else if (ypoints[i] == ypoints[k])
            {
                if (xpoints[i] > xpoints[k])
                    k = i;
            }
        }

        //將目前找到y軸最低且最右邊的點swap到(xpoints[0],ypoints[0])
        xlowest = xpoints[k];
        ylowest = ypoints[k];
        xpoints[k] = xpoints[0];
        ypoints[k] = ypoints[0];
        xpoints[0] = xlowest;
        ypoints[0] = ylowest;


    }

    //傳統的quick sort 排序法
    void Quick_Sort(int p, int q)
    {
        int r;

        if (p < q)
        {
            r = separate(p, q);
            Quick_Sort(p, r);
            Quick_Sort(r + 1, q);
        }
    }

    //利用divide and conquer的原理把原問題分成兩部分再個個擊破
    int separate(int p, int q)
    {
        int xp;
        int yp;
        int xtemp;
        int ytemp;

        xp = xpoints[p];
        yp = ypoints[p];
        int i = p - 1;
        int j = q + 1;

        while (true)
        {
            do
                j--;
            while (Judge(xp, yp, xpoints[j], ypoints[j]) == -1);

            do{

                if ((i + 1) < Maxpointnumber)
                    i = i + 1;
                else
                    break;
            }
            while (Judge(xp, yp, xpoints[i], ypoints[i]) == 1);

            if (i < j)
            {
                xtemp = xpoints[i];
                ytemp = ypoints[i];
                xpoints[i] = xpoints[j];
                ypoints[i] = ypoints[j];
                xpoints[j] = xtemp;
                ypoints[j] = ytemp;
            }
            else
            {
                xpoints[p] = xpoints[j];
                ypoints[p] = ypoints[j];
                xpoints[j] = xp;
                ypoints[j] = yp;
                break;
            }
        }
        return j;
    }

    //比較(x1,y1)與(x2,y2)的先後順序
    int Judge(int x1, int y1, int x2, int y2)
    {
        int xdist1;
        int xdist2;
        int ydist1;
        int ydist2;
        int side;


        side = Side_Of(xpoints[0], ypoints[0], x1, y1, x2, y2);

        if (side == Left_side)
            return -1;
        else if (side == Right_side)
            return 1;
        else
        {
            xdist1 = x1 - xpoints[0];
            ydist1 = y1 - ypoints[0];
            xdist2 = x2 - xpoints[0];
            ydist2 = y2 - ypoints[0];

            if ((xdist1 * xdist1 + ydist1 * ydist1) < (xdist2 * xdist2 + ydist2 * ydist2))
                return -1;
            else
                return 1;
        }
    }

    //判斷下一個要加入stack中的點是否符合convex hull之要求
    //"Right_side", 表示如果輸入這個點之後會有凹陷的情形產生
    int Side_Of(int x1, int y1, int x2, int y2, int x3, int y3)
    {
        int value;

        value = x1 * y2 - y1 * x2 + y1 * x3 - x1 * y3 + x2 * y3 - x3 * y2;

        if (value > 0)
            return Left_side;
        else if (value == 0)
            return collinear;
        else
            return Right_side;
    }

    //實際處理Graham_Scan的函式
    void Graham_Scan()
    {
        this.extreme.Push(this.currentpoints-1);
        this.extreme.Push(0);

        int i = 1;
        while(i < currentpoints) {

            //判斷輸入的點是否符合convex hull之要求
            if (Side_Of(xpoints[extreme.index[extreme.topindex-1]],
                    ypoints[extreme.index[extreme.topindex-1]],
                    xpoints[extreme.index[extreme.topindex]],
                    ypoints[extreme.index[extreme.topindex]],
                    xpoints[i], ypoints[i]) == Left_side)
            {
                //符合要求,加入此點
                extreme.Push(i);
                i++;
            }
            //不合要求,不存
            else {
                extreme.Pop();
            }

        }
        finalcheck = true;
    }

}
