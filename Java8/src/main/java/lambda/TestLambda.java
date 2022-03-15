package lambda;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author andersonLee
 * @version 1.0
 * @date 2022/3/4 10:39
 * @purpose null
 * @ModifiedRecords null
 */
public class TestLambda {
    @Test
    public void threadTest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("run.");
            }
        }).start();
    }
    @Test
    public void threadTest1() {
        new Thread(()-> System.out.println("run...")).start();
    }
    @Test
    public void threadTest2() {
        List persons = new ArrayList();
        persons.add(new Person("1", 3));
        persons.add(new Person("1", 2));
        persons.add(new Person("1", 3));
        persons.add(new Person("1", 4));
        Collections.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o2.getAge() - o1.getAge();
            }
        });
        Collections.sort(persons, ((Person o1, Person o2) -> o1.getAge() - o2.getAge()));
        Collections.sort(persons, (Comparator.comparingInt(Person::getAge)));
    }
}
