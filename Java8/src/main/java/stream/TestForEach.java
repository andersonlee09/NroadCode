package stream;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author andersonLee
 * @version 1.0
 * @date 2022/3/4 14:56
 * @purpose null
 * @ModifiedRecords null
 */
public class TestForEach {
    public static void main(String[] args) {


    }

    @Test
    public void testForEach() {
        List<String> one = new ArrayList<>();
        Collections.addAll(one, "迪丽热巴", "宋远桥", "苏星河", "老子", "庄子", "孙子");
        one.stream().forEach((String s) -> {
            System.out.println(s);
        });
        // 简写
        one.stream().forEach(s -> System.out.println(s));
        one.stream().forEach(System.out::println);
        one.forEach(System.out::println);
    }
}
