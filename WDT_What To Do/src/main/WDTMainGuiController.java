package main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import database.DataBaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

public class WDTMainGuiController implements Initializable{
	@FXML
	Button addButtonMain;
	@FXML
	TableView<taskEvent> taskTable;
	@FXML 
	TableColumn<taskEvent, Object> date;
	@FXML 
	TableColumn<taskEvent, String> title;
	@FXML 
	TableColumn<taskEvent, String> desc;
	@FXML
	TableColumn<taskEvent, String> prio;
	@FXML
	private WDTAddGuiController addTaskGuiController;
	
	Connection connection=null;
	ResultSet ResultS=null;
	DataBaseHandler handler;
	PreparedStatement PrepStat= null;

	ObservableList<taskEvent> list=FXCollections.observableArrayList();
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		handler=new DataBaseHandler();

		try {
			Connection connect =handler.getConnection();
			ResultSet ResSet= connect.createStatement().executeQuery("SELECT * FROM tasktable");
			while(ResSet.next()) {
				list.add(new taskEvent(ResSet.getObject("Date"),ResSet.getString("Title"),ResSet.getString("Description")));	

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		date.setCellValueFactory(new PropertyValueFactory<>("Date"));
		title.setCellValueFactory(new PropertyValueFactory<>("Title"));
		desc.setCellValueFactory(new PropertyValueFactory<>("Description"));
		taskTable.setItems(list);


	}
	public void onAddTask() throws IOException {

		Stage close = (Stage) addButtonMain.getScene().getWindow();
		close.close();

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("WDTAdd.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setOpacity(1);
		stage.setTitle("WDT -What To Do");
		stage.setScene(new Scene(root, 400, 350));
		stage.showAndWait();

	}

	public void deleteClick() throws SQLException{
		
		int selectedIndex = taskTable.getSelectionModel().getSelectedIndex();
		
		    if(selectedIndex >= 0){
		        connection = handler.getConnection();
		        
				String query1 = "DELETE FROM tasktable WHERE Title = ? ;";      
				
				PrepStat = connection.prepareStatement(query1);              
		        PrepStat.setString(1, title.getText()); 
		        PrepStat.execute();       
		        
		        taskTable.getItems().remove(selectedIndex);
		    } else {
		        Alert alert = new Alert(AlertType.ERROR);
		        alert.setTitle("ERROR:");
		        alert.setHeaderText("No task selected");
		        alert.setContentText("Please select task to delete.");
		        alert.showAndWait();
		    }
		}
	}



