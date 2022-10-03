package AA;

public class Worker extends Employee{
    public Worker(String name, double salary) {
        super(name, salary);
    }
    public void work()
    {
        System.out.println("员工："+super.getName()+"正在工作");
    }

    @Override
    public double getAnnual() {
        System.out.printf("员工的工资：");
        return super.getAnnual();
    }
}
