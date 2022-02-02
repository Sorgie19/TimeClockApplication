package employee;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for Employee data.
 * @author Ryan Sorge
 */
public class Employee
{
    private final String id;
    private final String fullName;
    private final String password;
    private Position position;
    private boolean clockedIn;
    private boolean onLunch;
    private boolean onBreak;
    private final List<String> events;

    /**
     * Constructs an instance of this class.
     * @param id the user identifier
     * @param fullName the full name of the user
     * @param password the password of the user
     * @param position the position of the user
     */
    public Employee(String id, String fullName, String password, Position position)
    {
        this(id, fullName, password, position, false, false, false);
    }

    /**
     * Constructs an instance of this class.
     * @param id the user identifier
     * @param fullName the full name of the user
     * @param password the password of the user
     * @param position the position of the user
     * @param clockedIn determines if user is clocked in
     * @param onLunch determines if user is on lunch break
     * @param onBreak determines if user is on break
     */
    public Employee(String id, String fullName, String password, Position position, boolean clockedIn, boolean onLunch,
            boolean onBreak)
    {
        this.id = id;
        this.fullName = fullName;
        this.password = password;
        this.position = position;
        this.clockedIn = clockedIn;
        this.onLunch = onLunch;
        this.onBreak = onBreak;
        events = new ArrayList<String>();
    }

    /**
     * Returns the id.
     * @return the id
     */
    public String getId()
    {
        return id;
    }

    /**
     * Returns the fullName.
     * @return the fullName
     */
    public String getFullName()
    {
        return fullName;
    }

    /**
     * Returns the clockedIn.
     * @return the clockedIn
     */
    public boolean isClockedIn()
    {
        return clockedIn;
    }

    /**
     * Returns the onLunch.
     * @return the onLunch
     */
    public boolean isOnLunch()
    {
        return onLunch;
    }

    /**
     * Returns the onBreak.
     * @return the onBreak
     */
    public boolean isOnBreak()
    {
        return onBreak;
    }

    /**
     * Sets the <code>clockedIn</code>.
     * @param clockedIn the clockedIn to set
     */
    public void setClockedIn(boolean clockedIn)
    {
        this.clockedIn = clockedIn;
    }

    /**
     * Sets the <code>onLunch</code>.
     * @param onLunch the onLunch to set
     */
    public void setOnLunch(boolean onLunch)
    {
        this.onLunch = onLunch;
    }

    /**
     * Sets the <code>onBreak</code>.
     * @param onBreak the onBreak to set
     */
    public void setOnBreak(boolean onBreak)
    {
        this.onBreak = onBreak;
    }

    /**
     * Returns the position.
     * @return the position
     */
    public Position getPosition()
    {
        return position;
    }

    /**
     * Sets the position.
     * @param position the position to set
     */
    public void setPosition(Position position)
    {
        this.position = position;
    }

    /**
     * Returns the events.
     * @return the events
     */
    public List<String> getEvents()
    {
        return events;
    }

    /**
     * Returns the password.
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }
}