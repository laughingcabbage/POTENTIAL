 /**
 * @Class Name : MemberEClassTestMain.java
 * @Description : 
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2019-05-28           최초생성
 *
 * @author 개발프레임웍크 실행환경 HR. 개발팀
 * @since 2019-05-28 
 * @version 1.0
 * @see
 *
 *  Copyright (C) by HR. KIM All right reserved.
 */
package com.hr.eclass.member.dao;



import org.apache.log4j.Logger;

import com.hr.eclass.member.domain.MemberEClassVO;


public class MemberEClassTestMain {

    Logger  LOG=Logger.getLogger(MemberEClassTestMain.class);
	//Dao
    MemberEClassDao dao=new MemberEClassDao();
	
	//Data
	MemberEClassVO memberVO_01;
	MemberEClassVO memberVO_02;
	MemberEClassVO memberVO_03;
	
	public MemberEClassTestMain(){
		memberVO_01=new MemberEClassVO("1", "j01","이상무0101", "1234" , "호동","james80@paran.com", "010-1234-5678", "서울시 마포구 홍대", "2", "admin", "", "admin","");
		memberVO_02=new MemberEClassVO("2", "j02","이상무0102", "1234" , "호동","james80@paran.com", "010-1234-5678", "서울시 마포구 홍대", "2", "admin", "", "admin","");
		memberVO_03=new MemberEClassVO("3", "j03","이상무0103", "1234" , "호동","james80@paran.com", "010-1234-5678", "서울시 마포구 홍대", "2", "admin", "", "admin","");
	}
	
    public void do_addAndGet(){
    	//Member Data
    	//---------------------------------------------------
    	//삭제
    	//---------------------------------------------------
    	int one   = dao.do_delete(memberVO_01);
    	int two   = dao.do_delete(memberVO_02);
    	int three = dao.do_delete(memberVO_03);
    	LOG.debug("one:"+one);
    	 
    	//---------------------------------------------------
    	//추가
    	//---------------------------------------------------
    	int oneMember   = dao.do_insert(memberVO_01);
    	int twoMember   = dao.do_insert(memberVO_02);
    	int threeMember = dao.do_insert(memberVO_03);

		if(oneMember==1 && twoMember==1 && threeMember==1){
			LOG.debug("===========================");
			LOG.debug("추가 성공");
			LOG.debug("===========================");
		}else{
			LOG.debug("===========================");
			LOG.debug("추가 실패");
			LOG.debug("===========================");
		}
    	
    	
    	int cntOne   = dao.checkUserId(memberVO_01.getGroupDiv(),memberVO_01.getUser_Id());
    	int cntTwo   = dao.checkUserId(memberVO_02.getGroupDiv(),memberVO_02.getUser_Id());
    	int cntThree = dao.checkUserId(memberVO_03.getGroupDiv(),memberVO_03.getUser_Id());
		if(cntOne==1 && cntTwo==1 && cntThree==1){
			LOG.debug("===========================");
			LOG.debug("추가 성공");
			LOG.debug("===========================");
		}else{
			LOG.debug("===========================");
			LOG.debug("추가 실패");
			LOG.debug("===========================");
		}
    	
    	//---------------------------------------------------
    	//조회
    	//---------------------------------------------------    	
    	MemberEClassVO member01      = dao.do_login(memberVO_01);
    	MemberEClassVO member02      = dao.do_login(memberVO_02);
    	MemberEClassVO member03      = dao.do_login(memberVO_03);
    	if (member01.equals(memberVO_01) && member02.equals(memberVO_02)) {
			LOG.debug("===========================");
			LOG.debug("로그인 성공");
			LOG.debug("===========================");    		
    	}else{
			LOG.debug("===========================");
			LOG.debug("로그인 실패");
			LOG.debug("===========================");     		
    	}
    }	
	public static void main(String[] args) {
		MemberEClassTestMain main=new MemberEClassTestMain();
		main.do_addAndGet();
	}

}
