 /**
 * @Class Name : AddressBookDao.java
 * @Description : 
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2019-05-15           최초생성
 *
 * @author 개발프레임웍크 실행환경 HR. 개발팀
 * @since 2019-05-15 
 * @version 1.0
 * @see
 *
 *  Copyright (C) by HR. KIM All right reserved.
 */
package com.hr.address.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hr.address.domain.MemberVO;
import com.hr.cmn.DTO;
import com.hr.cmn.WorkDiv;


/**
 * @author SIST
 *
 */
public class AddressBookDao implements WorkDiv {
	/**
	 * File읽어 to List<MemberVO>
	 */
	private List<MemberVO> addressBook=new ArrayList<MemberVO>();
	
	/**
	 * File 저장경로 
	 */
	//private final String FILE_PATH = "C:/TEMP/addRessBook.csv";
	private final String FILE_PATH = "//211.238.142.124/hr_data/javahr/addRessBook.csv";
	
	private Logger LOG=Logger.getLogger(AddressBookDao.class);
	
	
	/**
	 * 초기화,파일Read
	 */
	public AddressBookDao(){
		try{
			BufferedReader br=new BufferedReader(new FileReader(FILE_PATH));
			String line = "";//File 1개 라인 Data저장
			
			while(  (line = br.readLine()) !=null){
				//validation
				if(null == line || line.equals("")){
					break;
				}
				
				String[] strArry = line.split(",");
				MemberVO inVO=new MemberVO(
								strArry[0]
							   ,strArry[1]
							   ,Integer.parseInt(strArry[2])
							   ,strArry[3]
						      );
				addressBook.add(inVO);
			}
			
		}catch(FileNotFoundException fnfe){
			LOG.debug("===================");
			LOG.debug("FileNotFoundException="+fnfe.getMessage());
			LOG.debug("===================");
		}catch(IOException ioe){
			LOG.debug("===================");
			LOG.debug("IOException="+ioe.getMessage());
			LOG.debug("===================");			
		}
	}
	
	/**
	 * 
	 * @Method Name  : isMemberExist
	 * @작성일   : 2019. 5. 14.
	 * @작성자   : SIST
	 * @변경이력  : 최초작성
	 * @Method 설명 : ArrayList MemberVO존재 여부 Check
	 * @param vo
	 * @return boolean
	 */
	public boolean isMemberExist(MemberVO vo){
		boolean flag = false;
		
		for(MemberVO orgVO:this.addressBook){
			if(orgVO.getEmail().equals(vo.getEmail())){
				flag = true;
				break;
			}
		}
		
		return flag;
	}
	
	
	/* (non-Javadoc)
	 * @see com.hr.cmn.WorkDiv#do_save(com.hr.cmn.DTO)
	 */
	@Override
	public int do_save(DTO dto) {
		MemberVO vo=(MemberVO)dto;
		int saveCnt = 0;
		//data존재 여부 확인
		for(int i=0;i<addressBook.size();i++){
			MemberVO checkVO = addressBook.get(i);
			if(checkVO.getEmail().equals(vo.getEmail())){
				saveCnt = -1;
				return saveCnt;
			}
		}
		boolean flag = addressBook.add(vo);
		if(flag==true){
			saveCnt=1;
		}
		
		return saveCnt;
	}

	/* (non-Javadoc)
	 * @see com.hr.cmn.WorkDiv#do_delete(com.hr.cmn.DTO)
	 */
	@Override
	public int do_delete(DTO dto) {
		MemberVO vo=(MemberVO)dto;
		int flag = 0;
		//뒤에서 부터 찾아 삭제 할것.
		for(int i=this.addressBook.size()-1;i>=0;i--){
			MemberVO orgVO = addressBook.get(i);
			if(orgVO.getEmail().equals(vo.getEmail())){
				addressBook.remove(i);
				flag++;
				break;
			}
		}
		
		
		return flag;
	}

	/* (non-Javadoc)
	 * @see com.hr.cmn.WorkDiv#do_update(com.hr.cmn.DTO)
	 */
	@Override
	public int do_update(DTO dto) {
		MemberVO inVO=(MemberVO) dto;
		int flag = 0;
		//Data존재여부(email)
    	if(isMemberExist(inVO)){
    		do_delete(inVO);
    		flag = do_save(inVO);
    	}
    	
		return flag;
	}

	/* (non-Javadoc)
	 * @see com.hr.cmn.WorkDiv#do_selectOne(com.hr.cmn.DTO)
	 */
	@Override
	public DTO do_selectOne(DTO dto) {
		MemberVO inVO = (MemberVO) dto;
		MemberVO outVO = null;
		for(int i=0;i<this.addressBook.size();i++){
			MemberVO tmpVO =addressBook.get(i);
			if(tmpVO.getEmail().equals(inVO.getEmail())){
				outVO = tmpVO;
				break;
			}
		}
		return outVO;
	}

	/* (non-Javadoc)
	 * @see com.hr.cmn.WorkDiv#do_retrieve(com.hr.cmn.DTO)
	 */
	@Override
	public List<DTO> do_retrieve(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int saveFile(){
		int writeCnt = 0;//저장 Line Count
		FileWriter writer=null;
		
		try{
			writer = new FileWriter(FILE_PATH);
			
			for(int i=0;i<addressBook.size();i++){
				MemberVO outVO = addressBook.get(i);
				//String email, String name, int birthday, String cellPhone
				String outStr = outVO.getEmail()+","
								+outVO.getName()+","
								+outVO.getBirthday()+","
								+outVO.getCellPhone()+"\n";
				writer.write(outStr);
				writeCnt++;
			}//--for
			
		}catch(IOException e){
			LOG.debug("---------------------");
			LOG.debug("IOException:"+e.getMessage());
			LOG.debug("---------------------");
		}finally{
			try {
				writer.close();
			} catch (IOException e) {
				LOG.debug("---------------------");
				LOG.debug("IOException:"+e.getMessage());
				LOG.debug("---------------------");
			}
		}
		
		return writeCnt;		
	}

}
