package main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import static main.PatientListViewController.selectedPatient;

/**
 * FXML Controller class
 *
 * @author goodw
 */
public class WellnessListViewController implements Initializable {

    @FXML
    private Button btnWellnessDetails;

    @FXML
    private Button btnNewWellness;

    @FXML
    private Button btnSummaryViewWellness;

    @FXML
    private Button btnDeleteWellness;

    @FXML
    private TableView<Wellness> tblWellnessList;

    @FXML
    private TableColumn<Wellness, String> colDate;

    @FXML
    private TableColumn<Wellness, String> colReason;

    @FXML
    private TableColumn<Wellness, String> colNotes;

    @FXML
    void handleDeleteWellness(ActionEvent event) throws IOException {
        Wellness selectedWellness = tblWellnessList.getSelectionModel().getSelectedItem();

        String sqlDelete = "DELETE FROM lpgdb.wellness\n"
                + "WHERE patient_id = '" + selectedPatient.getId() + "'"
                + "AND date = '" + selectedWellness.getDate() + "';";

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("YOU ARE ABOUT TO PERMANENTLY DELETE A CHECKUP");
        alert.setContentText("Are you sure you want to do this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            try {
                conn = dbConnector.getConnection();
                stmt = conn.createStatement();
                stmt.executeUpdate(sqlDelete);

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            Parent root = FXMLLoader.load(getClass().getResource("wellnessListView.fxml"));
            Stage Stage = new Stage();
            Scene scene = new Scene(root);
            Stage.setScene(scene);
            Stage.show();

            Stage currentStage = (Stage) btnDeleteWellness.getScene().getWindow();
            currentStage.close();
        }
    }

    @FXML
    void handleNewWellness(ActionEvent event) throws IOException {

        WellnessNewController wellnessNew = new WellnessNewController();
        wellnessNew.openWellnessNew(selectedPatient);

        Stage currentStage = (Stage) btnNewWellness.getScene().getWindow();
        currentStage.close();

    }

    @FXML
    void handleReturnToSummary(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("patientProfileView.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        Stage currentStage = (Stage) btnSummaryViewWellness.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void handleWellnessDetails(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        if (tblWellnessList.getSelectionModel().getSelectedItem() == null) {
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No checkup selected.");
            alert.showAndWait();
        } else {
            alert.setTitle("Good Job");
            alert.setHeaderText(null);
            alert.setContentText("I know you selected a checkup on the list, "
                    + "but this function isn't currently working.\n"
                    + "Have a great day.");
            alert.showAndWait();
        }

    }

    static Wellness selectedWellness = new Wellness();

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

        populateTableView();

    }

    private void populateTableView() {

        try {

            list = FXCollections.observableArrayList();
            String query = "SELECT *\n"
                    + "FROM wellness\n"
                    + "WHERE patient_id = '" + selectedPatient.getId() + "'\n";
//                    + "AND reason IS NOT NULL\n"
//                    + "ORDER BY date DESC";
            conn = dbConnector.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {

                Wellness wellness = new Wellness();
                wellness.setDate(rs.getString("date"));
                wellness.setReason(rs.getString("reason"));
                wellness.setNotes(rs.getString("notes"));

                list.add(wellness);

            }

        } catch (SQLException e) {
            Logger.getLogger(PatientListViewController.class.getName()).log(Level.SEVERE, null, e);
        }

        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colReason.setCellValueFactory(new PropertyValueFactory<>("reason"));
        colNotes.setCellValueFactory(new PropertyValueFactory<>("notes"));

        tblWellnessList.setItems(list);

    }

    protected void openWellnessListView(Patient patient) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("wellnessListView.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
