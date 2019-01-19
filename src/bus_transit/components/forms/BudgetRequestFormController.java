/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bus_transit.components.forms;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utilities.DBUtilities;

/**
 * FXML Controller class
 *
 * @author Pororoy
 */
public class BudgetRequestFormController extends Application implements Initializable {

    @FXML
    private Button btn_save;
    @FXML
    private Button btn_attach;
    @FXML
    private JFXTextField tfULI;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass()
                                .getResource("BudgetRequest_1.fxml"));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }       

    @FXML
    private void handlesave(ActionEvent event) {
    }

    @FXML
    private void getULIInfo(){
        String q =  "SELECT employee.firstname, employee.lastname,employee.middlename, employee.position_code, employee.uli, \n" +
                    "UPPER(CONCAT(department.department_code,'-',position.position_name)) AS 'Department'\n" +
                    "FROM employee, department,POSITION\n" +
                    "WHERE employee.uli = 'PAY-NDP-01011990-5'\n" +
                    "AND position.position_code = employee.position_code\n" +
                    "GROUP BY employee.uli;";
        db.execute(q);
        
        
    }
    
    DBUtilities db = new DBUtilities();
    ResultSet rs;
    
    @FXML
    private void handleattach(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        Button b = (Button) event.getSource();
        Stage s = (Stage) b.getScene().getWindow();        
        File file = fileChooser.showOpenDialog(s);            
        if(file != null){            
            attach(file.getAbsoluteFile(), db.getRandom(7));
        }          
    }
    
    private void attach(File f, String mID){
        System.out.println(f.getAbsolutePath());
        System.out.println(f);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            FileInputStream fileData = new FileInputStream(f.getAbsolutePath());            
            String fileName = f.getName();
            String ext = fileName.substring(fileName.lastIndexOf(".") + 1,fileName.length());
            System.out.println("Filename: "+fileName);    
            System.out.println("File extenstion: "+ext);                
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bustransit_master","root", "071325");            
            String s = "INSERT INTO finance_budget_request_attachment (attachment_id,file) VALUES (?,?)";
            
            PreparedStatement ps = con.prepareStatement(s);
            ps.setString(1,mID);
            ps.setBinaryStream(2, fileData,(int) f.length());
            ps.executeUpdate();
            
            ps.close();
            fileData.close();
            con.close();                        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BudgetRequestFormController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BudgetRequestFormController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BudgetRequestFormController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BudgetRequestFormController.class.getName()).log(Level.SEVERE, null, ex);
        }               
    }    
}