package student_menu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Scanner;

/**
 *
 * @author Windows
 * Class designed for conditional inputs, as well as modification.
 */
public class Input {
    public static Scanner sc = new Scanner(System.in);
    /**
     * 
     * @param mess is the additionally string to sysout some information before inputting
     * @return String data user entered
     */
    public static String inputNonBlankStr(String mess){
        String temp = null;
        boolean flag = false;
       
        do
        {            
            System.out.print(mess);
            temp = sc.nextLine().trim();
        } while (temp.isEmpty());
       
       
        return temp;
    }
 
    public static String inputStr(String mess){
        System.out.println(mess);
        String temp = sc.nextLine().trim();
        return temp;
    }
    public static int inputInt(String mess,int min , int max)
    {
        if(min > max){
            int t = min; min = max; max = t;
        }
        int temp;
        do
        {
            System.out.print(mess);
            temp =Integer.parseInt(sc.nextLine());
        } while (temp < min || temp > max);
        return temp;
    }
    
    public static String inputPattern(String mess, String pattern)
    {
        String data;
        do
        {            
            System.out.println(mess);
            data = sc.nextLine().trim();
        } while (!data.matches(pattern));
        return data;
    }
}
    
