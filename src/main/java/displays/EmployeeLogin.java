package displays;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
 * Responsible for Employee Login.
 * @author Ryan Sorge
 */
public class EmployeeLogin extends JFrame implements KeyListener
{
    JMenuBar heading = new JMenuBar();
    JPanel display = new JPanel();
    Font large = new Font("Arial", Font.BOLD, 30);
    Font medium = new Font("Arial", Font.BOLD, 15);
    JLabel title = new JLabel("Welcome to Paychex");
    JLabel description = new JLabel("Enter your worker ID: ");
    JTextField textField = new JTextField("", 8);
    JButton registerButton = new JButton("Register New Employee");
    JFrame jFrame = new JFrame();

    Map<String, Employee> employees = new HashMap<String, Employee>();

    /**
     * Constructs an instance of this class.
     * @param employees
     */
    public EmployeeLogin(final Map<String, Employee> employees)
    {
        this.employees = employees;
        // sets title and layout
        setTitle("Time Clock Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout()); // layout

        setJMenuBar(heading); // sets top JMenuBar
        add(display); // JPanel
        // sets layout to grid
        display.setLayout(new GridLayout(5, 1));

        // adds JLabels and sets font
        display.add(title);
        title.setFont(large);
        display.add(description);
        description.setFont(medium);
        // PIN text field
        display.add(textField);
        textField.addKeyListener(this);
        // limits input to 8 characters
        textField.setDocument(new JTextFieldLimit(8));

        // Creates register user button button
        add(registerButton);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                EmployeeRegister employeeRegister = new EmployeeRegister(employees);
                employeeRegister.setSize(750, 300);
                employeeRegister.setLocationRelativeTo(null);
                employeeRegister.setVisible(true);
            }
        });
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        // When enter key is pressed
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            String enteredString = textField.getText();
            if(!employees.containsKey(enteredString))
            {
                JOptionPane.showMessageDialog(jFrame, "Worker ID not found");
            }
            else
            {
                JFrame jFrame = new JFrame();
                String result = showPasswordPrompt("Enter Password");
                if(result == null || !result.equals(employees.get(enteredString).getPassword()))
                {
                    JOptionPane.showMessageDialog(jFrame, "Password Incorrect");
                }
                else
                {
                    if(employees.get(enteredString).getPosition() == Position.Employee)
                    {
                        dispose();
                        EmployeeClock employeeClock = new EmployeeClock(employees.get(enteredString), employees);
                        employeeClock.setSize(700, 750);
                        employeeClock.setLocationRelativeTo(null);
                        employeeClock.setVisible(true);
                    }
                    if(employees.get(enteredString).getPosition() == Position.Administrator)
                    {
                        dispose();
                        AdminClock adminClock = new AdminClock(employees.get(enteredString), employees);
                        adminClock.setSize(700, 750);
                        adminClock.setLocationRelativeTo(null);
                        adminClock.setVisible(true);
                    }
                }
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        // TODO Auto-generated method stub

    }

    /**
     * Creates a password prompt when logging in.
     * @param title
     * @return password
     */
    public String showPasswordPrompt(String title)
    {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setEchoChar('*');
        passwordField.setColumns(20);

        int returnVal = JOptionPane.showConfirmDialog(this, passwordField, title, JOptionPane.OK_CANCEL_OPTION);

        if(returnVal == JOptionPane.OK_OPTION)
        {
            return new String(passwordField.getPassword());
        }
        else
        {
            return null;
        }
    }
}