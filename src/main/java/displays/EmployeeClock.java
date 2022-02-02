package displays;

import java.util.Map;

import javax.swing.JOptionPane;

import employee.Employee;

/**
 * Responsible for the Employee Clock.
 * @author Ryan Sorge
 */
public class EmployeeClock extends TimeClock
{
    /**
     * Constructs an instance of this class.
     * @param employee
     * @param employees
     */
    public EmployeeClock(final Employee employee, final Map<String, Employee> employees)
    {
        super(employee, employees);
        hideButtons();
    }

    @Override
    void clockEmployeeIn()
    {
        if(employee.isClockedIn())
        {
            JOptionPane.showMessageDialog(jFrame, "Error: Already clocked in");
        }
        else
        {
            logEvent("clocked in at:");
            employee.setClockedIn(true);
            goBackToLoginScreen();
        }
    }

    @Override
    void startLunchBreak()
    {
        if(!employee.isClockedIn())
        {
            JOptionPane.showMessageDialog(jFrame, "Error: Not clocked in");
        }
        else if(employee.isOnLunch())
        {
            JOptionPane.showMessageDialog(jFrame, "Error: Already on lunch break");
        }
        else if(employee.isOnBreak())
        {
            JOptionPane.showMessageDialog(jFrame, "Error: Can't start lunch break while on a break");
        }
        else
        {
            logEvent("started lunch at:");
            employee.setOnLunch(true);
            goBackToLoginScreen();
        }
    }

    @Override
    void endLunchBreak()
    {
        if(!employee.isClockedIn())
        {
            JOptionPane.showMessageDialog(jFrame, "Error: Not clocked in");
        }
        else if(!employee.isOnLunch())
        {
            JOptionPane.showMessageDialog(jFrame, "Error: Not on lunch break");
        }
        else
        {
            logEvent("ended lunch at:");
            employee.setOnLunch(false);
            goBackToLoginScreen();
        }
    }

    @Override
    void startBreak()
    {
        if(!employee.isClockedIn())
        {
            JOptionPane.showMessageDialog(jFrame, "Error: Not clocked in");
        }
        else if(employee.isOnBreak())
        {
            JOptionPane.showMessageDialog(jFrame, "Error: Already on break");
        }
        else if(employee.isOnLunch())
        {
            JOptionPane.showMessageDialog(jFrame, "Error: Can't take a break while on lunch");
        }
        else
        {
            logEvent("started break at:");
            employee.setOnBreak(true);
            goBackToLoginScreen();
        }
    }

    @Override
    void endBreak()
    {
        if(!employee.isClockedIn())
        {
            JOptionPane.showMessageDialog(jFrame, "Error: Not clocked in");
        }
        else if(employee.isClockedIn() && !employee.isOnBreak())
        {
            JOptionPane.showMessageDialog(jFrame, "Error: Not on break");
        }
        else
        {
            logEvent("ended break at:");
            employee.setOnBreak(false);
            goBackToLoginScreen();
        }
    }

    @Override
    void clockEmployeeOut()
    {
        if(!employee.isClockedIn())
        {
            JOptionPane.showMessageDialog(jFrame, "Error: Not clocked in");
        }
        else
        {
            logEvent("clocked in at:");
            employee.setClockedIn(true);
            goBackToLoginScreen();
        }
    }

    /**
     * Hides certain buttons from the user to not allow certain actions.
     */
    private void hideButtons()
    {
        if(!employee.isClockedIn())
        {
            clockIn.setVisible(true);
            clockOut.setVisible(false);
            startLunch.setVisible(false);
            startBreak.setVisible(false);
            endLunch.setVisible(false);
            endBreak.setVisible(false);
        }
        else if(employee.isClockedIn() && !employee.isOnBreak() && !employee.isOnLunch())
        {
            clockIn.setVisible(false);
            clockOut.setVisible(true);
            startLunch.setVisible(true);
            startBreak.setVisible(true);
            endLunch.setVisible(false);
            endBreak.setVisible(false);
        }
        else if(employee.isClockedIn() && !employee.isOnBreak() && employee.isOnLunch())
        {
            clockIn.setVisible(false);
            clockOut.setVisible(false);
            startLunch.setVisible(false);
            startBreak.setVisible(false);
            endLunch.setVisible(true);
            endBreak.setVisible(false);
        }

        else if(employee.isClockedIn() && employee.isOnBreak() && !employee.isOnLunch())
        {
            clockIn.setVisible(false);
            clockOut.setVisible(false);
            startLunch.setVisible(false);
            startBreak.setVisible(false);
            endLunch.setVisible(false);
            endBreak.setVisible(true);
        }

        if(employee.getEvents().isEmpty())
        {
            showData.setVisible(false);
        }
        else
        {
            showData.setVisible(true);
        }
    }
}
