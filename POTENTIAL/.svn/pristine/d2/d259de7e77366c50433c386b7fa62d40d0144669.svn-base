package com.potential.newbookadd.domain;

public class NewBookVO {
	private String nbName;
	private String nbWriter;
	private String nbPubYear;
	private String nbGenre;
	private String nbPubli;
	private String status;
//	private String imagePath;
	public NewBookVO(String nbName, String nbWriter, String nbPubli, String nbPubYear, String nbGenre, String status) {
		super();
		this.nbName = nbName;
		this.nbWriter = nbWriter;
		this.nbPubYear = nbPubYear;
		this.nbGenre = nbGenre;
		this.nbPubli = nbPubli;
		this.status = status;
	}
	public String getNbName() {
		return nbName;
	}
	public void setNbName(String nbName) {
		this.nbName = nbName;
	}
	public String getNbWriter() {
		return nbWriter;
	}
	public void setNbWriter(String nbWriter) {
		this.nbWriter = nbWriter;
	}
	public String getNbPubYear() {
		return nbPubYear;
	}
	public void setNbPubYear(String nbPubYear) {
		this.nbPubYear = nbPubYear;
	}
	public String getNbGenre() {
		return nbGenre;
	}
	public void setNbGenre(String nbGenre) {
		this.nbGenre = nbGenre;
	}
	public String getNbPubli() {
		return nbPubli;
	}
	public void setNbPubli(String nbPubli) {
		this.nbPubli = nbPubli;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nbGenre == null) ? 0 : nbGenre.hashCode());
		result = prime * result + ((nbName == null) ? 0 : nbName.hashCode());
		result = prime * result + ((nbPubYear == null) ? 0 : nbPubYear.hashCode());
		result = prime * result + ((nbPubli == null) ? 0 : nbPubli.hashCode());
		result = prime * result + ((nbWriter == null) ? 0 : nbWriter.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NewBookVO other = (NewBookVO) obj;
		if (nbGenre == null) {
			if (other.nbGenre != null)
				return false;
		} else if (!nbGenre.equals(other.nbGenre))
			return false;
		if (nbName == null) {
			if (other.nbName != null)
				return false;
		} else if (!nbName.equals(other.nbName))
			return false;
		if (nbPubYear == null) {
			if (other.nbPubYear != null)
				return false;
		} else if (!nbPubYear.equals(other.nbPubYear))
			return false;
		if (nbPubli == null) {
			if (other.nbPubli != null)
				return false;
		} else if (!nbPubli.equals(other.nbPubli))
			return false;
		if (nbWriter == null) {
			if (other.nbWriter != null)
				return false;
		} else if (!nbWriter.equals(other.nbWriter))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "NewBookVO [nbName=" + nbName + ", nbWriter=" + nbWriter + ", nbPubYear=" + nbPubYear + ", nbGenre="
				+ nbGenre + ", nbPubli=" + nbPubli + ", status=" + status + "]";
	}
	
	
}
