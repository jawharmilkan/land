/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

/**
 *
 * @author jawhar
 */
public class Employee extends User {
    private String complain, salaryMonth, salaryTransactionMethod;
    private float salaryAmount, salaryBonus;
        
    public void fileComplain(TableView<Owner> memberTableView, TextArea complainTextArea) throws IOException {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        File f =  null;
        
        FileWriter  fw = null;
        
        
        //unchecked exception
        
        try
        {
            f = new File("execComEmpGmComplains.txt");
        
        if(f.exists())
        {
            fw = new FileWriter(f,true); //APPEND MODE
        }
        else
        {
            fw = new FileWriter(f); //file creating
        }
        
        complain = complainTextArea.getText();
        
        //file created
        String str = memberTableView.getSelectionModel().getSelectedItem().getName() + "," + complain + "\n";
        fw.write(str); 

        a.setTitle("Complain");
        a.setHeaderText("Send successfull");
        a.setContentText("Complain has been filed successfully");
        a.showAndWait();
       
        
        
        }
        catch(IOException e)
        {
            System.out.println("Exception : "+e);
            
        }
        finally
        {
            fw.close();
        }
    }
    
    
    public void receiveSalary(float amount, String month, String transactionMethod, float bonus) {
       
        salaryAmount = amount;
        salaryMonth = month;
        salaryTransactionMethod = transactionMethod;
        salaryBonus = bonus;
        
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        File f = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        DataOutputStream dos = null;
        
        try 
        {
            f = new File("empMoneyInfo.bin");
            if(f.exists())
            {
                fos = new FileOutputStream(f,true);
            }
            else
            {
                fos = new FileOutputStream(f);
            }
            
            bos = new BufferedOutputStream(fos);
            dos = new DataOutputStream(fos);
            
           
            dos.writeUTF(salaryMonth);
            dos.writeFloat(salaryAmount);
            dos.writeFloat(salaryBonus);
            dos.writeUTF(salaryTransactionMethod);
            
            
            
            a.setTitle("Transfer Money");
            a.setHeaderText("Transfer successfull");
            a.setContentText("Money has been transferred successfully");
            a.showAndWait();
        } 
        catch (IOException e) 
        {
            a.setTitle("Transfer Money");
            a.setHeaderText("Transfer failed");
            a.setContentText("Unable to transfer money");
            a.showAndWait(); 
        } 
        finally 
        {
            try 
            {
                if(dos != null) 
                {
                    dos.close();
                }
              
            } 
            catch (IOException ex) 
            {
                System.out.println(ex);
            }
        }
    }

    
    
    public void getNotifiedAboutWorks(TableView<OngoingWork> ongoingWorksTableView) throws IOException {
        File f =  null;
        
        FileWriter  fw = null;
        
        //unchecked exception
        
        
        try
        {
            f = new File("empWorkStatus.txt");
        
        if(f.exists())
        {
            fw = new FileWriter(f,true); //APPEND MODE
        }
        else
        {
            fw = new FileWriter(f); //file ta creating
        }
        
        //file created
        
        OngoingWork o =   ongoingWorksTableView.getSelectionModel().getSelectedItem();
        
        String str =  o.toString()+ "," + "(Done)" + "\n";
        
       
        fw.write(str);
        
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Work");
        a.setHeaderText("Done");
        a.setContentText("Work status updated");
        a.showAndWait();
        }
        catch(IOException e)
        {
            System.out.println("Exception : "+e);
            
        }
        finally
        {
            fw.close();
        }
    }
    
}
