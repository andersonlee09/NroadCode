package map;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

/**
 * @author andersonLee
 * @version 1.0
 * @date 2022/3/4 15:53
 * @purpose null
 * @ModifiedRecords null
 */
public class MatchTest {
    @Test
    public void testMatch() {
        boolean b = Stream.of(5, 3, 6, 1)
// .allMatch(e -> e > 0); // allMatch: 元素是否全部满足条件
// .anyMatch(e -> e > 5); // anyMatch: 元素是否任意有一个满足条件
                .noneMatch(e -> e < 0); // noneMatch: 元素是否全部不满足条件
        System.out.println("b = " + b);
    }
    @Test
    public void testReduce() {
        int reduce = Stream.of(4, 5, 3, 9)
                .reduce(0, (a, b) -> {
                    System.out.println("a = " + a + ", b = " + b);
                    return a + b;
                });
        System.out.println(reduce);
    }
}
