package AA.cc;

public class text {
    public static void main(String[] args) {
        Person[] people = new Person[4];
        people[0] = new Student("小郭", "女", 21, "123");
        people[1] = new Student("小王", "男", 20, "456");
        people[2] = new Teacher("大郭", "女", 27, "321");
        people[3] = new Teacher("大王", "男", 26, "654");
        sortByAge(people);
        for (int i = 0; i < people.length; i++) {
            System.out.println(people[i]);
            sw(people[i]);
        }

    }

    public static void sortByAge(Person[] person) {
        Person temp = null;
        for (int i = 0; i < person.length - 1; i++) {//比较几轮
            for (int j = 0; j < person.length - 1 - i; j++) {//每轮和几个数进行比较
                if (person[j].getAge() > person[j + 1].getAge()) {
                    temp = person[j];
                    person[j] = person[j + 1];
                    person[j + 1] = temp;
                }
            }
        }

    }
    public static void sw(Person a)
    {
        if (a instanceof Teacher)
            ((Teacher) a).teach();
        if (a instanceof Student)
            ((Student) a).study();
    }
}
