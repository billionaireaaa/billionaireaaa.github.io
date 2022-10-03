package 草稿.zxc;

import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@SuppressWarnings("all")
public class test {
    public static void main(String[] args) {
    }
    @Test
    public void m()
    {
        DAO<User> userDAO = new DAO<>();
        userDAO.save("001",new User(1,11,"小明"));
        userDAO.save("002",new User(2,22,"小红"));
         List a=userDAO.list();
        System.out.println(a);

    }
}
class DAO<T>
{
    @Override
    public String toString() {
        return "DAO{" +
                "map=" + map +
                '}';
    }
    Map<String,T> map=new HashMap<>();
    public DAO() {
        this.map = new HashMap<>();
    }
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
        List<T> l=new ArrayList<>();
        Set<String> strings = map.keySet();
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            String next =  iterator.next();
            l.add(map.get(next));
        }
        return l;

    }
    @Test
    public void delete(String id)
    {
        map.remove(id);
    }
}
class User
{
    private int id;
    private int age;
    private String name;

    public User(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
