package com.potential.rental.dao;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.hr.cmn.DTO;
import com.hr.cmn.WorkDiv;
import com.potential.rental.domain.RentVO;

/**
 * RentalDAO
 * 
 * @Constructor:	FileData_DownLoad()
 * @FileData_DownLoad 파일데이터 받아오기
 * @saveToFile 내부리스트를 외부파일에 저장
 * @addToList 내부리스트에 VO 추가
 * @is_exist 대여상태인 책 중에서 찾고자 하는 책의 유무를 리턴
 * @deleteToList 대여한 책 리스트에서 책 제거
 * @return_VO 대여중인 책 리스트에서 찾는 책의 VO를 반환
 * @return_LendingDay 대여일을 Calendar형으로 리턴
 * @return_Select_all_List List에 있는 책 중 파라미터를 가진 전체목록을 리턴
 * @return_All_List 대여중인 책 목록전체를 리턴
 * @return_All_List_Bycategory 선택한 카테고리의 모든 리스트 리턴
 * @lending_Extension 대출연장
 * @get_remainder 반납일까지 남은일수계산
 * @return_Overdue_BookList 연체중인 책 리스트를 리턴
 * @get_All_Category 대출리스트의 카테고리 목록을 불러온다
 * 
 * */
public class RentalDAO implements WorkDiv{
	/**전체 대여리스트*/
	List<RentVO> list = new ArrayList<>();
	String TEMP_FILE_PATH = "C:\\Users\\sist\\Documents\\RentBookList.txt";
	//String FILE_PATH = "C:\\Users\\sist\\Desktop\\RentBookList.txt";	
	String FILE_PATH = "//211.238.142.124//hr_data//java04//RentBookList.csv";	//네트워크
	File file = new File(FILE_PATH);
	/**대출기간*/
	final int LENDING_DAY = 14;

	/**Constructor : FileData Download*/
	public RentalDAO(){
		FileData_DownLoad();
	}
	
	/**파일데이터 받아오기*/
	public void FileData_DownLoad(){
		List<RentVO> listTmp = new ArrayList<>();
		if(!file.exists()){
			return;
		}
		String tmp = "";
		try( BufferedReader br = new BufferedReader(new FileReader(FILE_PATH)) ) {	
			while( (tmp = br.readLine()) != null ){
				String[] tmpVo = tmp.split("&&&");
				RentVO rentvo = new RentVO(tmpVo[0].trim(),tmpVo[1].trim(),tmpVo[2].trim(),tmpVo[3].trim(),tmpVo[4],Integer.parseInt(tmpVo[5]));
				listTmp.add(rentvo);
			}			
			list = listTmp;
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**BookList to File
	 * */
	public int saveToFile() {
		FileWriter fw =null;
		//File tempfile = new File(TEMP_FILE_PATH);
		File tempfile = new File(FILE_PATH);	//네트워크
		try{
			tempfile.createNewFile();				
			fw = new FileWriter(tempfile);
			for(int i=0; i<list.size() ; i++)
				fw.write(list.get(i).toString() + "\r\n");
		}catch(IOException e){
				e.printStackTrace();
				return -1;
		}finally{
			try {fw.close();} catch (IOException e) {e.printStackTrace();}
		}
		if(tempfile.renameTo(file) == false){
			file.delete();
			tempfile.renameTo(file);
		}
		return 1;
	}
	
	/**
	 * @see bookName
	 * @see author
	 * @see category
	 * @see iD
	 * @see rentalDate
	 * @see possible
	 */
	public boolean addToList(RentVO rentVo) {
		if( this.is_exist(0,rentVo.getBookName()) == -1 ){
			list.add(rentVo);
			return true;
		}else{
			if( this.is_exist(1, rentVo.getAuthor()) == -1){
				list.add(rentVo);
				return true;
			}
		}
			return false;
	} 

	/**대여상태인 책 중에서 찾고자 하는 책의 유무를 리턴
	 * @param : int (0 or 1), String s
	 * @see 0-> s에 책의 이름<p>1-> 저자 입력<p>2-> ID입력
	 * <p>존재 = index_number
	 * <p>없음 = -1
	 * <p>같은 이름을 가진 책인 경우 제일 처음 검색된 하나의 i값만 리턴*/
	public int is_exist(int select, String name) {
		if(select == 0){
			for(int i=0 ; i<list.size() ; i++){
				if(list.get(i).getBookName().equals(name.trim())){
					return i;
				}
			}
		}else if(select == 1){
			for(int i=0 ; i<list.size() ; i++){
				if(list.get(i).getAuthor().equals(name.trim())){
					return i;
				}
			}
		}else if(select == 2){
			for(int i=0 ; i<list.size() ; i++){
				if(list.get(i).getID().equals(name.trim())){
					return i;
				}
			}
		}	
		return -1;
	}
	
	/**대여한 책 리스트에서 책 제거*/
	public boolean deleteToList(String name, String author) {
		int i=0;
		if(	((i=( this.is_exist(1, author) ))>= 0)
			&& (this.is_exist(0, name) >= 0) ){
			list.remove(i);
			return true;
		}
		return false;
	}
	
	/**대여중인 책 리스트에서 찾는 책의 VO를 반환*/
	public RentVO return_VO(String name, String author) {
		int i = this.is_exist(1, author);
		if(	i >= 0 && this.is_exist(0, name) >= 0 ){
			return list.get(i);
		}
		return null;
	}
	
	/**대여일을 Calendar형으로 리턴*/
	public Calendar return_LendingDay(String name, String author) {
		RentVO tmpVo = this.return_VO(name, author);
		if( tmpVo == null ) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		String[] date = tmpVo.getRentalDate().split("/");
		c.set(Integer.parseInt(date[0])+2000, Integer.parseInt(date[1]), Integer.parseInt(date[2]));
		return c;
	}
	
	/**List에 있는 책 중 파라미터를 가진 전체목록을 리턴
	 * @param int select, String name, 모집합 RentVO의 list
	 * @select ->0 ID
	 * @select ->1 책제목
	 * @select ->2 저자
	 * */
	public List<RentVO> return_Select_all_List(int select, String name, List<RentVO> list) {
		List<RentVO> tmplist = new ArrayList<>();
		switch(select){
			case 0:
				for(int i=0 ; i<list.size() ; i++){
					if(list.get(i).getID().equals(name.trim())){
						tmplist.add(list.get(i));
					}
				}
				break;
			case 1:
				for(int i=0 ; i<list.size() ; i++){
					if(list.get(i).getBookName().contains(name.trim())){
						tmplist.add(list.get(i));
					}
				}
				break;
			case 2:
				for(int i=0 ; i<list.size() ; i++){
					if(list.get(i).getAuthor().contains(name.trim())){
						tmplist.add(list.get(i));
					}
				}
				break;
			default:
				return null;
		}
		return tmplist;
	}
	
	/**대여중인 책 목록전체를 리턴*/
	public List<RentVO> return_All_List() {
		return list;
	}
	
	/**선택한 카테고리의 모든 리스트 리턴*/
	public List<RentVO> return_All_List_Bycategory(String categoryName){
		List<RentVO> tmp = new ArrayList<>();
		for(int i=0 ; i<list.size() ; i++){
			if(list.get(i).getCategory().equals(categoryName)){
				tmp.add(list.get(i));
			}
		}
		return tmp;
	}
	
	/**대출연장
	 * @param int select, String ID, String name, String author
	 * @See select 0 or 1
	 * @See 1일경우 연장불가를 무시하고 연장
	 * */
	public boolean lending_Extension(int select, String ID, String name, String author) {
		List<RentVO> tmp = return_Select_all_List(0, ID, list);	//대상 아이디의 모든 대출리스트
		if(tmp != null){
			RentVO tmpVo = this.return_VO(name, author);
			//대상이 책을 진짜 가지고있는지, 연장가능한지 검사
			if(tmpVo == null || tmpVo.getID().equals(ID)==false ) return false;
			if( select == 0 )if(tmpVo.getPossible()==1)return false;
			Calendar c = this.return_LendingDay(name, author);	//캘린더형으로 대여일 저장
			c.add(Calendar.DAY_OF_MONTH, +LENDING_DAY);	//연장
			String newDay = String.valueOf((c.get(Calendar.YEAR)-2000)) + "/"
					+ String.valueOf(c.get(Calendar.MONTH))		+ "/"
					+ String.valueOf(c.get(Calendar.DAY_OF_MONTH));
			tmpVo.setRentalDate(newDay);	//연장한 데이터를 덮어쓰기
			tmpVo.setPossible(1);	//연장불가로 만듦
			//기존데이터 지우고 새 데이터 추가
			deleteToList(tmpVo.getBookName(), tmpVo.getAuthor());
			addToList(tmpVo);
			return true;
		}
		return false;
	}
	
	/**반납일까지 남은일수계산
	 * @return 100일경우 계산실패*/
	public int get_remainder(String name, String author) {
		Calendar c = this.return_LendingDay(name, author);	//대여일
		Calendar now = Calendar.getInstance();	//지금날짜	
		c.add(Calendar.DAY_OF_MONTH, +LENDING_DAY);	//반납날
		now.add(Calendar.MONTH, +1);	//현재날짜는 개월이 -1되어 나으므로 보정
		double result =  (c.getTimeInMillis() - now.getTimeInMillis());
		result = (result / 1000 /60 /60 /24) ;	//밀리초단위를 하루단위로
		return (int)result;
				
	
	}
	
	/**연체중인 책 리스트를 리턴 */
	public List<RentVO> return_Overdue_BookList(){
		List<RentVO> tmp = new ArrayList<>();	
		for(int i=0 ; i<list.size() ; i++){
			if( this.get_remainder(list.get(i).getBookName(), list.get(i).getAuthor()) < 0) {
				tmp.add(list.get(i));
			}
		}
		return tmp;
	}
	
	/**대출리스트의 카테고리 목록을 불러온다*/
	public List<String> get_All_Category(){
		boolean flag = false;
		List<String> tmp = new ArrayList<>();
		tmp.add(list.get(0).getCategory());
		for(int i=1 ; i<list.size() ; i++){
			flag = false;
			int sizetmp = tmp.size();
			for(int q=0 ; q<sizetmp ; q++){
				if( list.get(i).getCategory().equals( tmp.get(q) ) ) {
					flag = true;
				}
			}
			if(flag == false) tmp.add(list.get(i).getCategory());
		}
		return tmp;
	}

	@Override
	public int do_save(DTO dto) {
		RentVO inVo = (RentVO)dto;
		addToList(inVo);
		if(saveToFile() == 1)
			return 1;
		return -1;
	}

	@Override
	public int do_delete(DTO dto) {
		RentVO inVo = (RentVO)dto;
		if(deleteToList(inVo.getBookName(), inVo.getAuthor()) == true)
			return 1;
		return -1;
	}

	@Override
	public int do_update(DTO dto) {
		RentVO inVo = (RentVO)dto;
		if(addToList(inVo) == true)
			return 1;
		return -1;
	}

	@Override
	public DTO do_selectOne(DTO dto) {
		RentVO inVo = (RentVO)dto;
		RentVO outVo = null;
		return_VO(inVo.getBookName(), inVo.getAuthor());
		return outVo;
	}

	@Override
	public List<?> do_retrieve(DTO dto) {
		RentVO inVo = (RentVO)dto;
		List<RentVO> outList = null;
		outList = return_Select_all_List(0, inVo.getID(), list);
		return outList;
	}
	
}