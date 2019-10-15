package com.potential.rental.control;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.potential.rental.dao.RentalDAO;
import com.potential.rental.domain.RentVO;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * 관리자 전용 대출현황 컨트롤러 클래스
 * */
public class RentListControl implements Initializable {
	private RentalDAO dao = new RentalDAO();
	
	//접근가능한 관리자아이디
	private String ADMIN = "관리자";
	
	//상태알림 텍스트필드
	@FXML	private TextField rent_alert;
	
	//콤보박스
	@FXML	private ComboBox<String> rent_comboBox1;	//검색용
	ObservableList<String> combolist1 = FXCollections.observableArrayList("ID", "책이름", "저자", "도서관");
	@FXML	private ComboBox<String> rent_comboBox2;	//카테고리용
	ObservableList<String> combolist2 = add_Category();
	
	//버튼
	@FXML	private Button rent_AllList;
	@FXML	private Button rent_OverList;
	/**대여전체리스트*/
	private List<RentVO> list1;
	/**연체중리스트*/
	private List<RentVO> list2;
	private List<RentVO> nowList = new ArrayList<>();	//현재리스트
	
	//TableView 부속
	@FXML	private TableView<TableRowData> rent_tableView;
	@FXML	private TableColumn<TableRowData, Boolean> rent_tableCheck;
	@FXML	private TableColumn<TableRowData, String> rent_tableColumn_ID;
	@FXML	private TableColumn<TableRowData, String> rent_tableColumn_name;
	@FXML	private TableColumn<TableRowData, String> rent_tableColumn_author;
	@FXML	private TableColumn<TableRowData, String> rent_tableColumn_category;
	@FXML	private TableColumn<TableRowData, String> rent_tableColumn_date;
	@FXML	private TableColumn<TableRowData, Integer> rent_tableColumn_possible;
	@FXML	private TableColumn<TableRowData, Boolean> rent_tableColumn_over;
 
    //검색버튼
    @FXML	private Button rent_searchButton;
    //검색바
    @FXML	private TextField rent_textInput;
    //새로고침
    @FXML	private Button rent_renew;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	//스테딕클래스에서 관리자가 맞는지 확인
//    	if(!StaticClass.getID().equals(ADMIN)){
//    		Alert alert = new Alert(AlertType.CONFIRMATION);
//    		alert.setTitle("INFOMATION");
//    		alert.setHeaderText("Administrator Only");
//    		alert.setContentText("접속한 대상이 관리자가 아닙니다.");
//    		ButtonType OK = new ButtonType("OK", ButtonData.CANCEL_CLOSE);
//            alert.getButtonTypes().setAll(OK);
//    		alert.showAndWait();
//    		Platform.exit();
//    	}
    	
    	rent_alert.setText("<System> 관리자 전용 대출현황 페이지");
    	//테이블리스트초기화
    	TableConstructor();
    	
    	//콤보박스리스트추가
    	rent_comboBox1.setItems(combolist1);
    	rent_comboBox2.setItems(combolist2);
           
    	//버튼: 전체
    	rent_AllList.setOnAction(event -> Button_All(event));
    	//버튼: 연체
    	rent_OverList.setOnAction(event -> Button_Over(event));
    	//버튼: 새로고침
    	rent_renew.setOnAction(event -> renew(event));
    	//카테고리 정렬
    	rent_comboBox2.setOnAction(event -> go_Category(event));
    	
    	//검색창 엔터입력
    	rent_textInput.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent key) {
				if(key.getCode().equals(KeyCode.ENTER))
					buttonEvent();
			}});
    	
    	//검색버튼
    	rent_searchButton.setOnMouseClicked(event -> {buttonEvent();});
    	
    	//마우스클릭으로 정보수정
    	mouseSelect();
    	
    	
    }//--initialize end
    
    /**테이블리스트초기화*/
    void TableConstructor(){
    	//checkBox();
    	rent_tableColumn_ID.setCellValueFactory(cellData-> cellData.getValue().IDProperty());
    	rent_tableColumn_name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    	rent_tableColumn_author.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
    	rent_tableColumn_category.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
    	rent_tableColumn_date.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
    	rent_tableColumn_possible.setCellValueFactory(cellData -> cellData.getValue().possibleProperty().asObject());
    	rent_tableColumn_over.setCellValueFactory(cellData -> cellData.getValue().overProperty().asObject());
        ObservableList<TableRowData> tableList = FXCollections.observableArrayList();//rent_tableView초기화, 데이터추가는 rent_tableView.add로
    	rent_tableView.setItems(tableList);
    
        list1 = dao.return_All_List();
        list2 = dao.return_Overdue_BookList();
        addTableList(list1);
        nowList = list1;
    }
    
    /**콤보박스에 카테고리 넣는 메소드*/
    ObservableList<String> add_Category() {
    	List<String> tmp = dao.get_All_Category();
        ObservableList<String> list = FXCollections.observableArrayList();
        for(int i=0 ; i<tmp.size() ; i++){
        	list.add(tmp.get(i));
        }
        return list;
    }
    
    /**테이블뷰에 나열*/
    void addTableList(List<RentVO> list) {
    	for(int i=0 ; i<list.size() ; i++){
    		rent_tableView.getItems().add(new TableRowData(
    				new SimpleStringProperty(list.get(i).getID())
    				, new SimpleStringProperty(list.get(i).getBookName())
    				, new SimpleStringProperty(list.get(i).getAuthor())
    				, new SimpleStringProperty(list.get(i).getCategory())
    				, new SimpleStringProperty(list.get(i).getRentalDate())
    				, new SimpleIntegerProperty(list.get(i).getPossible())
    				, new SimpleBooleanProperty( isOver(i, list) )));
    	}
    }
    
    /**테이블뷰 모두삭제*/
    void clearTableList(List<RentVO> list) {
    	if(rent_tableView.getItems().size() != 0){
	    	for(int i=list.size()-1 ; i>=0 ; i--){
	    		rent_tableView.getItems().remove(i);
	    	}
    	}
    }

    /**버튼 : 전체*/
    void Button_All(ActionEvent event) {
    	clearTableList(nowList);
    	addTableList(list1);
    	nowList = list1;
    	rent_alert.setText("<System> 대여상태인 모든 목록");
    }

    /**버튼 : 연체*/
    void Button_Over(ActionEvent event) {
    	clearTableList(nowList);
    	addTableList(list2);
    	nowList = list2;
    	rent_alert.setText("<System> 연체중인 모든 목록");
    }
    
    /**버튼 : 새로고침*/
    void renew(ActionEvent event) {
    	dao.FileData_DownLoad();
    	clearTableList(nowList);
    	TableConstructor();
    	rent_alert.setText("<System> 대여목록을 저장소에서 다시 받아왔습니다.");
    }
 
    /**검색버튼*/
    void buttonEvent() {
    		String combo ="";
    		//검색어 수집
    		String searchText = rent_textInput.getText().trim();
    		//콤보박스 선택없을경우
    		try{
    			combo = rent_comboBox1.getValue().toString();
    		}catch(NullPointerException ne){
    			rent_alert.setText("<System> 검색할 항목을 선택해야 합니다. ");
    		}
    		//테이블클리어
    		clearTableList(nowList);
    		//내용정리
    		List<RentVO> nowListTmp = new ArrayList<>();
    		switch(combo){
    			case "ID":
    				nowListTmp = dao.return_Select_all_List(0, searchText,list1);
    				if(nowListTmp == null) {
    					rent_alert.setText("<System> 존재하지않는 ID");
    					return;
    				}
    				nowList = nowListTmp;
    				rent_alert.setText("<System> ID : \'" + searchText + "\' (으)로 검색한 결과");
    				break;
    			case "책이름":
    				nowListTmp = dao.return_Select_all_List(1, searchText, list1);
    				if(nowListTmp == null) {
    					rent_alert.setText("<System> 존재하지않는 책 제목");
    					return;
    				}
    				nowList = nowListTmp;
    				rent_alert.setText("<System> 책 제목 : \'" + searchText + "\' (으)로 검색한 결과");
    				break;   
    			case "저자":
    				nowListTmp = dao.return_Select_all_List(2, searchText, list1);
    				if(nowListTmp == null) {
    					rent_alert.setText("<System> 존재하지않는 저자");
    					return;
    				}
    				nowList = nowListTmp;
    				rent_alert.setText("<System> 저자 : \'" + searchText + "\' (으)로 검색한 결과");
    				break;   
  				
    		}
    		//테이블에 표시
			addTableList(nowList);     		
    }
    
    /**연체유무를 리턴*/
    boolean isOver(int i, List<RentVO> list){
    	for(int q=0 ; q<list2.size() ; q++){	//연체리스트 list2에 들어가있으면 연체
    		if(list2.get(q).getID().equals(list.get(i).getID())){
    			
    			return true;
    		}
    	}
    	return false;
    }
    
    /**카테고리별 정렬*/
    void go_Category(ActionEvent event) {
    	String cetegory = rent_comboBox2.getSelectionModel().getSelectedItem();
		List<RentVO> tmp = new ArrayList<>();
		tmp = dao.return_All_List_Bycategory(cetegory);
		if(tmp == null) return;
		clearTableList(nowList);
    	addTableList(tmp);
    	nowList = tmp;
    	rent_alert.setText("<System> 카테고리 \'" + cetegory + "\' 의 모든 목록");
    }
    
    /**마우스 클릭하여 정보수정*/
    void mouseSelect(){
    	rent_tableView.setRowFactory(go -> {
            TableRow<TableRowData> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                	TableRowData rowData = row.getItem();
                	//System.out.println("Double click on: "+rowData);
                    
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Administrator Only");
                    alert.setHeaderText("대출목록에서 제거하거나 반납을 연장할 수 있습니다.");
                    alert.setContentText("ID : " + rowData.IDProperty().getValue()
                    					+ "\n제목 : " + rowData.nameProperty().getValue()
                    					+ "\n저자 : " + rowData.authorProperty().getValue()
                    					+ "\n대여일 : " + rowData.dateProperty().getValue()
                    					+ "\n연체여부 : " + rowData.overProperty().getValue()
                    					+ "\n연장가능여부(0:가능/1:불가) : " + rowData.possibleProperty().getValue()
                    					);

                    ButtonType buttonTypeOne = new ButtonType("목록에서제거");
                    ButtonType buttonTypeTwo = new ButtonType("연장");
                    ButtonType buttonTypeCancel = new ButtonType("취소", ButtonData.CANCEL_CLOSE);

                    alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);
                    
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == buttonTypeOne){
                        //목록에서 제거
                    	Alert alert2 = new Alert(AlertType.CONFIRMATION);
                		alert2.setTitle("확인");
                		alert2.setHeaderText("대상을 목록에서 제거합니다.");
                		alert2.setContentText("제거합니까?");
                		Optional<ButtonType> result2 = alert2.showAndWait();
                		if (result2.get() != ButtonType.OK){
                		   return;
                		} 
                    	dao.deleteToList( rowData.nameProperty().getValue(), rowData.authorProperty().getValue());
                    	dao.saveToFile();
                    	clearTableList(nowList);
                    	TableConstructor();
                    	rent_alert.setText("<System> 대상 \'" + rowData.nameProperty().getValue() + "\'을 제거하고 목록을 다시 불러왔습니다.");
                    } else if (result.get() == buttonTypeTwo) {
                    	//대출연장
                    	if( dao.return_VO( rowData.nameProperty().getValue(), rowData.authorProperty().getValue()).getPossible() == 1){
                    		//연장이 불가한 경우
                    		Alert alert2 = new Alert(AlertType.CONFIRMATION);
                    		alert2.setTitle("확인");
                    		alert2.setHeaderText("연장이 불가한 책 입니다.");
                    		alert2.setContentText("무시하고 연장하시겠습니까?");
                    		Optional<ButtonType> result2 = alert2.showAndWait();
                    		if (result2.get() != ButtonType.OK){
                    		   return;
                    		}
                    	}
                        dao.lending_Extension(1, rowData.IDProperty().getValue(), rowData.nameProperty().getValue(), rowData.authorProperty().getValue());
                        dao.saveToFile();
                        clearTableList(nowList);
                    	TableConstructor();
                    	rent_alert.setText("<System> \'" + rowData.nameProperty().getValue() + "\' 의 대출기한을 연장했습니다.");
                    }
                }
            });
            return row ;
        });
    }

}