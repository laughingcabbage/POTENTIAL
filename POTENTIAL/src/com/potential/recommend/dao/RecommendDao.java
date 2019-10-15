/**
 * @Class Name : RecommendDao.java
 * @Description : 
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2019-05-31           최초생성
 *
 * @author 개발프레임웍크 실행환경 HR.POTENTIAL 개발팀
 * @since 2019-05-31 
 * @version 1.0
 * @see
 *
 *  Copyright (C) by HR.POTENTIAL All right reserved.
 */
package com.potential.recommend.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.potential.recommend.domain.RecommendVO;

/**
 * @author sist
 *
 */
public class RecommendDao {

	private List<RecommendVO> list = new ArrayList<>();
	private final String FILE_PATH = "//211.238.142.124//hr_data//java04//Recommendation.csv";
	
	//초기화
	public RecommendDao(){
		readFile();
	}
	
	//파일 -> list
	public void readFile(){
		try {
			BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
			String line = "";
			
			while((line=br.readLine())!=null){
				if(line.trim().equals("")) break;
				
				String[] lineArr = line.split("&&&");
				RecommendVO vo = new RecommendVO(Integer.parseInt(lineArr[0]), lineArr[1], lineArr[2], lineArr[3], lineArr[4]);
				list.add(vo);
			}
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException:"+e.getMessage());
		} catch (IOException e) {
			System.out.println("IOException:"+e.getMessage());
		}
	}
	
	//중복확인하기
	public boolean BookExists(RecommendVO vo){
		boolean flag=false;
		for(int i=0; i<list.size(); i++){
			RecommendVO tmpVO = list.get(i);
			if(tmpVO.getIndex()==vo.getIndex()){
				flag=true;
				return flag;
			}
		}
		return flag;
	}
	
	//리스트에 vo 추가하기
	public int do_save(RecommendVO vo){
		int saveCnt=0; 
		list.add(vo);
		saveCnt++;
		return saveCnt;
	}
	
	//파일 저장하기
	public int saveFile(){
		int writeCnt = 0;
		FileWriter writer = null;
		try {
			writer = new FileWriter(FILE_PATH);
			for(int i=0; i<list.size(); i++){
				RecommendVO vo = list.get(i);
				String strVO = vo.getIndex()+"&&&"+vo.getTitle()+"&&&"+vo.getAuthor()+"&&&"+vo.getPublisher()+"&&&"+vo.getReview()+"\n";
				writer.write(strVO);
				writeCnt++;
			}
		} catch (IOException e) {
			System.out.println("IOException:"+e.getMessage());
		} finally{
			try {
				writer.close();
			} catch (IOException e) {
				System.out.println("IOException:"+e.getMessage());
			}
		}
		return writeCnt;
	}
	
	//getList
	public List<RecommendVO> getList(){	
		return list;
	}
}
