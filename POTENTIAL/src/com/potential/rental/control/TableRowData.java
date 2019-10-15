package com.potential.rental.control;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;

public class TableRowData {
	private BooleanProperty check = new SimpleBooleanProperty(false);
    private StringProperty ID;
    private StringProperty name;
    private StringProperty author;
    private StringProperty category;
    private StringProperty date;
    private IntegerProperty possible;
    private BooleanProperty over; 
    
	public TableRowData(StringProperty iD, StringProperty name, StringProperty author, StringProperty category, StringProperty date,
			 IntegerProperty possible, BooleanProperty over) {
		ID = iD;
		this.name = name;
		this.author = author;
		this.category = category;
		this.date = date;
		this.possible = possible;
		this.over = over;
	}
	
	public BooleanProperty checkProperty() {
        return check;
    }
	public void setcheck(Boolean bool){
		new SimpleBooleanProperty(bool);
	}
	public StringProperty IDProperty() {
        return ID;
    }
	public StringProperty nameProperty() {
        return name;
    }
	public StringProperty authorProperty() {
        return author;
    }
	public StringProperty categoryProperty() {
        return category;
    }
	public StringProperty dateProperty() {
        return date;
    }
    public IntegerProperty possibleProperty() {
        return possible;
    }
    public BooleanProperty overProperty() {
        return over;
    }
    
    
}
