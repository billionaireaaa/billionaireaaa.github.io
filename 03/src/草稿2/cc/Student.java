package AA.cc;

public class Student extends Person {
    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public Student(String name, String sex, int age, String stu_id) {
        super(name, sex, age);
        this.stu_id = stu_id;
    }

    public void study()
    {
        System.out.println("好好学习");
    }
    private String stu_id;

    @Override
    public String play(Person a) {
        return super.play(a)+"足球";
    }



    @Override
    public String toString() {
        return super.toString()+", stu_id='" + stu_id ;
    }
}
