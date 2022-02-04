package displays;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.swing.JButton;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import employee.Employee;

/**
 * Responsible for
 * @author Ryan Sorge
 */
public class AdminClock extends TimeClock
{
    JButton generateReportButton = new JButton("Generate Report");

    /**
     * Constructs an instance of this class.
     * @param employee
     * @param employees
     */
    public AdminClock(Employee employee, final Map<String, Employee> employees)
    {
        super(employee, employees);
        add(generateReportButton);

        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                HSSFWorkbook workbook = openOrCreateExcelSheet();
                generateReport(workbook);
            }

            private HSSFWorkbook openOrCreateExcelSheet()
            {
                HSSFWorkbook workbook = null;
                SimpleDateFormat day = new SimpleDateFormat("dd");
                SimpleDateFormat fileFormat = new SimpleDateFormat("MM-yyyy");
                HSSFSheet sheet;
                HSSFRow rowhead, row;
                FileOutputStream outputStream;
                File excelSpreadsheet;

                long time = System.currentTimeMillis();

                // creates file based on the month and year, i.e. 07-2015.xls
                String fileName = fileFormat.format(time);

                // get the day of the month
                String dayOfMonthStr = day.format(time);
                int dayOfMonth = Integer.parseInt(dayOfMonthStr);

                System.out.println("Day of month " + dayOfMonth);

                // file location
                fileName = "C:/Users/RS071903/Timesheets" + fileName + ".xls";
                excelSpreadsheet = new File(fileName);

                try
                {
                    workbook = new HSSFWorkbook();

                    for(Map.Entry<String, Employee> entry : employees.entrySet())
                    {
                        System.out.println("Creating new Excel spreadsheet");

                        // creates a sheet in the workbook for every employee
                        sheet = workbook.createSheet(entry.getValue().getFullName());
                        System.out.println("Created new sheet for " + entry.getValue().getFullName());

                        // creates the 1st row and sets the value of the 1st cell to the
                        // employee's name
                        rowhead = sheet.createRow(0);
                        rowhead.createCell(0).setCellValue(entry.getValue().getFullName());

                        // merges cell A1 and B1 for the employee's name
                        sheet.addMergedRegion(new CellRangeAddress(0, // first row (0-based)
                                0, // last row (0-based)
                                0, // first column (0-based)
                                1 // last column (0-based)
                        ));

                        // creates 2nd row
                        row = sheet.createRow(1);

                        row.createCell(0).setCellValue("Log");
                        sheet.autoSizeColumn(0);
                    }

                    // writes these changes to the Excel spreadsheet
                    outputStream = new FileOutputStream(excelSpreadsheet);
                    workbook.write(outputStream);
                    outputStream.close();
                }
                catch(IOException e)
                {
                }
                return workbook;
            }

            private void generateReport(HSSFWorkbook workbook)
            {
                for(Map.Entry<String, Employee> entry : employees.entrySet())
                {
                    if(!entry.getValue().getEvents().isEmpty())
                    {
                        // find the worksheet that matches the employee's name
                        HSSFSheet sheet = workbook.getSheet(entry.getValue().getFullName());

                        for(String log : entry.getValue().getEvents())
                        {
                            int rowNum = sheet.getPhysicalNumberOfRows();
                            HSSFRow row = sheet.getRow(rowNum);
                            row = sheet.createRow(rowNum);
                            row.createCell(0).setCellValue(log);
                            // autosize newly filled columns
                            sheet.autoSizeColumn(0);

                            FileOutputStream outputStream;
                            File excelSpreadsheet;
                            SimpleDateFormat fileFormat = new SimpleDateFormat("MM-yyyy");
                            long time = System.currentTimeMillis();

                            // creates file based on the month and year, i.e. 07-2015.xls
                            String fileName = fileFormat.format(time);

                            // get the day of the month

                            // file location
                            fileName = "C:/Users/RS071903/Timesheets" + fileName + ".xls";
                            excelSpreadsheet = new File(fileName);
                            // writes these changes to the spreadsheet
                            try
                            {
                                outputStream = new FileOutputStream(excelSpreadsheet);
                                workbook.write(outputStream);
                                outputStream.close();
                            }
                            catch(IOException e)
                            {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                        }
                    }
                }
            }
        });
    }

    @Override
    void clockEmployeeIn()
    {
        logEvent("clocked in at:");
        employee.setClockedIn(true);
    }

    @Override
    void startLunchBreak()
    {
        logEvent("started lunch at:");
        employee.setOnLunch(true);
    }

    @Override
    void startBreak()
    {
        logEvent("started break at:");
        employee.setOnBreak(true);
    }

    @Override
    void endLunchBreak()
    {
        logEvent("ended lunch at:");
        employee.setOnLunch(false);
    }

    @Override
    void endBreak()
    {
        logEvent("ended break at:");
        employee.setOnBreak(false);
    }

    @Override
    void clockEmployeeOut()
    {
        logEvent("clocked out at:");
        employee.setClockedIn(false);
    }
}
