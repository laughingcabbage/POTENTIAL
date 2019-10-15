 /**
 * @Class Name : WorkDiv.java
 * @Description : DAO에 표준 메소드:CRUD
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
package com.hr.cmn;

import java.util.List;

/**
 * @author SIST
 *
 */
public interface WorkDiv {

	/**
	 * 
	 * @Method Name  : do_save
	 * @작성일   : 2019. 5. 15.
	 * @작성자   : SIST
	 * @변경이력  : 최초작성
	 * @Method 설명 : 저장기능 구현
	 * @param dto
	 * @return int(0보다 크면 성공)
	 */
	public int do_save(DTO dto);
	
	/**
	 * 
	 * @Method Name  : do_delete
	 * @작성일   : 2019. 5. 15.
	 * @작성자   : SIST
	 * @변경이력  : 최초작성
	 * @Method 설명 : 삭제기능 구현
	 * @param dto
	 * @return int(0보다 크면 성공)
	 */
	public int do_delete(DTO dto);
	
	/**
	 * 
	 * @Method Name  : do_update
	 * @작성일   : 2019. 5. 15.
	 * @작성자   : SIST
	 * @변경이력  : 최초작성
	 * @Method 설명 : 수정기능 구현
	 * @param dto
	 * @return int(0보다 크면 성공)
	 */
	public int do_update(DTO dto);
	
	/**
	 * 
	 * @Method Name  : do_selectOne
	 * @작성일   : 2019. 5. 15.
	 * @작성자   : SIST
	 * @변경이력  : 최초작성
	 * @Method 설명 : 단건조회
	 * @param dto
	 * @return DTO
	 */
	public DTO do_selectOne(DTO dto);
	
	/**
	 * 
	 * @Method Name  : do_retrieve
	 * @작성일   : 2019. 5. 15.
	 * @작성자   : SIST
	 * @변경이력  : 최초작성
	 * @Method 설명 : 목록조회
	 * @param dto
	 * @return List<DTO>
	 */
	public List<?> do_retrieve(DTO dto);
	
	
	
	
	
	
	
}
