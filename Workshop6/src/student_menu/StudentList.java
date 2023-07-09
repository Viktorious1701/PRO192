/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student_menu;

import java.util.ArrayList;

/**
 *
 * @author Windows
 */
public class StudentList extends ArrayList<Student> {
    
    public StudentList() {
        super();
    }
    /**
     * 
     * @param code to find the code of a student that the list has
     * @return the index position, or null if not found
     */
    public Student search (String code){
        code = code.trim().toUpperCase();
        for(int i =0 ; i < this.size(); i ++)
            if(this.get(i).getCode().equals(code)) return this.get(i); // found at index i
        return null; // not found 
    }
    /**
     * 
     * @param code to check if there is already an existing code name
     * @return true if there is duplicate or false vice versa
     */
    private boolean isCodeDup(String code){
        code = code.trim().toUpperCase();
        return search(code)!= null;
    }
    /**
     * add information of a student
     */
    public void addStudent(){
        // Input data of new student
        String newCode, newName ;
        int newMark;
        boolean codeDuplicated= false;
        do { 
            newCode = Input.inputPattern("St. code S000: ", "[sS][\\d]{3}");
            newCode = newCode.trim().toUpperCase();
            codeDuplicated = isCodeDup(newCode); // check if duplicate
            
            if(codeDuplicated) System.out.println("Code is duplicated!");
        }while(codeDuplicated == true);
        
        newName = Input.inputNonBlankStr("Name of student: ");
        newName = newName.toUpperCase();
        newMark = Input.inputInt("Mark: ", 0, 10); // min 0, max 10 of mark
        
        Student x = new Student(newCode,newName,newMark);
        this.add(x);
        System.out.println("Student" + newCode + " has been added to the list");
    }
    /**
     * search for a student with a provided code
     */
    public void searchStudent(){
        if(this.isEmpty())
            System.out.println("Empty List! ");
        else{
            String code = Input.inputStr("Input student code: ");
            Student x = this.search(code); // search student code
                    
                    if(x == null)
                        System.out.println("Code does not exist");
                    else
                        System.out.println("found: " + x);
        }
        
    }
    /**
     * update student information
     */
    public void updateStudent(){
        if(this.isEmpty())
            System.out.println("Empty List!");
        else{
            String uCode = Input.inputStr("input code of updated student: ");
            Student x = this.search(uCode);
            if(x == null)
                System.out.println("Code not existed!");
            else {
                // update name
                String mess = "Old name: " +x.getName() + ", New name: ";
                String newName = Input.inputNonBlankStr(mess);
                x.setName(newName);
                
                // update mark
                mess = "Old mark: " + x.getMark() + ", New mark : ";
                int newMark = Input.inputInt(mess, 0, 10);
                x.setMark(newMark);
                
                System.out.println("Student" +uCode + "has been updated");
            }
        }
    }
    /**
     * remove a student information with provided code
     */
    public void removeStudent(){
        if(this.isEmpty())
            System.out.println("Empty List! ");
        else 
        {
            String rCode = Input.inputStr("Input code to be removed: ");
            Student x = this.search(rCode);
            if(x == null)
                System.out.println("Student not found");
            else
            {
                this.remove(x);
                System.out.println("Student removed successfully");
                
            }
        }
    }
    public void showAll(){
        if(this.isEmpty())  System.out.println("Empty List!");
        else {
            System.out.println("Student list:");
            for(Student i : this)
                System.out.println(i);
            System.out.println("Total of: "+ this.size() + " student(s).");
        }
    }
}
