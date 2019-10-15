package com.potential.rental.domain;

import com.hr.cmn.DTO;

public class RentVO extends DTO{
	/**책 이름*/
	private String BookName;
	/**저자*/
	private String author;
	/**카테고리*/
	private String category;
	/**빌린사람ID*/
	private String ID;
	/**빌린날짜 (ex: 2019/12/01)*/
	private String rentalDate;
	/**연장가능유무*/
	private int possible;
	
	/**
	 * @param bookName
	 * @param author
	 * @param category
	 * @param iD
	 * @param rentalDate
	 * @param possible
	 */
	public RentVO(String bookName, String author, String category, String iD, String rentalDate,
			int possible) {
		super();
		BookName = bookName;
		this.author = author;
		this.category = category;
		ID = iD;
		this.rentalDate = rentalDate;
		this.possible = possible;
	}

	/**
	 * @return the bookName
	 */
	public String getBookName() {
		return BookName;
	}

	/**
	 * @param bookName the bookName to set
	 */
	public void setBookName(String bookName) {
		BookName = bookName;
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
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setID(String iD) {
		ID = iD;
	}

	/**
	 * @return the rentalDate
	 */
	public String getRentalDate() {
		return rentalDate;
	}

	/**
	 * @param rentalDate the rentalDate to set
	 */
	public void setRentalDate(String rentalDate) {
		this.rentalDate = rentalDate;
	}

	/**
	 * @return the possible
	 */
	public int getPossible() {
		return possible;
	}

	/**
	 * @param possible the possible to set
	 */
	public void setPossible(int possible) {
		this.possible = possible;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return (BookName != null ? BookName + "&&&" : "")
				+ (author != null ? author + "&&&" : "")
				+ (category != null ? category + "&&&" : "")
				+ (ID != null ? ID + "&&&" : "")
				+ (rentalDate != null ? rentalDate + "&&&" : "")
				+ possible;
	}
	
}