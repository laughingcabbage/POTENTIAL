 /**
 * @Class Name : MemberVO.java
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
package com.hr.address.domain;

import com.hr.cmn.DTO;

/**
 * @author SIST
 *
 */
public class MemberVO extends DTO {
	/**이메일 */
	private String email;
	
	/**이름 */
	private String name;
	
	/**생년월일 */
	private int birthday;
	
	/**핸드폰 번호 */
	private String cellPhone;
	
	
	public MemberVO(){}


	/**
	 * @param email
	 * @param name
	 * @param birthday
	 * @param cellPhone
	 */
	public MemberVO(String email, String name, int birthday, String cellPhone) {
		super();
		this.email = email;
		this.name = name;
		this.birthday = birthday;
		this.cellPhone = cellPhone;
	}


	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the birthday
	 */
	public int getBirthday() {
		return birthday;
	}


	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(int birthday) {
		this.birthday = birthday;
	}


	/**
	 * @return the cellPhone
	 */
	public String getCellPhone() {
		return cellPhone;
	}


	/**
	 * @param cellPhone the cellPhone to set
	 */
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MemberVO [email=" + email + ", name=" + name + ", birthday=" + birthday + ", cellPhone=" + cellPhone
				+ ", getNo()=" + getNo() + "]";
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + birthday;
		result = prime * result + ((cellPhone == null) ? 0 : cellPhone.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberVO other = (MemberVO) obj;
		if (birthday != other.birthday)
			return false;
		if (cellPhone == null) {
			if (other.cellPhone != null)
				return false;
		} else if (!cellPhone.equals(other.cellPhone))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
	
}
