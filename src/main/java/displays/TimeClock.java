package displays;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import employee.Employee;

/**
 * Responsible for the user being able to clock in and out of shifts.
 * @author Ryan Sorge
 */
public abstract class TimeClock extends JFrame
{
    protected final Employee employee;
    protected final Map<String, Employee> employees;
    JMenuBar heading = new JMenuBar();
    JPanel display = new JPanel();
    Font large = new Font("Arial", Font.BOLD, 30);
    Font medium = new Font("Arial", Font.BOLD, 15);

    JButton clockIn = new JButton("Clock In");
    JButton clockOut = new JButton("Clock Out");
    JButton startLunch = new JButton("Start Lunch");
    JButton startBreak = new JButton("Start Break");
    JButton endLunch = new JButton("End Lunch");
    JButton endBreak = new JButton("End Break");
    JButton showData = new JButton("Show Log");
    JButton backButton = new JButton("Back");
    JFrame jFrame = new JFrame();

    TimeClock(final Employee employee, final Map<String, Employee> employees)
    {
        this.employee = employee;
        this.employees = employees;

        // sets title and layout
        setTitle("Time Clock Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout()); // layout
        setJMenuBar(heading); // sets top JMenuBar
        add(display); // JPanel
        // sets layout to grid

        // adds JLabels and sets font
        JLabel title = new JLabel("Welcome to Paychex: " + employee.getFullName());
        title.setFont(large);
        display.add(title);
        title.setFont(large);

        SimpleDigitalClock digitalClock = new SimpleDigitalClock();
        add(digitalClock);
        add(clockIn);
        add(showData);
        add(startLunch);
        add(startBreak);
        add(clockOut);
        add(endBreak);
        add(endLunch);
        add(backButton);

        clockIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                clockEmployeeIn();
            }
        });

        startLunch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                startLunchBreak();
            }
        });

        endLunch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                endLunchBreak();
            }
        });

        startBreak.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                startBreak();
            }
        });

        endBreak.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                endBreak();
            }
        });

        clockOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                clockEmployeeOut();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                goBackToLoginScreen();
            }
        });

        showData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String log = "";
                for(String s : employee.getEvents())
                {
                    log += s + "\n";
                }
                JOptionPane.showMessageDialog(jFrame, log);
            }
        });

        setVisible(true);
    }

    /**
     * Clocks user in.
     */
    abstract void clockEmployeeIn();

    /**
     * Starts user's lunch break.
     */
    abstract void startLunchBreak();

    /**
     * Starts user's break.
     */
    abstract void startBreak();

    /**
     * Ends a user lunch break.
     */
    abstract void endLunchBreak();

    /**
     * Ends a user break.
     */
    abstract void endBreak();

    /**
     * Clocks a user out.
     */
    abstract void clockEmployeeOut();

    /**
     * Returns frame back to login screen.
     */
    void goBackToLoginScreen()
    {
        dispose();
        EmployeeLogin window = new EmployeeLogin(employees);
        window.setSize(600, 200);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    /**
     * Logs each event to the corresponding user's log.
     * @param text
     */
    void logEvent(String text)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        String event = employee.getFullName() + " " + text + " " + formatter.format(date);
        System.out.println(event);
        employee.getEvents().add(event);
    }
}
