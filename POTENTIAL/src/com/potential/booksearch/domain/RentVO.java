package com.potential.booksearch.domain;

import com.hr.cmn.DTO;



public class RentVO extends DTO {
	

	
		String ID;
		String bookname;
		String rentdate;
		String rent;
		public RentVO(String iD, String bookname, String rentdate, String rent) {
			super();
			ID = iD;
			this.bookname = bookname;
			this.rentdate = rentdate;
			this.rent = rent;
		}
		
		/**
		 * @return the rent
		 */
		public String getRent() {
			return rent;
		}

		/**
		 * @param rent the rent to set
		 */
		public void setRent(String rent) {
			this.rent = rent;
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
		 * @return the bookname
		 */
		public String getBookname() {
			return bookname;
		}
		/**
		 * @param bookname the bookname to set
		 */
		public void setBookname(String bookname) {
			this.bookname = bookname;
		}
		/**
		 * @return the rentdate
		 */
		public String getRentdate() {
			return rentdate;
		}
		/**
		 * @param rentdate the rentdate to set
		 */
		public void setRentdate(String rentdate) {
			this.rentdate = rentdate;
		}
		public RentVO(String iD, String bookname, String rentdate) {
			super();
			ID = iD;
			this.bookname = bookname;
			this.rentdate = rentdate;
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
}



