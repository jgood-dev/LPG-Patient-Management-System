package main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static main.PatientListViewController.selectedPatient;
import static main.WellnessListViewController.selectedWellness;

/**
 * FXML Controller class
 *
 * @author goodw
 */
public class WellnessSelectedController implements Initializable {

    @FXML
    private Label lblPatientName;
    @FXML
    private TextField txtDateWellnessSelected;
    @FXML
    private TextArea txtCurrentMedsWellnessSelected;
    @FXML
    private TextField txtHeightWellnessSelected;
    @FXML
    private TextField txtWeightWellnessSelected;
    @FXML
    private TextField txtBpSysWellnessSelected;
    @FXML
    private TextField txtBpDiaWellnessSelected;
    @FXML
    private TextField txtHrWellnessSelected;
    @FXML
    private TextArea txtReasonWellnessSelected;
    @FXML
    private TextField txtPhysicianWellnessSelected;
    @FXML
    private TextArea txtNotesWellnessSelected;
    @FXML
    private Button btnReturnToListSelected;

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private ObservableList<Wellness> list;
    private DBConnector dbConnector;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dbConnector = new DBConnector();

        populateFields();

    }

    protected void openWellnessSelected(Wellness wellness) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("wellnessSelected.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    private void populateFields() {

        String patientName = selectedPatient.getLast() + ", " + selectedPatient.getFirst() + " " + selectedPatient.getMiddle();
        lblPatientName.setText(patientName);

        txtDateWellnessSelected.setText(selectedWellness.getDate());
    }

}
