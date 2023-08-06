/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 *
 * @author jawhar
 */
public abstract class User {
    protected String username, password, userType;
    
    public void register(String user, String password, String uType)
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        File f = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        DataOutputStream dos = null;
        
        try 
        {
            f = new File("accountLogininfo.bin");
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
            
           
            dos.writeUTF(user);
            dos.writeUTF(password);
            dos.writeUTF(uType);
            
            a.setTitle("Account");
            a.setHeaderText("Creation successfull");
            a.setContentText("Account has been created successfully");
            a.showAndWait();
        } 
        catch (IOException e) 
        {
            a.setTitle("Account");
            a.setHeaderText("Creation failure");
            a.setContentText("User Registration Failed!");
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
    
    private void setUserScene(String type, ActionEvent event) throws IOException 
    {
        if (type.equals("Land Owner")) 
        {
            Parent userSceneParent = FXMLLoader.load(getClass().getResource("landOwnerHomeScene.fxml"));
            Scene scene2 = new Scene(userSceneParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(scene2);
            window.show();
        } 
        else if (type.equals("Employee")) 
        {
            Parent userSceneParent = FXMLLoader.load(getClass().getResource("employeeHomeScene.fxml"));
            Scene scene2 = new Scene(userSceneParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(scene2);
            window.show();
        } 
        else if (type.equals("H.R")) 
        {
            Parent userSceneParent = FXMLLoader.load(getClass().getResource("hRHomeScene.fxml"));
            Scene scene2 = new Scene(userSceneParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(scene2);
            window.show();
        } 
        else if (type.equals("Finance Department")) 
        {
            Parent userSceneParent = FXMLLoader.load(getClass().getResource("financeDepartmentHomeScene.fxml"));
            Scene scene2 = new Scene(userSceneParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(scene2);
            window.show();
        } 
        else if (type.equals("Legal Advisor")) 
        {
            Parent userSceneParent = FXMLLoader.load(getClass().getResource("legalAdvisorHomeScene.fxml"));
            Scene scene2 = new Scene(userSceneParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(scene2);
            window.show();

        }

    }
    
    /**
     *
     * @param user
     * @param password
     * @param uType
     * @param event
     */
    public void login(String user, String password, String uType, ActionEvent event)
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        File f = null;
        FileInputStream fis = null;
        DataInputStream dis = null;

        try 
        {
            f = new File("accountLoginInfoExecCom.bin");
            if (!f.exists())
            {
                a.setTitle("Login Issue");
                a.setHeaderText("Issue found");
                a.setContentText("The login information file is not present!");
                a.showAndWait();
            } 
            else 
            {
                fis = new FileInputStream(f);

                dis = new DataInputStream(fis);

                while (true) {
                    if (user.equals(dis.readUTF())) 
                    {
                        if (password.equals(dis.readUTF())) 
                        {
                            if (uType.equals(dis.readUTF())) 
                            {
                                setUserScene(uType, event);
                                break;
                            }
                        }
                    }
                }
            }
        } 
        catch (IOException e) 
        {
            a.setTitle("Login Issue");
            a.setHeaderText("Issue found");
            a.setContentText("Invalid login credentials, please try again!");
            a.showAndWait();

        } 
        finally 
        {
            try 
            {
                if (dis != null) 
                {
                    dis.close();
                }
            } catch (IOException e) 
            {
                System.out.println(e);
            }
        }
    }
    
}
