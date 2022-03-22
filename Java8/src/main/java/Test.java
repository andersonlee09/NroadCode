import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author andersonLee
 * @version 1.0
 * @date 2022/3/12 13:41
 * @purpose null
 * @ModifiedRecords null
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(isPalindrome(121));
    }

    public static boolean isPalindrome(int x) {
        if (x < 0) return false;
        int cur = 0;
        while (x > 0) {
            cur = cur * 10 + (x % 10);
            x /= 10;
        }
        return cur == x;
    }


    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        // 这道题给出两种思路
        // 第一种：对每一行都进行二分查找,如果target大于第一个数或者小于最后一个数，那就退出
        // 第二种：让初始位于左下角或者右上角，然后不断递进就可以了
        if (matrix.length == 0 || matrix[0].length == 0) return false;
        int x = matrix.length - 1, y = 0;
        while (x > 0 && y < matrix[0].length) {
            if (matrix[x][y] == target) {
                return true;
            } else if (matrix[x][y] > target) {
                x--;
            } else {
                y++;
            }
        }
        return false;
    }

    public static int nthUglyNumber(int n) {
        int a = 0, b = 0, c = 0;
        int[] dp = new int[n];
        dp[0] = 1;
        int cnt = 1;
        while (cnt < n) {
            int temp = Math.min(2 * dp[a], Math.min(3 * dp[b], 5 * dp[c]));
            if (temp == 2 * dp[a]) {
                a++;
            } else if (temp == 3 * dp[b]) {
                b++;
            } else {
                c++;
            }
            dp[cnt] = temp;
            cnt++;
        }
        return dp[n-1];
    }

}
