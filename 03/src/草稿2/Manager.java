package AA;

public class Manager extends Employee {
    private double bonus;
    public void manage()
    {
        System.out.println("经理："+this.getName()+"管理着公司");
    }

    @Override
    public void work() {
        System.out.println("经理在工作");
    }

    @Override
    public double getAnnual() {
        System.out.printf("经理的工资：");
        return super.getAnnual()+bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public Manager(String name, double salary, double bonus) {
        super(name, salary);
        this.bonus = bonus;
    }
}
