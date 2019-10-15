package com.potential.login.controll;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.LoggingMXBean;

import com.hr.address.domain.StaticVO;
import com.hr.eclass.cmn.HRConst;
import com.potential.member.dao.MemberDao;
import com.potential.member.domain.MemberVO;

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


public class LoginContoller implements Initializable {
	@FXML private  TextField id;
	@FXML private  PasswordField  pw;
	@FXML private  Button login;
	@FXML private  Button memberBtn;
	@FXML private  Button loginBtn;
	@FXML private  Label logincheck;
	@FXML private Button language;
	@FXML private Label langid;
	@FXML private Label langpw;
	int count=0;
	
	private List<MemberVO>Memberlist =new ArrayList<>();	
	private final String file="//211.238.142.124/hr_data/java04/memberlist.csv";
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		memberBtn.setOnAction(e->membersAction(e));
		loginBtn.setOnAction(e->LoginAction(e));
		language.setOnAction(e->languageAction(e));
			
	}
	public void membersAction(ActionEvent event){
		try{
			Parent root =FXMLLoader.load(getClass().getResource("/com/potential/member/view/MemberPage.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/com/hr/test/app.css").toString());
			Stage window = new Stage();
			window.setScene(scene);
			window.show();
			window.setTitle("Sign");	
			}catch(Exception e){
				
				e.printStackTrace();
		}
		
	}
	public void LoginAction(ActionEvent event){
		StaticVO.UserID=id.getText();
		String Uid = StaticVO.UserID;
		String Upw=pw.getText();
		LoginContoller log=new LoginContoller();
		MemberDao MD=new MemberDao();
		Memberlist=MD.readFile(file);
		
		for(int i =0;i<Memberlist.size();i++){
			MemberVO checkVO=Memberlist.get(i);
			
			if(checkVO.getId().equals(Uid)&&checkVO.getPw().equals(Upw)&&checkVO.getId().equals("Admin") ){
				try{
					Stage stage = (Stage) loginBtn.getScene().getWindow();
		    	     stage.close();
					Parent root =FXMLLoader.load(getClass().getResource("/com/potential/adminmenu/view/AdminMenuPage.fxml"));
					Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("/com/hr/test/app.css").toString());
					Stage window = new Stage();
					window.setScene(scene);
					window.show();
					window.setTitle("Admin Menu");	
					}catch(Exception e){
						
						e.printStackTrace();
				}
				
				
			}
			else if(checkVO.getId().equals(Uid)&&checkVO.getPw().equals(Upw)){
				try{
					Stage stage = (Stage) loginBtn.getScene().getWindow();
		    	     stage.close();
					Parent root =FXMLLoader.load(getClass().getResource("/com/potential/menu/view/Menupage.fxml"));
					Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("/com/hr/test/app.css").toString());
					Stage window = new Stage();
					window.setScene(scene);
					window.show();
					window.setTitle("Main Menu");
				}catch(Exception e){
					
					e.printStackTrace();
			}
			}
			else{
				logincheck.setText("존재하지 않는 회원 정보 입니다.");
			}
			
		}
		
    	
		
	}
	public void languageAction(ActionEvent e){
		String resourceName="message";
		ResourceBundle rb;
		rb=ResourceBundle.getBundle(resourceName);
		if(count%2==1){
		
		rb= ResourceBundle.getBundle(resourceName,Locale.getDefault());
		langid.setText(rb.getString("id.text"));
		langpw.setText(rb.getString("pw.text"));
		 memberBtn.setText(rb.getString("sign.text"));
		loginBtn.setText(rb.getString("Login.text"));
		language.setText(rb.getString("language.text"));
		}
		else{
			rb = ResourceBundle.getBundle(resourceName,Locale.ENGLISH);
			langid.setText(rb.getString("id.text"));
			langpw.setText(rb.getString("pw.text"));
			 memberBtn.setText(rb.getString("sign.text"));
			loginBtn.setText(rb.getString("Login.text"));
			language.setText(rb.getString("language.text"));
		}
		count++;
		
	}
	
	

}
