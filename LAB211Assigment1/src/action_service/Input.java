package action_service;

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
     * @param mess is the additionally string to system.out some information before inputting
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
    /**
     * string with spaces is acceptable (\n, \t,...)
     * @param mess
     * @return 
     */
    public static String inputStr(String mess){
        System.out.println(mess);
        String temp = sc.nextLine().trim();
        return temp;
    }
    /**
     * Input an integer with minimum value
     * @param mess
     * @param min
     * @return integer
     */
    public static int inputInt(String mess,int min)
    {
        
        int temp;
        do
        {
            System.out.print(mess);
            temp =Integer.parseInt(sc.nextLine());
        } while (temp < min);
        return temp;
    }
    /**
     * input integer in a range from min to max
     * @param mess
     * @param min
     * @param max
     * @return integer
     */
     public static int inputIntMax(String mess,int min, int max)
    {
        if( min  > max){
            int t = min;
            min = max;
            max = min;
        }
        int temp;
        do
        {
            System.out.print(mess);
            temp =Integer.parseInt(sc.nextLine());
        } while (temp < min || temp > max);
        return temp;
    }
    
}
    
