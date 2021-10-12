package main;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author goodw
 */
public class Patient {

    Wellness wellness = new Wellness();

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty last = new SimpleStringProperty();
    private final StringProperty first = new SimpleStringProperty();
    private final StringProperty middle = new SimpleStringProperty();
    private final StringProperty dob = new SimpleStringProperty();
    private final StringProperty address = new SimpleStringProperty();
    private final StringProperty phone = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty gender = new SimpleStringProperty();
    private final StringProperty ethnicity = new SimpleStringProperty();
    private final StringProperty physician = new SimpleStringProperty();
    private final StringProperty insProvider = new SimpleStringProperty();
    private final StringProperty insPhone = new SimpleStringProperty();
    private final StringProperty insPolicy = new SimpleStringProperty();
    private final StringProperty ssn = new SimpleStringProperty();

    public int getId() {
        return id.get();
    }

    public String getLast() {
        return last.get();
    }

    public String getFirst() {
        return first.get();
    }

    public String getMiddle() {
        return middle.get();
    }

    public String getDob() {
        return dob.get();
    }

    public String getAddress() {
        return address.get();
    }

    public String getPhone() {
        return phone.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getGender() {
        return gender.get();
    }

    public String getEthnicity() {
        return ethnicity.get();
    }

    public String getPhysician() {
        return physician.get();
    }

    public String getInsProvider() {
        return insProvider.get();
    }

    public String getInsPhone() {
        return insPhone.get();
    }

    public String getInsPolicy() {
        return insPolicy.get();
    }

    public String getSsn() {
        return ssn.get();
    }

    public void setId(int value) {
        id.set(value);
    }

    public void setFirst(String value) {
        first.set(value);
    }

    public void setLast(String value) {
        last.set(value);
    }

    public void setMiddle(String value) {
        middle.set(value);
    }

    public void setDob(String value) {
        dob.set(value);
    }

    public void setAddress(String value) {
        address.set(value);
    }

    public void setPhone(String value) {
        phone.set(value);
    }

    public void setEmail(String value) {
        email.set(value);
    }

    public void setGender(String value) {
        gender.set(value);
    }

    public void setEthnicity(String value) {
        ethnicity.set(value);
    }

    public void setPhysician(String value) {
        physician.set(value);
    }

    public void setInsProvider(String value) {
        insProvider.set(value);
    }

    public void setInsPhone(String value) {
        insPhone.set(value);
    }

    public void setInsPolicy(String value) {
        insPolicy.set(value);
    }

    public void setSsn(String value) {
        ssn.set(value);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty lastProperty() {
        return last;
    }

    public StringProperty firstProperty() {
        return first;
    }

    public StringProperty middleProperty() {
        return middle;
    }

    public StringProperty dobProperty() {
        return dob;
    }

    public StringProperty addressProperty() {
        return address;
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public StringProperty ethinicityProperty() {
        return ethnicity;
    }

    public StringProperty physicianProperty() {
        return physician;
    }

    public StringProperty insProviderProperty() {
        return insProvider;
    }

    public StringProperty insPhoneProperty() {
        return insPhone;
    }

    public StringProperty insPolicyProperty() {
        return insPolicy;
    }

    public StringProperty ssnProperty() {
        return ssn;
    }

}
