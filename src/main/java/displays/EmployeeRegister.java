package displays;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import common.JTextFieldLimit;
import employee.Employee;
import employee.Position;

/**
 * Responsible for registering a new user to the application.
 * @author Ryan Sorge
 */
public class EmployeeRegister extends JFrame
{
    private static final String FILE_NAME = "C:\\Users\\RS071903\\employees.txt";
    JMenuBar heading = new JMenuBar();
    JPanel display = new JPanel();
    Font large = new Font("Arial", Font.BOLD, 30);
    Font medium = new Font("Arial", Font.BOLD, 15);
    JLabel workerId = new JLabel("Enter your worker ID (Max 8 char): ");
    JLabel fullName = new JLabel("Enter your full name: ");
    JLabel password = new JLabel("Enter your password: ");
    JTextField userIdTextField = new JTextField("", 8);
    JTextField fullNameTextField = new JTextField("", 8);
    JPasswordField passwordTextField = new JPasswordField("", 8);
    JButton registerButton = new JButton("Register New Employee");
    JButton backButton = new JButton("Back");
    JFrame jFrame = new JFrame();

    Map<String, Employee> employees = new HashMap<String, Employee>();

    /**
     * Constructs an instance of this class.
     * @param employees the map of employees
     */
    public EmployeeRegister(final Map<String, Employee> employees)
    {
        this.employees = employees;
        // sets title and layout
        setTitle("Time Clock Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setLayout(new FlowLayout()); // layout

        setJMenuBar(heading); // sets top JMenuBar
        getContentPane().add(display); // JPanel
        getContentPane().add(dislay2);
        // sets layout to grid
        display.setLayout(new GridLayout(6, 1));

        // adds JLabels and sets font
        display.add(workerId);
        workerId.setFont(large);
        display.add(userIdTextField);
        // limits input to 8 characters
        userIdTextField.setDocument(new JTextFieldLimit(8));

        display.add(fullName);
        fullName.setFont(large);
        display.add(fullNameTextField);

        display.add(password);
        password.setFont(large);
        display.add(passwordTextField);

        getContentPane().add(registerButton);
        registerButton.setVisible(true);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String userId = userIdTextField.getText();
                String name = fullNameTextField.getText();
                String password = String.valueOf(passwordTextField.getPassword());
                if(userId.length() <= 0)
                {
                    JOptionPane.showMessageDialog(jFrame, "UserID cannot be empty");
                }
                else if(name.length() <= 0)
                {
                    JOptionPane.showMessageDialog(jFrame, "Name cannot be empty");
                }
                else if(employees.containsKey(userId))
                {
                    JOptionPane.showMessageDialog(jFrame, "Worker ID already exits");
                }
                else if(password.length() <= 0)
                {
                    JOptionPane.showMessageDialog(jFrame, "Password cannot be empty");
                }
                else
                {
                    Employee employee = new Employee(userId, name, password, Position.Employee);
                    employees.put(userId, employee);
                    writeToFile(userId, name, password);
                    dispose();
                    EmployeeLogin employeeLogin = new EmployeeLogin(employees);
                    employeeLogin.setSize(600, 200);
                    employeeLogin.setLocationRelativeTo(null);
                    employeeLogin.setVisible(true);
                }
            }
        });

        add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                EmployeeLogin employeeLogin = new EmployeeLogin(employees);
                employeeLogin.setSize(600, 200);
                employeeLogin.setLocationRelativeTo(null);
                employeeLogin.setVisible(true);
            }
        });

    }

    /**
     * Writes new user to text file.
     * @param userId the user id. Cannot be blank.
     * @param fullName the full name. Cannot be blank.
     * @param password the password. Cannot be blank.
     */
    private void writeToFile(String userId, String fullName, String password)
    {
        File file = new File(FILE_NAME);
        FileWriter fr;
        try
        {
            fr = new FileWriter(file, true);
            fr.write("\n" + userId + "," + fullName + "," + password + "," + Position.Employee.toString());
            System.out.println("Successfully registered user:" + fullName);
            fr.close();
        }
        catch(IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}