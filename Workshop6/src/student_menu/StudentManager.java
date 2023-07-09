/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student_menu;
/**
 *
 * @author Windows
 */
public class StudentManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String[] options = {
            "Add new student",
            "Search a student", 
            "Update name and mark",
            "Remove a student",
            "List all", "Quit"
        };
        int choice = 0;
        StudentList list  = new StudentList();   
        try
        {
            do
        {            
            System.out.println("Student managing program");
            choice = Menu.getChoice(options);
            switch(choice){
                case 1: list.addStudent(); break;
                case 2: list.searchStudent(); break;
                case 3: list.updateStudent(); break;
                case 4: list.removeStudent(); break;
                case 5: list.showAll(); break;
                default: System.out.println("Goodbye!");
            }
        } while (choice > 0 && choice < 6);
        } catch (Exception e)
        {
            System.out.println(e);
        }
    }

}
