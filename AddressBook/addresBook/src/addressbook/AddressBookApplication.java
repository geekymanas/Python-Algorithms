/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.io.IOException;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeTableColumn;
import javafx.stage.Stage;
import dao.ApplicationDao;


/**
 *
 * @author lenovo
 */
public class AddressBookApplication extends  Application{
 
    @Override
    

    public void start(Stage primaryStage) throws IOException{
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setUrl("jdbc:mysql://localhost:3306/addressBook");
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("1234");
        final ApplicationDao applicationDao = new ApplicationDao();
        applicationDao.setDataSource(mysqlDataSource);
        
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("AddressBook.fxml"));
        Parent root = fxmlLoader.load();
        AddressBookController controller = fxmlLoader.getController();
        controller.setApplicationDao(applicationDao);
        primaryStage.setTitle("Address Book");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    
     public static void main(String[] args) {
        launch(args);
    }
    
}
