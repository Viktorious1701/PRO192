/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action_service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Windows
 */
public class Input {

    public static Scanner sc = new Scanner(System.in);

    /**
     *
     * @param mess is the additionally string to system.out some information
     * before inputting
     * @return String data user entered
     */
    public static String inputNonBlankStr(String mess) {
        String temp = null;
        boolean flag = false;

        do
        {
            System.out.print(mess);
            temp = sc.nextLine().trim();
            if (temp.isEmpty())
            {
                System.out.println("Invalid String! String cannot be blank or with only spaces!");
            }
        } while (temp.isEmpty());

        return temp;
    }

    /**
     * string with spaces is acceptable (\n, \t,...)
     *
     * @param mess
     * @return
     */
    public static String inputStr(String mess) {
        System.out.println(mess);
        String temp = sc.nextLine().trim();
        return temp;
    }

    /**
     * Input an integer with minimum value
     *
     * @param mess
     * @param min
     * @return integer
     */
    public static int inputInt(String mess, int min) {
        // access the string method to ensure there is no error while parsing integers
        String temp = inputNonBlankStr(mess);
        int result = 0;
        do
        {

            result = Integer.parseInt(temp);
        } while (result < min);
        return result;
    }

    /**
     * input integer in a range from min to max
     *
     * @param mess
     * @param min
     * @param max
     * @return integer
     */
    public static int inputIntMax(String mess, int min, int max) {
        if (min > max)
        {
            int t = min;
            min = max;
            max = min;
        }
        String temp = inputNonBlankStr(mess);
        int result = 0;
        do
        {
            result = Integer.parseInt(temp);
        } while (result < min || result > max);
        return result;
    }

    /**
     * Code must be of the format Fxyzt (xyzt is a 4 digit number) other wise
     * return false because of validation
     *
     * @return
     */
    public static String inputValidCode() {
        do
        {
            String temp = inputNonBlankStr("Enter the new Flight Code (Format Fxxxx): ");
            // set to uppercase to allow user entering f / F
            temp = temp.toUpperCase();
            String regex = "F\\d{4}";
            // initalize new pattern format
            Pattern pattern = Pattern.compile(regex);
            // check if the string is matching the format
            Matcher matcher = pattern.matcher(temp);

            if (matcher.matches())
            {
                System.out.println("Input successfully");
                return temp;
            } else
            {
                System.out.println("Input Failed, Format must be in Fxxxx (xxxx is a 4-digit number)");
            }
        } while (true);
    }
    public static String inputValidEmpCode() {
        do
        {
            String temp = inputNonBlankStr("Enter the new Crew Member Code (Format Exxx): ");
            // set to uppercase to allow user entering E/e
            temp = temp.toUpperCase();
            String regex = "E\\d{4}";
            // initalize new pattern format
            Pattern pattern = Pattern.compile(regex);
            // check if the string is matching the format
            Matcher matcher = pattern.matcher(temp);

            if (matcher.matches())
            {
                System.out.println("Input successfully");
                return temp;
            } else
            {
                System.out.println("Input Failed, Format must be in Fxxxx (xxxx is a 4-digit number)");
            }
        } while (true);
    }
    public static String inputValidCity(String mess) {

        do
        {
            String temp = inputNonBlankStr(mess);
            if (temp.trim() != null)
            {
                System.out.println("Enter city name successfully!");
                return temp.trim();
            }
        } while (true);

    }

    public static Date inputTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        boolean validInput = false;
        Date date = null;
        do
        {
            try
            {
                
                String depDate = inputNonBlankStr("Enter the date (optional for time) (dd/MM/yyyy hh:mm:ss): ");
                if(isValidDate(depDate)){
                    //set the default time if not input
                    String time = "00:00:00";
                    String result = depDate + " " + time;
                    date = sdf.parse(result);
                    // the date is valid, break the while loop
                    validInput = true;
                }else
                    System.out.println("invalid Date, date does not exist in the calendar!");
            } catch (ParseException e)
            {
                System.out.println("Invalid Date, please enter a valid date (dd/MM/yyyy)!");
            }
        } while (!validInput);
        return date;
    }

    public static int inputTotalSeatsNumber() {
        int result = inputInt("enter the total number of available seats: ", 0);
        return result;
    }
    
    public static boolean isValidDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);

        try
        {
            Date date = sdf.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            // Check if the year, month, and day are valid
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1; // Months are 0-based
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            if (year >= 1900 && year <= 9999 && month >= 1 && month <= 12)
            {
                // array so ngay trong 1 thang, rieng thang 2 se tuy thuoc vao leap year
                int[] daysInMonth =
                {
                    31, isLeapYear(year) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
                };
                return day >= 1 && day <= daysInMonth[month - 1];
            } else
            {
                return false; // Invalid year or month
            }
        } catch (ParseException e)
        {
            return false; // Invalid date format
        }
    }

    private static boolean isLeapYear(int year) {
        // A year is a leap year if it is evenly divisible by 4.
        //However, if a year is divisible by 100 but not divisible by 400, it is not a leap year.
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

}
