package com.potential.rental.control;

import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.hr.address.domain.StaticVO;
import com.potential.rental.dao.RentalDAO;
import com.potential.rental.domain.RentVO;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;

/**
 * User전용 마이페이지 대여목록 컨트롤러 클래스
 * */
public class MypageRentalControl implements Initializable{
	private RentalDAO dao = new RentalDAO();
	private String USER = "test";
	final int LENDING_DAY = 14;
	private String expiration = "";
	Number nullSelect;	//대여일 연장 관련
	
	@FXML private ListView<String> userrent_listView;
	@FXML private ImageView userrent_imageView;
	@FXML private TextField userrent_IDview;
	@FXML private TextField userrent_bookname;
	@FXML private TextField userrent_authorView;
	@FXML private TextField userrent_category;
	@FXML private TextField userrent_where;
	@FXML private TextField userrent_borrowedDate;
	@FXML private TextField userrent_expire;
	@FXML private Button userrent_extendBtn;
	
	Alert alert = new Alert(AlertType.INFORMATION);

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//스테딕 클래스에서 유저아이디 받아오기
		//USER = StaticClass.getID();
		USER = StaticVO.UserID;
		//초기화
		call_User();
		
		//연장버튼
		extention_Borrow();
		
	}//--initialize end
	
	/**초기화*/
	public void call_User(){
		//유저확인
		int i = dao.is_exist(2, USER);
		if(i == -1) {
			alert.setTitle("INFOMATION");
    		alert.setHeaderText("대출중인 책이 없습니다");
    		ButtonType buttonTypeOne = new ButtonType("OK");
    		alert.getButtonTypes().setAll(buttonTypeOne);
    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == buttonTypeOne){
    			
    		} 
		}
		userrent_IDview.setText("ID: "+ USER);
		Call_UserIndex();
	}
	
	/**유저대출목록*/
	public String Call_UserIndex(){
		//유저대출목록호출
		List<RentVO> list = dao.return_Select_all_List(0, USER, dao.return_All_List());
		String[] s = new String[list.size()];
		for(int q=0 ; q<list.size() ; q++){s[q] = list.get(q).getBookName();}
		userrent_listView.setItems(FXCollections.observableArrayList(s));
		
		//리스트뷰에서 아이템 클릭 시 이벤트처리
		userrent_listView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				int key = (int)newValue;
				nullSelect = oldValue;
				if((int)newValue == -1) return;
				//userrent_imageView.setImage(new Image("@"));
				userrent_bookname.setText(list.get(key).getBookName());
				userrent_authorView.setText(list.get(key).getAuthor());
				userrent_category.setText(list.get(key).getCategory());
				userrent_borrowedDate.setText(list.get(key).getRentalDate());
				Calendar c = dao.return_LendingDay(list.get(key).getBookName(), list.get(key).getAuthor());
				c.add(Calendar.DAY_OF_MONTH, LENDING_DAY);
				expiration = c.get(Calendar.YEAR)-2000 + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.DAY_OF_MONTH);
				userrent_expire.setText(expiration);
			}
		});
		return expiration;
	}
	
	/**대출연장*/
	public void extention_Borrow() {
		userrent_extendBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String nowBook = userrent_bookname.getText(); //현재 프로세스 진행중인 책 
				if(nowBook == "" || userrent_authorView.getText() == "") return;
				if(dao.lending_Extension(0, USER,nowBook, userrent_authorView.getText()) == true){
					dao.saveToFile();
					Call_UserIndex();
					alert.setTitle("대출연장성공");
					alert.setHeaderText(nowBook);
					alert.setContentText("대출기한을 연장했습니다.\n새로운 반납일은 " + expiration + " 입니다");
					alert.showAndWait();        		
				}else{
					if(nullSelect != null){	//리스트에서 선택을 아예 안했으면 무반응
						alert.setTitle("대출연장실패");
						alert.setHeaderText(nowBook);
						alert.setContentText("더 이상 대출기한을 연장할 수 없습니다.");
						alert.showAndWait();
					}else return;
				}
			}
		});
	}
}