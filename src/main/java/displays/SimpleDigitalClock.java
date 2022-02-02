package displays;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Got clock code from here
 * https://www.c-sharpcorner.com/UploadFile/16154c/digital-clock-using-swing-in-java/.
 */
class SimpleDigitalClock extends JPanel
{
    String stringTime;
    int hour, minute, second;
    String aHour = "";
    String bMinute = "";
    String cSecond = "";

    public void setStringTime(String abc)
    {
        stringTime = abc;
    }

    public int Number(int a, int b)
    {
        return (a <= b) ? a : b;
    }

    SimpleDigitalClock()
    {
        Timer t = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                repaint();
            }
        });
        t.start();
    }

    @Override
    public void paintComponent(Graphics v)
    {
        super.paintComponent(v);
        Calendar rite = Calendar.getInstance();
        hour = rite.get(Calendar.HOUR_OF_DAY);
        minute = rite.get(Calendar.MINUTE);
        second = rite.get(Calendar.SECOND);
        if(hour < 10)
        {
            aHour = "0";
        }
        if(hour >= 10)
        {
            aHour = "";
        }
        if(minute < 10)
        {
            bMinute = "0";
        }
        if(minute >= 10)
        {
            bMinute = "";
        }
        if(second < 10)
        {
            cSecond = "0";
        }
        if(second >= 10)
        {
            cSecond = "";
        }
        setStringTime(aHour + hour + ":" + bMinute + minute + ":" + cSecond + second);
        v.setColor(Color.BLACK);
        int length = Number(getWidth(), getHeight());
        Font Font1 = new Font("SansSerif", Font.PLAIN, length / 5);
        v.setFont(Font1);
        v.drawString(stringTime, (int) length / 6, length / 2);
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(200, 200);
    }

}