package lambda;

/**
 * @author andersonLee
 * @version 1.0
 * @date 2022/3/4 13:20
 * @purpose null
 * @ModifiedRecords null
 */
public class Person {
    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public void run() {

    }
}
