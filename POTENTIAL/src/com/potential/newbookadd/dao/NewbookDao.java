package com.potential.newbookadd.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.hr.cmn.DTO;
import com.hr.cmn.WorkDiv;
import com.potential.newbookadd.domain.NewBookVO;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NewbookDao implements WorkDiv {
	@FXML Button addBtn;
	@FXML TextField addTitile;
	@FXML TextField addWriter;
	@FXML TextField addPub;
	@FXML TextField addPubYear;
	@FXML TextField addGenre;
	private List<NewBookVO> nbList = new ArrayList<>();
	private final String safi = "//211.238.142.124/hr_data/java04/booklist.csv";
	

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
	
	public int saveFile(List<NewBookVO> nbList) {
		int writeCnt = 0;// 저장 Line Count
		FileWriter writer = null;

		try {
			writer = new FileWriter(safi);

			for (int i = 0; i < nbList.size(); i++) {
				NewBookVO outVO = nbList.get(i);
				String outStr = outVO.getNbName() + "," + outVO.getNbWriter() + "," + outVO.getNbPubli() + ","
						+ outVO.getNbPubYear() +","+ outVO.getNbGenre() + "," + outVO.getStatus() + "\n";
				writer.write(outStr);
				writeCnt++;
			} // for
			writer.close();
		} catch (IOException e) {
			
		} 

		return writeCnt;
	}
	

	public List<NewBookVO> readFile(String file){
		List<NewBookVO> befonb=new ArrayList<>();
		try {
			BufferedReader br=new BufferedReader(new FileReader(file));
			String line="";//file 한라인 data저장
			while ((line=br.readLine())!=null){
					
				if(null==line||line.equals("")){
					break;
				}
				String[] strArry= line.split(",");
				NewBookVO inVO=new NewBookVO(strArry[0],strArry[1],strArry[2],strArry[3],strArry[4],strArry[5]);
				
				befonb.add(inVO);
			}
		} catch (FileNotFoundException fnfe) {
			
		}catch(IOException ioe){
					
		}
		return befonb;
	}
	
	
	
}
