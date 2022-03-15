package stream;

import lambda.Person;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author andersonLee
 * @version 1.0
 * @date 2022/3/4 14:31
 * @purpose null
 * @ModifiedRecords null
 */
public class TestStream {
    @Test
    public void run() {
        // 集合类stream
        ArrayList<Object> list = new ArrayList<>();
        list.add(new Person("miss", 22));
        Stream<Object> stream = list.stream();
        System.out.println(stream);
        // map类stream
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "str1");
        map.put(2, "str2");
        map.put(3, "str3");
        System.out.println(map.values());
        Stream<String> stream1 = map.values().stream();

    }
}
