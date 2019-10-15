package com.potential.booksearch.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.potential.booksearch.domain.RentVO;



public class RentDao {
	public List<RentVO> readFile(String file){
		List<RentVO> content=new ArrayList<>();
		try {
			BufferedReader br=new BufferedReader(new FileReader(file));
			String line="";//file 한라인 data저장
			while ((line=br.readLine())!=null){
				
				if(null==line||line.equals("")){
					break;
				}
				String[] strArry= line.split(",");
				RentVO inVO=new RentVO(strArry[0],strArry[1],strArry[2]);
				content.add(inVO);
			}
		} catch (FileNotFoundException fnfe) {
			
		}catch(IOException ioe){
			
			
		}
		return content;
	}
	public 	void rentWrite(FileWriter writer,List<RentVO> rentlist ){
		
		try {
			for(int i=0;i<rentlist.size();i++){
			RentVO outVO=rentlist.get(i);				
			String outStr =outVO.getID()+","+outVO.getBookname()+","+outVO.getRentdate()+"\n";
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

}
