package main;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author goodw
 */
public class Wellness {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty date = new SimpleStringProperty();
    private final StringProperty time = new SimpleStringProperty();
    private final StringProperty bpDia = new SimpleStringProperty();
    private final StringProperty bpSys = new SimpleStringProperty();
    private final StringProperty weight = new SimpleStringProperty();
    private final StringProperty height = new SimpleStringProperty();
    private final StringProperty currentMeds = new SimpleStringProperty();
    private final StringProperty reason = new SimpleStringProperty();
    private final StringProperty notes = new SimpleStringProperty();
    private final StringProperty physician = new SimpleStringProperty();

    public int getId() {
        return id.get();
    }

    public String getDate() {
        return date.get();
    }

    public String getTime() {
        return time.get();
    }

    public String getBpDia() {
        return bpDia.get();
    }

    public String getBpSys() {
        return bpSys.get();
    }

    public String getWeight() {
        return weight.get();
    }

    public String getHeight() {
        return height.get();
    }

    public String getCurrentMeds() {
        return currentMeds.get();
    }

    public String getReason() {
        return reason.get();
    }

    public String getNotes() {
        return notes.get();
    }

    public String getPhysician() {
        return physician.get();
    }

    public void setId(int value) {
        id.set(value);
    }

    public void setDate(String value) {
        date.set(value);
    }

    public void setTime(String value) {
        time.set(value);
    }

    public void setBpDia(String value) {
        bpDia.set(value);
    }

    public void setBpSys(String value) {
        bpSys.set(value);
    }

    public void setWeight(String value) {
        weight.set(value);
    }

    public void setHeight(String value) {
        height.set(value);
    }

    public void setCurrentMeds(String value) {
        currentMeds.set(value);
    }

    public void setReason(String value) {
        reason.set(value);
    }

    public void setNotes(String value) {
        notes.set(value);
    }

    public void setPhysician(String value) {
        physician.set(value);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty dateProperty() {
        return date;
    }

    public StringProperty timeProperty() {
        return time;
    }

    public StringProperty bpDiaProperty() {
        return bpDia;
    }

    public StringProperty bpSysProperty() {
        return bpSys;
    }

    public StringProperty weightProperty() {
        return weight;
    }

    public StringProperty heightProperty() {
        return height;
    }

    public StringProperty currentMedsProperty() {
        return currentMeds;
    }

    public StringProperty reasonProperty() {
        return reason;
    }

    public StringProperty notesProperty() {
        return notes;
    }

    public StringProperty physicianProperty() {
        return physician;
    }
}
