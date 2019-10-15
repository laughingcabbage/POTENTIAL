 /**
 * @Class Name : MemberEClassDao.java
 * @Description : 
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2019-05-20           최초생성
 *
 * @author 개발프레임웍크 실행환경 HR. 개발팀
 * @since 2019-05-20 
 * @version 1.0
 * @see
 *
 *  Copyright (C) by HR. KIM All right reserved.
 */
package com.hr.eclass.member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hr.eclass.cmn.DTO;
import com.hr.eclass.cmn.HRConst;
import com.hr.eclass.cmn.WorkDiv;
import com.hr.eclass.member.domain.MemberEClassVO;
import com.hr.eclass.member.domain.WorkInfoVO;

/**
 * @author SIST
 *
 */
public class MemberEClassDao implements WorkDiv {

	private Connection conn = null;

	/**
	  * DB를 연결한다.
	  * @param
	  * @return Connection
	  */
	private Connection connect() {
		String DB_URL 		= HRConst.DB_URL;
		String DB_USER 		= HRConst.DB_USER;
		String DB_PASSWORD 	= HRConst.DB_PASSWORD;
		try {
			// 드라이버를 로딩한다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 데이터베이스의 연결을 설정한다.
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			System.out.println("Version:"+HRConst.VERSION);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		} catch (Exception e) {
			System.out.println("=====================");
			e.getStackTrace();
			System.out.println("=====================");
		} 
		return conn;
	}

	
	/**
	  * DB를 연결을 종료한다.
	  * @param
	  * @return void
	  */	
	private void disconnect()
	{
		try{
			if(conn != null){
				conn.close();
			}

		}catch(SQLException se){
			System.out.println(se.getMessage());
		}catch(Exception e){
			try{
				System.out.println("=====================");
				System.out.println("con Close : " + e.getMessage());
				System.out.println("=====================");
			}catch(Exception e2){
				System.out.println("=====================");
				e2.getStackTrace();
				System.out.println("=====================");
			}
		}
	}
	
	
	/**
	 * 로그인 처리 
	 * @param user_id
	 * @param passwd
	 * @param div
	 * @return DTO
	 */	
	public MemberEClassVO do_login(MemberEClassVO dto){
		MemberEClassVO memberVO=dto;

		
		int idCnt = checkUserId(memberVO.getGroupDiv(),  memberVO.getUser_Id());
		if( idCnt ==  0){
			memberVO.setMessage("ID를 확인 하세요.");
			memberVO.setMessageDiv("00");//ID를 확인 하세요.
			return memberVO;
		}
		
		int passwdCnt = this.checkPasswd(memberVO.getGroupDiv(),memberVO.getUser_Id(), memberVO.getPw());
		if( passwdCnt ==  0){
			memberVO.setMessage("비밀번호를 확인 하세요.");
			memberVO.setMessageDiv("01");//비밀번호를 확인 하세요.
			return memberVO;
		}
		
		
		MemberEClassVO outMember= (MemberEClassVO) do_selectOne(memberVO);
		//--Message
		outMember.setMessageDiv("11");
		outMember.setMessage("["+outMember.getName()+"]님\n환영 합니다.");
		
		return outMember;		
	}
	
	
	/**
	 * ID 체크:완료
	 * @param userId,group_div
	 * @return:OK
	 */
	public int checkUserId(String group_div,String userId){
		//USER_ID 체크
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		StringBuilder sb=new StringBuilder();
		try{
			connect();
			
			sb.append(" SELECT COUNT(*) as cnt   \n");
			sb.append(" FROM MEMBER              \n");
			sb.append(" WHERE group_div = ?      \n");
			sb.append(" AND   user_id = ?             \n");
			
			pstmt = conn.prepareStatement(sb.toString());
			System.out.println("=====================");
			System.out.println("checkUserId : \n" + sb.toString());

			System.out.println("group_div : " + group_div);
			System.out.println("userId : " + userId);
			System.out.println("=====================");
			
			pstmt.setString(1, group_div);
			pstmt.setString(2, userId);
			
			rs = pstmt.executeQuery();

			if(rs.next())
			{
				result = rs.getInt("cnt");
			}
			
		}catch(Exception ex){
			System.out.println("=====================");
			System.out.println("SelectDBCountDao : " + ex);
			System.out.println("=====================");
		}finally{
			try{
				if(rs != null)
					rs.close();
			}catch(Exception ex){
				System.out.println("=====================");
				ex.getStackTrace();
				System.out.println("=====================");
			}
			try{
				if(pstmt != null)
					pstmt.close();
			}catch(Exception ex){
				System.out.println("=====================");
				ex.getStackTrace();
				System.out.println("=====================");
			}
			disconnect();
		}
		
		System.out.println("=====================");
		System.out.println("checkUserId : " );
		System.out.println("result : " + result);
		System.out.println("=====================");		
		return result;
	}
	
	
	/**
	 * ID/비번 체크
	 * @param userId
	 * @return int
	 */
	public int checkPasswd(String group_div, String userId,String passwd){
		//USER_ID 체크
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		StringBuilder sb=new StringBuilder();
		try{
			connect();
			
			sb.append(" SELECT COUNT(*) as cnt   \n");
			sb.append(" FROM member              \n");
			sb.append(" WHERE group_div = ?      \n");
			sb.append(" AND   user_id = ?        \n");
			sb.append(" AND   pw  = ?            \n");
			
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, group_div);
			pstmt.setString(2, userId);
			pstmt.setString(3, passwd);

			System.out.println("=====================");
			System.out.println("checkPasswd : \n" + sb.toString());
			System.out.println("group_div : " + group_div);
			System.out.println("userId : " + userId);
			System.out.println("passwd : " + passwd);
			System.out.println("=====================");
			
			
			rs = pstmt.executeQuery();

			if(rs.next())
			{
				result = rs.getInt("cnt");
			}
			
		}catch(Exception ex){
			System.out.println("=====================");
			System.out.println("SelectDBCountDao : " + ex);
			System.out.println("=====================");
		}finally{
			try{
				if(rs != null)
					rs.close();
			}catch(Exception ex){
				System.out.println("=====================");
				ex.getStackTrace();
				System.out.println("=====================");
			}
			try{
				if(pstmt != null)
					pstmt.close();
			}catch(Exception ex){
				System.out.println("=====================");
				ex.getStackTrace();
				System.out.println("=====================");
			}
			disconnect();
		}
		System.out.println("=====================");
		System.out.println("checkPasswd : " );
		System.out.println("result : " + result);
		System.out.println("=====================");
		
		return result;
	}
	
	
	/**
	 * 
	 */
	public MemberEClassDao() {
		// TODO Auto-generated constructor stub
	}


	/**
	  * 사용자를 수정한다.
	  * @param vo - 수정할 정보가 담긴 DTO
	  * @return 등록 결과  0보다 크면 성공
	  */	
	public int do_update(DTO t) {
		MemberEClassVO memberVO=(MemberEClassVO) t;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result  = -1;		 
		
		StringBuilder sb=new StringBuilder();
		sb.append(" UPDATE member           \n");
		sb.append(" 	   set namE=?,      \n");
		sb.append(" 	   pw=?,            \n");        
		sb.append(" 	   nic_nm=?,        \n");    
		sb.append(" 	   email=?,         \n");     
		sb.append(" 	   cellphoNE=?,     \n"); 
		sb.append(" 	   address=?,       \n");   
		sb.append(" 	   auth=?,          \n");     
		sb.append(" 	   mod_id=?,        \n");    
		sb.append(" 	   mod_dt=SYSDATE   \n");
		sb.append(" WHERE group_div = ?     \n");
		sb.append(" AND user_id          = ?     \n");   
		 
		 
		try{
			connect();
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1,memberVO.getName()==null?"무명":memberVO.getName());
			pstmt.setString(2,memberVO.getPw());
			pstmt.setString(3,memberVO.getNicNm()==null?"무닉네임":memberVO.getNicNm());
			pstmt.setString(4,memberVO.getEmail()==null?"nomail@naver.com":memberVO.getEmail());
			pstmt.setString(5,memberVO.getCellphone()==null?"010-0001-1234":memberVO.getCellphone());
			pstmt.setString(6,memberVO.getAddress()==null?"서울시 마포구 홍대":memberVO.getAddress());
			pstmt.setString(7,memberVO.getAuth()==null?"2":memberVO.getAuth());
			pstmt.setString(8,memberVO.getModId());
			pstmt.setString(9,memberVO.getGroupDiv());
			pstmt.setString(10,memberVO.getUser_Id());
			
			
			System.out.println("=====================");
			System.out.println("do_update : \n" + sb.toString());
			System.out.println("memberVO : " + memberVO.toString());
			System.out.println("=====================");
			
			result  = pstmt.executeUpdate();
			
		}catch(Exception ex){
			System.out.println("=====================");
			ex.printStackTrace();
			System.out.println("=====================");
		}finally{
			try{
				if(pstmt != null)
					pstmt.close();
			}catch(Exception ex){
				System.out.println("=====================");
				ex.getStackTrace();
				System.out.println("=====================");
			}
			disconnect();
		}
		
		System.out.println("=====================");
		System.out.println("result : " + result);
		System.out.println("=====================");		
		
	 return result;
	}

	/**
	  * 사용자를 등록한다.
	  * @param vo - 등록할 정보가 담긴 DTO
	  * @return MemberEClassVO:OK
	  */
	public MemberEClassVO memberJoin(DTO t) {
		MemberEClassVO memberVO=(MemberEClassVO) t;
		//ID 중복 Check
		int idCnt = checkUserId(memberVO.getGroupDiv(), memberVO.getUser_Id());
		if( idCnt <  0){
			memberVO.setMessage("ID를 확인 하세요.");
			memberVO.setMessageDiv("00");//ID를 확인 하세요.
			return memberVO;
		}
		
	    //조 구분을 입력 하세요.
		if( memberVO.getGroupDiv()==null){
			memberVO.setMessage("조구분을 확인 하세요.\nex)1,2,3,4");
			memberVO.setMessageDiv("00");//조구분을 확인 하세요.
			return memberVO;
		}
		
		int result = do_insert(memberVO);
		System.out.println("=====================");
		System.out.println("result=" + result);
		System.out.println("=====================");
		if(result>0){
			memberVO.setMessage("회원가입이 완료 되었습니다.");
			memberVO.setMessageDiv("00");
		}else{
			memberVO.setMessage("회원가입에 오류가 발생 되었습니다.");
			memberVO.setMessageDiv("10");
		}
		
		return memberVO;
	}
	
	/**
	  * 사용자를 등록한다.
	  * @param vo - 등록할 정보가 담긴 DTO
	  * @return 등록 결과:OK
	  */
	public int do_insert(DTO t) {
		
		MemberEClassVO memberVO=(MemberEClassVO) t;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result  = 0;
		
		StringBuilder sb=new StringBuilder();
		//insert
		sb.append(" INSERT INTO member (   \n");
		sb.append("  group_div,        \n");
		sb.append("  user_id,          \n");
		sb.append("  name,             \n");
		sb.append("  pw,               \n");
		sb.append("  nic_nm,           \n");
		sb.append("  email,            \n");
		sb.append("  cellphone,        \n");
		sb.append("  address,          \n");
		sb.append("  auth,             \n");
		sb.append("  reg_id,           \n");
		sb.append("  reg_dt,           \n");
		sb.append("  mod_id,           \n");
		sb.append("  mod_dt            \n");
		sb.append(" ) VALUES (         \n");
		sb.append(" ?,                 \n");
		sb.append(" ?,                 \n");
		sb.append(" ?,                 \n");
		sb.append(" ?,                 \n");
		sb.append(" ?,                 \n");
		sb.append(" ?,                 \n");
		sb.append(" ?,                 \n");
		sb.append(" ?,                 \n");
		sb.append(" ?,                 \n");
		sb.append(" ?,                 \n");
		sb.append(" SYSDATE,           \n");
		sb.append(" ?,                 \n");
		sb.append(" SYSDATE            \n");
		sb.append(" )                  \n");			
		try{
			connect();			
			pstmt = conn.prepareStatement(sb.toString());
			System.out.println("=====================");
			System.out.println("do_insert : \n" + sb.toString());
			System.out.println("memberVO : " + memberVO.toString());
			System.out.println("getGroupDiv :" + memberVO.getGroupDiv());
			System.out.println("=====================");
			
			pstmt.setString(1,memberVO.getGroupDiv());
			pstmt.setString(2,memberVO.getUser_Id());
			pstmt.setString(3,memberVO.getName()==null?"무명":memberVO.getName());
			pstmt.setString(4,memberVO.getPw());
			pstmt.setString(5,memberVO.getNicNm()==null?"무닉네임":memberVO.getNicNm());
			pstmt.setString(6,memberVO.getEmail()==null?"nomail@naver.com":memberVO.getEmail());
			pstmt.setString(7,memberVO.getCellphone()==null?"010-0001-1234":memberVO.getCellphone());
			pstmt.setString(8,memberVO.getAddress()==null?"서울시 마포구 홍대":memberVO.getAddress());
			pstmt.setString(9,memberVO.getAuth()==null?"2":memberVO.getAuth());
			pstmt.setString(10,memberVO.getRegId());
			pstmt.setString(11,memberVO.getModId());
			

			result  = pstmt.executeUpdate();
			System.out.println("=result="+result);
		}catch(Exception ex){
			System.out.println("=====================");
			ex.getStackTrace();
			System.out.println("=====================");
		}finally{
			try{
				if(pstmt != null)
					pstmt.close();
			}catch(Exception ex){
				System.out.println("=====================");
				ex.getStackTrace();
				System.out.println("=====================");
			}
			disconnect();
		}
		
		return result;
 }

	/**
	  * 사용자를 삭제한다.
	  * @param vo - 삭제할 정보가 담긴 DTO
	  * @return 삭제 결과:OK
	  */
	public int do_delete(DTO dto) {
		MemberEClassVO memberVO=(MemberEClassVO) dto;
		//USER_ID 체크
		PreparedStatement pstmt = null;

		int result = 0;
		
		StringBuilder sb=new StringBuilder();
		try{
			connect();
			
			sb.append(" DELETE FROM member  \n");
			sb.append(" WHERE group_div = ? \n");
			sb.append(" AND user_id = ?     \n");
			
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1,memberVO.getGroupDiv());
			pstmt.setString(2,memberVO.getUser_Id());
			
			System.out.println("=====================");
			System.out.println("do_delete : \n" + sb.toString());

			System.out.println("group_div :" + memberVO.getGroupDiv());
			System.out.println("user_id :" + memberVO.getUser_Id());
			System.out.println("=====================");
			
			result = pstmt.executeUpdate();
			
		}catch(SQLException ex){
			System.out.println("=====================");
			System.out.println("SelectDBCountDao : " + ex);
			System.out.println("=====================");
		}finally{

			try{
				if(pstmt != null)
					pstmt.close();
			}catch(Exception ex){
				System.out.println("=====================");
				ex.getStackTrace();
				System.out.println("=====================");
			}
			disconnect();
		}
		System.out.println("=====================");
		System.out.println("do_delete : " + result);
		System.out.println("=====================");
		
		return result;
	}

	/**
	  * 사용자를 단건 조회한다.
	  * @param DTO - 등록할 정보가 담긴 DTO
	  * @return MemberEClassVO:OK
	  */
	public DTO do_selectOne(DTO dto) {
		MemberEClassVO memberVO=(MemberEClassVO)dto;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		StringBuilder sb=new StringBuilder();
		MemberEClassVO outMember=new MemberEClassVO();
		
		try{
			connect();
			sb.append(" SELECT group_div,    \n");
			sb.append("     user_id,         \n");
			sb.append("     name,            \n");
			sb.append("     pw,              \n");
			sb.append("     nic_nm,          \n");
			sb.append("     email,           \n");
			sb.append("     cellphone,       \n");
			sb.append("     address,         \n");
			sb.append("     auth,            \n");
			sb.append("     reg_id,          \n");
			sb.append("     TO_CHAR(reg_dt,'YYYYMMDD') REG_DT, \n");
			sb.append("     mod_id,          \n");
			sb.append("     TO_CHAR(mod_dt,'YYYYMMDD') MOD_DT \n");
			sb.append("  FROM MEMBER	     \n");
			sb.append(" WHERE group_div = ?	 \n");
			sb.append("   AND user_id   = ?	 \n");
			
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, memberVO.getGroupDiv());
			pstmt.setString(2, memberVO.getUser_Id());
			
			System.out.println("=====================");
			System.out.println("do_login : \n" + sb.toString());

			System.out.println("group_div : " + memberVO.getGroupDiv());
			System.out.println("userId : " + memberVO.getUser_Id());
			System.out.println("=====================");
			
			rs = pstmt.executeQuery();

			if(rs.next())
			{
				outMember.setGroupDiv(rs.getString("GROUP_DIV"));
				outMember.setId(rs.getString("USER_ID"));
				outMember.setName(rs.getString("NAME"));
				outMember.setPw(rs.getString("PW"));
				outMember.setNicNm(rs.getString("NIC_NM"));
				outMember.setEmail(rs.getString("EMAIL"));
				outMember.setCellphone(rs.getString("CELLPHONE"));
				outMember.setAddress(rs.getString("ADDRESS"));
				outMember.setAuth(rs.getString("AUTH"));
				outMember.setRegId(rs.getString("REG_ID"));
				outMember.setRegDt(rs.getString("REG_DT"));
				outMember.setModId(rs.getString("MOD_ID"));
				outMember.setModDt(rs.getString("MOD_DT"));

				System.out.println("==member==================="+outMember.toString());
			}
		}catch(Exception ex){
			System.out.println("=====================");
			System.out.println("do_selectOne : " + ex);
			System.out.println("=====================");
		}finally{
			try{
				if(rs != null)
					rs.close();
			}catch(Exception ex){
				System.out.println("=====================");
				ex.getStackTrace();
				System.out.println("=====================");
			}
			try{
				if(pstmt != null)
					pstmt.close();
			}catch(Exception ex){
				System.out.println("=====================");
				ex.getStackTrace();
				System.out.println("=====================");
			}
			disconnect();
		}
		
		//1조인 경우 WorkInfo
		if(memberVO.getGroupDiv().equals("1")){
			WorkInfoVO outWorkInfoVO = getWorkInfo(dto);
			System.out.println("=====================");
			System.out.println("=outWorkInfoVO="+outWorkInfoVO);
			System.out.println("=====================");
			outMember.setWorkInfoVO(outWorkInfoVO);
		}
		
		return outMember;	
	}
	
	public WorkInfoVO getWorkInfo(DTO dto) {
		MemberEClassVO memberVO=(MemberEClassVO)dto;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		StringBuilder sb=new StringBuilder();
		com.hr.eclass.member.domain.WorkInfoVO outWorkInfoVO=new WorkInfoVO();
		try{
			connect();
			sb.append(" SELECT group_div,                      \n"); 
			sb.append("        user_id,                        \n");
			sb.append("        work_year,                      \n");
			sb.append("        work_part,                      \n");
			sb.append("        start_work_time,                \n");
			sb.append("        end_work_time,                  \n");
			sb.append("        work_ho_per_day                 \n");
			sb.append(" FROM work_info                         \n");
			sb.append(" WHERE group_div = ?                    \n");
			sb.append(" AND   user_id   = ?                    \n");
			
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, memberVO.getGroupDiv());
			pstmt.setString(2, memberVO.getUser_Id());
			
			System.out.println("=====================");
			System.out.println("do_login : \n" + sb.toString());

			System.out.println("group_div : " + memberVO.getGroupDiv());
			System.out.println("userId : " + memberVO.getUser_Id());
			System.out.println("=====================");
			
			rs = pstmt.executeQuery();

			if(rs.next())
			{
				outWorkInfoVO.setWorkYear(rs.getString("work_year"));
				outWorkInfoVO.setWorkPart(rs.getString("work_part"));
				outWorkInfoVO.setStartWorkTime(rs.getString("start_work_time"));
				outWorkInfoVO.setEndWorkTime(rs.getString("end_work_time"));
				outWorkInfoVO.setWorkHoPerDay(rs.getString("work_ho_per_day"));
				
				System.out.println("==outWorkInfoVO==================="+outWorkInfoVO.toString());
			}
		}catch(Exception ex){
			System.out.println("=====================");
			System.out.println("do_selectOne : " + ex);
			System.out.println("=====================");
		}finally{
			try{
				if(rs != null)
					rs.close();
			}catch(Exception ex){
				System.out.println("=====================");
				ex.getStackTrace();
				System.out.println("=====================");
			}
			try{
				if(pstmt != null)
					pstmt.close();
			}catch(Exception ex){
				System.out.println("=====================");
				ex.getStackTrace();
				System.out.println("=====================");
			}
			disconnect();
		}
		
		return outWorkInfoVO;	
	}
	

	/**
	  * 사용자를 조회한다.
	  * @param DTO - 조회할 정보가 담긴 DTO
	  * @return List<?>
	  */
	public List<MemberEClassVO> do_retrieve(DTO t) {
		
		List<MemberEClassVO> list =new ArrayList<MemberEClassVO>();
		MemberEClassVO memberVO= (MemberEClassVO)t;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sb=new StringBuilder();
		
		StringBuilder searchSb=new StringBuilder();
		
		//검색처리 
		boolean searchCon = StringUtils.isEmpty(memberVO.getSearchCondition());
		
		//searchSb.append(" WHERE T1.GRP like '%"+memberVO.getSearchKeyword()+ "%' \n");
		
		if(searchCon != true){
			
			//ID검색
			if(t.getSearchCondition().equals("0")){
				searchSb.append(" AND user_id LIKE '%"+memberVO.getSearchKeyword()+ "%' \n");
			//이름검색	
			}else if(t.getSearchCondition().equals("1")){
				searchSb.append(" AND name LIKE '%"+memberVO.getSearchKeyword()+ "%' \n");
			}else if(t.getSearchCondition().equals("2")){
				searchSb.append(" AND email LIKE '%"+memberVO.getSearchKeyword()+ "%' \n");
			}else if(t.getSearchCondition().equals("3")){
				searchSb.append(" AND nic_nm LIKE '%"+memberVO.getSearchKeyword()+ "%' \n");
			}			
		}
		
		try{
			connect();
			sb.append(" SELECT T1.*,T2.*                                              \n");
			sb.append(" FROM                                                          \n");
			sb.append(" (                                                             \n");
			sb.append("  SELECT B.*                                                   \n");
			sb.append("  FROM                                                         \n");
			sb.append("     (SELECT ROWNUM AS rnum, A.*                               \n");
			sb.append("      FROM (                                                   \n");
			sb.append("            SELECT *                                           \n");
			sb.append("            FROM member                                        \n");
			sb.append("            WHERE group_div > '0'                              \n");
			sb.append(   searchSb.toString()                                             );
			sb.append("            ORDER BY mod_dt DESC                               \n");
			sb.append("           )A                                                  \n");
			//sb.append("      WHERE ROWNUM <=(:PAGE_SIZE * (:PAGE_NUM-1)+:PAGE_SIZE)   \n");
			sb.append("      WHERE ROWNUM <=(? * (?-1)+?)   \n");
			sb.append("     ) B                                                       \n");
			//sb.append("  WHERE B.RNUM >= (:PAGE_SIZE * (:PAGE_NUM-1)+1)               \n");
			sb.append("  WHERE B.RNUM >= (? * (?-1)+1)               \n");
			sb.append(" )T1                                                           \n");
			sb.append(" NATURAL JOIN                                                  \n");
			sb.append(" (                                                             \n");
			sb.append("     SELECT COUNT(*) TOTAL_CNT                                 \n");
			sb.append("     FROM member A                                             \n");
			sb.append("     WHERE group_div >'0'                                      \n");
			sb.append(   searchSb.toString()                                             );
			sb.append(" )T2                                                           \n");
			
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, memberVO.getGroupDiv()==null?"%": memberVO.getGroupDiv());
			pstmt.setString(2, memberVO.getPageSize());
			pstmt.setString(3, memberVO.getPageIndex());
			pstmt.setString(4, memberVO.getPageSize());
			pstmt.setString(5, memberVO.getPageIndex());
			pstmt.setString(6, memberVO.getPageSize());
			
			
			System.out.println("=====================");
			System.out.println("do_retrieve : \n" + sb.toString());
			System.out.println("memberVO : \n" + memberVO);
			System.out.println("dto : \n" + t);
			System.out.println("=====================");
			
			rs = pstmt.executeQuery();

			while(rs.next())
			{   
				MemberEClassVO outMember=new MemberEClassVO();
				outMember.setGroupDiv(rs.getString("GROUP_DIV"));
				outMember.setId(rs.getString("USER_ID"));
				outMember.setName(rs.getString("NAME"));
				outMember.setPw(rs.getString("PW"));
				outMember.setName(rs.getString("NIC_NM"));
				outMember.setEmail(rs.getString("EMAIL"));
				outMember.setCellphone(rs.getString("CELLPHONE"));
				outMember.setAddress(rs.getString("ADDRESS"));
				outMember.setAuth(rs.getString("AUTH"));
				outMember.setRegId(rs.getString("REG_ID"));
				outMember.setRegDt(rs.getString("REG_DT"));
				outMember.setModId(rs.getString("MOD_ID"));
				outMember.setModDt(rs.getString("MOD_DT"));
				
				outMember.setNo(rs.getString("RNUM"));
				outMember.setTotalCnt(rs.getString("TOTAL_CNT"));
				
				//--Message
				outMember.setMessageDiv("11");
				outMember.setMessage("환영 합니다.");

				list.add(outMember);
				//System.out.println("==member==================="+outMember.toString());
			}
		}catch(Exception ex){
			System.out.println("=====================");
			System.out.println("SelectDBCountDao : " + ex);
			System.out.println("=====================");
		}finally{
			try{
				if(rs != null)
					rs.close();
			}catch(Exception ex){
				System.out.println("=====================");
				ex.getStackTrace();
				System.out.println("=====================");
			}
			try{
				if(pstmt != null)
					pstmt.close();
			}catch(Exception ex){
				System.out.println("=====================");
				ex.getStackTrace();
				System.out.println("=====================");
			}
			disconnect();
		}
		
		return list;	
	}



}
