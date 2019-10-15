package com.potential.member.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import com.hr.cmn.DTO;
import com.hr.cmn.WorkDiv;
import com.potential.member.controll.MemberContoller;
import com.potential.member.domain.MemberVO;





public class MemberDao implements WorkDiv {
	public List<MemberVO> readFile(String file){
		List<MemberVO> content=new ArrayList<>();
		try {
			BufferedReader br=new BufferedReader(new FileReader(file));
			String line="";//file 한라인 data저장
			while ((line=br.readLine())!=null){
				
				if(null==line||line.equals("")){
					break;
				}
				String[] strArry= line.split(",");
				MemberVO inVO=new MemberVO(strArry[0],strArry[1],strArry[2],strArry[3],strArry[4]);
				content.add(inVO);
			}
		} catch (FileNotFoundException fnfe) {
			
		}catch(IOException ioe){
			
			
		}
		return content;
	}
	public MemberVO getInputData(String Uid,String Upw,String Uname,String Uaddress, String Utel){
		MemberVO outVO=null;
		outVO=new MemberVO(Uid,Upw,Uname,Uaddress,Utel);
		return outVO ;
	}
	public void MemberWrite(FileWriter writer,List<MemberVO> Memberlist ){
		MemberContoller mc= new MemberContoller();
		try {
			for(int i=0;i<Memberlist.size();i++){
			MemberVO outVO=Memberlist.get(i);				
			String outStr =outVO.getId()+","+outVO.getPw()+","+outVO.getName()+","+outVO.getAddress()+","+outVO.getTel()+"\n";
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

}
