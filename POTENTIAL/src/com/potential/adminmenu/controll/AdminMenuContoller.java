package com.potential.adminmenu.controll;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.LoggingMXBean;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class AdminMenuContoller implements Initializable {
	@FXML private  Button NewBookBtn;
	@FXML private  Button bookapplyListBtn;
	@FXML private  Button RentalBooklistBtn;
	@FXML private  Button RecommendedBooksBtn;
	@FXML private  Button StudyRoomAdmin;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		NewBookBtn.setOnAction(event->NewBookBtnpage(event));
		bookapplyListBtn.setOnAction(event->bookapplyListpage(event));
		RentalBooklistBtn.setOnAction(event->RentalBooklistpage(event));
		RecommendedBooksBtn.setOnAction(e->RecommendedBookspage(e));
		StudyRoomAdmin.setOnAction(e->StudyRoomAdminpage(e));
		
	}
	public void StudyRoomAdminpage(ActionEvent event){
		try {
			Parent root =FXMLLoader.load(getClass().getResource("/com/potential/studyroom/view/StudyRoom.fxml"));
			Scene scene = new Scene(root);
			Stage window = new Stage();
			scene.getStylesheets().add(getClass().getResource("/com/hr/test/app2.css").toString());
			window.setScene(scene);
			window.show();
			window.setTitle("AdminStudyRoom");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void NewBookBtnpage(ActionEvent event){
		try {
			Parent root =FXMLLoader.load(getClass().getResource("/com/potential/newbookadd/view/newbookadd.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/com/hr/test/app.css").toString());
			Stage window = new Stage();
			window.setScene(scene);
			window.show();
			window.setTitle("BookAdd");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void bookapplyListpage(ActionEvent event){
		try {
			Parent root =FXMLLoader.load(getClass().getResource("/com/potential/bookapplylist/view/View.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/com/hr/test/app.css").toString());
			Stage window = new Stage();
			window.setScene(scene);
			window.show();
			window.setTitle("ApplyList");
		} catch (Exception e) {
			// TODO: handle exception
		}
	
		
	}
	public void RentalBooklistpage(ActionEvent event){
		try {
			Parent root =FXMLLoader.load(getClass().getResource("/com/potential/rental/view/RentalListPage.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/com/hr/test/app.css").toString());
			Stage window = new Stage();
			window.setScene(scene);
			window.show();
			window.setTitle("RentalList");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
		
	public void RecommendedBookspage(ActionEvent event){
		try {
			Parent root =FXMLLoader.load(getClass().getResource("/com/potential/recommend/view/view.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/com/hr/test/app.css").toString());
			Stage window = new Stage();
			window.setScene(scene);
			window.show();
			window.setTitle("RecommendAdmin");
		} catch (Exception e) {
			// TODO: handle exception
		}
			
	}
	
}
