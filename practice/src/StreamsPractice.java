import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamsPractice {

    static class Employee {
        private String name;
        private String department;
        private double salary;
        private String gender;

        public Employee(String name, String department, double salary, String gender) {
            this.name = name;
            this.department = department;
            this.salary = salary;
            this.gender = gender;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        // Constructor, getters, setters
    }
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Ross", "Rachel", "Joey", "Chandler", "Monika", "Phoebe");
        list.stream().filter(x -> x.length()==4).forEach(System.out::println);

        List<Integer> listInt = Arrays.asList(11,12,44,55,101,111,108,153,100,700,800);
        listInt.stream().map(x -> String.valueOf(x))
                .filter(x-> x.startsWith("1")).forEach(System.out::println);
        listInt.stream().filter(x -> String.valueOf(x).startsWith("1")).forEach(System.out::println);


        int a = 12345;
        int sum = String.valueOf(a)
                .chars()
                .map(c -> c - '0')
                .sum();
        System.out.println(sum);

        sum = String.valueOf(a)
                .chars()
                .map(Character::getNumericValue)
                .sum();
        System.out.println(sum);

        //Flatten list
        // [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
        // output [1,2,3,4,5,6,7,8,9]
        List<List<Integer>> lists = new ArrayList<>();
        lists.add(Arrays.asList(1,2,3));
        lists.add(Arrays.asList(4,5,6));
        lists.add(Arrays.asList(7,8,9));

        List<Integer> out = lists.stream().flatMap(x -> x.stream()).collect(Collectors.toList());
        System.out.println(out);

        // Check if two strings are anagrams,
        //Ex: ant and tan
        //Make it case insensi
        String s1 = "helLo";
        String s2 = "lelho";

        boolean isAnagram = s1.length() == s2.length() &&
                s1.chars()
                        .sorted()
                        .boxed() // we xan use mapToObject( c -> (char)c) as well
                        .collect(Collectors.toList())
                        .equals(
                                s2.chars()
                                        .sorted()
                                        .boxed()
                                        .collect(Collectors.toList())
                        );

        System.out.println(isAnagram);

        Map<Character, Long> map1 = s1.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()));

        Map<Character, Long> map2 = s2.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()));

        System.out.println(map1.equals(map2)); // true

        // in single line and case insensitive
        System.out.println(s1.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Character::toLowerCase,
                        Collectors.counting())).equals(s2.chars()
                        .mapToObj(c -> (char) c)
                        .collect(Collectors.groupingBy(
                                Character::toLowerCase,
                                Collectors.counting()))));


// Employee class exists with fields name, department, salary, and gender.
//Group employees by department
        List<Employee> employees = List.of(
                new Employee("Alice", "IT", 70000, "Female"),
                new Employee("Bob", "HR", 50000, "Male"),
                new Employee("Charlie", "IT", 80000, "Male"),
                new Employee("David", "Finance", 90000, "Male"),
                new Employee("Eva", "HR", 60000, "Female")
        );
        Map<String, List<Employee>> employeesByDepartment = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));
        employeesByDepartment.forEach((department, employeess) ->
                System.out.println(department + " -> " +
                        employeess.stream()
                                .map(Employee::getName)
                                .toList())
        );

        Map<String, List<String>> employeesByDepartment2 = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.mapping(
                                Employee::getName,
                                Collectors.toList()
                        )
                ));
        System.out.println(employeesByDepartment2);



        // How do you remove duplicate elements from a list using Java 8 streams

        List<String> list2 = Arrays.asList("Ross", "Rachel", "Joey", "Chandler", "Monika", "Phoebe", "Ross", "Rachel", "Joey");
        System.out.println(list2);
        List<String> dist = list2.stream().distinct().collect(Collectors.toList());
        System.out.println(dist);
    }

}
