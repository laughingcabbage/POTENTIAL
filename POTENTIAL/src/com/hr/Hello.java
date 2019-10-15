/**
 * @Class Name : Hello.java
 * @Description : Hello
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2018.07.02           최초생성
 *
 * @author 개발프레임웍크 POTENTIAL  개발팀
 * @since 2018.07.10 
 * @version 1.0
 * @see
 *
 *  Copyright (C) by DY. KIM All right reserved.
 */

package com.hr;
import org.apache.log4j.Logger;
/**
 * @author sist
 *
 */
public class Hello {
	final static Logger LOG=Logger.getLogger(Hello.class);
	
	/**
	 * @Method Name  : main
	 * @작성일   : 2019. 5. 8.
	 * @작성자   : sist
	 * @변경이력  : 최초작성
	 * @Method 설명 :
	 * @param args
	 */

	public static void main(String[] args) {
		LOG.debug("Hellow,World(한글)");
		LOG.debug("HI");

	}

}
