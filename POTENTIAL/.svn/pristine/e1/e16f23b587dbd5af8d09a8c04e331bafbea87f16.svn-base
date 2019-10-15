package com.potential.rental.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppMain_AdminRental extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent p = FXMLLoader.load(getClass().getResource("/com/potential/rental/view/RentalListPage.fxml"));
		Scene scene = new Scene(p);
		primaryStage.setTitle("대여목록 - administrator");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);

	}

}