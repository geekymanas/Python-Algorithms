/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TreeTableColumn;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableRow;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import dao.ApplicationDao;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Tooltip;


/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class AddressBookController implements Initializable {
  
    @FXML
    private JFXTreeTableView<User> treeTableView;
    @FXML
    private JFXTextField nameField;
    @FXML
    private JFXButton addBtn;

    private final ObservableList<User> users = FXCollections.observableArrayList();
    private Stage addressEntryStage;
    private AddressEntryController addressEntryController;
    private ApplicationDao applicationDao;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        JFXTreeTableColumn<User, String> nameColumn = (JFXTreeTableColumn<User, String>)treeTableView.getColumns().get(0);
        nameColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<User, String> param) -> (param.getValue().getValue().name));

        JFXTreeTableColumn<User, LocalDate> birthDayColumn = (JFXTreeTableColumn<User, LocalDate>)treeTableView.getColumns().get(1);
        birthDayColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<User, LocalDate> param) -> param.getValue().getValue().birthDay);        

        JFXTreeTableColumn<User, String> companyColumn = (JFXTreeTableColumn<User, String>)treeTableView.getColumns().get(2);
        companyColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<User, String> param) -> param.getValue().getValue().company);
        
        JFXTreeTableColumn<User, String> phNoColumn = (JFXTreeTableColumn<User, String>)treeTableView.getColumns().get(3);
        phNoColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<User, String> param) -> (param.getValue().getValue().phNo));
        
        JFXTreeTableColumn<User, String> emailColumn = (JFXTreeTableColumn<User, String>)treeTableView.getColumns().get(4);
        emailColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<User, String> param) ->(param.getValue().getValue().email));
        
        JFXTreeTableColumn<User, String> addressColumn = (JFXTreeTableColumn<User, String>)treeTableView.getColumns().get(5);
        addressColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<User, String> param) -> (param.getValue().getValue().address));
        
        final TreeItem<User> root = new RecursiveTreeItem<>(users, RecursiveTreeObject::getChildren);
        treeTableView.setRoot(root);
        treeTableView.setShowRoot(false);
        treeTableView.setEditable(false);
        treeTableView.setRowFactory(tv -> {
            TreeTableRow<User> row = new TreeTableRow<>();
            row.setTooltip(new Tooltip("Double Click to Edit/Delete"));
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    User rowData = row.getItem();
                    try {
                        showAddInput();
                        addressEntryController.setData(rowData);
                    } catch (IOException ex) {
                        Logger.getLogger(AddressBookController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            return row;
        });
   }

    @FXML
    private void searchOp(ActionEvent event) {
        treeTableView.getRoot().getChildren().clear();
        String Name = nameField.getText();
        try {
            List<User> addressdata = applicationDao.searchAddressBook(Name);
            users.addAll(addressdata);
        } catch (Exception e) {
            Logger.getLogger(AddressBookController.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void createAddressEntryScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("AddressEntry.fxml"));
        Parent root = fxmlLoader.load();
        addressEntryController = fxmlLoader.getController();
        addressEntryController.setAddressBookController(this);
        addressEntryController.setApplicationDao(applicationDao);
        addressEntryStage = new Stage();
        addressEntryStage.initOwner((Stage) addBtn.getScene().getWindow());
        addressEntryStage.setScene(new Scene(root));
    }
         
    @FXML
    private void showAddInput() throws IOException{
        if (addressEntryStage == null) {
            createAddressEntryScene();
        }
        addressEntryStage.show();
    }
    
    public void setApplicationDao(ApplicationDao applicationDao) {
        this.applicationDao = applicationDao;
    }
    
    public void delete() {
        users.remove(treeTableView.getSelectionModel().getSelectedItem().getValue());
        treeTableView.getSelectionModel().clearSelection(0);
    }
    
}


