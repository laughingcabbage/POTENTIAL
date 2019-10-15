package com.potential.menu.controll;

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


public class MenuContoller implements Initializable {
	@FXML private  Button booksearchBtn;
	@FXML private  Button bookapplyBtn;
	@FXML private  Button mypageBtn;
	@FXML private  Button urcd;
	@FXML private  Button sroom;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		booksearchBtn.setOnAction(e->booksearchpage(e));
		bookapplyBtn.setOnAction(e->bookapplypage(e));
		mypageBtn.setOnAction(e->mypagepage(e));	
		urcd.setOnAction(e->rcmdpage(e));
		sroom.setOnAction(e->roomapge(e));
		
	}
	public void booksearchpage(ActionEvent event){
		try {
			Parent root =FXMLLoader.load(getClass().getResource("/com/potential/booksearch/view/booksearchpage.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/com/hr/test/app.css").toString());
			Stage window = new Stage();
			window.setScene(scene);
			window.show();
			window.setTitle("BookSearch");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public void bookapplypage(ActionEvent event){
		try {
			Parent root =FXMLLoader.load(getClass().getResource("/com/potential/bookApply/view/View.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/com/hr/test/app.css").toString());
			Stage window = new Stage();
			window.setScene(scene);
			window.show();
			window.setTitle("BookApply");
		} catch (Exception e) {
			// TODO: handle exception
		}
	
		
	}
	
	
	public void mypagepage(ActionEvent event){
		try {
			Parent root =FXMLLoader.load(getClass().getResource("/com/potential/mypagerewrite/view/MypageMenu.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/com/hr/test/app.css").toString());
			Stage window = new Stage();
			window.setScene(scene);
			window.show();
			window.setTitle("Mypage");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void rcmdpage(ActionEvent event){
		try {
			Parent root =FXMLLoader.load(getClass().getResource("/com/potential/userrecommend/view/view.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/com/potential/userrecommend/view/application.css").toString());
			Stage window = new Stage();
			window.setScene(scene);
			window.show();
			window.setTitle("Userrecommend");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void roomapge(ActionEvent event){
		try {
			Parent root =FXMLLoader.load(getClass().getResource("/com/potential/studyroom/view/StudyRoom.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/com/hr/test/app2.css").toString());
			Stage window = new Stage();
			window.setScene(scene);
			window.show();
			window.setTitle("Studyroom");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
