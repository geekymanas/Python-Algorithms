package addressbook;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.time.LocalDate;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public final class User extends RecursiveTreeObject<User> {
    public long addressId;
    public StringProperty phNo;
    public StringProperty name;
    public StringProperty address;
    public StringProperty company;
    public StringProperty email;
    public SimpleObjectProperty<LocalDate> birthDay;

    public User(long addressId, String phNo, String name, String address, String company, String email, LocalDate birthDay) {
        this.addressId = addressId;
        this.phNo = new SimpleStringProperty(phNo);
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.company = new SimpleStringProperty(company);
        this.email = new SimpleStringProperty(email);
        this.birthDay = new SimpleObjectProperty<>(birthDay);
    }

    public User() {
        addressId = 0;
        this.phNo = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
        this.address =new SimpleStringProperty();
        this.company = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.birthDay = new SimpleObjectProperty<>();
    }

}
