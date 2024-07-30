public class EmployeeManagementSystem {
    
    static class Employee {
        private int employeeId;
        private String name;
        private String position;
        private double salary;

        public Employee(int employeeId, String name, String position, double salary) {
            this.employeeId = employeeId;
            this.name = name;
            this.position = position;
            this.salary = salary;
        }

        public int getEmployeeId() {
            return employeeId;
        }

        public String getName() {
            return name;
        }

        public String getPosition() {
            return position;
        }

        public double getSalary() {
            return salary;
        }

        @Override
        public String toString() {
            return "Employee(id=" + employeeId + ", name=" + name + ", position=" + position + ", salary=" + salary + ")";
        }
    }

    private Employee[] employees;
    private int size;

    public EmployeeManagementSystem(int capacity) {
        employees = new Employee[capacity];
        size = 0;
    }

    public void addEmployee(Employee employee) {
        if (size >= employees.length) {
            System.out.println("Array is full. Cannot add more employees.");
            return;
        }
        employees[size++] = employee;
    }

    public Employee searchEmployee(int employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                return employees[i];
            }
        }
        return null;
    }

    public void traverseEmployees() {
        for (int i = 0; i < size; i++) {
            System.out.println(employees[i]);
        }
    }

    public void deleteEmployee(int employeeId) {
        int indexToRemove = -1;
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove == -1) {
            System.out.println("Employee not found.");
            return;
        }

        for (int i = indexToRemove; i < size - 1; i++) {
            employees[i] = employees[i + 1];
        }
        employees[size - 1] = null;
        size--;
    }

    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem(10);

        ems.addEmployee(new Employee(1, "Alice", "Developer", 60000));
        ems.addEmployee(new Employee(2, "Bob", "Manager", 80000));
        ems.addEmployee(new Employee(3, "Charlie", "Analyst", 55000));

        System.out.println("All Employees:");
        ems.traverseEmployees();

        System.out.println("\nSearching for employee with ID 2:");
        System.out.println(ems.searchEmployee(2));

        System.out.println("\nDeleting employee with ID 2:");
        ems.deleteEmployee(2);

        System.out.println("\nAll Employees after deletion:");
        ems.traverseEmployees();
    }
}
