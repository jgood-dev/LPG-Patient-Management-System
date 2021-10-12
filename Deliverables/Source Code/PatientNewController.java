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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author goodw
 */
public class PatientNewController implements Initializable {

    @FXML
    private TextArea txtAddressNew;

    @FXML
    private TextField txtPhoneNew;

    @FXML
    private TextField txtEmailNew;

    @FXML
    private TextField txtDobNew;

    @FXML
    private TextField txtGenderNew;

    @FXML
    private TextField txtSsnNew;

    @FXML
    private TextField txtEthnicityNew;

    @FXML
    private TextField txtPhysicianNew;

    @FXML
    private TextArea txtCurrentMedsNew;

    @FXML
    private TextField txtInsProvNew;

    @FXML
    private TextField txtInsPhoneNew;

    @FXML
    private TextField txtInsPolicyNew;

    @FXML
    private Button btnSaveNew;

    @FXML
    private Button btnResetNew;

    @FXML
    private Button btnCancelNew;

    @FXML
    private TextField txtFirstNameNew;

    @FXML
    private TextField txtLastNameNew;

    @FXML
    private TextField txtMiNew;

    @FXML
    void handleSave(ActionEvent event) throws IOException {

//        Do some validation
        if (validateFields()) {

//            Get new patient information entered by user
            String newFirstName = txtFirstNameNew.getText();
            String newLastName = txtLastNameNew.getText();
            String newMi = txtMiNew.getText();
            String newAddress = txtAddressNew.getText();
            String newPhone = txtPhoneNew.getText();
            String newEmail = txtEmailNew.getText();
            String newDob = txtDobNew.getText();
            String newGender = txtGenderNew.getText();
            String newSsn = txtSsnNew.getText();
            String newEthnicity = txtEthnicityNew.getText();
            String newPhysician = txtPhysicianNew.getText();
            String newInsProv = txtInsProvNew.getText();
            String newInsPhone = txtInsPhoneNew.getText();
            String newInsPolicy = txtInsPolicyNew.getText();
            String newCurrentMeds = txtCurrentMedsNew.getText();

//       INSERT statement in SQL to add new patient to database 
            String sql = "INSERT INTO lpgdb.patient\n"
                    + "(`patient_id`,\n"
                    + "`first_name`,\n"
                    + "`last_name`,\n"
                    + "`middle_name`,\n"
                    + "`dob`,\n"
                    + "`address`,\n"
                    + "`phone`,\n"
                    + "`email`,\n"
                    + "`gender`,\n"
                    + "`race`,\n"
                    + "`physician`,\n"
                    + "`insurance_provider`,\n"
                    + "`insurance_phone`,\n"
                    + "`insurance_policy`,\n"
                    + "`ssn`)\n"
                    + "VALUES\n"
                    + "(default,\n"
                    + "'" + newFirstName + "',\n"
                    + "'" + newLastName + "',\n"
                    + "'" + newMi + "',\n"
                    + "'" + newDob + "',\n"
                    + "'" + newAddress + "',\n"
                    + "'" + newPhone + "',\n"
                    + "'" + newEmail + "',\n"
                    + "'" + newGender + "',\n"
                    + "'" + newEthnicity + "',\n"
                    + "'" + newPhysician + "',\n"
                    + "'" + newInsProv + "',\n"
                    + "'" + newInsPhone + "',\n"
                    + "'" + newInsPolicy + "',\n"
                    + "'" + newSsn + "');";

            try {

                conn = dbConnector.getConnection();
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
//            System.out.println(sql);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Patient Added!");
                alert.showAndWait();
                Stage currentStage = (Stage) btnSaveNew.getScene().getWindow();
                currentStage.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            Parent root = FXMLLoader.load(getClass().getResource("patientListView.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }

    @FXML
    void handleReset(ActionEvent event) {

        txtFirstNameNew.setText("");
        txtLastNameNew.setText("");
        txtMiNew.setText("");
        txtAddressNew.setText("");
        txtPhoneNew.setText("");
        txtEmailNew.setText("");
        txtDobNew.setText("");
        txtGenderNew.setText("");
        txtSsnNew.setText("");
        txtEthnicityNew.setText("");
        txtPhysicianNew.setText("");
        txtInsProvNew.setText("");
        txtInsPhoneNew.setText("");
        txtInsPolicyNew.setText("");
        txtCurrentMedsNew.setText("");

    }

    @FXML
    void handleCancel(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("patientListView.fxml"));
        Stage Stage = new Stage();
        Scene scene = new Scene(root);
        Stage.setScene(scene);
        Stage.show();

        Stage currentStage = (Stage) btnCancelNew.getScene().getWindow();
        currentStage.close();
    }

    Alert validationAlert = new Alert(AlertType.ERROR);

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private DBConnector dbConnector;

//    ______________________INITIALIZE THE CONTROLLER______________________
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dbConnector = new DBConnector();

    }

    private boolean validateFields() {

        if (validateFirstName()
                && validateLastName()
                && validatePhone()
                && validateDob()
                && validateGender()
                && validateSsn()
                && validateInsProv()
                && validateInsPhone()
                && validateInsPolicy()) {

            return true;

        }

        return false;

    }

    private boolean validateFirstName() {

        if (txtFirstNameNew.getText() == null || txtFirstNameNew.getText().trim().isEmpty()) {
            validationAlert.setTitle("Missing Field");
            validationAlert.setHeaderText(null);
            validationAlert.setContentText("You must enter a first name");
            validationAlert.showAndWait();
        } else {
            return true;
        }

        return false;
    }

    private boolean validateLastName() {

        if (txtLastNameNew.getText() == null || txtLastNameNew.getText().trim().isEmpty()) {
            validationAlert.setTitle("Missing Field");
            validationAlert.setHeaderText(null);
            validationAlert.setContentText("You must enter a last name");
            validationAlert.showAndWait();
        } else {
            return true;
        }

        return false;
    }

    private boolean validatePhone() {

        if (txtPhoneNew.getText() == null || txtPhoneNew.getText().trim().isEmpty()) {
            validationAlert.setTitle("Missing Field");
            validationAlert.setHeaderText(null);
            validationAlert.setContentText("You must enter a patient phone number");
            validationAlert.showAndWait();
        } else {
            return true;
        }

        return false;
    }

    private boolean validateDob() {

        if (txtDobNew.getText() == null || txtDobNew.getText().trim().isEmpty()) {
            validationAlert.setTitle("Missing Field");
            validationAlert.setHeaderText(null);
            validationAlert.setContentText("You must enter a date of birth");
            validationAlert.showAndWait();
        } else {
            return true;
        }

        return false;
    }

    private boolean validateGender() {

        if (txtGenderNew.getText() == null || txtGenderNew.getText().trim().isEmpty()) {
            validationAlert.setTitle("Missing Field");
            validationAlert.setHeaderText(null);
            validationAlert.setContentText("You must enter a gender");
            validationAlert.showAndWait();
        } else {
            if (txtGenderNew.getText().length() > 1) {
                validationAlert.setTitle("Input Error");
                validationAlert.setHeaderText(null);
                validationAlert.setContentText("Enter only M or F in the gender field");
                validationAlert.showAndWait();
            } else {
                return true;
            }
        }

        return false;

    }

    private boolean validateSsn() {

        if (txtSsnNew.getText() == null || txtSsnNew.getText().trim().isEmpty()) {
            validationAlert.setTitle("Missing Field");
            validationAlert.setHeaderText(null);
            validationAlert.setContentText("You must enter a social security number");
            validationAlert.showAndWait();
        } else {
            return true;
        }

        return false;

    }

    private boolean validateInsProv() {

        if (txtInsProvNew.getText() == null || txtInsProvNew.getText().trim().isEmpty()) {
            validationAlert.setTitle("Missing Field");
            validationAlert.setHeaderText(null);
            validationAlert.setContentText("You must enter an insurance provider");
            validationAlert.showAndWait();
        } else {
            return true;
        }

        return false;

    }

    private boolean validateInsPhone() {

        if (txtInsPhoneNew.getText() == null || txtInsPhoneNew.getText().trim().isEmpty()) {
            validationAlert.setTitle("Missing Field");
            validationAlert.setHeaderText(null);
            validationAlert.setContentText("You must enter a phone number for the insurance provider");
            validationAlert.showAndWait();
        } else {
            return true;
        }

        return false;

    }

    private boolean validateInsPolicy() {

        if (txtInsPolicyNew.getText() == null || txtInsPolicyNew.getText().trim().isEmpty()) {
            validationAlert.setTitle("Missing Field");
            validationAlert.setHeaderText(null);
            validationAlert.setContentText("You must enter an insurance policy number");
            validationAlert.showAndWait();
        } else {
            return true;
        }

        return false;

    }

}
