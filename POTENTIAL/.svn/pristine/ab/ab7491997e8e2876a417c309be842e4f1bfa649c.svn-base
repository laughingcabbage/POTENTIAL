/**
 * @Class Name : BookApplyListController.java
 * @Description : 
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2019-05-29           최초생성
 *
 * @author 개발프레임웍크 실행환경 HR.POTENTIAL 개발팀
 * @since 2019-05-29 
 * @version 1.0
 * @see
 *
 *  Copyright (C) by HR.POTENTIAL All right reserved.
 */
package com.potential.bookapplylist.control;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.potential.bookapplylist.dao.BookApplyListDao;
import com.potential.bookapplylist.domain.BookApply;
import com.potential.bookapplylist.domain.BookApplyVO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author sist
 *
 */
public class BookApplyListController implements Initializable{
 
	BookApplyListDao dao = new BookApplyListDao();
			
	/**테스트용 vo*/
	BookApplyVO vo01 = new BookApplyVO("threeeyed", "책책책책", "저자", "출판사", "1999", "비고", "승인");
	
	/**알림창*/
	Alert alert = new Alert(AlertType.INFORMATION);
	
	/**현재리스트*/
	List<BookApplyVO> nowList = new ArrayList<>();
	
	/**ObservableList*/
	@FXML private ObservableList<BookApply> observableList = FXCollections.observableArrayList();
	
	/**텍스트필드*/
	@FXML private TextField inputKeyword;
	
	/**버튼*/
	@FXML private Button search;
	@FXML private Button approve;
	@FXML private Button reject;
	
	/**콤보박스*/
	@FXML private ComboBox searchCondition;
	
	/**체크박스*/
	@FXML private CheckBox checkBox;
	
	/**테이블*/
	@FXML private TableView<BookApply> table; 
	@FXML private TableColumn<BookApply, String> checkBoxColumn;
	@FXML private TableColumn<BookApply, String> idColumn;
	@FXML private TableColumn<BookApply, String> titleColumn;
	@FXML private TableColumn<BookApply, String> authorColumn;
	@FXML private TableColumn<BookApply, String> publisherColumn;
	@FXML private TableColumn<BookApply, String> pubYearColumn;
	@FXML private TableColumn<BookApply, String> commentColumn;
	@FXML private TableColumn<BookApply, String> statusColumn;
	
	
	/**
	 * <검색> 버튼 누르면 검색 조건에 따라 검색하기
	 */
	public void searchBook(){
		//알림창 설정
		alert.setTitle("신청도서목록");
		alert.setHeaderText(null);
		
		//콤보박스에 선택된 검색조건을 문자열로 저장
		String condition = (String) searchCondition.getValue();
		
		//검색창에 입력된 키워드를 문자열로 저장
		String keyword = inputKeyword.getText();
		
		//검색조건을 선택하지 않았으면
		if(condition==null) {
			alert.setContentText("검색조건을 선택하세요.");
			alert.show();
		}else {
			//입력 받은 키워드로 검색한 결과를 리스트로 만들기 
			List<BookApplyVO> searchResult = dao.do_search(condition, keyword);
			
			//검색 결과가 없으면 알림창 띄우기
			if(searchResult.isEmpty()){
				alert.setContentText("검색결과가 없습니다.");
				alert.show();
			}else{//검색 결과가 있으면 현재 테이블에 있는 현재리스트를 지우고 검색결과리스트로 채우기
				observableList.clear();
				fillTable(searchResult);
				
				//현재 테이블에 보이는 리스트를 '현재리스트'로 저장하기
				nowList = searchResult;
			}
		}
	}
	
	/**
	 * 신청도서의 상태 변경하기
	 */
	public void changeStatus(String status){
		//알림창 설정
		alert.setTitle("신청도서목록");
		alert.setHeaderText(null);
		
		//체크박스에 체크가 된 책들을 모아 리스트로 만들기
		List<BookApplyVO> tmpList = new ArrayList<>();
				
		//observableList와 list의 요소들 비교
		for(int i=0; i<observableList.size(); i++){
		BookApply bookApply = observableList.get(i);
			for(int j=0; j<dao.getList().size(); j++) {
				BookApplyVO tmpVO = dao.getList().get(j);
				
				//observableList에서 체크박스에 체크가 되어있고 && list에서 제목과 출판사가 똑같은 책이 있으면
				if(bookApply.getCheckBox().isSelected() && bookApply.getTitle().equals(tmpVO.getTitle()) && bookApply.getPublisher().equals(tmpVO.getPublisher())){
					tmpList.add(tmpVO);	
				}  
			}
		}
		//체크박스가 체크된 도서가 없을 경우
		if(tmpList.isEmpty()){
			alert.setContentText("선택된 도서가 없습니다.");
			alert.show();
		}else{
			//알림창으로 승인, 거부 여부 재확인
			Alert confirm = new Alert(AlertType.CONFIRMATION, status+"하시겠습니까?", ButtonType.YES, ButtonType.NO);
			confirm.setTitle("신청도서목록");
			confirm.setHeaderText(null);
			confirm.showAndWait();
					
			//yes를 누르면
			if(confirm.getResult()==ButtonType.YES) {
						
				//상태를 String status로 변경
				for(int i=0; i<tmpList.size(); i++) {
					for(int j=0; j<dao.getList().size(); j++) {
						BookApplyVO tmpVO = dao.getList().get(j);
						if(tmpList.get(i).getTitle().equals(tmpVO.getTitle()) && tmpList.get(i).getPublisher().equals(tmpVO.getPublisher())) {
							tmpVO.setStatus(status);
							dao.do_save(tmpVO);
						}
					}
				}		
				//알림창
				alert.setContentText("변경되었습니다.");
				alert.show();
			}	
			//파일 저장
			dao.saveFile();
				
			//테이블 업데이트
			observableList.clear();
			fillTable(nowList);
		}
	}
	
	/**
	 * 상단의 체크박스 체크하면 모든 체크박스 체크하기
	 */
	public void checkAll() {
		boolean flag=false;
		if(checkBox.isSelected()) flag=true;
		for(int i=0; i<observableList.size(); i++){
			if(flag==true){
				observableList.get(i).getCheckBox().setSelected(true);
			}else{
				observableList.get(i).getCheckBox().setSelected(false);
			}
		}
    }

	/**
	 * 테이블에 데이터 채우기
	 * @param list
	 */
	public void fillTable(List<BookApplyVO> list){
		for(int i=0; i<list.size(); i++){
			BookApplyVO tmpVO = list.get(i);
			observableList.add(new BookApply(new CheckBox()
							,tmpVO.getId()
							,tmpVO.getTitle()
							,tmpVO.getAuthor()
							,tmpVO.getPublisher()
							,tmpVO.getPubYear()
							,tmpVO.getComment()
							,tmpVO.getStatus()));
		}
			table.setItems(observableList);
	}
	
	public void initialize(URL location, ResourceBundle resources){
		//table설정
		checkBoxColumn.setCellValueFactory(new PropertyValueFactory<BookApply, String>("checkBox"));
		idColumn.setCellValueFactory(new PropertyValueFactory<BookApply, String>("id"));
		titleColumn.setCellValueFactory(new PropertyValueFactory<BookApply, String>("title"));
		authorColumn.setCellValueFactory(new PropertyValueFactory<BookApply, String>("author"));
		publisherColumn.setCellValueFactory(new PropertyValueFactory<BookApply, String>("publisher"));
		pubYearColumn.setCellValueFactory(new PropertyValueFactory<BookApply, String>("pubYear"));
		commentColumn.setCellValueFactory(new PropertyValueFactory<BookApply, String>("comment"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<BookApply, String>("status"));
		nowList=dao.getList();
		fillTable(nowList);
		
		//검색창 엔터입력
    	inputKeyword.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent key) {
				if(key.getCode().equals(KeyCode.ENTER))
					searchBook();
			}});
    	
    	//검색버튼 누르면 검색하기
    	search.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.MOUSE_CLICKED !=null){
					searchBook();
				}
			}
		});
    	
    	//도서구입 승인하기
    	approve.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.MOUSE_CLICKED != null){
					changeStatus("승인");
				}
			}
		});
    	
    	//도서구입 거부하기
    	reject.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.MOUSE_CLICKED != null){
					changeStatus("거부");
				}
			}
		});
    	
    	//checkbox 클릭하면 모두 선택/해제
    	checkBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.MOUSE_CLICKED!=null){
					checkAll();
				}
			}
		});
	}
	
}