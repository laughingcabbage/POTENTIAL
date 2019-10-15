package com.potential.member.controll;

import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.hr.address.domain.StaticVO;
import com.potential.member.dao.MemberDao;
import com.potential.member.domain.MemberVO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class MemberContoller implements Initializable{
	
	
		@FXML private Button submitBtn;
	    @FXML private TextField id;
	    @FXML private PasswordField pw;
	    @FXML private PasswordField pw2;
	    @FXML private TextField name;
	    @FXML private TextField address;
	    @FXML private TextField tel;
	    @FXML private Button overlapBtn;
	    @FXML private Label over;
	    @FXML private Label pwcheck;
		private List<MemberVO>Memberlist =new ArrayList<>();	
		private final String file="//211.238.142.124/hr_data/java04/memberlist.csv";
		Alert alert = new Alert(AlertType.INFORMATION);

		
	 
	   

	    @Override
	    public void initialize(URL location, ResourceBundle resources){
	    	submitBtn.setOnAction(e->submitAction(e));
	    	overlapBtn.setOnAction(e->overlapAction(e, null));
	     }
	      

	    public void submitAction (ActionEvent event){
	    	 String Uid=id.getText();
			 String Upw=pw.getText();
			 String Upw2=pw2.getText();
			 String Uname=name.getText();
			 String Uaddress=address.getText();
			 String Utel=tel.getText();
			 MemberContoller memc=new MemberContoller();
			 MemberVO inVO=null;
			 MemberDao MD=new MemberDao();
		    Memberlist=MD.readFile(memc.file);
	    	
	    	
		    if(id.getText() == null || id.getText().trim().isEmpty()){
				alert.setContentText("ID 값을 입력해주세요.");
				alert.showAndWait();
			}else if(pw.getText() == null || pw.getText().trim().isEmpty()){
				alert.setContentText("패스워드 값을 입력해주세요.");
				alert.showAndWait();
			}else if(name.getText() == null || name.getText().trim().isEmpty()){
				alert.setContentText("이름을 입력해주세요.");
				alert.showAndWait();
			}else if (address.getText() == null || address.getText().trim().isEmpty()){
				alert.setContentText("주소 값을 입력해주세요.");
				alert.showAndWait();
			}else if (tel.getText() == null || tel.getText().trim().isEmpty()){
				alert.setContentText("연락처를 입력해주세요.");
				alert.showAndWait();
			}else if(overlapAction(event,inVO)==1){
	       		inVO=MD.getInputData(Uid,Upw,Uname,Uaddress,Utel);
		       	Memberlist.add(inVO);
		       	if(Upw.equals(Upw2)){
			       	try {
		    			FileWriter writer=new FileWriter(memc.file);
				    	MD.MemberWrite(writer,Memberlist);
					} catch (Exception e) {
						
					}
		    		Stage stage = (Stage) submitBtn.getScene().getWindow();
		    	    
		    	    stage.close();
		       	}else{
		       		pwcheck.setText("패스워드가 다릅니다.");
		       		pwcheck.setText("비밀번호가 틀립니다");
		       	}     	
	    	}
	    	else if(!Upw.equals(Upw2)){

	    		over.setText("패스워드가 다릅니다.");

	    		over.setText("아이디 중복");

	    	}
	    }
	    
	    	
	    	 
			
	    
	    public int overlapAction (ActionEvent event,MemberVO inVO) {
	    	String Uid=id.getText();
			String Upw=pw.getText();
			String Upw2=pw2.getText();
			String Uname=name.getText();
			String Uaddress=address.getText();
			String Utel=tel.getText();
	    	MemberContoller memc=new MemberContoller();
	    	MemberDao MD= new MemberDao();
	    	Memberlist=MD.readFile(memc.file);
	    	inVO=MD.getInputData(Uid,Upw,Uname,Uaddress,Utel);
	      	int count=0;
	    		for(int i =0;i<Memberlist.size();i++){
	    			MemberVO checkVO=Memberlist.get(i);
	    			if(checkVO.getId().equals(inVO.getId())){

	    				over.setText("아이디가 중복입니다.");

	    				over.setText("아이디 중복");

	    				count=-1;
	    				return count;
	    			}
	    			
	    		}
	    		over.setText("사용가능");
	    		return count=1;
	   }
	    
	    	
	    
}

	