package map;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

/**
 * @author andersonLee
 * @version 1.0
 * @date 2022/3/4 15:13
 * @purpose null
 * @ModifiedRecords null
 */
public class StreamTest {
    @Test
    public void testMap() {
        // 转化为流式数据
        Stream<String> original = Stream.of("11", "22", "33");
        // 流式数据利用map进行映射
        Stream<Integer> result = original.map(Integer::parseInt);
        // 进行遍历
        result.forEach(s -> System.out.println(s + 10));
    }

}
