package main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static main.PatientListViewController.selectedPatient;

/**
 * FXML Controller class
 *
 * @author goodw
 */
public class WellnessNewController implements Initializable {

    @FXML
    private TextField txtDateWellnessNew;
    @FXML
    private TextField txtTimeWellnessNew;
    @FXML
    private TextArea txtCurrentMedsWellnessNew;
    @FXML
    private TextField txtHeightWellnessNew;
    @FXML
    private TextField txtWeightWellnessNew;
    @FXML
    private TextField txtBpSysWellnessNew;
    @FXML
    private TextField txtBpDiaWellnessNew;
    @FXML
    private TextField txtHrWellnessNew;
    @FXML
    private TextField txtReasonWellnessNew;
    @FXML
    private TextField txtPhysicianWellnessNew;
    @FXML
    private TextArea txtNotesWellnessNew;
    @FXML
    private Button btnSaveWellnessNew;
    @FXML
    private Button btnResetWellnessNew;
    @FXML
    private Button btnCancelWellnessNew;
    @FXML
    private Label lblPatientName;

    @FXML
    void handleSave(ActionEvent event) throws IOException {

//        first checkes if the date, time, and reason fields have valid inputs
        if (validateFields()) {

//        Get new wellness information entered by user
            String patientId = Integer.toString(selectedPatient.getId());
            String newDate = txtDateWellnessNew.getText();
            String newTime = txtTimeWellnessNew.getText();
            String newReason = txtReasonWellnessNew.getText();
            String newHeight = txtHeightWellnessNew.getText();
            String newWeight = txtWeightWellnessNew.getText();
            String newBpSys = txtBpSysWellnessNew.getText();
            String newBpDia = txtBpDiaWellnessNew.getText();
            String newHr = txtHrWellnessNew.getText();
            String newCurrentMeds = txtCurrentMedsWellnessNew.getText();
            String newPhysician = txtPhysicianWellnessNew.getText();
            String newNotes = txtNotesWellnessNew.getText();

//        INSERT statement in SQL to add new wellness checkup to database
            String sqlWellness = "INSERT INTO lpgdb.wellness\n"
                    + "(`patient_id`,\n"
                    + "`date`,\n"
                    + "`time`,\n"
                    + "`bp_diastolic`,\n"
                    + "`bp_systolic`,\n"
                    + "`weight`,\n"
                    + "`height`,\n"
                    + "`current_meds`,\n"
                    + "`reason`,\n"
                    + "`notes`)\n"
                    + "VALUES\n"
                    + "('" + patientId + "',\n"
                    + "'" + newDate + "',\n"
                    + "'" + newTime + "',\n"
                    + "'" + newBpDia + "',\n"
                    + "'" + newBpSys + "',\n"
                    + "'" + newWeight + "',\n"
                    + "'" + newHeight + "',\n"
                    + "'" + newCurrentMeds + "',\n"
                    + "'" + newReason + "',\n"
                    + "'" + newNotes + "');";

            try {
//      connects to database and executes query
                conn = dbConnector.getConnection();
                stmt = conn.createStatement();
                stmt.executeUpdate(sqlWellness);
                System.out.println(sqlWellness);

//      lets the user know the checkup was added to the patient
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Wellness Checkup Added!");
                alert.showAndWait();

//      opens the patient's profile after saving the information in the database
                Parent root = FXMLLoader.load(getClass().getResource("wellnessListView.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

//      closes the new wellness form page
                Stage currentStage = (Stage) btnSaveWellnessNew.getScene().getWindow();
                currentStage.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    @FXML
    void handleReset(ActionEvent event) {

        txtDateWellnessNew.setText("");
        txtTimeWellnessNew.setText("");
        txtReasonWellnessNew.setText("");
        txtHeightWellnessNew.setText("");
        txtWeightWellnessNew.setText("");
        txtBpSysWellnessNew.setText("");
        txtBpDiaWellnessNew.setText("");
        txtHrWellnessNew.setText("");
        txtCurrentMedsWellnessNew.setText("");
        txtPhysicianWellnessNew.setText("");
        txtNotesWellnessNew.setText("");

    }

    @FXML
    void handleCancel(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("patientProfileView.fxml"));
        Stage Stage = new Stage();
        Scene scene = new Scene(root);
        Stage.setScene(scene);
        Stage.show();

        Stage currentStage = (Stage) btnCancelWellnessNew.getScene().getWindow();
        currentStage.close();
    }

    Alert validationAlert = new Alert(AlertType.ERROR);

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private DBConnector dbConnector;

//    ______________________INITIALIZE THE CONTROLLER______________________erride
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        String patientName = selectedPatient.getLast() + ", " + selectedPatient.getFirst() + " " + selectedPatient.getMiddle();
        lblPatientName.setText(patientName);

        dbConnector = new DBConnector();

    }

    protected void openWellnessNew(Patient patient) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("wellnessNew.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    private boolean validateFields() {

        if (validateDate()
                && validateTime()
                && validateReason()) {
            return true;
        }

        return false;
    }

    private boolean validateDate() {

        if (txtDateWellnessNew.getText() == null || txtDateWellnessNew.getText().trim().isEmpty()) {
            validationAlert.setTitle("Missing Field");
            validationAlert.setHeaderText(null);
            validationAlert.setContentText("You must enter a date");
            validationAlert.showAndWait();
        } else {
            if (txtDateWellnessNew.getText().length() > 10) {
                validationAlert.setTitle("Bad Input");
                validationAlert.setHeaderText(null);
                validationAlert.setContentText("The date needs to be in the format:\n\n"
                        + "YYYY-MM-DD");
                validationAlert.showAndWait();
            } else {
                return true;
            }
        }

        return false;
    }

    private boolean validateTime() {

        if (txtTimeWellnessNew.getText() == null || txtTimeWellnessNew.getText().trim().isEmpty()) {
            validationAlert.setTitle("Missing Field");
            validationAlert.setHeaderText(null);
            validationAlert.setContentText("You must enter a time");
            validationAlert.showAndWait();
        } else {
            if (txtTimeWellnessNew.getText().length() > 8) {
                validationAlert.setTitle("Bad Input");
                validationAlert.setHeaderText(null);
                validationAlert.setContentText("The time needs to be in the format:\n\n"
                        + "HH:MM");
                validationAlert.showAndWait();
            } else {
                return true;
            }
        }

        return false;
    }

    private boolean validateReason() {

        if (txtReasonWellnessNew.getText() == null || txtReasonWellnessNew.getText().trim().isEmpty()) {
            validationAlert.setTitle("Missing Field");
            validationAlert.setHeaderText(null);
            validationAlert.setContentText("You must enter a reason");
            validationAlert.showAndWait();
        } else {
            return true;
        }

        return false;
    }

}
