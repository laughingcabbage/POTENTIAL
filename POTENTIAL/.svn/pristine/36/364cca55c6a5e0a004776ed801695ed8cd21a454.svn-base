package com.potential.mypagerewrite.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.hr.cmn.DTO;
import com.hr.cmn.WorkDiv;
import com.potential.member.domain.MemberVO;


public class MypageRewriteDao implements WorkDiv {
	
	private List<MemberVO> mbList = new ArrayList<>();
	private final String rewrite = "//211.238.142.124/hr_data/java04/memberlist.csv";
	

	@Override
	public int do_save(DTO dto) {
		MemberVO vo = (MemberVO)dto;
		int saveCnt = 0;
		//data존재 여부 확인
		for(int i=0;i<mbList.size();i++){
			MemberVO checkVO = mbList.get(i);
			if(checkVO.getId().equals(vo.getId())){
				saveCnt = -1;
				return saveCnt;
			} // Id이 중복일 때 저장 안함.
		}
		boolean flag = mbList.add(vo);
		if(flag==true){
			saveCnt=1;
		}
		
		return saveCnt;
	}



	@Override
	public int do_update(DTO dto) {
		MemberVO inVO=(MemberVO) dto;
		int flag = 0;
		//Data 존재여부(email)
		if(isMemberExist(inVO)){
			do_delete(inVO);
			flag = do_save(inVO);
		}
		return flag;
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
	
	public MemberVO inputDataRewrite(String Uid,String Upw,String Uname,String Uaddress, String Utel){
		MemberVO outVO=null;
		outVO=new MemberVO(Uid,Upw,Uname,Uaddress,Utel);
		return outVO ;
	}
	
	public List<MemberVO> readFile(String file){
		List<MemberVO> befonb=new ArrayList<>();
		try {
			BufferedReader br=new BufferedReader(new FileReader(file));
			String line="";
			while ((line=br.readLine())!=null){
					
				if(null==line||line.equals("")){
					break;
				}
				String[] strArry= line.split(",");
				MemberVO inVO=new MemberVO(strArry[0],strArry[1],strArry[2],strArry[3],strArry[4]);
				
				befonb.add(inVO);
			}
		} catch (FileNotFoundException fnfe) {
			
		}catch(IOException ioe){
					
		}
		return befonb;
	}
	
	public int saveFile(List<MemberVO> mbList) {
		int writeCnt = 0;
		FileWriter writer = null;

		try {
			writer = new FileWriter(rewrite);

			for (int i = 0; i < mbList.size(); i++) {
				MemberVO outVO = mbList.get(i);
				String outStr = outVO.getId() + "," + outVO.getPw() + "," + outVO.getName() + ","
						+ outVO.getAddress() +","+ outVO.getTel() + "\n";
				writer.write(outStr);
				writeCnt++;
			}
			writer.close();
		} catch (IOException e) {
			
		} 

		return writeCnt;
	}
	@Override
	public int do_delete(DTO dto) {
		MemberVO vo = (MemberVO)dto;
		int flag = 0;
		for(int i=this.mbList.size()-1;i>=0;i--){
			MemberVO orgVO = mbList.get(i);
			if(orgVO.getId().equals(vo.getId())){
				mbList.remove(i);
				flag++;
				break;				
			}
		}
		return flag;
	}
	
	public boolean isMemberExist(MemberVO vo){
		boolean flag = false;
		
		for(MemberVO orgVO:this.mbList){
			if(orgVO.getId().equals(vo.getId())){
				flag = true;
				break;
			}
		}
		return flag;
	}
	

}
