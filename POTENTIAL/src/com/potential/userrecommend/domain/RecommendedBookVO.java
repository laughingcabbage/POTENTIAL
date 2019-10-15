/**
 * @Class Name : RecommendedBookVO.java
 * @Description : 
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2019-06-03           최초생성
 *
 * @author 개발프레임웍크 실행환경 HR.POTENTIAL 개발팀
 * @since 2019-06-03 
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
public class RecommendedBookVO {
	private String index;
	private String title;
	private String author;
	private String publisher;
	private String review;
	
	public RecommendedBookVO(){}

	/**
	 * @param index
	 * @param title
	 * @param author
	 * @param publisher
	 * @param review
	 */
	public RecommendedBookVO(String index, String title, String author, String publisher, String review) {
		super();
		this.index = index;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.review = review;
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

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * @return the review
	 */
	public String getReview() {
		return review;
	}

	/**
	 * @param review the review to set
	 */
	public void setReview(String review) {
		this.review = review;
	}
}
