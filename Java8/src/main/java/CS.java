import java.util.*;

/**
 * @author andersonLee
 * @version 1.0
 * @date 2022/3/15 10:36
 * @purpose null
 * @ModifiedRecords null
 */
public class CS {
    public static void main(String[] args) {
        int i = 0;
        int i1 = i++ + ++i;
        System.out.println(i1);
//        print(2, 2);



    }


    static void print(int i, int cnt) {
        System.out.println("i: " + i + " ================= cnt:" + cnt);
        print(i + 1, cnt * 2);


    }
}
