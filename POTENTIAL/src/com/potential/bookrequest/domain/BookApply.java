package com.potential.bookrequest.domain;

import javafx.beans.property.StringProperty;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.CheckBox;

/**
 * @author sist
 *
 */
public class BookApply {

	private StringProperty id;
	private StringProperty title;
	private StringProperty author;
	private StringProperty publisher;
	private StringProperty pubYear;
	private StringProperty comment;
	private StringProperty status;
	private CheckBox checkBox;
	
	public BookApply(CheckBox checkBox, String id, String title, String author, String publisher, 
								String pubYear, String comment, String status) {
		super();
		this.checkBox = new CheckBox();
		this.id = new SimpleStringProperty(id);
		this.title = new SimpleStringProperty(title);
		this.author = new SimpleStringProperty(author);
		this.publisher = new SimpleStringProperty(publisher);
		this.pubYear = new SimpleStringProperty(pubYear);
		this.comment = new SimpleStringProperty(comment);
		this.status = new SimpleStringProperty(status);
	}

	public String getId() {
		return id.get();
	}

	public void setId(String id) {
		this.id.set(id);
	}

	public String getTitle() {
		return title.get();
	}

	public void setTitle(String title) {
		this.title.set(title);
	}

	public String getAuthor() {
		return author.get();
	}

	public void setAuthor(String author) {
		this.author.set(author);
	}

	public String getPublisher() {
		return publisher.get();
	}

	public void setPublisher(String publisher) {
		this.publisher.set(publisher);
	}

	public String getPubYear() {
		return pubYear.get();
	}

	public void setPubYear(String pubYear) {
		this.pubYear.set(pubYear);
	}

	public String getComment() {
		return comment.get();
	}

	public void setComment(String comment) {
		this.comment.set(comment);
	}

	public String getStatus() {
		return status.get();
	}

	public void setStatus(String status) {
		this.status.set(status);
	}
	
	public CheckBox getCheckBox() {
		return checkBox;
	}
	
	public void setCheckBox(CheckBox checkBox) {
		this.checkBox = checkBox;
	}
	
}
