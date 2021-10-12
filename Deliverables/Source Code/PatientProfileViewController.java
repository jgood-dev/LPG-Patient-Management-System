package main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static main.PatientListViewController.selectedPatient;

/**
 * FXML Controller class
 *
 * @author goodw
 */
public class PatientProfileViewController implements Initializable {

    @FXML
    private TabPane tbpProfile;

    @FXML
    private TextArea txtAddress;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtDob;

    @FXML
    private TextField txtGender;

    @FXML
    private TextField txtSsn;

    @FXML
    private TextField txtEthnicity;

    @FXML
    private TextField txtPhysician;

    @FXML
    private TextArea txtCurrentMeds;

    @FXML
    private TextField txtInsProv;

    @FXML
    private TextField txtInsPhone;

    @FXML
    private TextField txtInsPolicy;

    @FXML
    private Button btnEditProfile;

    @FXML
    private Button btnBackToList;

    @FXML
    private Button btnSaveProfile;

    @FXML
    private Button btnCancelProfile;

    @FXML
    private TextField txtNumCheckups;

    @FXML
    private TextField txtMostRecentCheckupDate;

    @FXML
    private TextField txtHeightMostRec;

    @FXML
    private TextField txtWeightMostRec;

    @FXML
    private TextField txtBpSysMostRecent;

    @FXML
    private TextField txtBpDiaMostRecent;

    @FXML
    private TextField txtHrMostRec;

    @FXML
    private TextArea txtCurrentMedsMostRec;

    @FXML
    private TextField txtHeightAvg;

    @FXML
    private TextField txtWeightAvg;

    @FXML
    private TextField txtBpSysAvg;

    @FXML
    private TextField txtBpDiaAvg;

    @FXML
    private TextField txtHrAvg;

    @FXML
    private Button btnSeeAllCheckup;

    @FXML
    private Button btnAddNewCheckup;

    @FXML
    private Button btnDeleteProfile;

    @FXML
    private Label lblPatientName;

    @FXML
    void handleBackToList(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("patientListView.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        Stage currentStage = (Stage) btnBackToList.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void handleDeleteProfile(ActionEvent event) throws IOException {
        String sqlDeletePatient = "DELETE FROM lpgdb.patient\n"
                + "WHERE patient_id = '" + selectedPatient.getId() + "';";
        String sqlDeleteWellness = "DELETE FROM lpgdb.wellness\n"
                + "WHERE patient_id = '" + selectedPatient.getId() + "';";

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("YOU ARE ABOUT TO PERMANENTLY DELETE A PROFILE");
        alert.setContentText("Are you sure you want to do this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            try {
                conn = dbConnector.getConnection();
                stmt = conn.createStatement();
                stmt.executeUpdate(sqlDeletePatient);
                stmt.executeUpdate(sqlDeleteWellness);

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            Parent root = FXMLLoader.load(getClass().getResource("patientListView.fxml"));
            Stage Stage = new Stage();
            Scene scene = new Scene(root);
            Stage.setScene(scene);
            Stage.show();

            Stage currentStage = (Stage) btnDeleteProfile.getScene().getWindow();
            currentStage.close();

        }
    }

    @FXML
    void handleEditProfile(ActionEvent event) {

        txtAddress.setEditable(true);
        txtAddress.setStyle("-fx-control-inner-background: white");
        txtPhone.setEditable(true);
        txtPhone.setStyle("-fx-control-inner-background: white");
        txtEmail.setEditable(true);
        txtEmail.setStyle("-fx-control-inner-background: white");
        txtDob.setEditable(true);
        txtDob.setStyle("-fx-control-inner-background: white");
        txtGender.setEditable(true);
        txtGender.setStyle("-fx-control-inner-background: white");
        txtSsn.setEditable(true);
        txtSsn.setStyle("-fx-control-inner-background: white");
        txtEthnicity.setEditable(true);
        txtEthnicity.setStyle("-fx-control-inner-background: white");
        txtPhysician.setEditable(true);
        txtPhysician.setStyle("-fx-control-inner-background: white");
        txtInsProv.setEditable(true);
        txtInsProv.setStyle("-fx-control-inner-background: white");
        txtInsPhone.setEditable(true);
        txtInsPhone.setStyle("-fx-control-inner-background: white");
        txtInsPolicy.setEditable(true);
        txtInsPolicy.setStyle("-fx-control-inner-background: white");
        txtCurrentMeds.setEditable(true);
        txtCurrentMeds.setStyle("-fx-control-inner-background:  white");

        btnSaveProfile.setOpacity(1);
        btnSaveProfile.setDisable(false);
        btnCancelProfile.setOpacity(1);
        btnCancelProfile.setDisable(false);
        btnEditProfile.setOpacity(0);
        btnEditProfile.setDisable(true);
        btnDeleteProfile.setOpacity(0);
        btnDeleteProfile.setDisable(true);
        btnBackToList.setOpacity(0);
        btnBackToList.setDisable(true);

    }

    @FXML
    void handleSaveButton(ActionEvent event) {

        txtAddress.setEditable(false);
        txtAddress.setStyle("-fx-control-inner-background:  #D0D3D4");
        txtPhone.setEditable(false);
        txtPhone.setStyle("-fx-control-inner-background:  #D0D3D4");
        txtEmail.setEditable(false);
        txtEmail.setStyle("-fx-control-inner-background:  #D0D3D4");
        txtDob.setEditable(false);
        txtDob.setStyle("-fx-control-inner-background:  #D0D3D4");
        txtGender.setEditable(false);
        txtGender.setStyle("-fx-control-inner-background:  #D0D3D4");
        txtSsn.setEditable(false);
        txtSsn.setStyle("-fx-control-inner-background:  #D0D3D4");
        txtEthnicity.setEditable(false);
        txtEthnicity.setStyle("-fx-control-inner-background:  #D0D3D4");
        txtPhysician.setEditable(false);
        txtPhysician.setStyle("-fx-control-inner-background:  #D0D3D4");
        txtInsProv.setEditable(false);
        txtInsProv.setStyle("-fx-control-inner-background:  #D0D3D4");
        txtInsPhone.setEditable(false);
        txtInsPhone.setStyle("-fx-control-inner-background:  #D0D3D4");
        txtInsPolicy.setEditable(false);
        txtInsPolicy.setStyle("-fx-control-inner-background:  #D0D3D4");
        txtCurrentMeds.setEditable(false);
        txtCurrentMeds.setStyle("-fx-control-inner-background:  #D0D3D4");

        btnSaveProfile.setOpacity(0);
        btnSaveProfile.setDisable(true);
        btnCancelProfile.setOpacity(0);
        btnCancelProfile.setDisable(true);
        btnEditProfile.setOpacity(1);
        btnEditProfile.setDisable(false);
        btnDeleteProfile.setOpacity(1);
        btnDeleteProfile.setDisable(false);
        btnBackToList.setOpacity(1);
        btnBackToList.setDisable(false);

//        Get the information stored in all the text boxes
        String newAddress = txtAddress.getText();
        String newPhone = txtPhone.getText();
        String newEmail = txtEmail.getText();
        String newDob = txtDob.getText();
        String newGender = txtGender.getText();
        String newSsn = txtSsn.getText();
        String newEthnicity = txtEthnicity.getText();
        String newPhysician = txtPhysician.getText();
        String newInsProv = txtInsProv.getText();
        String newInsPhone = txtInsPhone.getText();
        String newInsPolicy = txtInsPolicy.getText();
        String newCurrentMeds = txtCurrentMeds.getText();

//        UPDATE statement in SQL to make the changes to the database
        String sqlPatient = "UPDATE lpgdb.patient\n"
                + "SET address = '" + newAddress + "',\n"
                + "phone = '" + newPhone + "',\n"
                + "email = '" + newEmail + "',\n"
                + "dob = '" + newDob + "',\n"
                + "gender = '" + newGender + "',\n"
                + "ssn = '" + newSsn + "',\n"
                + "race = '" + newEthnicity + "',\n"
                + "physician = '" + newPhysician + "',\n"
                + "insurance_provider = '" + newInsProv + "',\n"
                + "insurance_phone = '" + newInsPhone + "',\n"
                + "insurance_policy = '" + newInsPolicy + "'\n"
                + "WHERE patient_id = '" + selectedPatient.getId() + "';";

        String sqlWellness = "UPDATE lpgdb.wellness\n"
                + "SET current_meds = '" + newCurrentMeds + "'\n"
                + "WHERE patient_id = '" + selectedPatient.getId() + "';";

        try {

            conn = dbConnector.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlPatient);
            stmt.executeUpdate(sqlWellness);

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Profile Updated!");
            alert.showAndWait();

//            System.out.println(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    void handleCancelButton(ActionEvent e) {

        txtAddress.setEditable(false);
        txtAddress.setStyle("-fx-control-inner-background:  #D0D3D4");
        txtPhone.setEditable(false);
        txtPhone.setStyle("-fx-control-inner-background:  #D0D3D4");
        txtEmail.setEditable(false);
        txtEmail.setStyle("-fx-control-inner-background:  #D0D3D4");
        txtDob.setEditable(false);
        txtDob.setStyle("-fx-control-inner-background:  #D0D3D4");
        txtGender.setEditable(false);
        txtGender.setStyle("-fx-control-inner-background:  #D0D3D4");
        txtSsn.setEditable(false);
        txtSsn.setStyle("-fx-control-inner-background:  #D0D3D4");
        txtEthnicity.setEditable(false);
        txtEthnicity.setStyle("-fx-control-inner-background:  #D0D3D4");
        txtPhysician.setEditable(false);
        txtPhysician.setStyle("-fx-control-inner-background:  #D0D3D4");
        txtInsProv.setEditable(false);
        txtInsProv.setStyle("-fx-control-inner-background:  #D0D3D4");
        txtInsPhone.setEditable(false);
        txtInsPhone.setStyle("-fx-control-inner-background:  #D0D3D4");
        txtInsPolicy.setEditable(false);
        txtInsPolicy.setStyle("-fx-control-inner-background:  #D0D3D4");
        txtCurrentMeds.setEditable(false);
        txtCurrentMeds.setStyle("-fx-control-inner-background:  #D0D3D4");

        btnSaveProfile.setOpacity(0);
        btnSaveProfile.setDisable(true);
        btnCancelProfile.setOpacity(0);
        btnCancelProfile.setDisable(true);
        btnEditProfile.setOpacity(1);
        btnEditProfile.setDisable(false);
        btnDeleteProfile.setOpacity(1);
        btnDeleteProfile.setDisable(false);
        btnBackToList.setOpacity(1);
        btnBackToList.setDisable(false);

    }

    @FXML
    void handleSeeAllCheckups(ActionEvent event) throws IOException {

        WellnessListViewController wellnessList = new WellnessListViewController();
        wellnessList.openWellnessListView(selectedPatient);

        Stage currentStage = (Stage) btnSeeAllCheckup.getScene().getWindow();
        currentStage.close();

    }

    @FXML
    void handleNewWellness(ActionEvent event) throws IOException {

        WellnessNewController wellnessNew = new WellnessNewController();
        wellnessNew.openWellnessNew(selectedPatient);

        Stage currentStage = (Stage) btnAddNewCheckup.getScene().getWindow();
        currentStage.close();
    }

    Patient patient = selectedPatient;

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private DBConnector dbConnector;
    String mostRecentDate = "";
    int numCheckups;
    int heightAvg;
    int weightAvg;
    int bpSysAvg;
    int bpDiaAvg;

//    ______________________INITIALIZE THE CONTROLLER______________________
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        txtAddress.setStyle("-fx-control-inner-background:  #D0D3D4");
        txtCurrentMeds.setStyle("-fx-control-inner-background:  #D0D3D4");

        dbConnector = new DBConnector();

        getAverages();

        populateFields();
    }

    // Populate all fields in profile page with info from database WHERE patient_id = patient.getId()
    protected void openPatientProfile(Patient patient) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("patientProfileView.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    private void populateFields() {
        String patientName = patient.getLast() + ", " + patient.getFirst() + " " + patient.getMiddle();
        lblPatientName.setText(patientName);

        txtAddress.setText(patient.getAddress());
        txtPhone.setText(patient.getPhone());
        txtEmail.setText(patient.getEmail());
        txtDob.setText(patient.getDob());
        txtGender.setText(patient.getGender());
        txtSsn.setText(patient.getSsn());
        txtEthnicity.setText(patient.getEthnicity());
        txtPhysician.setText(patient.getPhysician());
        txtInsProv.setText(patient.getInsProvider());
        txtInsPhone.setText(patient.getInsPhone());
        txtInsPolicy.setText(patient.getInsPolicy());
        txtCurrentMeds.setText(patient.wellness.getCurrentMeds());
        txtMostRecentCheckupDate.setText(patient.wellness.getDate());
        txtHeightMostRec.setText(patient.wellness.getHeight());
        txtWeightMostRec.setText(patient.wellness.getWeight());
        txtBpSysMostRecent.setText(patient.wellness.getBpSys());
        txtBpDiaMostRecent.setText(patient.wellness.getBpDia());

        txtNumCheckups.setText(Integer.toString(numCheckups));
        txtHeightAvg.setText(Integer.toString(heightAvg));
        txtWeightAvg.setText(Integer.toString(weightAvg));
        txtBpSysAvg.setText(Integer.toString(bpSysAvg));
        txtBpDiaAvg.setText(Integer.toString(bpDiaAvg));

    }

    private void getAverages() {

        numCheckups = getNumCheckups();
        heightAvg = getAvgHeight();
        weightAvg = getAvgWeight();
        bpSysAvg = getAvgBpSys();
        bpDiaAvg = getAvgBpDia();

    }

    private int getNumCheckups() {
        String sql = "SELECT COUNT(patient_id)\n"
                + "FROM wellness\n"
                + "WHERE patient_id = '" + selectedPatient.getId()
                + "' AND wellness.reason IS NOT NULL";

        try {
            conn = dbConnector.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();

            return rs.getInt(1);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    private int getAvgHeight() {
        String sql = "SELECT AVG(height)\n"
                + "FROM wellness\n"
                + "WHERE patient_id = '" + selectedPatient.getId()
                + "' AND wellness.reason IS NOT NULL";

        try {
            conn = dbConnector.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();

            return rs.getInt(1);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    private int getAvgWeight() {
        String sql = "SELECT AVG(weight)\n"
                + "FROM wellness\n"
                + "WHERE patient_id = '" + selectedPatient.getId()
                + "' AND wellness.reason IS NOT NULL";

        try {
            conn = dbConnector.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();

            return rs.getInt(1);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    private int getAvgBpSys() {
        String sql = "SELECT AVG(bp_systolic)\n"
                + "FROM wellness\n"
                + "WHERE patient_id = '" + selectedPatient.getId()
                + "' AND wellness.reason IS NOT NULL";

        try {
            conn = dbConnector.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();

            return rs.getInt(1);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    private int getAvgBpDia() {
        String sql = "SELECT AVG(bp_diastolic)\n"
                + "FROM wellness\n"
                + "WHERE patient_id = '" + selectedPatient.getId()
                + "' AND wellness.reason IS NOT NULL";

        try {
            conn = dbConnector.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();

            return rs.getInt(1);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

}
