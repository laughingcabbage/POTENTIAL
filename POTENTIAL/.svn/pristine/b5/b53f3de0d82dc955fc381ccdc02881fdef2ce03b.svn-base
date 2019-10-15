 /**
 * @Class Name : AddressBookTest.java
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
package com.hr.test;

import org.apache.log4j.Logger;

import com.hr.address.dao.AddressBookDao;
import com.hr.address.domain.MemberVO;


public class AddressBookTest {
	
	private MemberVO vo01;
	private MemberVO vo02;
	private MemberVO vo03;
	private AddressBookDao addressBookDao;
	private static final Logger LOG=Logger.getLogger(AddressBookTest.class);
	public AddressBookTest(){
		addressBookDao=new AddressBookDao();
		vo01=new MemberVO("hr01@naver.com","이상무01",19950101,"010-1234-5678");
		vo02=new MemberVO("hr02@naver.com","이상무02",19950102,"010-1234-5679");
		vo03=new MemberVO("hr03@naver.com","이상무03",19950103,"010-1234-5680");
	}
	
	/**
	 * @Method Name  : main
	 * @작성일   : 2019. 5. 15.
	 * @작성자   : SIST
	 * @변경이력  : 최초작성
	 * @Method 설명 :
	 * @param args
	 */
	public static void main(String[] args) {
		AddressBookTest addressBookTest=new AddressBookTest();
		addressBookTest.addAndGet();
		addressBookTest.update();
	}
	
	public void update(){
		//-----------------------------
		//삭제
		//-----------------------------
		addressBookDao.do_delete(vo01);
		//-----------------------------
		//추가
		//-----------------------------
		addressBookDao.do_save(vo01);
		//-----------------------------
		//수정
		//-----------------------------
		//vo01=new MemberVO("hr01@naver.com","이상무01",19950101,"010-1234-5678");
		vo01.setName("이상무01_U");
		vo01.setBirthday(20000101);
		vo01.setCellPhone("017-1234-5678");
		
		int uFlag = addressBookDao.do_update(vo01);
		//-----------------------------
		//단건조회
		//-----------------------------
		MemberVO outVO = (MemberVO) addressBookDao.do_selectOne(vo01);
		//-----------------------------
		//비교
		//-----------------------------
		if(outVO.equals(vo01)){
			LOG.debug("===================");
			LOG.debug("수정성공");
			LOG.debug("===================");
		}else{
			LOG.debug("===================");
			LOG.debug("수정실패");
			LOG.debug("===================");			
		}
		//-----------------------------
		//파일저장
		//-----------------------------		
		addressBookDao.saveFile();
		
	}
	
	public void addAndGet(){
		//-----------------------------
		//삭제
		//-----------------------------
		addressBookDao.do_delete(vo01);
		addressBookDao.do_delete(vo02);
		//-----------------------------
		//추가
		//-----------------------------
		int addOne = addressBookDao.do_save(vo01);
		int addTwo = addressBookDao.do_save(vo02);
//		LOG.debug("===================");
//		LOG.debug("addOne="+addOne);
//		LOG.debug("===================");
		addressBookDao.saveFile();
		//-----------------------------
		//단건조회
		//-----------------------------	
		MemberVO outVO =(MemberVO) addressBookDao.do_selectOne(vo01);
		MemberVO outVO02 =(MemberVO) addressBookDao.do_selectOne(vo02);
		LOG.debug("===================");
		LOG.debug("outVO="+outVO);
		LOG.debug("===================");		
		//-----------------------------
		//비교
		//-----------------------------		
		if(vo01.equals(outVO) && vo02.equals(outVO02)){
			LOG.debug("===================");
			LOG.debug("등록성공");
			LOG.debug("===================");			
		}else{
			LOG.debug("===================");
			LOG.debug("등록실패");
			LOG.debug("===================");			
		}
	}
	
	

}
