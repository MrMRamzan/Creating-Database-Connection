/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadatabasetest;
import com.mysql.jdbc.StringUtils;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Muhammad Ramzan
 */
public class JavaDatabaseTest {

    /* function to validate that the id mudt contin only digits */
    static public boolean isValidId(String Id) {
        if(Id.matches("[0-9]+")) {
            return true;
        }
        return false;
    }
    /* function to validate that the name of the student should contain only alphabets   */
    static public boolean isValidName(String name) {
        if(name.matches("[a-zA-Z]+")) {
            return true;
        }
        return false;
    }
    
    /* validating the registration number */
    static public boolean isValidRegNo(String str) {
        String[] splitedStr=str.split("-");
        if(splitedStr[0].matches("[0-9]+") && splitedStr[1].matches("[a-zA-Z]+") && splitedStr[2].matches("[0-9]+")) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // TODO code application logic here
        
        try{
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/test";
        String usr = "root";
        String pwd = "ramzan";
        Connection con = DriverManager.getConnection(url, usr, pwd);
        
        Statement st = con.createStatement();
        
        /* table column Names record attributes*/
        String stdID = null;
        String stdName = null;
        String stdRegNumber = null;
        
        String temp;
        
        /* first window input option string*/
        String input;
        
        /* Update window input option strings*/
        String updateStr;
        String updateStrOption; 
        
        while(true) {
       
        input = JOptionPane.showInputDialog(null,"Choose 1 to Update Student Record\nChoose 2 to Insert New Student\n"
            + "Choose 3 to Dalete a Student\nChoose 4 to View the Record","OptionWindow");
        /* Operation on the student record */
        switch (input) {
            /* Updating the student record */
            case "1" : {
                
                updateStr = JOptionPane.showInputDialog(null,"Choose 1 to Update Student ID\nChoose 2 to Update Student Name\n"
                + "Choose 3 to Update Student Register Number","Update Record"); 
                
                /* Update the student record */
                switch(updateStr) {
                    /* Updating the student Id */
                    case "1": {
                        updateStrOption =  JOptionPane.showInputDialog(null,"Enter the StudentID to be updated(new ID)"
                            + " and Name\n1,Adnan","Update Record"); 
                        
                        String[] str = updateStrOption.split(",");
                        
                            if(str.length == 2) {
                                if(isValidId(str[0]) && isValidName(str[1])){
                                    stdID = str[0];
                                    stdName = str[1]; 
                                } else {
                                    JOptionPane.showMessageDialog(null,"Invalid Input");
                                    break;
                                }
                            } else {     
                                JOptionPane.showMessageDialog(null,"Invalid Input");
                                break;
                            }

                        String sql = "UPDATE student SET Id = "+stdID+" WHERE Name = '"+stdName+"'";
                        int num = st.executeUpdate(sql);
                        System.out.println(num+"records updated");
                        break;
                    }//case 1 ended
                    /* Updating the student Name */
                    case "2": {
                        updateStrOption =  JOptionPane.showInputDialog(null,"Enter the Student Name "
                            + "to be updated(new Name)"
                            + " and Student ID\nAdnan,1","Update Record"); 
                        
                        String[] str = updateStrOption.split(",");
                        
                            if(str.length == 2) {
                                if(isValidName(str[0]) && isValidId(str[1])){
                                    stdName = str[0];
                                    stdID = str[1];  
                                } else {
                                    JOptionPane.showMessageDialog(null,"Invalid Input");
                                    break; 
                                }
                                 
                            } else {   
                            JOptionPane.showMessageDialog(null,"Invalid Input");
                            break; 
                            }
                            
                        String sql = "UPDATE student SET Name = '"+stdName+"' WHERE Id = "+stdID+"";
                        int num = st.executeUpdate(sql);
                        System.out.println(num+"records updated");
                        break;
                    }//case 2 ended
                    /* Updating the student Registration Number */
                    case "3": {
                        updateStrOption =  JOptionPane.showInputDialog(null,"Enter the Student Register Numbre "
                            + "to be updated(new Reg. Number)"
                            + " and Student ID\n2015-cs-1,1","Update Record"); 
                        
                        String[] str = updateStrOption.split(",");
                        
                            if(str.length == 2) {
                                if(isValidRegNo(str[0]) && isValidId(str[1])) {
                                    stdRegNumber = str[0];
                                    stdID = str[1];  
                                } else {
                                    JOptionPane.showMessageDialog(null,"Invalid Input");
                                    break;  
                                }
                                 
                            } else {
                                JOptionPane.showMessageDialog(null,"Invalid Input");
                                break;    
                            }

                        
                        String sql = "UPDATE student SET RegNo = '"+stdRegNumber+"' WHERE Id = "+stdID+"";
                        int num = st.executeUpdate(sql);
                        System.out.println(num+"records updated");
                        break;
                    }// case 3 ended
                }//inner switch is ended
                break;
            }//case 1 is ended
            
            /* Inserting a new student record */
            case "2": {
            temp =  JOptionPane.showInputDialog(null,"Enter the Student Id,Name,Registeration Number\n "
                + "1,Ali,2015-CS-3","Update Record"); 
            
            String[] str = temp.split(",");
            
                if(str.length >= 3) {
                    if(isValidId(str[0]) && isValidName(str[1]) && isValidRegNo(str[2])){
                        stdID = str[0];
                        stdName = str[1];
                        stdRegNumber = str[2];
                    } else {
                        JOptionPane.showMessageDialog(null,"Invalid Input");
                        break;
                    }
    
                } else {
                    JOptionPane.showMessageDialog(null,"Invalid Input");
                    break;
                }

            String insert = "INSERT INTO student(Id,Name,RegNo) Values(" + stdID + ", '" +
                    stdName + "', " + "'" + stdRegNumber +"')";
            int num = st.executeUpdate(insert);
            System.out.println(num+"records updated");
            break;
            }// case 2 is ended
            
            /* Delete a student record */
            case "3": {
            temp =  JOptionPane.showInputDialog(null,"Enter the Student ID to remove his/her record","Update Record");
            
                if(isValidId(temp)){
                    stdID = temp;
                } else {
                    JOptionPane.showMessageDialog(null,"Invalid Input");
                    break;
                }
                
            int num = st.executeUpdate("DELETE from student WHERE id = "+stdID+"");
            System.out.println(num+"records updated");
            break;
            }// case 3 is ended
            
            /* View the record of students */
            case "4": {
                
            String sql = "SELECT*FROM student";
            ResultSet rs = st.executeQuery(sql);
            
                while (rs.next()) {
                    int StudentId = rs.getInt("Id");
                    String name = rs.getString("Name");
                    String regNo = rs.getString("RegNo");
                    System.out.println(StudentId+"  "+name+"  "+regNo);
                }
            break;
            }// case 4 is ended
            default:
            {
                JOptionPane.showMessageDialog(null,"Worng Input Value");
                break;
            }
        }//switch on first choice
/////////////////////////////////////////////////hard code
/* show the whole table (d)*/
//        String sql = "SELECT*FROM student";
//        ResultSet rs = st.executeQuery(sql);        
//        while (rs.next()) {
//            String name = rs.getString("Name");
//            String regNo = rs.getString("RegNo");
//            System.out.println(name+"  "+regNo);
//        }
//        con.close();
/* Update (a)*/
//        String name = "Ahmed";
//        String regNo = "2015-cs-5";
//        String sql = "UPDATE student SET Name = '"+name+"' WHERE RegNo = '"+regNo+"'";
//        int num = st.executeUpdate(sql);
//        System.out.println(num+"records updated");
/* delete (c)*/
//          int num = st.executeUpdate("DELETE from student WHERE id = 2");
//          System.out.println(num+"records updated");
/* insert (b)*/
//            String insert = "INSERT INTO student(Id,Name,RegNo)" 
//                             +"Values(4,'dbc','2015-cs-6')";
//            int num = st.executeUpdate(insert);
//            System.out.println(num+"records updated");
////////////////////////////////////////////////////

      }//while is ended
        
        } catch (Exception e){
            System.out.println(e);
        }
    }//main is ended
    
}//class is ended
