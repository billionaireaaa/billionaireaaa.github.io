package AA.a;

import java.util.Objects;

public class teacher {
    private String name;
    private int id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        teacher teacher = (teacher) o;
        return id == teacher.id && Objects.equals(name, teacher.name);
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public teacher(String name, int id) {
        this.name = name;
        this.id = id;
    }
}
