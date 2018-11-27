package main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.Initializable;
import database.DataBaseHandler;

public class WDTAddGuiController implements Initializable {
	Connection conn=null;
	PreparedStatement PrepStat= null;
	DataBaseHandler handler;

	@FXML
	TextField title;
	@FXML
	DatePicker date;
	@FXML
	Button addButton;
	@FXML
	Button cancelButton;
	@FXML
	Button deleteButton;
	@FXML 
	TextField description;
	@FXML
	ToggleGroup priority;
	
	

				

	String query ="INSERT INTO tasktable(Date,Title,Description,Priority)"+" VALUES (?,?,?,?)";
	public void initialize(URL url, ResourceBundle rb) {
		handler=new DataBaseHandler();
		// TODO

	}    
	
	public void addTaskClick(ActionEvent e) {
		
		RadioButton selectedRadioButton = (RadioButton) priority.getSelectedToggle();
		
		conn = handler.getConnection();

		try
		{
			PrepStat=conn.prepareStatement(query);
		}
		catch(SQLException e1) 
		{
			e1.printStackTrace();
		}
		try 
		{
			PrepStat.setObject(1, date.getValue());
			PrepStat.setString(2, title.getText());
			PrepStat.setString(3, description.getText());
			PrepStat.setString(4, selectedRadioButton.getText());
			
			PrepStat.executeUpdate();
			conn.close();
		} 

		catch (SQLException e1)
		{
			e1.printStackTrace();
		}

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("WDT - What To Do");
		alert.setHeaderText("What To Do");
		alert.setContentText("Task created");
		alert.showAndWait();
		ClearFields();

	}


	public void ClearFields() {
		date.setValue(null);
		title.clear();
		description.clear();
	}
	public void cancelClick() throws IOException {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
		Parent root = FXMLLoader.load(getClass().getResource("WDTMain.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("WDT - What To Do");
		stage.setScene(scene);
		stage.show();       
	}
}	