package com.potential.booksearch.domain;

import com.hr.cmn.DTO;

import javafx.beans.property.SimpleStringProperty;

public class TablePropertyVO extends DTO {

	 private SimpleStringProperty name;
	    private SimpleStringProperty writer;
	    private SimpleStringProperty pub;
	    private SimpleStringProperty pubyear;
	    private SimpleStringProperty remarks;
	    private SimpleStringProperty state;
		/**
		 * @return the name
		 */
		public String getName() {
			return name.get();
		}
		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name.set(name);
		}
		/**
		 * @return the writer
		 */
		public String getWriter() {
			return writer.get();
		}
		/**
		 * @param writer the writer to set
		 */
		public void setWriter(String writer) {
			this.writer.set(writer);
		}
		/**
		 * @return the pub
		 */
		public String getPub() {
			return pub.get();
		}
		/**
		 * @param pub the pub to set
		 */
		public void setPub(String pub) {
			this.pub.set(pub);
		}
		/**
		 * @return the pubyear
		 */
		public String getPubyear() {
			return pubyear.get();
		}
		/**
		 * @param pubyear the pubyear to set
		 */
		public void setPubyear(String pubyear) {
			this.pubyear.set(pubyear);
		}
		/**
		 * @return the remarks
		 */
		public String getRemarks() {
			return remarks.get();
		}
		/**
		 * @param remarks the remarks to set
		 */
		public void setRemarks(String remarks) {
			this.remarks.set(remarks);
		}
		public String getState() {
			return state.get();
		}
		/**
		 * @param remarks the remarks to set
		 */
		public void setState(String state) {
			this.remarks.set(state);
		}
		public TablePropertyVO(String name, String writer, String pub,
				String pubyear, String remarks ,String state) {
			super();
			this.name =new SimpleStringProperty(name);
			this.writer =new SimpleStringProperty(writer);
			this.pub =new SimpleStringProperty(pub);
			this.pubyear =new SimpleStringProperty(pubyear);
			this.remarks =new SimpleStringProperty(remarks);
			this.state=new SimpleStringProperty(state);
			
		}
}
