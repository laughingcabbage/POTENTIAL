/**
 * @Class Name : RecommendVO.java
 * @Description : 
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2019-05-31           최초생성
 *
 * @author 개발프레임웍크 실행환경 HR.POTENTIAL 개발팀
 * @since 2019-05-31 
 * @version 1.0
 * @see
 *
 *  Copyright (C) by HR.POTENTIAL All right reserved.
 */
package com.potential.userrecommend.domain;

/**
 * @author sist
 *
 */
public class UserRecommendVO {

	private String index;
	private String id;
	private String comment;
	private double rating;
	
	public UserRecommendVO(){}

	/**
	 * @param index
	 * @param title
	 * @param author
	 * @param publisher
	 * @param review
	 */
	public UserRecommendVO(String index, String id, String comment, double rating) {
		super();
		this.index = index;
		this.id = id;
		this.comment = comment;
		this.rating = rating;
	}

	/**
	 * @return the index
	 */
	public String getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(String index) {
		this.index = index;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the review
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param review the review to set
	 */
	public void setReview(String comment) {
		this.comment = comment;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserRecommendVO [index=" + index + ", id=" + id + ", comment=" + comment + ", rating=" + rating + "]\n";
	}
	
	
}
