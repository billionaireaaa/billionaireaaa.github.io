package AA;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DAO<T> {

        Map<String,T> map=new HashMap<>();
        @Test
        public void save(String id, T entity)
        {
            map.put(id, entity);
        }
        @Test
        public T get(String id)
        {
            return map.get(id);
        }
        @Test
        public void update(String id,T entity)
        {
            map.put(id, entity);
        }
        @Test
        public List<T> list()
        {
            return (List<T>) map.keySet();
        }
        @Test
        public void delete(String id)
        {
            map.remove(id);
        }


    public static void main(String[] args) {

    }

}
class User {
    private int id;
    private int age;
    private String name;

    public User(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }
}

