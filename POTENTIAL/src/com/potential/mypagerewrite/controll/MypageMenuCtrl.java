package com.potential.mypagerewrite.controll;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.hr.address.domain.StaticVO;
import com.potential.member.domain.MemberVO;
import com.potential.mypagerewrite.dao.MypageRewriteDao;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class MypageMenuCtrl implements Initializable {
	@FXML PasswordField mypagePw;
	@FXML PasswordField mypagePwcheck;
	@FXML TextField mypageName;
	@FXML TextField mypageAddress;
	@FXML TextField mypageTel;
	@FXML Button rewrt;
	@FXML Label message;
	@FXML Button myrentBtn;
	
	Alert alert = new Alert(AlertType.INFORMATION);
	private List<MemberVO> mbList = new ArrayList<>();
	private final String rewrite = "//211.238.142.124/hr_data/java04/memberlist.csv";
	@Override
	public void initialize(URL var1, ResourceBundle var2) {
		
		rewrt.setOnAction(e->handlerREAction(e));
		myrentBtn.setOnAction(e->handlerRentAction(e));
	}
	
	public void handlerREAction(ActionEvent e){
		
		String userId = StaticVO.UserID;
		String userPw = mypagePw.getText();
		String userPwcherck = mypagePwcheck.getText();
		String userName = mypageName.getText();
		String userAddress = mypageAddress.getText();
		String userTel = mypageTel.getText();
		
		
		MypageRewriteDao daoo= new MypageRewriteDao();
		MemberVO member = new MemberVO(userId,userPw,userName,userAddress,userTel);
		
		
		if(StaticVO.UserID == null || StaticVO.UserID.trim().isEmpty()){
			alert.setContentText("ID 값이 없습니다.");
			alert.showAndWait();
		}else if(mypageName.getText() == null || mypageName.getText().trim().isEmpty()){
			alert.setContentText("이름을 입력해주세요");
			alert.showAndWait();
		}else if(mypageAddress.getText() == null || mypageAddress.getText().trim().isEmpty()){
			alert.setContentText("주소를 입력해주세요.");
			alert.showAndWait();
		}else if(mypageTel.getText() == null || mypageTel.getText().trim().isEmpty()){
			alert.setContentText("연락처를 입력해주세요.");
			alert.showAndWait();
		}else if (mypagePw.getText() == null || mypagePw.getText().trim().isEmpty()){
			alert.setContentText("PW를 입력해주세요.");
			alert.showAndWait();
		}else if(userPw.equals(userPwcherck) ){
		
				mbList = daoo.readFile(rewrite);	
			
		
				for(int i=this.mbList.size()-1;i>=0;i--){
					MemberVO orgVO = mbList.get(i);
					if(orgVO.getId().equals(member.getId())){
						mbList.remove(i);
						break;				
					}
				}
				mbList.add(member);
				daoo.saveFile(mbList);
				
				alert.setContentText("회원정보가 수정 되었습니다.");
				alert.showAndWait();
				Stage stage = (Stage) rewrt.getScene().getWindow();
	    	     stage.close();		
		
		 }else if(!userPw.equals(userPwcherck)){
			 alert.setContentText("PW와 PW*이 다릅니다.");
			alert.showAndWait();
		 }else{
			 message.setText("오류!");
		 }

  }
	public void handlerRentAction(ActionEvent event){
		try{
			Parent root =FXMLLoader.load(getClass().getResource("/com/potential/rental/view/Mypage_Rental.fxml"));
			Scene scene = new Scene(root);
			
			Stage window = new Stage();
			window.setScene(scene);
			window.show();
			window.setTitle("RentalList");	
			}catch(Exception e){
				
				e.printStackTrace();
		}
	}
	
	
}
