/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bus_transit;

import bus_transit.system.entities.User;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Llamera
 */
public class SidePaneController implements Initializable {   
    @FXML private Label lblEmployeeFullName;
    @FXML private Label lblPosition;
    @FXML private Label lblDepartment;
    @FXML
    private JFXButton dashboard;
    @FXML
    private Accordion accdModules;
    @FXML
    private TitledPane tlpTraining;
    @FXML
    private JFXButton btnTraining;
    @FXML
    private TitledPane tlpLearning;
    @FXML
    private JFXButton btnModules;
    @FXML
    private JFXButton btnTests;
    @FXML
    private JFXButton btnTestResults;
    @FXML
    private TitledPane tlpCompentency;
    @FXML
    private JFXButton btnJobQuaification;
    @FXML
    private JFXButton btnJobQuaification1;
    @FXML
    private TitledPane tlpSuccession;
    @FXML
    private JFXButton btnSuccessionPlan;
    @FXML
    private JFXButton btnTestScedule;
    @FXML
    private TitledPane tlpESS;
    @FXML
    private JFXButton btnSuccessionPlan2;
    @FXML
    private TitledPane tlpApplicants;
    @FXML
    private JFXButton btnSuccessionPlan1;
    @FXML
    private JFXButton btnSuccessionPlan11;
    @FXML
    private TitledPane tlpRecruitment;
    @FXML
    private TitledPane tlpNewHire;
    @FXML
    private TitledPane tlpPerformance;
    @FXML
    private TitledPane tlpSocilRecognition;
    @FXML
    private TitledPane tlpSchedule;
    @FXML
    private TitledPane tlpTimeSheet;
    @FXML
    private TitledPane tlpLeaveManagement;
    @FXML
    private TitledPane tlpClaims;
    @FXML
    private TitledPane tlpAttendance;
    @FXML
    private TitledPane tlpPayroll;
    @FXML
    private TitledPane tlpCompensation;
    @FXML
    private TitledPane tlpAnalytics;
    @FXML
    private TitledPane tlpCoreHuman;
    @FXML
    private JFXButton btnReports;
    
    public static User user;
    
    public static String employeeFullName;
    public static String userLevel;
    public static String department;
    public static String positionId;    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.user = user;
        System.out.println(user.username);
        lblEmployeeFullName.setText(user.fullname);
        lblPosition.setText(user.position.toUpperCase());        
        cloneModules();
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }    

    @FXML
    private void loadFunction(ActionEvent event) throws IOException {
        JFXButton b = (JFXButton) event.getSource();
        String file = b.getAccessibleText().toString();        
        System.out.println(file);
        try {
            Parent pane = FXMLLoader.load(getClass().getResource(file));
            pane.prefWidth(DashboardController.scrlCenterContent.getWidth());
            pane.prefHeight(DashboardController.scrlCenterContent.getHeight());            
            DashboardController.scrlCenterContent.setContent(pane);
            DashboardController.draw.close();            
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    ObservableList<TitledPane> titledPanes;
    private void cloneModules(){
        titledPanes = accdModules.getPanes();
        titledPanes.forEach((titledPane) -> {
            String t = titledPane.getText().trim().toLowerCase().replace(" ", "_");
            titledPane.setUserData(t);           
        });        
    }
    
    private void filterUser(User usr){
        
    }
    
    @FXML
    private void loadDashboard(ActionEvent event) {
        try {
            JFXButton b = (JFXButton) event.getSource();
            Stage stage = (Stage) b.getScene().getWindow();
            stage.close();
            Stage dash = new Stage(StageStyle.UNDECORATED);
            Parent root;            
            root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            Scene scene = new Scene(root);
            dash.setScene(scene);
            dash.setMaximized(true);
            dash.show();              
        } catch (IOException ex) {
            Logger.getLogger(SidePaneController.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
}