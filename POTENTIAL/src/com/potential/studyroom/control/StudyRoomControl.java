package com.potential.studyroom.control;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.hr.address.domain.StaticVO;
import com.potential.studyroom.dao.StudyRoomDAO;
import com.potential.studyroom.domain.StudyRoomVO;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class StudyRoomControl implements Initializable {
	StudyRoomDAO dao = null;
	private String ID = "user";		//접속자아이디
	private String ADMIN_ID ="Admin"; //관리자로 인식할 아이디
	Alert alert = null;
	private int nowSeat = 0;
	
	@FXML private Label labelID;
	@FXML private Label selectNow;
	@FXML private TextField seat;
	@FXML private TextField info;
	@FXML private Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10
	,b11,b12,b13,b14,b15,b16,b17,b18,b19,b20,b21,b22,b23
	,b24,b25,b26,b27,b28,b29,b30,b31,b32,b33,b34,b35,b36
	,b37,b38,b39,b40,b41,b42,b43,b44,b45,b46,b47,b48,b49
	,b50,b51,b52,b53,b54,b55,b56, renew;
	
	@FXML private Button selectBtn;
	@FXML private AnchorPane page1;
	
	int turm = 0;
	private Button[] btns = new Button[56];
	List<Integer> usingList = new ArrayList<>();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//스테틱클래스에서 아이디 받아오기
		ID = StaticVO.UserID;
		
		//초기화
		constructor();
		
		//자동퇴실+시계
		nowtime();

	}

	/**초기화*/
	public void constructor(){
		dao = new StudyRoomDAO();
		alert = new Alert(AlertType.INFORMATION);
		labelID.setText("ID : " + ID );
		
		btns[0] = b1;		btns[1] = b2;		btns[2] = b3;		btns[3] = b4;		btns[4] = b5;		btns[5] = b6;
		btns[6] = b7;		btns[7] = b8;		btns[8] = b9;		btns[9] = b10;		btns[10] = b11;		btns[11] = b12;
		btns[12] = b13;		btns[13] = b14;		btns[14] = b15;		btns[15] = b16;		btns[16] = b17;		btns[17] = b18;
		btns[18] = b19;		btns[19] = b20;		btns[20] = b21;		btns[21] = b22;		btns[22] = b23;		btns[23] = b24;
		btns[24] = b25;		btns[25] = b26;		btns[26] = b27;		btns[27] = b28;		btns[28] = b29;		btns[29] = b30;
		btns[30] = b31;		btns[31] = b32;		btns[32] = b33;		btns[33] = b34;		btns[34] = b35;		btns[35] = b36;
		btns[36] = b37;		btns[37] = b38;		btns[38] = b39;		btns[39] = b40;		btns[40] = b41;		btns[41] = b42;
		btns[42] = b43;		btns[43] = b44;		btns[44] = b45;		btns[45] = b46;		btns[46] = b47;		btns[47] = b48;
		btns[48] = b49;		btns[49] = b50;		btns[50] = b51;		btns[51] = b52;		btns[52] = b53;		btns[53] = b54;
		btns[54] = b55;		btns[55] = b56;
		
		btnColor();
		nowSeat = dao.return_NowSeat(ID);
		usingList = dao.usingList();
	}
	
	/**좌석선택버튼*/
	public void selectBtn(){
		String seatnum = seat.getText();
		if(seatnum.isEmpty()) return;
		if( dao.is_Exist(0, ID) != -1 ) {
			if(dao.is_Exist(1, seatnum)==1 ){
				alert.setTitle("알림");
				alert.setHeaderText("선택불가");
				alert.setContentText("이미 사용중인 좌석입니다.");
				alert.showAndWait();
				return;	
			}else{
				alert.setTitle("알림");
				alert.setHeaderText("선택불가");
				alert.setContentText("이미 선택한 좌석이 있습니다.");
				alert.showAndWait();
				return;	
			}	
		}
		if(dao.is_Exist(1, seatnum)==1) return;
		dao.in_Room(ID, Integer.parseInt(seatnum) );
		nowSeat = Integer.parseInt(seatnum);
		usingList = dao.usingList();
		alert.setTitle("알림");
		alert.setHeaderText("좌석선택완료");
		alert.setContentText("사용시간은 3시간이며 연장은 한번만 가능합니다.");
		alert.showAndWait();
		btnActionRenew();
	}
	
	/**연장버튼*/
	public void extensionBtn(){
		if(nowSeat<=0) return;
		Alert alert2 = new Alert(AlertType.INFORMATION);
		alert2.setTitle("알림");
		alert2.setHeaderText("연장 [" + nowSeat + "] 번 좌석");
		alert2.setContentText("퇴실시간으로부터 3시간 연장됩니다.");
	    ButtonType buttonTypeOne = new ButtonType("연장");
        ButtonType buttonTypeCancel = new ButtonType("취소", ButtonData.CANCEL_CLOSE);

        alert2.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
        Optional<ButtonType> result = alert2.showAndWait();
        if (result.get() == buttonTypeOne){
        	if( dao.extension(ID) ){
	        	alert2.setTitle("알림");
				alert2.setHeaderText("연장 [" + nowSeat + "] 번 좌석");
				alert2.setContentText("연장되었습니다.\n퇴실시간 : " 
									  +dao.retrun_ExitTime(ID));
				ButtonType buttonTypeOK = new ButtonType("확인", ButtonData.CANCEL_CLOSE);
				alert2.getButtonTypes().setAll(buttonTypeOK);
				alert2.showAndWait();
				dao.fileToList();
				return ;
        	}else
        		alert2.setTitle("알림");
				alert2.setHeaderText("연장불가 [" + nowSeat + "] 번 좌석");
				alert2.setContentText("이미 한 번 연장하였습니다.\n퇴실시간 : " 
									  +dao.retrun_ExitTime(ID));
				ButtonType buttonTypeOK = new ButtonType("확인", ButtonData.CANCEL_CLOSE);
				alert2.getButtonTypes().setAll(buttonTypeOK);
				alert2.showAndWait();
				return ;
			
        }else return;
	}
	
	/**퇴실버튼*/
	public void exitBtn(){
		if( !ID.equals(ADMIN_ID) ){
			if( dao.is_Exist(0, ID) != -1){
				Alert alert2 = new Alert(AlertType.INFORMATION);
				alert2.setTitle("알림");
				alert2.setHeaderText("퇴실확인 [" + nowSeat + "] 번 좌석");
				alert2.setContentText("퇴실하시겠습니까?");
			    ButtonType buttonTypeOne = new ButtonType("퇴실");
	            ButtonType buttonTypeCancel = new ButtonType("취소", ButtonData.CANCEL_CLOSE);
	
	            alert2.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
	            Optional<ButtonType> result = alert2.showAndWait();
	            if (result.get() == buttonTypeOne){
	            	dao.exit_Room(ID);
	            	nowSeat = 0;
	            	alert2.setTitle("알림");
	    			alert2.setHeaderText("퇴실");
	    			alert2.setContentText("퇴실하셨습니다.");
	    			ButtonType buttonTypeOK = new ButtonType("확인", ButtonData.CANCEL_CLOSE);
	    			alert2.getButtonTypes().setAll(buttonTypeOK);
	    			alert2.showAndWait();
	    			dao.fileToList();
	    			usingList = dao.usingList();
	    			btnColor();
	    			
	            }else return;   
			}
		}else{
			if(!seat.getText().isEmpty()){
				if( dao.is_Exist(1, seat.getText()) == 1){
					Alert alert2 = new Alert(AlertType.INFORMATION);
					alert2.setTitle("ADMIN");
					alert2.setHeaderText("퇴실확인 [" + seat.getText() + "] 번 좌석");
					alert2.setContentText("퇴실시킵니까?");
				    ButtonType buttonTypeOne = new ButtonType("퇴실");
		            ButtonType buttonTypeCancel = new ButtonType("취소", ButtonData.CANCEL_CLOSE);
		            
		            alert2.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
		            Optional<ButtonType> result = alert2.showAndWait();
		            if (result.get() == buttonTypeOne){
		            	String returnID = dao.return_ID( Integer.parseInt( (seat.getText()) ) );
		            	dao.exit_Room( returnID );
		            	if(returnID.equals(ID))
		            	nowSeat = 0;
		            	alert2.setTitle("알림");
		    			alert2.setHeaderText("퇴실");
		    			alert2.setContentText("퇴실하셨습니다.");
		    			ButtonType buttonTypeOK = new ButtonType("확인", ButtonData.CANCEL_CLOSE);
		    			alert2.getButtonTypes().setAll(buttonTypeOK);
		    			alert2.showAndWait();
		    			dao.fileToList();
		    			usingList = dao.usingList();
		    			btnColor();
		    			
		            }else return;
				}else return;
			}else return;
		}
	}
	

	
	/**자동퇴실+시계*/
	public void nowtime(){
		Thread thread = new Thread(){
			public void run(){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				while(true){
					String time = sdf.format(new Date());
					Platform.runLater(() -> {
						info.setText(time);
						turm++;
						
						if(turm == 5) {
							btnColor(); 
							turm=0;
							if(usingList.size() != 0){
								boolean flag = false;
								for(int i=0 ; i<usingList.size(); i++){
									Calendar tmp = dao.return_EndCalendar(usingList.get(i)-1);
									if(tmp == null) return;
									Calendar now = sdf.getCalendar();
									
//									System.out.println("now  " +now.getTimeInMillis());								
//									System.out.println("tmp  " +tmp.getTimeInMillis());

									if(tmp.getTimeInMillis() <= now.getTimeInMillis()){
										//System.out.println("지남");
										dao.exit_Room(usingList.get(i)-1);
										flag = true;
									}else{
										//System.out.println("안지남");
									}
								}
								if(flag == true) {usingList = dao.usingList(); flag = false;}
							}
							dao.Save();
						}
					});
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {e.printStackTrace();}
				}
			}
		};
		thread.setDaemon(true);
		thread.start();	
	}
	
	/**버튼번호를 텍스트필드로*/
	public void btnAction(){
		
		for(int i=0 ; i<btns.length ; i++){
			if( btns[i].isFocused() ) seat.setText(btns[i].getText());	
		}
		
	}
	
	/**버튼색깔변경*/
	public void btnColor(){
		for(int i=0 ; i<btns.length ; i++){
			StudyRoomVO inVO = dao.return_Index(i);
			if( !inVO.getID().equals("null") ){
				if( inVO.getID().equals(ID) )
					btns[i].setStyle(" -fx-background-color: #0000ff ");
				else btns[i].setStyle(" -fx-background-color: #ff0000 ");
			}else
				btns[i].setStyle(" -fx-background-color: #add8e6 ");
		}
	}
	
	/**새로고침*/
	public void btnActionRenew(){
		dao.fileToList();
		btnColor();
	}
	
}