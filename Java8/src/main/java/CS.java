import java.util.ArrayList;
import java.util.List;

/**
 * @author andersonLee
 * @version 1.0
 * @date 2022/3/15 10:36
 * @purpose null
 * @ModifiedRecords null
 */
public class CS {
    public static void main(String[] args) {
        int i = 0, cnt = 1;
//        print(i, cnt);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        System.out.println();
    }


    static void print(int i, int cnt) {
        System.out.println("i: " + i + " ================= cnt:" + cnt);
        print(i + 1, cnt * 2);
    }
}
