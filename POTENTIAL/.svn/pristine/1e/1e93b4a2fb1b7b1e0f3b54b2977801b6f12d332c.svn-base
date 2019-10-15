/**
 * @Class Name : Recommend.java
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
package com.potential.recommend.domain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;

/**
 * @author sist
 *
 */
public class RecommendedBook {
	
	private CheckBox checkBox;
	private Image image;
	private SimpleIntegerProperty index;
	private SimpleStringProperty title;
	private SimpleStringProperty author;
	private SimpleStringProperty publisher;
	private SimpleStringProperty review;
	
	public RecommendedBook(){}

	/**
	 * @param index
	 * @param title
	 * @param author
	 * @param publisher
	 * @param review
	 */
	public RecommendedBook(CheckBox checkBox, int index, String title, String author, String publisher, String review) {
		super();
		this.checkBox = new CheckBox();
		this.index = new SimpleIntegerProperty(index);
		this.title = new SimpleStringProperty(title);
		this.author = new SimpleStringProperty(author);
		this.publisher = new SimpleStringProperty(publisher);
		this.review = new SimpleStringProperty(review);
	}

	public Image getImage() {
		return image;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
	
	public CheckBox getCheckBox(){
		return checkBox;
	}
	
	public void setCheckBox(){
		this.checkBox = checkBox;
	}
	
	/**
	 * @return the index
	 */
	public int getIndex() {
		return index.get();
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index.set(index);
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title.get();
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title.set(title);
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author.get();
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author.set(author);
	}

	/**
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher.get();
	}

	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(String publisher) {
		this.publisher.set(publisher);
	}

	/**
	 * @return the review
	 */
	public String getReview() {
		return review.get();
	}

	/**
	 * @param review the review to set
	 */
	public void setReview(String review) {
		this.review.set(review);
	}
	
	

}
