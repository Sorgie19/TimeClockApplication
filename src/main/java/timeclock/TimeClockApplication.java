package timeclock;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import displays.EmployeeLogin;
import employee.Employee;
import employee.Position;

/**
 * Responsible for the main TimeClockApplication.
 * @author Ryan Sorge (RS071903)
 */
public class TimeClockApplication
{
    /**
     * Main program.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {
        Map<String, Employee> employees = new HashMap<String, Employee>();
        readEmployees(employees);
        EmployeeLogin employeeLogin = new EmployeeLogin(employees);
        employeeLogin.setSize(600, 200);
        employeeLogin.setLocationRelativeTo(null);
        employeeLogin.setVisible(true);
    }

    /**
     * Reads employees from a text file in the following format UserId,Full Name, Password, Position
     * (<code>Employee</code> or Administrator).
     * @param employees the map of employees
     * @throws IOException
     */
    private static void readEmployees(Map<String, Employee> employees) throws IOException
    {
        File file = new File("C:\\Users\\RS071903\\employees.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        while((line = bufferedReader.readLine()) != null)
        {
            List<String> split = Arrays.asList(line.split(","));
            Employee employee = new Employee(split.get(0), split.get(1), split.get(2), Position.valueOf(split.get(3)));
            employees.put(employee.getId(), employee);

        }
        bufferedReader.close();
    }
}