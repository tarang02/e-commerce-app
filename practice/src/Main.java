import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
// Imagine you have a List<Employee> where each employee has an
// id, name, and salary. sort this list alphabetically by the employee's name.
public class Main {
    public static void main(String[] args) {
        Employee e1 = new Employee(1, "Varun", 1000);
        Employee e2 = new Employee(2, "Pratik", 500);
        Employee e3 = new Employee(3, "Shyam", 2000);
        Employee e4 = new Employee(4, "Aaabbbbbbcccccccddddddddeeeeeeeefffffff", 1500);
        Employee e5 = new Employee(5, "Z", 200);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(e4);
        employeeList.add(e5);
        employeeList.add(e1);
        employeeList.add(e2);
        employeeList.add(e3);

        for (Employee employee : employeeList) {
            System.out.println(employee.salary);
        }
        System.out.println("-----");
//        Collections.sort(employeeList);
        for (Employee employee : employeeList) {
            System.out.println(employee.salary);
        }
        for (int i = 0; i < 2; i++) {
            System.out.println(employeeList.get(i).salary);
        }

    }
//    public static class Employee implements Comparable<Employee>{
//        int id;
//        String name;
//        int salary;
//
//        public Employee(int id, String name, int salary) {
//            this.salary = salary;
//            this.id = id;
//            this.name = name;
//        }
//
//        @Override
//        public int compareTo(Employee o) {
//            return Integer.compare(o.salary, this.salary);
//        }
//    }
}