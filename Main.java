import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import company.models.Manager;
import company.models.Worker;
import company.abstracts.Employee;

public class Main {
    public static void main(String[] args) {
        Worker worker1 = new Worker("John", 2000, 1, "2022-01-01", "Junior Developer");
        Worker worker2 = new Worker("Alice", 1800, 2, "2023-03-15", "Senior Developer");
        Worker worker3 = new Worker("Bob", 1900, 3, "2020-12-10", "Tester");
        Worker worker4 = new Worker("Eve", 2100, 4, "2021-06-20", "Project Manager");
        Worker worker5 = new Worker("Tom", 2200, 3, "2021-06-20", "Project Manager"); // Nowa instancja Worker z tym
                                                                                      // samym id co worker3
        Manager manager1 = new Manager("Michael", 5000, 5, "2019-05-10", "Department Head");
        Manager manager2 = new Manager("Anna", 5500, 1, "2019-05-10", "CTO"); // Nowa instancja Manager z tym samym id
                                                                              // co worker1

        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(worker1);
        employees.add(worker2);
        employees.add(worker3);
        employees.add(worker4);
        employees.add(worker5);
        employees.add(manager1);
        employees.add(manager2);

        double totalSalary = 0;
        double totalManagerSalary = 0;
        double totalWorkerSalary = 0;

        // Mapa do przechowywania pracowników o tym samym id
        Map<Integer, List<Employee>> idMap = new HashMap<>();
        for (Employee emp : employees) {
            totalSalary += emp.getSalary();
            if (emp instanceof Manager) {
                totalManagerSalary += emp.getSalary();
            } else if (emp instanceof Worker) {
                totalWorkerSalary += emp.getSalary();
            }
            // Sprawdź, czy pracownik o tym samym id już istnieje w mapie
            if (idMap.containsKey(emp.getId())) {
                idMap.get(emp.getId()).add(emp);
            } else {
                List<Employee> empList = new ArrayList<>();
                empList.add(emp);
                idMap.put(emp.getId(), empList);
            }
        }

        // Wyświetlanie informacji o sumach pensji
        System.out.println("Total salary: " + totalSalary);
        System.out.println("Total manager salary: " + totalManagerSalary);
        System.out.println("Total worker salary: " + totalWorkerSalary);

        // Wyświetlanie pracowników o tym samym id
        for (Map.Entry<Integer, List<Employee>> entry : idMap.entrySet()) {
            List<Employee> empList = entry.getValue();
            if (empList.size() > 1) {
                System.out.println("Employees with same ID: ");
                for (Employee emp : empList) {
                    System.out.println("- " + emp.getName());
                }
            }
        }
    }
}