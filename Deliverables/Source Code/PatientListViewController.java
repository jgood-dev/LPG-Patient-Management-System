package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author goodw
 */
public class PatientListViewController implements Initializable {

    @FXML
    private TextField txtIdSearch;

    @FXML
    private TextField txtPatientSearch;

    @FXML
    private Button btnIdSearch;

    @FXML
    private Button btnPatientSearch;

    @FXML
    private Button btnNewPatient;

    @FXML
    private Button btnViewProfile;

    @FXML
    private Button btnNewCheckup;

    @FXML
    private Button btnExit;

    @FXML
    private TableView<Patient> tblPatientList;

    @FXML
    private TableColumn<Patient, Integer> colID;

    @FXML
    private TableColumn<Patient, String> colLast;

    @FXML
    private TableColumn<Patient, String> colFirst;

    @FXML
    private TableColumn<Patient, String> colMI;

    @FXML
    void handleIdSearch(ActionEvent event) {
        sql = "SELECT *\n"
                + "FROM patient\n"
                + "LEFT JOIN wellness ON patient.patient_id=wellness.patient_id\n"
                + "WHERE patient.patient_id LIKE '%" + txtIdSearch.getText().trim() + "%'\n"
                + "GROUP BY patient.patient_id\n"
                + "ORDER BY patient.patient_id";

        populateTableView();
    }

    @FXML
    void handleNameSearch(ActionEvent event) {
        sql = "SELECT *\n"
                + "FROM patient\n"
                + "LEFT JOIN wellness ON patient.patient_id=wellness.patient_id\n"
                + "WHERE last_name LIKE '%" + txtPatientSearch.getText().trim() + "%'"
                + "OR first_name LIKE '%" + txtPatientSearch.getText().trim() + "%'\n"
                + "GROUP BY patient.patient_id\n"
                + "ORDER BY patient.patient_id";

        populateTableView();
    }

    @FXML
    void handleNewPatient(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("patientNew.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        Stage currentStage = (Stage) btnNewPatient.getScene().getWindow();
        currentStage.close();

    }

    @FXML
    void handleViewProfile(ActionEvent event) throws IOException {

        if (tblPatientList.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No patient selected.");
            alert.showAndWait();
        } else {
            selectedPatient = tblPatientList.getSelectionModel().getSelectedItem();

            PatientProfileViewController profileView = new PatientProfileViewController();
            profileView.openPatientProfile(selectedPatient);

            Stage currentStage = (Stage) btnViewProfile.getScene().getWindow();
            currentStage.close();
        }

    }

    @FXML
    void handleNewWellness(ActionEvent event) throws IOException {

        if (tblPatientList.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No patient selected.");
            alert.showAndWait();
        } else {
            selectedPatient = tblPatientList.getSelectionModel().getSelectedItem();

            WellnessNewController wellnessNew = new WellnessNewController();
            wellnessNew.openWellnessNew(selectedPatient);

            Stage currentStage = (Stage) btnNewCheckup.getScene().getWindow();
            currentStage.close();
        }

    }

    @FXML
    void handleExit(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    static Patient selectedPatient = new Patient();
    int patientId;
    String sql = "SELECT *\n"
            + "FROM patient\n"
            + "LEFT JOIN wellness ON patient.patient_id=wellness.patient_id\n"
            + "GROUP BY patient.patient_id\n"
            + "ORDER BY patient.patient_id, wellness.date";

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private ObservableList<Patient> list;
    private DBConnector dbConnector;

//    ______________________INITIALIZE THE CONTROLLER______________________
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dbConnector = new DBConnector();

        populateTableView();

    }

    private void populateTableView() {

        try {
            list = FXCollections.observableArrayList();
            conn = dbConnector.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {

                Patient patient = new Patient();
                patient.setId(rs.getInt("patient_id"));
                patient.setLast(rs.getString("last_name"));
                patient.setFirst(rs.getString("first_name"));
                patient.setMiddle(rs.getString("middle_name"));
                patient.setDob((rs.getString("dob")));
                patient.setAddress((rs.getString("address")));
                patient.setPhone((rs.getString("phone")));
                patient.setEmail((rs.getString("email")));
                patient.setGender((rs.getString("gender")));
                patient.setEthnicity((rs.getString("race")));
                patient.setPhysician((rs.getString("physician")));
                patient.setInsProvider((rs.getString("insurance_provider")));
                patient.setInsPhone((rs.getString("insurance_phone")));
                patient.setInsPolicy((rs.getString("insurance_policy")));
                patient.setSsn((rs.getString("ssn")));

                patient.wellness.setDate(rs.getString("date"));
                patient.wellness.setCurrentMeds(rs.getString("current_meds"));
                patient.wellness.setHeight(rs.getString("height"));
                patient.wellness.setWeight(rs.getString("weight"));
                patient.wellness.setBpSys(rs.getString("bp_systolic"));
                patient.wellness.setBpDia(rs.getString("bp_diastolic"));

                list.add(patient);

            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientListViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colLast.setCellValueFactory(new PropertyValueFactory<>("last"));
        colFirst.setCellValueFactory(new PropertyValueFactory<>("first"));
        colMI.setCellValueFactory(new PropertyValueFactory<>("middle"));

        tblPatientList.setItems(list);

    }

}
