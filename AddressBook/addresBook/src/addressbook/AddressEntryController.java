/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.RowData;
import dao.ApplicationDao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JOptionPane;
/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class AddressEntryController implements Initializable {

    @FXML
    private JFXTextField namField;
    @FXML
    private JFXTextField phoneField;
    @FXML
    private JFXTextField addressField;
    @FXML
    private JFXTextField companyField;
    @FXML
    private JFXTextField emailField;
    @FXML
    private JFXDatePicker birthdayField;
    @FXML
    private JFXButton saveBtn;
    @FXML
    private JFXButton cancelBtn;

    private ApplicationDao applicationDao;
    private User address = new User();    
    private AddressBookController addressBookController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

    @FXML
    private void SaveData(ActionEvent event) {
      //call applicationDao.save();
      Date date = (Date) Date.from(birthdayField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
      applicationDao.saveData(address.addressId, namField.getText(), date, companyField.getText(), phoneField.getText(), emailField.getText(), addressField.getText());
      address.company.setValue(companyField.getText());
      address.name.setValue(namField.getText());
      address.email.setValue(emailField.getText());
      address.phNo.setValue(phoneField.getText());
      address.address.setValue(addressField.getText());
      address.birthDay.setValue(birthdayField.getValue());
      setData(new User());
      ((Stage) saveBtn.getScene().getWindow()).close();      
    }

    @FXML
    private void CancelOp(ActionEvent event) {
       setData(new User());
       ((Stage) cancelBtn.getScene().getWindow()).close();
    }
 
    public void setApplicationDao(ApplicationDao applicationDao) {
        this.applicationDao = applicationDao;
    }
    
    public void setData(User rowData){
       address = rowData;
       namField.setText(rowData.name.getValue());
       phoneField.setText(rowData.phNo.getValue());
       addressField.setText(rowData.address.getValue());
       companyField.setText(rowData.company.getValue());
       birthdayField.setValue(rowData.birthDay.getValue());
       emailField.setText(rowData.email.getValue());
    }

    @FXML
    private void deleteData(ActionEvent event){ 
       applicationDao.deleteData(address.addressId);     
       setData(new User());
       addressBookController.delete();
       ((Stage) cancelBtn.getScene().getWindow()).close();
    }
    
    public void setAddressBookController(AddressBookController addressBookController){
        this.addressBookController = addressBookController;
    }
}
