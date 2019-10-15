package com.hr.test;
	
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main2 extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
	try {
			Parent root = FXMLLoader.load(getClass().getResource("/com/potential/login/view/LoginPage.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Login View");
			scene.getStylesheets().add(getClass().getResource("/com/hr/test/app.css").toString());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
