package AA.cc;

public class Teacher extends Person {
    public String getWork_id() {
        return work_id;
    }

    public void setWork_id(String work_id) {
        this.work_id = work_id;
    }

    public Teacher(String name, String sex, int age, String work_id) {
        super(name, sex, age);
        this.work_id = work_id;
    }
public void teach()
{
    System.out.println("认真教学");
}
    private String work_id;

    @Override
    public String play(Person a) {
        return super.play(a)+"象棋";
    }

    @Override
    public String toString() {
        return super.toString()+", +work_id="+work_id;
    }
}
