package com.potential.recommend.control;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.potential.recommend.dao.RecommendDao;
import com.potential.recommend.domain.RecommendVO;
import com.potential.recommend.domain.RecommendedBook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * @author sist
 *
 */
public class Control implements Initializable {

	/**DAO*/
	RecommendDao dao = new RecommendDao();
	
	/**VO*/
	private RecommendedBook recommend = new RecommendedBook();
	
	/**날짜정보*/
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
	
	/**파일저장번호*/
	private String index = sdf.format(new Date());
	
	/**버튼*/
	@FXML private Button image;
	@FXML private Button save;
	@FXML private Button delete;
	@FXML private Button reset;
	
	/**알림창*/
	Alert alert = new Alert(AlertType.INFORMATION);
	
	/**파일 다이얼로그 창*/
	@FXML private AnchorPane anchorPane;
	
	/**텍스트필드*/
	@FXML private TextField inputDate;
	@FXML private TextField inputTitle;
	@FXML private TextField inputAuthor;
	@FXML private TextField inputPublisher;
	@FXML private TextArea inputReview;
	
	/**테이블*/
	@FXML private TableView<RecommendedBook> table;
	@FXML private TableColumn<RecommendedBook, String> checkBoxColumn;
	@FXML private TableColumn<RecommendedBook, Integer> indexColumn;
	@FXML private TableColumn<RecommendedBook, String> titleColumn;
	@FXML private TableColumn<RecommendedBook, String> authorColumn;
	@FXML private TableColumn<RecommendedBook, String> publisherColumn;
	
	/**체크박스*/
	@FXML private CheckBox checkBox;
	
	/**이미지뷰*/
	@FXML private ImageView imageView;
	
	/**observableList*/
	private ObservableList<RecommendedBook> observableList = FXCollections.observableArrayList();
	
	/**파일 복사*/
	private Path to;
	private Path from;
	private File selectedFile;
	
	/**
	 * 입력 받은 정보 파일에 추가하기
	 */
	public void add(){
		//알림창 설정
		alert.setTitle("이달의 추천도서");
		alert.setHeaderText(null);
		
		String imagePath = "//211.238.142.124//hr_data//java04//images//"+inputDate.getText()+".jpg";
		File imageFile = new File(imagePath);
				
		//텍스트필드 비어 있으면 알림창 띄우기 
		if (inputDate.getText() == null || inputDate.getText().trim().isEmpty()) {
			alert.setContentText("년월을 입력해 주세요.(예:201905)");
		}else if (inputTitle.getText() == null || inputTitle.getText().trim().isEmpty()) {
			alert.setContentText("제목을 입력해 주세요.");
		}else if(inputAuthor.getText() == null || inputAuthor.getText().trim().isEmpty()){
			alert.setContentText("저자를 입력해 주세요.");
		}else if(inputPublisher.getText() == null || inputPublisher.getText().trim().isEmpty()){
			alert.setContentText("출판사를 입력해 주세요.");
		}else if(inputPublisher.getText() == null || inputReview.getText().trim().isEmpty()){
			alert.setContentText("소개를 입력해 주세요.");
		}else if(!imageFile.exists()) {
			alert.setContentText("표지 이미지를 등록해 주세요.");
		}else{
			//각 필드에 입력된 값을 모아 vo로 저장
			String review = inputReview.getText().replaceAll("\n", "\t");
			RecommendVO vo = new RecommendVO(Integer.parseInt(inputDate.getText()),
											 inputTitle.getText(),
											 inputAuthor.getText(),
											 inputPublisher.getText(), 
											 review);
			
			//중복확인하기 
			boolean flag = dao.BookExists(vo);
			
			//중복이 아니면 저장하고 알림창 띄우기
			if(flag==false){
				dao.do_save(vo);
				alert.setContentText("저장되었습니다.");
			}else if(flag==true){//중복이면 덮어쓰기 할 것인지 확인
				Alert confirm = new Alert(AlertType.CONFIRMATION, "이미 해당 달의 추천도서가 존재합니다. 덮어쓰기 하시겠습니까?", ButtonType.YES, ButtonType.NO);
				confirm.setTitle("이달의 추천도서");
				confirm.setHeaderText(null);
				confirm.showAndWait();
				
				//yes를 누르면
				if(confirm.getResult()==ButtonType.YES) {
					//기존 추천도서를 삭제하고 새로운 추천도서를 저장
					for(int i=0; i<dao.getList().size(); i++){
						RecommendVO tmpVO = dao.getList().get(i);
						if(tmpVO.getIndex()==vo.getIndex()){
							dao.getList().remove(i);
							dao.do_save(vo);
							alert.setContentText("저장되었습니다.");
						}
					}
				}else {
					alert.setContentText("취소되었습니다.");
				}
			}
			//테이블 업데이트
			observableList.clear();
			fillTable(dao.getList());
			
			//각 텍스트필드 비우기
			inputDate.setText(index);
			inputTitle.setText("");
			inputAuthor.setText("");
			inputPublisher.setText("");
			inputReview.setText("");
		}
		alert.show();
			
		//파일 저장
		dao.saveFile();
			
	}
	
	/**
	 * 이미지 저장받기
	 */
	public void saveImage() {
		//알림창 설정
		alert.setTitle("이달의 추천도서");
		alert.setHeaderText(null);
		
		//파일 탐색기 설정
		Stage stage = (Stage) anchorPane.getScene().getWindow();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Image");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.jpg"));
		selectedFile = fileChooser.showOpenDialog(stage);
		
		if(selectedFile!=null){//선택된 파일이 있으면
			from=Paths.get(selectedFile.toURI());
			to=Paths.get("//211.238.142.124//hr_data//java04//images//"+inputDate.getText()+".jpg");
			File newFile = new File(to.toString());
			
			if(newFile.exists()){//중복이면 덮어쓸 것인지 확인
				Alert confirm = new Alert(AlertType.CONFIRMATION, inputDate.getText()+"월의 표지가 이미 존재합니다. 덮어쓰시겠습니까?", ButtonType.YES, ButtonType.NO);
				confirm.setTitle("이달의 추천도서");
				confirm.setHeaderText(null);
				confirm.showAndWait();
						
				//yes를 누르면
				if(confirm.getResult()==ButtonType.YES) {
					newFile.delete();//기존 파일 삭제
					try {
						//이미지를 프로젝트 이미지소스 폴더로 복사
						Path file = Files.copy(from, to);
						alert.setContentText("표지 이미지가 등록되었습니다.");
						alert.show();
						
						//imageView 업데이트
						BufferedImage bi = ImageIO.read(newFile);
						Image image = SwingFXUtils.toFXImage(bi, null);
						imageView.setImage(image);
						
					} catch (IOException e) {
						System.out.println("IOException:"+e.getMessage());
					}
					
				}else {//no를 누르면 알림창 띄우기
					alert.setContentText("취소되었습니다.");
					alert.show();
				}
			}else {//중복이 아니면 저장
				try {
					Path file = Files.copy(from, to);
					alert.setContentText("표지 이미지가 등록되었습니다.");
					alert.show();
					
					//imageView 업데이트
					BufferedImage bi = ImageIO.read(newFile);
					Image image = SwingFXUtils.toFXImage(bi, null);
					imageView.setImage(image);
				} catch (IOException e) {
					System.out.println("IOException:"+e.getMessage());
				}
			}
		}
	}
	
	/**
	 * 테이블에 데이터 채우기
	 * @param list
	 */
	public void fillTable(List<RecommendVO> list){
		for(int i=list.size()-1; i>=0; i--){
			RecommendVO tmpVO = list.get(i);
			observableList.add(new RecommendedBook(new CheckBox()
											,tmpVO.getIndex()
											,tmpVO.getTitle()
											,tmpVO.getAuthor()
											,tmpVO.getPublisher()
											,tmpVO.getReview())); 
			Comparator<RecommendedBook> comparator = Comparator.comparing(RecommendedBook::getIndex);
			comparator = comparator.reversed(); 
			FXCollections.sort(observableList, comparator); 
		}
		table.setItems(observableList);
	}
	
	/**
	 * 테이블 더블클릭하면 리뷰 보여주기
	 */
	public void showReview() {
		//텍스트필드에 리뷰 보여주기
		RecommendedBook recommend = table.getSelectionModel().getSelectedItem();
		String review = recommend.getReview().replaceAll("\t", "\n");
		inputDate.setText(String.valueOf(recommend.getIndex()));
		inputTitle.setText(recommend.getTitle());
		inputAuthor.setText(recommend.getAuthor());
		inputPublisher.setText(recommend.getPublisher());
		inputReview.setText(review);
		
		//imageView 업데이트
		File coverImage = new File("//211.238.142.124//hr_data//java04//images//"+recommend.getIndex()+".jpg");
		try {
			BufferedImage bi = ImageIO.read(coverImage);
			Image image = SwingFXUtils.toFXImage(bi, null);
			imageView.setImage(image);
		} catch (IOException e) {//해당 인덱스를 제목으로 하는 파일이 없을 경우 "표지 이미지가 없습니다" 파일 보여주기
			File noImage = new File("//211.238.142.124//hr_data//java04//images//noImage.jpg");
			try {
				BufferedImage bi = ImageIO.read(noImage);
				Image image = SwingFXUtils.toFXImage(bi, null);
				imageView.setImage(image);
				System.out.println("IOException:"+e.getMessage());
			} catch (IOException e1) {
				System.out.println("IOException:"+e1.getMessage());
			}
			
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
	 * 추천도서 삭제하기
	 */
	public void delete(){
		//체크박스에 체크가 된 책들을 모아 리스트로 만들기
		List<RecommendVO> tmpList = new ArrayList<>();
				
		//observableList와 list의 요소들 비교
		for(int i=0; i<observableList.size(); i++){
		RecommendedBook recommend = observableList.get(i);
			for(int j=0; j<dao.getList().size(); j++) {
				RecommendVO tmpVO = dao.getList().get(j);
				
				//observableList에서 체크박스에 체크가 되어있고 && list에서 index가 똑같으면
				if(recommend.getCheckBox().isSelected() && recommend.getIndex()==tmpVO.getIndex()){
					tmpList.add(tmpVO);	
				}  
			}
		}
		//체크박스가 체크된 도서가 없을 경우
		if(tmpList.isEmpty()){
			alert.setTitle("이달의 추천도서");
			alert.setHeaderText(null);
			alert.setContentText("선택된 도서가 없습니다.");
			alert.show();
		}else{
			//알림창으로 삭제 여부 재확인
			Alert confirm = new Alert(AlertType.CONFIRMATION, "삭제하시겠습니까?", ButtonType.YES, ButtonType.NO);
			confirm.setTitle("이달의 추천도서");
			confirm.setHeaderText(null);
			confirm.showAndWait();
					
			//yes를 누르면
			if(confirm.getResult()==ButtonType.YES) {
						
				//선택된 항목을 리스트에서 삭제
				for(int i=0; i<tmpList.size(); i++) {
					for(int j=0; j<dao.getList().size(); j++) {
						RecommendVO tmpVO = dao.getList().get(j);
						File coverImage = new File("//211.238.142.124//hr_data//java04//images//"+tmpVO.getIndex()+".jpg");
						if(tmpList.get(i).getIndex()==tmpVO.getIndex()) {
							dao.getList().remove(j);
							coverImage.delete();
						}
					}
				}		
				//알림창
				alert.setTitle("신청도서목록");
				alert.setHeaderText(null);
				alert.setContentText("삭제되었습니다.");
				alert.show();
			}	
			//파일 저장
			dao.saveFile();
				
			//테이블 업데이트
			observableList.clear();
			fillTable(dao.getList());
		}
	}
	
	/**
	 * 새로쓰기 버튼 누르면 모든 텍스트필드 초기화
	 */
	public void reset() {
		Alert confirm = new Alert(AlertType.CONFIRMATION, "글쓰기 창을 초기화합니다. 계속하시겠습니까?", ButtonType.YES, ButtonType.NO);
		confirm.setTitle("이달의 추천도서");
		confirm.setHeaderText(null);
		confirm.showAndWait();
			
		//yes를 누르면
		if(confirm.getResult()==ButtonType.YES) {
			//각 텍스트필드 비우기
			inputDate.setText(index);
			inputTitle.setText("");
			inputAuthor.setText("");
			inputPublisher.setText("");
			inputReview.setText("");
			
			//이미지뷰 비우기
			File noImage = new File("//211.238.142.124//hr_data//java04//images//noImage.jpg");
			try {
				BufferedImage bi = ImageIO.read(noImage);
				Image image = SwingFXUtils.toFXImage(bi, null);
				imageView.setImage(image);
			} catch (IOException e) {
				System.out.println("IOException:"+e.getMessage());
			}
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Table설정
		checkBoxColumn.setCellValueFactory(new PropertyValueFactory<RecommendedBook, String>("checkBox"));
		indexColumn.setCellValueFactory(new PropertyValueFactory<RecommendedBook, Integer>("index"));
		titleColumn.setCellValueFactory(new PropertyValueFactory<RecommendedBook, String>("title"));
		authorColumn.setCellValueFactory(new PropertyValueFactory<RecommendedBook, String>("author"));
		publisherColumn.setCellValueFactory(new PropertyValueFactory<RecommendedBook, String>("publisher"));
		
		File noImage = new File("//211.238.142.124//hr_data//java04//images//noImage.jpg");
		try {
			BufferedImage bi = ImageIO.read(noImage);
			Image image = SwingFXUtils.toFXImage(bi, null);
			imageView.setImage(image);
		} catch (IOException e1) {
			System.out.println("IOException:"+e1.getMessage());
		}
		
		//데이터 채우기
		fillTable(dao.getList());
		
		//년월 미리 정해주기
		inputDate.setText(index);
	
		//그림 입력 받아 프로젝트 폴더에 저장하기
		image.setOnAction(event->saveImage());
		
		//입력받은 정보 파일에 추가하기
		save.setOnAction(event->add());
		
		//헤더의 체크박스 체크하면 모두 체크/언체크
		checkBox.setOnAction(event->checkAll());
		
		//추천도서 삭제
		delete.setOnAction(event->delete());
		
		//테이블에서 더블클릭하면 상세내용 보여주기
		table.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.getClickCount()!=2) return;//더블클릭일 때만 실행
					showReview();
			}
		});
		
		//새로쓰기 버튼 누르면 텍스트필드 비우기
		reset.setOnAction(event->reset());
		
	}
}
