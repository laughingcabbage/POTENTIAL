package com.potential.bookrequest.view;

import org.apache.log4j.BasicConfigurator;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * @author sist
 *
 */
public class Main extends Application {

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		BasicConfigurator.configure();
		Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
		
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("도서신청");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * @Method Name  : main
	 * @작성일   : 2019. 5. 22.
	 * @작성자   : sist
	 * @변경이력  : 최초작성
	 * @Method 설명 :
	 * @param args
	 */

	public static void main(String[] args) {
		launch(args);
	}

}
