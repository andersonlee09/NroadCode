import java.util.*;

/**
 * @author andersonLee
 * @version 1.0
 * @date 2022/3/12 13:41
 * @purpose null
 * @ModifiedRecords null
 */
public class Test {
    public static void main(String[] args) {
    }

    static public int[] getLeastNumbers(int[] arr, int k) {
        // 最小的K个数字用大顶堆
        int[] res = new int[k];
        Queue<Integer> queue = new PriorityQueue<>(((o1, o2) -> o2- o1));
        for (int e : arr) {
            // 当前数字小于堆顶元素才会入堆
            if (queue.isEmpty() || queue.size() < k || e < queue.peek()) {
                queue.add(e);
            }
            if (queue.size() > k) {
                queue.remove(); // 删除堆顶最大元素
            }
        }
        int cnt = 0;
        for (Integer integer : queue) {
            res[cnt++] = integer;
        }
        return res;
    }

    static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
    }

    public void dfs(TreeNode node) {
        if (node == null) return;
        System.out.println(node.val);
        dfs(node.left);
        dfs(node.right);
    }

}


