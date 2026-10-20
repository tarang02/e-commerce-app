import java.util.Comparator;

public class Employee{
    int id;
    String name;
    int salary;

    public Employee(int id, String name, int salary) {
        this.salary = salary;
        this.id = id;
        this.name = name;
    }

    public static class salaryComparator implements Comparator<Employee>{


        @Override
        public int compare(Employee o1, Employee o2) {
            return 0;
        }
    }
}
