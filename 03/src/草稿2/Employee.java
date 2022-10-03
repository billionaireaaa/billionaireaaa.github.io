package AA;

public abstract class Employee {
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
    abstract public void work();
    private String name;
    private double salary;

    public double getAnnual() {
        return 12 * salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
}
