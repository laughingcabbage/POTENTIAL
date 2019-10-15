package com.potential.booksearch.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hr.cmn.DTO;
import com.hr.cmn.WorkDiv;
import com.potential.booksearch.domain.BookSearchVO;
import com.potential.booksearch.domain.RentVO;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;



public class BookSearchDao implements WorkDiv {
	private Alert alert = new Alert(AlertType.INFORMATION);
	@Override
	public int do_save(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int do_delete(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int do_update(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DTO do_selectOne(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DTO> do_retrieve(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<BookSearchVO> readFile(String file){
		List<BookSearchVO> content=new ArrayList<>();
		try {
			BufferedReader br=new BufferedReader(new FileReader(file));
			String line="";//file 한라인 data저장
			while ((line=br.readLine())!=null){
				
				if(null==line||line.equals("")){
					break;
				}
				String[] strArry= line.split(",");
				BookSearchVO inVO=new BookSearchVO(strArry[0],strArry[1],strArry[2],strArry[3],strArry[4],strArry[5]);
				content.add(inVO);
			}
		} catch (FileNotFoundException fnfe) {
			
		}catch(IOException ioe){
			
			
		}
		return content;
	}
	public BookSearchVO getInputData(String inbookname,String inwriter,String inpub,String inpubYear, String inremarks, String state){
		BookSearchVO outVO=null;
		outVO=new BookSearchVO(inbookname,inwriter,inpub,inpubYear,inremarks,state);
		return outVO ;
	}
	public 	void BookWrite(FileWriter writer,List<BookSearchVO> Booklist ){
		try {
			for(int i=0;i<Booklist.size();i++){
			BookSearchVO outVO=Booklist.get(i);				
			String outStr =outVO.getName()+","+outVO.getWriter()+","+outVO.getPub()+","+outVO.getPubYear()+","+outVO.getRemarks()+","+outVO.getState()+"\n";
			writer.write(outStr);
			
			}
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			
		}finally {
			try {
				writer.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	public List<BookSearchVO> do_search(String search_value,String strcom,List<BookSearchVO> booklist) {
		 
		  List<BookSearchVO> list=new ArrayList<>();
		  String searchKeyword = search_value;
		  String searchCond    = strcom;
		  Pattern p = Pattern.compile(".*"+searchKeyword+".*");

		  for(int i=0;i<booklist.size();i++){
		   BookSearchVO outVO = booklist.get(i);
		   Matcher m = null;
		   if(searchCond.equals("name") ){
			    m = p.matcher(outVO.getName());
			   }
			   else if(searchCond.equals("writer") ){
				    m = p.matcher(outVO.getWriter());
				   }
			   else if(searchCond.equals("pub")){
				    m = p.matcher(outVO.getPub());
				   }
			   else if(searchCond.equals("pubyear")){
				    m = p.matcher(outVO.getPubYear());
				   }
		   
		   if (m.matches()) {
		    list.add(outVO);
		   }
		  }
		  
		  return list;
		 }
	
	
	   public List<RentVO> do_rent(BookSearchVO inVO,List<BookSearchVO> Booklist, String id, List<RentVO> rentlist) {
		   long time = System.currentTimeMillis(); 
	          
		      SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		 	String str = dayTime.format(new Date(time));
		 	 int count=0;

		      for(int i = Booklist.size()-1;i>=0;i--){
		         BookSearchVO orgVO = Booklist.get(i);
		        
		         if(orgVO.getName().equals(inVO.getName())&&orgVO.getWriter().equals(inVO.getWriter())&&orgVO.getPub().equals(inVO.getPub())&&orgVO.getPubYear().equals(inVO.getPubYear())&&orgVO.getState().equals("대여")){
		        	 count++;
		        	 continue;
		         }
		        
		         else if(orgVO.getName().equals(inVO.getName())&&orgVO.getWriter().equals(inVO.getWriter())&&orgVO.getPub().equals(inVO.getPub())&&orgVO.getPubYear().equals(inVO.getPubYear()) ){
		        	 orgVO.setState("대여");
		        	 Booklist.set(i,orgVO);
		        	 rentlist.add(new RentVO(id, orgVO.getName(),str));
		            count=0;
		            alert.setContentText("대여 되엇습니다");
					alert.showAndWait();
		            break;
		         }
		      }
		      if(count !=0){
		    	  alert.setContentText("대여중이라 대여불가합니다");
				  alert.showAndWait();
		      }
		      return rentlist;
		
		      
		   }

}
